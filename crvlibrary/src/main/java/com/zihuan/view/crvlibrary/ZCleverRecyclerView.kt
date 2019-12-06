package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
class ZCleverRecyclerView : FrameLayout {

    private lateinit var mZWrapRecyclerView: ZRecyclerWrapRecyclerView
    private lateinit var mRecyclerBuilderZ: ZBaseRecyclerBuilder

    constructor(context: Context) : super(context) {
        createView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        createView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        createView()
    }

    private fun createView() {
        var recyclerView = RecyclerView(context)
        addView(recyclerView)
        mZWrapRecyclerView = ZRecyclerWrapRecyclerView(recyclerView)
    }

    fun buildVerticalLayout(adapter: RecyclerView.Adapter<*>) = mZWrapRecyclerView.buildVerticalLayout(adapter).apply {
        mRecyclerBuilderZ = this
    }

    fun buildHorizontalLayout(adapter: RecyclerView.Adapter<*>) = mZWrapRecyclerView.buildHorizontalLayout(adapter).apply {
        mRecyclerBuilderZ = this
    }

    fun buildGridLayout(adapter: RecyclerView.Adapter<*>, type: Int) = mZWrapRecyclerView.buildGridLayout(adapter, type).apply {
        mRecyclerBuilderZ = this
    }

    /**
     * 为了便于调用(懒),暴露出当前构建者
     * 获取当前构建者之前,一定要调用buildVerticalLayout等方法进行创建
     */
    fun getBuilder() = mRecyclerBuilderZ

}