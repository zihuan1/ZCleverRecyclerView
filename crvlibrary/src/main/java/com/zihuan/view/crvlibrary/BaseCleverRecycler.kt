package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
abstract class BaseCleverRecycler<B : BaseRecyclerBuilder> : FrameLayout {

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0) : super(context, attrs, defStyleAttr) {
        initWrapper()
    }

    lateinit var viewWrapper: RecyclerViewWrapper<B>

    abstract fun bindRecycler(): RecyclerView

    abstract fun createBuilder(wrapper: RecyclerViewWrapper<B>)

    private fun initWrapper() {
        this.viewWrapper = RecyclerViewWrapper(bindRecycler())
        createBuilder(viewWrapper)
    }

    /**
     * 建议使用最新用法
     * @see buildVerticalLayout<Adapter>
     * 构造一个指定布局方向的RecyclerView
     */
    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) = viewWrapper.buildVerticalLayout(adapter)

    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) = viewWrapper.buildHorizontalLayout(adapter)

    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) = viewWrapper.buildGridLayout(adapter, type)

    /**
     * 传一个Adapter泛型自动实例化,与上面的方法没有本质区别
     */
    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildVerticalLayout(vararg parameters: Any) =
            viewWrapper.buildVerticalLayout<Adapter>(*parameters)

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildHorizontalLayout(vararg parameters: Any) =
            viewWrapper.buildHorizontalLayout<Adapter>(*parameters)

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildGridLayout(type: Int, vararg parameters: Any) =
            viewWrapper.buildGridLayout<Adapter>(type, *parameters)

    /**
     * 获取当前构建者
     */
    fun getBuilder() = viewWrapper.getBuilder()

}