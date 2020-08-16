package com.zihuan.demo

import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.zihuan.view.crvlibrary.ZBaseRecyclerWrapper

class CompleteWrapperZ(private val recyclerView: RecyclerView, private val refreshLayout: SmartRefreshLayout) : ZBaseRecyclerWrapper<CompleteBuilderZ>() {

    override fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): CompleteBuilderZ {
        return CompleteBuilderZ(recyclerView, adapter, type, refreshLayout)
    }
}