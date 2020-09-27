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
    constructor(context: Context) : super(context) {
        initWrapper()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initWrapper()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initWrapper()
    }

    private lateinit var viewWrapper: RecyclerViewWrapper<B>

    abstract fun createWrapper(): RecyclerViewWrapper<B>

    private fun initWrapper() {
        this.viewWrapper = createWrapper()
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
            buildVerticalLayout(RecyclerAdapterFactory.createAdapter<Adapter>(*parameters))

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildHorizontalLayout(vararg parameters: Any) =
            buildHorizontalLayout(RecyclerAdapterFactory.createAdapter<Adapter>(*parameters))


    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildGridLayout(vararg parameters: Any, type: Int) =
            buildGridLayout(RecyclerAdapterFactory.createAdapter<Adapter>(*parameters), type)


    /**
     * 获取当前构建者
     */
    fun getBuilder() = viewWrapper.getBuilder()


}