package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView包装类
 * @author Zihuan
 */
open abstract class ZBaseRecyclerWrapper<T : ZBaseRecyclerBuilder> {
    /**
     * 构建一个竖向布局的view
     */
    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) =
            createBuilder(adapter, RECYCLER_VERTICAL)

    /**
     * 构建一个横向布局的view
     */
    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) =
            createBuilder(adapter, RECYCLER_HORIZONTAL)

    /**
     * 构建一个九宫格布局的view
     */
    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) =
            createBuilder(adapter, type)

    /**
     * 包装RecyclerView
     */
    abstract fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): T


}