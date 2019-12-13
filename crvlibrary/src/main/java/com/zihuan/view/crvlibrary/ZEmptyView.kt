package com.zihuan.view.crvlibrary

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout


class ZEmptyView : FrameLayout {
    constructor(context: Context) : super(context) {
        visibility = View.GONE
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        inflate(context, emptyLayout, this)
    }


    var mListener: ZEmptyViewListener? = null
    fun bindClick(vararg viewIds: Int) {
        if (null == mListener) {
            var listener = context
            if (listener is ZEmptyViewListener) {
                mListener = listener
            } else {
                throw NullPointerException("没有实现ZEmptyViewListener空布局点击监听")
            }
        }

        viewIds.forEach {
            findViewById<View>(it)?.setOnClickListener { view ->
                //应该是返回当前view
                mListener?.onClick(view.id, view)
            }
        }
    }

    companion object {
        var emptyViewShow = true
        var emptyLayout = R.layout.z_recycler_empty_layout

    }

}