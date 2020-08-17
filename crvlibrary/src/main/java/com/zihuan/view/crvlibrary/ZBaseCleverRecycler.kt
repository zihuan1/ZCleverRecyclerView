package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
abstract class ZBaseCleverRecycler<B : ZBaseRecyclerBuilder, T : ZBaseRecyclerWrapper<B>> :
        FrameLayout {
    lateinit var mRecyclerBuilder: B


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    protected abstract fun createView(): RecyclerView

    protected abstract fun createWrapper(recyclerView: RecyclerView): T


//    @Deprecated(message = "请使用最新用法", replaceWith = ReplaceWith("buildVerticalLayout<Adapter>()"))
    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) =
            createWrapper(createView()).buildVerticalLayout(adapter).apply { mRecyclerBuilder = this }

//    @Deprecated(message = "请使用最新用法", replaceWith = ReplaceWith("buildHorizontalLayout<Adapter>()"))
    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) =
            createWrapper(createView()).buildHorizontalLayout(adapter).apply { mRecyclerBuilder = this }

//    @Deprecated(message = "请使用最新用法", replaceWith = ReplaceWith("buildGridLayout<Adapter>()"))
    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) =
            createWrapper(createView()).buildGridLayout(adapter, type).apply { mRecyclerBuilder = this }

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildVerticalLayout(vararg param: Any) =
            createWrapper(createView()).buildVerticalLayout<Adapter>(*param).apply { mRecyclerBuilder = this }

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildHorizontalLayout(vararg param: Any) =
            createWrapper(createView()).buildHorizontalLayout<Adapter>(*param).apply { mRecyclerBuilder = this }

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildGridLayout(type: Int, vararg param: Any) =
            createWrapper(createView()).buildGridLayout<Adapter>(type, param).apply { mRecyclerBuilder = this }


    protected fun getBuilder() = mRecyclerBuilder

}