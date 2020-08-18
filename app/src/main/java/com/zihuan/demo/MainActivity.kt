package com.zihuan.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.view.crvlibrary.EmptyView
import com.zihuan.view.crvlibrary.EmptyViewListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        EmptyViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var builder = rv_view.buildVerticalLayout<ReAdapter>()
        builder.setWrapContent()
//        rv_view.buildVerticalLayout(ReAdapter())
        var list = ArrayList<String>()
        EmptyView.emptyLayout = R.layout.empty_layout
//        多个rv可以分别设置
        rv_view.getBaseBuilder().setEmptyViewListener(EmptyViewListener { viewId, _ ->
            Log.e("点击1111", "viewid$viewId")
        })

        (0..10).forEach {
            list.add("$it")
        }
        rv_view.getBaseBuilder().setData(list)
        rv_view.getBaseBuilder().setEmptyViewClick(R.id.tv_1, R.id.tv_2)
        bt_click.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
        bt_click_perform.setOnClickListener {
            Toast.makeText(this, "点击", Toast.LENGTH_LONG).show()
//            rv_view.getBaseBuilder().performItemClick(1, 2, 3)
            rv_view.getBaseBuilder().performItemChildClick(1, 2, 3, children = R.id.tv_2)
        }
        bt_setdata.setOnClickListener {
            (0..10).forEach {
                list.add("$it")
            }
            builder.setData(list)
//            rv_view.getBaseBuilder().getData<String>()[0] = "修改"
//            Log.e("获取当前值", rv_view.getBaseBuilder().getData<String>()[0])
//            builder.setData(rv_view.getBaseBuilder().getData<String>())
        }
        bt_clear.setOnClickListener {
            rv_view.getBaseBuilder().clearData()
        }
    }


    override fun onClick(viewId: Int, view: View?) {
        Log.e("点击", "viewid$viewId")
    }

}
