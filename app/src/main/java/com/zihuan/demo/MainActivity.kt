package com.zihuan.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var builder = rv_view.buildVerticalLayout(ReAdapter()).setWrapContent()
        var list = ArrayList<String>()

        rv_view.getBaseBuilder().setData(list)
//        ZEmptyView.emptyLayout = R.layout.empty_layout
//        rv_view.getBaseBuilder().setEmptyView()
        bt_click.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
        bt_setdata.setOnClickListener {
            (0..100).forEach {
                list.add("$it")
            }
            builder.setData(list)
        }
        bt_clear.setOnClickListener {
            list.clear()
            rv_view.getBaseBuilder().setData(list)
        }
    }

    override fun onResume() {
        super.onResume()
        rv_view.getBaseBuilder().setEmptyView()
    }
}
