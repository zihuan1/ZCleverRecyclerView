package com.zihuan.view.crvlibrary.test

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zihuan.view.crvlibrary.*

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
abstract class BaseCleverRecycler2 : FrameLayout {

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    abstract fun bindRecycler(): RecyclerView

    /**
     * 建议使用最新用法
     * @see vertical<Adapter>
     * 构造一个指定布局方向的RecyclerView
     */
    fun vertical(adapter: RecyclerView.Adapter<*>) = setRecyclerParam(adapter, RECYCLER_VERTICAL)

    fun horizontal(adapter: RecyclerView.Adapter<*>) = setRecyclerParam(adapter, RECYCLER_HORIZONTAL)

    fun grid(adapter: RecyclerView.Adapter<*>, type: Int) = setRecyclerParam(adapter, type)


    /**
     * 传一个Adapter泛型自动实例化,与上面的方法没有本质区别
     */
    inline fun <reified Adapter : RecyclerView.Adapter<*>> vertical(vararg parameters: Any) {
        val adapter = ClassFactory.createAdapter<Adapter>(*parameters)
        return vertical(adapter)
    }


    inline fun <reified Adapter : RecyclerView.Adapter<*>> horizontal(vararg parameters: Any) =
            horizontal(ClassFactory.createAdapter<Adapter>(*parameters))

    inline fun <reified Adapter : RecyclerView.Adapter<*>> grid(type: Int, vararg parameters: Any) =
            grid(ClassFactory.createAdapter<Adapter>(*parameters), type)


    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRecyclerData: RecyclerData
    private lateinit var mRecyclerQuickOperation: RecyclerQuickOperation

    private fun setRecyclerParam(adapter: RecyclerView.Adapter<*>, type: Int) {
        if (adapter is RecyclerData) {
            mRecyclerData = adapter
        } else {
            throw ClassCastException("没有实现数据刷新接口")
        }
        mRecyclerView = when (type) {
            LinearLayoutManager.VERTICAL -> bindRecycler().initVertical(adapter)
            LinearLayoutManager.HORIZONTAL -> bindRecycler().initHorizontal(adapter)
            else -> bindRecycler().initGrid(type, adapter)
        }
        mRecyclerQuickOperation = RecyclerQuickOperation(mRecyclerView)
    }

    private var emptyViewBase: EmptyView? = null
    private var listData = ArrayList<Any>()
    private var mDisableEmptyView = true

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
        mRecyclerData.update(listData)
    }

    fun <T> getAdapter(): T {
        return mRecyclerView.adapter as T
    }

    /**
     * 设置空布局
     */
    fun setEmptyView(viewBase: EmptyView?) {
        viewBase?.apply {
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

}