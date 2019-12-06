package com.zihuan.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zihuan.view.crvlibrary.ZRecyclerWrapRecyclerView
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var list = ArrayList<String>()
        (0..100).forEach {
            list.add("$it")
        }
//        var builder = ZRecyclerWrapRecyclerView(re_view2)
//        builder.buildVerticalLayout(ReAdapter()).setData(list)
//        builder.getBuilder()
        var builder = ZRecyclerWrapRecyclerView(re_view2).buildVerticalLayout(ReAdapter())
        builder.setData(list)
    }
}
