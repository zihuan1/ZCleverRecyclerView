package com.zihuan.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zihuan.view.crvlibrary.EmptyView
import com.zihuan.view.crvlibrary.EmptyViewListener
import com.zihuan.view.crvlibrary.RecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        EmptyViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var builder = rv_view.buildVerticalLayout<ReAdapter>()
        builder.getRecyclerOperation().setWrapContent()
//        rv_view.buildVerticalLayout(ReAdapter())
        var list = ArrayList<String>()
        EmptyView.emptyLayout = R.layout.empty_layout
//        多个rv可以分别设置
        rv_view.getBuilder().setEmptyViewListener { viewId, _ ->
            Log.e("点击1111", "viewid$viewId")
        }
        (0..20).forEach {
            list.add("$it")
        }
// result的类型在kotlin1.3中推断为String?，在Kotlin1.4中为String
        rv_view.getBuilder().setData(list)
        rv_view.getBuilder().setEmptyViewClick(R.id.tv_1, R.id.tv_2)
        bt_click.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
        builder.addOnScrollListener { state, layoutManager, scrollX, scrollY ->
            Log.e("addOnScrollListener 状态", "state$state scrollY$scrollY")
        }
        bt_click_perform.setOnClickListener {
//            Toast.makeText(this, "点击", Toast.LENGTH_LONG).show()
            rv_view.getBuilder().performItemClick(1)
//            rv_view.getBuilder().performItemChildClick(1, children = R.id.ivTest)
        }
        bt_setdata.setOnClickListener {
            (0..20).forEach {
                list.add("$it")
            }
            builder.setData(list)
//            rv_view.getBuilder().getData<String>()[0] = "修改"
//            Log.e("获取当前值", rv_view.getBuilder().getData<String>()[0])
//            builder.setData(rv_view.getBuilder().getData<String>())
        }
        bt_clear.setOnClickListener {
            rv_view.getBuilder().clearData()
        }
    }


    override fun onClick(viewId: Int, view: View?) {
        Log.e("点击", "viewid$viewId")
    }

}
