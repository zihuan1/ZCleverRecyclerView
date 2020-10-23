package com.zihuan.view.crvlibrary

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * RecyclerView 数据刷新接口
 * @author Zihuan
 */
fun interface RecyclerData {
    fun update(data: List<*>)
}

fun interface RecyclerViewScrollListener {

    /**
     * @param state 滚动状态
     * @param layoutManager 布局管理器
     * @param scrollX 横向滚动 正值向上滚动负值向下滚动
     * @param scrollY 竖向滚动 正直像左滚动负值向由滚动
     */
    fun onScrollStateChanged(state: Int, layoutManager: LinearLayoutManager, scrollX: Int, scrollY: Int)
}

fun interface EmptyViewListener {
    /**
     * @param viewId 当前viewId
     * @param view   当前view
     */
    fun onClick(viewId: Int, view: View?)
}
