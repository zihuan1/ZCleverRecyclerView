package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
class CleverRecyclerView : BaseCleverRecycler<BaseRecyclerBuilder, DefRecyclerWrapper> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    override fun createView() =
            RecyclerView(context).apply { this@CleverRecyclerView.addView(this) }

    override fun createWrapper(recyclerView: RecyclerView) = DefRecyclerWrapper(recyclerView)

}