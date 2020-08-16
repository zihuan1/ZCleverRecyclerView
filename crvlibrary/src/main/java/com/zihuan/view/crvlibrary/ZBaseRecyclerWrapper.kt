package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView包装类
 * @author Zihuan
 */
open abstract class ZBaseRecyclerWrapper<T : ZBaseRecyclerBuilder> {
    /**
     * 构建一个竖向布局的view
     */
    @Deprecated(message = "请使用最新用法", replaceWith = ReplaceWith("buildVerticalLayout<Adapter>()"))
    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) = createBuilder(adapter, RECYCLER_VERTICAL)

    /**
     * 传一个Adapter泛型自动实例化,与上一个方法没有本质区别
     * @param Adapter 适配器泛型
     */
    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildVerticalLayout(vararg param: Any) =
            newAdapter<Adapter>(RECYCLER_VERTICAL, *param)


    /**
     * 构建一个横向布局的view
     */
    @Deprecated(message = "请使用最新用法", replaceWith = ReplaceWith("buildHorizontalLayout<Adapter>()"))
    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) =
            createBuilder(adapter, RECYCLER_HORIZONTAL)

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildHorizontalLayout(vararg param: Any) =
            newAdapter<Adapter>(RECYCLER_HORIZONTAL, *param)

    /**
     * 构建一个九宫格布局的view
     */
    @Deprecated(message = "请使用最新用法", replaceWith = ReplaceWith("buildGridLayout<Adapter>()"))
    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) =
            createBuilder(adapter, type)

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildGridLayout(type: Int, vararg param: Any) =
            newAdapter<Adapter>(type, *param)


    inline fun <reified Adapter : RecyclerView.Adapter<*>> newAdapter(type: Int, vararg param: Any): T {
        val paramTypes = param.map { it::class.java }.toTypedArray()
        val adapter = Adapter::class.java.getDeclaredConstructor(*paramTypes).newInstance(*param)
        return createBuilder(adapter, type)
    }

    /**
     * 包装RecyclerView
     */
    abstract fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): T


}