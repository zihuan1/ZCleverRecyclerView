package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
abstract class ZBaseCleverRecycler<B : ZBaseRecyclerBuilder, T : ZBaseRecyclerWrapper<B>> :
    FrameLayout {
    private lateinit var mRecyclerBuilder: B


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    protected abstract fun createView(): RecyclerView

    protected abstract fun createWrapper(recyclerView: RecyclerView): T

    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) =
        createWrapper(createView()).buildVerticalLayout(adapter).apply { mRecyclerBuilder = this }

    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) =
        createWrapper(createView()).buildHorizontalLayout(adapter).apply { mRecyclerBuilder = this }

    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) =
        createWrapper(createView()).buildGridLayout(adapter, type).apply { mRecyclerBuilder = this }

    protected fun getBuilder() = mRecyclerBuilder

}