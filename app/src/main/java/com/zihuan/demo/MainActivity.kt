package com.zihuan.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.view.crvlibrary.ZEmptyView
import com.zihuan.view.crvlibrary.ZEmptyViewListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ZEmptyViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var builder = rv_view.buildVerticalLayout(ReAdapter()).setWrapContent()
        var list = ArrayList<String>()
        Log.e("布局", R.layout.empty_layout.toString())
        ZEmptyView.emptyLayout = R.layout.empty_layout
//        多个rv可以分别设置
//        rv_view.getBaseBuilder().setEmptyViewListener(ZEmptyViewListener { viewId, _ ->
//            Log.e("点击1111", "viewid$viewId")
//        })

        rv_view.getBaseBuilder().setData(list)
        rv_view.getBaseBuilder().setEmptyViewClick(R.id.tv_1, R.id.tv_2)
        bt_click.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
        bt_setdata.setOnClickListener {
            (0..100).forEach {
                list.add("$it")
            }
            rv_view.getBaseBuilder().getData<String>()[0] = "修改"
            Log.e("获取当前值", rv_view.getBaseBuilder().getData<String>()[0])
            builder.setData(rv_view.getBaseBuilder().getData<String>())

        }
        bt_clear.setOnClickListener {
            rv_view.getBaseBuilder().clearData()
        }
    }

    override fun onResume() {
        super.onResume()
        rv_view.getBaseBuilder().setEmptyView()
    }

    override fun onClick(viewId: Int, view: View?) {
        Log.e("点击", "viewid$viewId")
    }

}
