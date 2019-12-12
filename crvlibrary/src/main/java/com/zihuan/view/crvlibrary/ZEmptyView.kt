package com.zihuan.view.crvlibrary

import android.content.Context
import android.view.View
import android.widget.FrameLayout


class ZEmptyView : FrameLayout {
    constructor(context: Context) : super(context) {
        visibility = View.GONE
        inflate(context, emptyLayout, this)
    }

    companion object {
        var emptyLayout = R.layout.z_recycler_empty_layout
    }

}