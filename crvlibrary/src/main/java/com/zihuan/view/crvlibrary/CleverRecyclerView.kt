package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 模版类
 * @author Zihuan
 */
class CleverRecyclerView : BaseCleverRecycler<BaseRecyclerBuilder> {
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    override fun bindRecycler(): RecyclerView {
        return RecyclerView(context).apply { this@CleverRecyclerView.addView(this) }
    }

    override fun createBuilder(wrapper: RecyclerViewWrapper<BaseRecyclerBuilder>) {
        wrapper.createBuilder<BaseRecyclerBuilder>()
    }


}