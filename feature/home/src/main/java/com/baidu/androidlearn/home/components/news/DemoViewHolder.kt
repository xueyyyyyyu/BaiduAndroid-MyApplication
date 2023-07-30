package com.baidu.androidlearn.home.components.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baidu.androidlearn.home.R

/**
 * 用于复用的ViewHolder
 *
 * @author: xiejikun
 * @since: 2023/7/7
 */
class DemoViewHolder : RecyclerView.ViewHolder {

    /** 封面图 */
    var ivCover: ImageView
        private set
    /** 标题 */
    var tvTitle:TextView
        private set
    /** 内容 */
    var tvContent: TextView
        private set


    constructor(view: View) : super(view) {
        ivCover = view.findViewById(R.id.cover)
        tvTitle = view.findViewById(R.id.tv_title)
        tvContent = view.findViewById(R.id.tv_content)
    }
}