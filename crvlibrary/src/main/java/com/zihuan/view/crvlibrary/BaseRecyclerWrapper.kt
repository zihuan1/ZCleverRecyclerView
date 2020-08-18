package com.zihuan.view.crvlibrary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView包装类
 * @author Zihuan
 */
open abstract class BaseRecyclerWrapper<Builder : BaseRecyclerBuilder> {
    /**
     * 构建一个竖向布局的view
     */
    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) = createBuilder(adapter, RECYCLER_VERTICAL)

    /**
     * 传一个Adapter泛型自动实例化,与上一个方法没有本质区别
     * @param Adapter 适配器泛型
     * @param parameters 构造函数需要的参数列表
     * parameters数量一定要与构造函数所需的参数数量相匹配，否则抛出异常
     * 注意：Adapter中的构造函数目前不支持可变长参数，只能是固定参数
     *
     */
    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildVerticalLayout(vararg parameters: Any) =
            newAdapter<Adapter>(RECYCLER_VERTICAL, *parameters)


    /**
     * 构建一个横向布局的view
     */
    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) =
            createBuilder(adapter, RECYCLER_HORIZONTAL)

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildHorizontalLayout(vararg parameters: Any) =
            newAdapter<Adapter>(RECYCLER_HORIZONTAL, *parameters)

    /**
     * 构建一个九宫格布局的view
     */
    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) =
            createBuilder(adapter, type)

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildGridLayout(type: Int, vararg parameters: Any) =
            newAdapter<Adapter>(type, *parameters)


    @RequiresApi(Build.VERSION_CODES.O)
    inline fun <reified Adapter : RecyclerView.Adapter<*>> newAdapter(type: Int, vararg parameters: Any): Builder {
//        val paramTypes = parameters.map { it::class.java }.toTypedArray()
//        val adapter = Adapter::class.java.getDeclaredConstructor(*paramTypes).newInstance(*parameters)
        //获取目标类构造列表
        val constructor2 = Adapter::class.java.constructors
        var adapter2: Adapter? = null
        constructor2.forEach {
            //将param数量与构造列表中的函数参数数量进行匹配
            if (it.parameterCount == parameters.size) {
                //获取目标类运行时构造参数列表所需类型，以构造函数需要的类型为准
                var paramTypes2 = it.parameters.map { mapIt -> mapIt.type }.toTypedArray()
                adapter2 = Adapter::class.java.getDeclaredConstructor(*paramTypes2).newInstance(*parameters)
                return@forEach
            }
        }
        if (adapter2 == null) {
            throw NoSuchMethodException("没有与${parameters.map { it.javaClass.simpleName }}匹配的构造函数")
        }
        return createBuilder(adapter2!!, type)
    }

    /**
     * 包装RecyclerView
     */
    abstract fun createBuilder(adapter: RecyclerView.Adapter<*>, type: Int): Builder

}