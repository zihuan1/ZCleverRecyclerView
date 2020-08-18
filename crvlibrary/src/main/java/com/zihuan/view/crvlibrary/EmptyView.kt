package com.zihuan.view.crvlibrary

import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.IdRes

/**
 * 空布局类
 */
class EmptyView : FrameLayout {
    /**
     * 默认布局
     */
    constructor(context: Context) : super(context) {
        initView(emptyLayout)
    }

    /**
     * 自定义布局
     */
    constructor(view: View) : super(view.context) {
        visibility = View.GONE
        addView(view)
    }

    constructor(@IdRes resId: Int, context: Context) : super(context) {
        initView(resId)
    }

    private fun initView(resId: Int) {
        visibility = View.GONE
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        inflate(context, resId, this)
    }

    var mListener: EmptyViewListener? = null
    fun bindClick(vararg viewIds: Int) {
        if (null == mListener) {
            var listener = context
            if (listener is EmptyViewListener) {
                mListener = listener
            } else {
                throw NullPointerException("没有实现EmptyViewListener空布局点击监听")
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
        //初始化之前设置emptyLayout可以设置全局空布局
        var emptyLayout = R.layout.def_recycler_empty_layout
    }

}