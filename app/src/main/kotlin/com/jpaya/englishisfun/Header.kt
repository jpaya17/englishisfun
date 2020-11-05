package com.jpaya.englishisfun

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.header.view.*

class Header @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.header, this, true)
        loadAttrs(attrs)
    }

    private fun loadAttrs(attrs: AttributeSet?) {
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.Header, 0, 0)
            val title = resources.getText(styledAttributes.getResourceId(R.styleable.Header_header_title, R.string.app_name))
            tv_title.text = title
            styledAttributes.recycle()
        }
    }
}
