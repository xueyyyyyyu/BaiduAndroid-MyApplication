package com.baidu.androidlearn.lesson9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author: xiejikun
 * @since: 2023/7/7
 * lesson4
 */
class DemoAdapter : RecyclerView.Adapter<DemoViewHolder> {

    /** 保存数据的列表 */
    private val demoList: List<ItemBean>

    constructor(demoList: List<ItemBean>) {
        this.demoList = demoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return DemoViewHolder(view)
    }


    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        val itemBean = demoList[position]

        itemBean.coverUrl?.let {
            holder.ivCover?.setImageResource(it)
        }

        itemBean.title?.let {
            holder.tvTitle.text = it
        }

        itemBean.content?.let {
            holder.tvContent.text = it
        }
    }


    override fun getItemCount(): Int {
        return demoList.size
    }
}