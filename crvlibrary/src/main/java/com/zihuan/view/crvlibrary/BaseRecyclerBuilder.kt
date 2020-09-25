package com.zihuan.view.crvlibrary

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zihuan.view.crvlibrary.CleverConstants.SCROLL_STATE_SCROLLING
import com.zihuan.view.crvlibrary.CleverConstants.SCROLL_STATE_START
import com.zihuan.view.crvlibrary.CleverConstants.SCROLL_STATE_STOP

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


//    internal inline fun <reified A : RecyclerView.Adapter<*>> initQuickOperation() {
//        val adapter = mRecyclerQuickOperation.getAdapter<A>()
//    }

    /**
     * 设置数据
     */
    open fun setData(list: ArrayList<*>) = apply {
        listData.clear()
        listData.addAll(list)
        if (EmptyView.emptyViewShow && list.isNullOrEmpty() && mDisableEmptyView) {
            setEmptyView(emptyViewBase)
            emptyViewBase?.visibility = View.VISIBLE
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
     * 监听RecyclerView滚动
     */
    fun addOnScrollListener(listener: RecyclerViewScrollListener) {
        var currentDx = 0
        var currentDy = 0
        var state = SCROLL_STATE_STOP
        var layoutManager: LinearLayoutManager? = null
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.layoutManager is LinearLayoutManager) {
                    layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    state = when (newState) {
                        RecyclerView.SCROLL_STATE_DRAGGING -> SCROLL_STATE_START
                        RecyclerView.SCROLL_STATE_IDLE -> SCROLL_STATE_STOP
                        else -> SCROLL_STATE_SCROLLING
                    }
                }
                layoutManager?.apply {
                    listener.onScrollStateChanged(state, this, currentDx, currentDy)
                }
//                Log.e("onScrollStateChanged 状态", state.toString())
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                Log.e("onScrolled 状态", "$SCROLL_STATE_SCROLLING")
                currentDx = dx
                currentDy = dy
                layoutManager?.apply {
                    listener.onScrollStateChanged(SCROLL_STATE_SCROLLING, this, currentDx, currentDy)
                }

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
    fun setEmptyView(viewBase: EmptyView?) {
        viewBase.apply {
            mRecyclerView.visibility = View.GONE
            emptyViewBase = viewBase
            var viewParent = mRecyclerView.parent
            if (viewParent is ViewGroup) {
                if (viewParent.indexOfChild(viewBase) == -1)
                    viewParent.addView(viewBase)
            } else {
                Log.e("RecyclerView", "默认空布局不可用,请手动设置")
            }
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
