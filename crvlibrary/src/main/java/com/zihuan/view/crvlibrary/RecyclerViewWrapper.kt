package com.zihuan.view.crvlibrary

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/***
 * RecyclerView 默认的包装类
 * @author Zihuan
 */
class RecyclerViewWrapper<Builder : BaseRecyclerBuilder> {

    var baseBuilder: Builder? = null
    private var recyclerView: RecyclerView

    constructor(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }


    /**
     * 建议使用最新用法
     * @see buildVerticalLayout<Adapter>
     * 构造一个指定布局方向的RecyclerView
     */
      fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) = assembly(adapter, RECYCLER_VERTICAL)

    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) = assembly(adapter, RECYCLER_HORIZONTAL)

    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) = assembly(adapter, type)

    /**
     * 传一个Adapter泛型自动实例化,与上面的方法没有本质区别
     */
    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildVerticalLayout(vararg parameters: Any) =
            buildVerticalLayout(ClassFactory.createAdapter<Adapter>(*parameters))

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildHorizontalLayout(vararg parameters: Any) =
            buildHorizontalLayout(ClassFactory.createAdapter<Adapter>(*parameters))

    inline fun <reified Adapter : RecyclerView.Adapter<*>> buildGridLayout(type: Int, vararg parameters: Any) =
            buildGridLayout(ClassFactory.createAdapter<Adapter>(*parameters), type)

    private fun assembly(adapter: RecyclerView.Adapter<*>, type: Int): Builder {
        //包装类调用
        if (baseBuilder == null) createBuilder<BaseRecyclerBuilder>()
        setRecyclerParam(adapter, type)
        return baseBuilder!!
    }


    inline fun <reified Builder2> createBuilder(vararg parameters: Any) {
        baseBuilder = ClassFactory.create<Builder2>(*parameters) as Builder
    }

    private fun setRecyclerParam(adapter: RecyclerView.Adapter<*>, type: Int) {
        val recyclerData: RecyclerData
        if (adapter is RecyclerData) {
            recyclerData = adapter
        } else {
            throw ClassCastException("没有实现数据刷新接口")
        }
        recyclerView = when (type) {
            LinearLayoutManager.VERTICAL -> recyclerView.initVertical(adapter)
            LinearLayoutManager.HORIZONTAL -> recyclerView.initHorizontal(adapter)
            else -> recyclerView.initGrid(type, adapter)
        }
        baseBuilder?.setRecyclerParam(recyclerData, recyclerView)
    }

    fun getBuilder(): Builder {
        return baseBuilder!!
    }
}

