package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
abstract class BaseCleverRecycler<Builder : BaseRecyclerBuilder, Wrapper : BaseRecyclerWrapper<Builder>> : FrameLayout {
    protected lateinit var mRecyclerBuilder: Builder

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    protected abstract fun createView(): RecyclerView

    protected abstract fun createWrapper(recyclerView: RecyclerView): Wrapper

    /**
     * 建议使用最新用法
     * @see buildVerticalLayout<Adapter>
     * 构造一个指定布局方向的RecyclerView
     */
    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) =
            createWrapper(createView()).buildVerticalLayout(adapter).apply { mRecyclerBuilder = this }

    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) =
            createWrapper(createView()).buildHorizontalLayout(adapter).apply { mRecyclerBuilder = this }

    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) =
            createWrapper(createView()).buildGridLayout(adapter, type).apply { mRecyclerBuilder = this }


    /**
     * 传一个Adapter泛型自动实例化,与上面的方法没有本质区别
     * @param Adapter 适配器泛型
     * @param parameters 构造函数需要的参数列表
     * parameters数量一定要与构造函数所需的参数数量相匹配，否则抛出异常
     * 注意：Adapter中的构造函数目前不支持可变长参数，只能是固定参数
     *
     */
    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildVerticalLayout(vararg parameters: Any) =
            createWrapper(createView()).buildVerticalLayout<Adapter>(*parameters).apply { mRecyclerBuilder = this }

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildHorizontalLayout(vararg parameters: Any) =
            createWrapper(createView()).buildHorizontalLayout<Adapter>(*parameters).apply { mRecyclerBuilder = this }

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildGridLayout(type: Int, vararg parameters: Any) =
            createWrapper(createView()).buildGridLayout<Adapter>(type, parameters).apply { mRecyclerBuilder = this }

    /**
     * 获取当前构建者之前,一定要调用buildVerticalLayout等方法进行创建
     */
    fun getBuilder() = mRecyclerBuilder


}