package com.zihuan.view.crvlibrary

import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView


/**
 * RecyclerView快捷操作类
 */
class RecyclerQuickOperation(val mRecyclerView: RecyclerView) {


    /**
     *设置自定义的LayoutManager
     */
    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        mRecyclerView.layoutManager = layoutManager
    }

    fun <T : RecyclerView.LayoutManager> getLayoutManager() = mRecyclerView.layoutManager as T

    /**
     * 获取当前适配器
     */
    fun <T : RecyclerView.Adapter<*>> getAdapter() = mRecyclerView.adapter as T

    /**
     * 设置分割线
     */
    fun setDivider(rvd: RecyclerView.ItemDecoration) =
            apply { mRecyclerView.addItemDecoration(rvd) }

    /**
     * 滚动到底部
     */
    fun scrollToBottom() = apply { scrollToPosition(mRecyclerView.adapter!!.itemCount) }

    /***
     *滚动到指定位置 有动画
     */
    fun scrollToPosition(pos: Int) = apply { mRecyclerView.smoothScrollToPosition(pos) }

    /***
     *滚动到指定位置 没有动画
     */
    fun setCurrentPosition(pos: Int) {
        mRecyclerView.scrollToPosition(pos)
    }

    /**
     *包裹内容
     */
    fun setWrapContent() = apply {
        mRecyclerView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        )
    }

}