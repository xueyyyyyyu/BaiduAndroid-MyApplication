package com.baidu.androidlearn.home.components.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baidu.androidlearn.home.R
import com.baidu.androidlearn.news.NewsTopic


/**
 * @author: xiejikun
 * @since: 2023/7/7
 * lesson4
 */
class DemoAdapter : RecyclerView.Adapter<DemoViewHolder> {

    /** 保存数据的列表 */
    private val demoList: List<NewsTopic>

    constructor(demoList: List<NewsTopic>) {
        this.demoList = demoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return DemoViewHolder(view)
    }


    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        val newsTopic = demoList[position]

        newsTopic.imageUrl.let {
            holder.ivCover.setImageResource(it)
        }

        newsTopic.title.let {
            holder.tvTitle.text = it
        }

        newsTopic.content.let {
            holder.tvContent.text = it
        }
    }


    override fun getItemCount(): Int {
        return demoList.size
    }
}