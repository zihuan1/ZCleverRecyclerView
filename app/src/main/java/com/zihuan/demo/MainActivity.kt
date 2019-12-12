package com.zihuan.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.view.crvlibrary.ZEmptyView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var builder = rv_view.buildVerticalLayout(ReAdapter()).setWrapContent()
        var list = ArrayList<String>()
        (0..100).forEach {
            list.add("$it")
        }
//        builder.setData(list)
        rv_view.getBaseBuilder().setData(list).scrollToBottom()
        ZEmptyView.emptyLayout = R.layout.empty_layout
        rv_view.getBaseBuilder().setEmptyView()
        bt_click.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        rv_view.getBaseBuilder().setEmptyView()
    }
}
