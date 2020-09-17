package com.zihuan.view.crvlibrary

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 构建者类
 * @author Zihuan
 */
open class BaseRecyclerBuilder {

    private var mRecyclerView: RecyclerView
    private var mZRecyclerData: RecyclerData
    private var emptyViewBase: EmptyView? = null
    private var listData = ArrayList<Any>()
    private var mDisableEmptyView = true
    private var mRecyclerQuickOperation: RecyclerQuickOperation

    constructor(adapter: RecyclerView.Adapter<*>, type: Int, recyclerView: RecyclerView) {
        if (adapter is RecyclerData) {
            mZRecyclerData = adapter
        } else {
            throw ClassCastException("没有实现数据刷新接口")
        }
        mRecyclerView = when (type) {
            LinearLayoutManager.VERTICAL -> recyclerView.initVertical(adapter)
            LinearLayoutManager.HORIZONTAL -> recyclerView.initHorizontal(adapter)
            else -> recyclerView.initGrid(type, adapter)
        }
        mRecyclerQuickOperation = RecyclerQuickOperation(mRecyclerView)
    }

    /**
     * 设置数据
     */
    open fun setData(list: ArrayList<*>) = apply {
        listData.clear()
        listData.addAll(list)
        if (EmptyView.emptyViewShow && list.isNullOrEmpty() && mDisableEmptyView) {
            setEmptyView()
        } else {
            dismissEmptyView()
        }
        mZRecyclerData.update(listData)
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
     * 控制当前RecyclerView的空布局显示状态
     */
    fun disableCurrentEmptyView() = apply {
        mDisableEmptyView = false
    }

    fun enableCurrentEmptyView() = apply {
        mDisableEmptyView = true
    }


    /**
     * 指定位置的Item执行点击事件
     * 需要注意的是指定的位置必须在屏幕内
     */
    fun performItemClick(vararg position: Int) {
        position.forEach {
            val layoutManager = mRecyclerView.layoutManager
            val view = layoutManager?.findViewByPosition(it)
            if (null == view) {
                Log.e("BaseRecyclerBuilder", "$it 位置Item没有在屏幕内")
                return
            }
            view.performClick()
        }
    }

    /**
     * 指定位置的Item的Child执行点击事件
     * 需要注意的是指定的位置必须在屏幕内
     */
    fun performItemChildClick(vararg position: Int, @IdRes children: Int) {
        position.forEach {
            try {
                val layoutManager = mRecyclerView.layoutManager
                val view = layoutManager?.findViewByPosition(it)
                if (null == view) {
                    Log.e("BaseRecyclerBuilder", "没有此 $children Id,或$it 位置Item没有在屏幕内")
                    return
                }
                val childView: View = view.findViewById(children)
                if (null != childView) {
                    childView.performClick()
                } else {
                    Log.e("BaseRecyclerBuilder", "没有此 $children Id,请检查")
                }
            } catch (e: Exception) {
                Log.e("BaseRecyclerBuilder", "请检查$e")
            }
        }
    }

    /**
     *
     */
    fun addOnScrollListener() {
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val manager = recyclerView.layoutManager as LinearLayoutManager
                // 当不滚动时
                if (newState === RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    val lastVisibleItem = manager.findFirstCompletelyVisibleItemPosition()
                    val totalItemCount = manager.itemCount
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    /**
     * 获取获取当前RecyclerView
     */
    fun getRecyclerView() = mRecyclerView

    /**
     * 设置空布局
     */
    fun setEmptyView(viewBase: EmptyView = getEmptyView()) {
        mRecyclerView.visibility = View.GONE
        emptyViewBase = viewBase
        viewBase.visibility = View.VISIBLE
        var viewParent = mRecyclerView.parent
        if (viewParent is ViewGroup) {
            if (viewParent.indexOfChild(viewBase) == -1)
                viewParent.addView(viewBase)
        } else {
            Log.e("RecyclerView", "默认空布局不可用,请手动设置")
        }
    }

    private fun dismissEmptyView() {
        if (mRecyclerView.visibility == View.GONE) {
            mRecyclerView.visibility = View.VISIBLE
            emptyViewBase?.visibility = View.GONE
        }
    }

    private fun getEmptyView(): EmptyView {
        if (null == emptyViewBase) emptyViewBase = EmptyView(mRecyclerView.context)
        return emptyViewBase!!
    }

    /**
     * 设置监听
     */

    fun setEmptyViewListener(listener: EmptyViewListener) {
        getEmptyView().mListener = listener
    }

    /**
     * 设置空布局中的view的点击事件,可以多个
     * 一定要先设置监听事件
     */
    fun setEmptyViewClick(vararg viewId: Int) {
        getEmptyView().bindClick(*viewId)
    }

    /**
     * 获取RecyclerView快捷操作类
     */
    fun getRecyclerOperation() = mRecyclerQuickOperation
}
