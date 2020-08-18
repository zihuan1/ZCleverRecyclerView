package com.zihuan.demo

import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.zihuan.view.crvlibrary.BaseRecyclerWrapper

class CompleteWrapper(private val recyclerView: RecyclerView, private val refreshLayout: SmartRefreshLayout) : BaseRecyclerWrapper<CompleteBuilder>() {

    override fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): CompleteBuilder {
        return CompleteBuilder(recyclerView, adapter, type, refreshLayout)
    }
}