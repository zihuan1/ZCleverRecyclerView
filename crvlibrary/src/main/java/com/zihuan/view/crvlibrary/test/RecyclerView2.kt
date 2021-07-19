package com.zihuan.view.crvlibrary.test

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class RecyclerView2 @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseCleverRecycler2(context, attrs, defStyleAttr) {
    override fun bindRecycler(): RecyclerView {
        return RecyclerView(context).apply { this@RecyclerView2.addView(this) }
    }
}