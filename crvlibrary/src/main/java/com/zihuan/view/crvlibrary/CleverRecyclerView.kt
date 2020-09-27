package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
class CleverRecyclerView : BaseCleverRecycler<BaseRecyclerBuilder> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun createWrapper(): RecyclerViewWrapper<BaseRecyclerBuilder> {
        val view = RecyclerView(context).apply { this@CleverRecyclerView.addView(this) }
        return RecyclerViewWrapper<BaseRecyclerBuilder>(view)
                .apply { createBuilder<BaseRecyclerBuilder>() }

    }


}