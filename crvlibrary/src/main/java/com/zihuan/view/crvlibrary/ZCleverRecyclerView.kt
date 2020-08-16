package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
class ZCleverRecyclerView : ZBaseCleverRecycler<ZBaseRecyclerBuilder, ZDefRecyclerWrapper> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    override fun createView() =
        RecyclerView(context).apply { this@ZCleverRecyclerView.addView(this) }

    override fun createWrapper(recyclerView: RecyclerView) =
        ZDefRecyclerWrapper(recyclerView)

    /**
     * 为了便于调用(懒),暴露出当前构建者
     * 获取当前构建者之前,一定要调用buildVerticalLayout等方法进行创建
     */
    fun getBaseBuilder() = getBuilder()


}