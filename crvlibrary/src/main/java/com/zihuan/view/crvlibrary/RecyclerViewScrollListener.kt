package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.LinearLayoutManager

fun interface RecyclerViewScrollListener {

    /**
     * @param state 滚动状态
     * @param layoutManager 布局管理器
     * @param scrollX 横向滚动 正值向上滚动负值向下滚动
     * @param scrollY 竖向滚动 正直像左滚动负值向由滚动
     */
    fun onScrollStateChanged(state:Int, layoutManager: LinearLayoutManager, scrollX:Int, scrollY:Int)
}