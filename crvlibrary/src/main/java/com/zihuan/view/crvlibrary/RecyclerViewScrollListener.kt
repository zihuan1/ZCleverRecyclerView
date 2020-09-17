package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.RecyclerView

interface RecyclerViewScrollListener {

    /**
     * @param view RecyclerView
     * @param state 滚动状态
     * @param scrollX 横向滚动
     * @param scrollY 竖向滚动
     */
    fun onScrollStateChanged(view:RecyclerView,state:Int,orientation:Int,scrollX:Int,scrollY:Int)
    fun onScrollIng(view:RecyclerView)
}