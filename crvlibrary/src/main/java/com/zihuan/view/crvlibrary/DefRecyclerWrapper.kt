package com.zihuan.view.crvlibrary

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

/***
 * RecyclerView 默认的包装类
 * @author Zihuan
 */
class DefRecyclerWrapper(private val recyclerView: RecyclerView) : BaseRecyclerWrapper<BaseRecyclerBuilder>() {
    override fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): BaseRecyclerBuilder {
        if (recyclerView.parent == null) {
            Log.e("DefRecyclerWrapper", "请先为RecyclerView设置根布局")
        }
        return BaseRecyclerBuilder(adapter, type, recyclerView)
    }
}