package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.RecyclerView

/***
 * RecyclerView 默认的包装类
 * @author Zihuan
 */
class ZDefRecyclerWrapper(private val recyclerView: RecyclerView) : ZBaseRecyclerWrapper<ZBaseRecyclerBuilder>() {
    override fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): ZBaseRecyclerBuilder {

        return ZBaseRecyclerBuilder(adapter, type, recyclerView)
    }
}