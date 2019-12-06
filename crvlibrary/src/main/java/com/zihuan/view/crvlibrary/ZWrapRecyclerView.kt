package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.RecyclerView

/***
 * RecyclerView 包装类
 * @author Zihuan
 */
class ZWrapRecyclerView(private var recyclerView: RecyclerView) {

    private lateinit var mZRecyclerBuilder: ZRecyclerBuilder

    /**
     * 构建一个竖向布局的view
     */
    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) = buildRecyclerView(adapter, RECYCLER_VERTICAL)

    /**
     * 构建一个横向布局的view
     */
    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) = buildRecyclerView(adapter, RECYCLER_HORIZONTAL)

    /**
     * 构建一个九宫格布局的view
     */
    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) = buildRecyclerView(adapter, type)

    /**
     * 获取构建者
     */
    fun getBuilder() = mZRecyclerBuilder

    /**
     * 获取获取当前RecyclerView
     */
    fun getRecyclerView() = recyclerView

    /**
     * 包装RecyclerView
     */
    private fun buildRecyclerView(adapter: RecyclerView.Adapter<*>, type: Int) =
        ZRecyclerBuilder(adapter, type, recyclerView).apply { mZRecyclerBuilder = this }
}