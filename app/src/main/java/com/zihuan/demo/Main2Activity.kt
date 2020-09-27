package com.zihuan.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zihuan.view.crvlibrary.BaseRecyclerBuilder
import com.zihuan.view.crvlibrary.RecyclerViewWrapper
import com.zihuan.view.crvlibrary.EmptyView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.bt_click

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
//        var builder = DefRecyclerWrapper(re_view2).buildVerticalLayout(ReAdapter())
        val list1 = arrayListOf("123")
        val list2 = arrayListOf("456")
        var builder = RecyclerViewWrapper<BaseRecyclerBuilder>(re_view2).buildVerticalLayout<ReAdapter2>(list1, list2)
        builder.setData(list)
        builder.getRecyclerOperation().setWrapContent()
        bt_click.setOnClickListener {
            startActivity(Intent(this, Main3Activity::class.java))
        }
        EmptyView.emptyLayout = R.layout.empty_layout2
    }
}
