package com.zihuan.view.crvlibrary

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout


class ZEmptyView : FrameLayout {
    constructor(context: Context) : super(context) {
        visibility = View.GONE
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        inflate(context, emptyLayout, this)
    }

    companion object {
        var emptyViewShow = true
        var emptyLayout = R.layout.z_recycler_empty_layout
    }

}