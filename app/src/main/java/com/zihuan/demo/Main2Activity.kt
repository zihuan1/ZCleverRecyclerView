package com.zihuan.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zihuan.view.crvlibrary.ZDefRecyclerWrapper
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.bt_click
import kotlinx.android.synthetic.main.activity_main3.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var list = ArrayList<String>()
        (0..100).forEach {
            list.add("$it")
        }
//        var builder = ZCompleteWrapper(re_view2)
//        builder.buildVerticalLayout(ReAdapter()).setData(list)
//        builder.getBuilder()
        var builder = ZDefRecyclerWrapper(re_view2).buildVerticalLayout(ReAdapter())
        builder.setData(list)
        bt_click.setOnClickListener {
            startActivity(Intent(this, Main3Activity::class.java))
        }
    }
}
