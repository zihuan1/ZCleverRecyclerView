package com.zihuan.view.crvlibrary

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 构建者类
 * @author Zihuan
 */
open class ZBaseRecyclerBuilder {

    private var mRecyclerView: RecyclerView
    private var mZRecyclerData: ZRecyclerData
    private var emptyView: ZEmptyView? = null
    private var listData = ArrayList<Any>()
    private var mDisableEmptyView = true

    constructor(adapter: RecyclerView.Adapter<*>, type: Int, recyclerView: RecyclerView) {
        if (adapter is ZRecyclerData) {
            mZRecyclerData = adapter
        } else {
            throw ClassCastException("没有实现数据刷新接口")
        }
        mRecyclerView = when (type) {
            LinearLayoutManager.VERTICAL -> recyclerView.initVertical(adapter)
            LinearLayoutManager.HORIZONTAL -> recyclerView.initHorizontal(adapter)
            else -> recyclerView.initGrid(type, adapter)
        }
    }

    /**
     * 设置数据
     */
    open fun setData(list: ArrayList<*>) = apply {
        listData.addAll(list)
        if (ZEmptyView.emptyViewShow && list.isNullOrEmpty() && mDisableEmptyView) {
            setEmptyView()
        } else {
            dismissEmptyView()
        }
        mZRecyclerData.update(list)
    }

    /**
     * 获取当前数据
     */
    fun <T> getData() = listData as ArrayList<T>

    /**
     * 清空数据
     */
    fun clearData() {
        listData.clear()
        setData(listData)
    }

    /**
     * 获取当前适配器
     * 暂时的方法将来会优化
     */
    fun <T : RecyclerView.Adapter<*>> getAdapter() = getRecyclerView().adapter as T

    fun disableCurrentEmptyView() = apply {
        mDisableEmptyView = false
    }

    fun eableCurrentEmptyView() = apply {
        mDisableEmptyView = true
    }

    /**
     *设置自定义的LayoutManager
     */
    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        mRecyclerView.layoutManager = layoutManager
    }

    fun <T : RecyclerView.LayoutManager> getLayoutManager() = mRecyclerView.layoutManager as T

    /**
     * 设置分割线
     */
    fun setDivider(rvd: RecyclerView.ItemDecoration) =
        apply { mRecyclerView.addItemDecoration(rvd) }

//    fun setDivider(action: RecycleViewDivider.() -> Unit) = apply {
//
//        mRecyclerView.addItemDecoration()
//    }

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
     * 获取获取当前RecyclerView
     */
    fun getRecyclerView() = mRecyclerView

    /**
     *包裹内容
     */
    fun setWrapContent() = apply {
        mRecyclerView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
    }


    /**
     * 设置空布局
     */
    fun setEmptyView(view: View = getEmptyView()) {
        mRecyclerView.visibility = View.GONE
        if (view.visibility != View.VISIBLE) {
            view.visibility = View.VISIBLE
            var viewParent = mRecyclerView.parent
            if (viewParent is ViewGroup) {
                if (viewParent.indexOfChild(view) == -1)
                    viewParent.addView(view)
            } else {
                Log.e("RecyclerView", "默认空布局不可用,请手动设置")
            }
        }
    }

    fun dismissEmptyView() {
        if (mRecyclerView.visibility == View.GONE) {
            mRecyclerView.visibility = View.VISIBLE
            emptyView?.visibility = View.GONE
        }
    }

    private fun getEmptyView(): ZEmptyView {
        if (null == emptyView) {
            emptyView = ZEmptyView(mRecyclerView.context)
        }
        return emptyView!!
    }

    /**
     * 设置监听
     */

    fun setEmptyViewListener(listener: ZEmptyViewListener) {
        getEmptyView().mListener = listener
    }

    /**
     * 设置空布局中的view的点击事件,可以多个
     * 一定要先设置监听事件
     */
    fun setEmptyViewClick(vararg viewId: Int) {
        getEmptyView().bindClick(*viewId)
    }

}
