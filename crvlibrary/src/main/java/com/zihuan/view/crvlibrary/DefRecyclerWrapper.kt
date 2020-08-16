package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.RecyclerView

/***
 * RecyclerView 默认的包装类
 * @author Zihuan
 */
class DefRecyclerWrapper(private val recyclerView: RecyclerView) : BaseRecyclerWrapper<BaseRecyclerBuilder>() {
    override fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): BaseRecyclerBuilder {

        return BaseRecyclerBuilder(adapter, type, recyclerView)
    }
}