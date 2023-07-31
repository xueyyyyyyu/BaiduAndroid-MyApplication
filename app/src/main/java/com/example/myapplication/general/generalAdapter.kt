package com.example.myapplication.general

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class generalAdapter(
    private var generallist: ArrayList<general>, // 使用 var 关键字，使得之后可以修改 generallist
    private val generalDao: generalDao
) : RecyclerView.Adapter<generalAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cover: ImageView = view.findViewById(R.id.tv_cover)
        val title: TextView = view.findViewById(R.id.tv_keycontent)
        val content: TextView = view.findViewById(R.id.tv_content)
        val fire: ImageView = view.findViewById(R.id.tv_fire)
        val tip: TextView = view.findViewById(R.id.tv_tip)
        val from: TextView = view.findViewById(R.id.tv_from)

        fun bind(general: general) {
            // 使用Glide加载图片并显示在ImageView中
            Glide.with(itemView.context)
                .load(general.imageUrl) // 使用General对象的imageUrl字段作为图片的URL
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_image) // 设置占位符图片，防止加载过程中出现空白
                        .error(R.drawable.error_image) // 设置加载失败时显示的错误图片
                )
                .transition(DrawableTransitionOptions.withCrossFade()) // 设置加载图片的过渡效果
                .into(cover) // 将加载的图片显示在cover ImageView中

            title.text = general.title
            content.text = general.content
            fire.setImageResource(general.iconResource)
            tip.text = general.label
            from.text = general.source
        }
    }
    fun insertGeneralAtTop(general: general) {
        generallist.add(0, general) // 将新的 general 对象添加到列表头部
        notifyItemInserted(0) // 通知 RecyclerView 在顶部插入新项
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.generalnews_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return generallist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val general = generallist[position]
        holder.bind(general)

        holder.itemView.setOnClickListener {
            // 启动新线程执行数据库操作
            GlobalScope.launch(Dispatchers.IO) {
                // 在数据库中插入新的 general 对象
                generalDao.insertGeneral(general)
            }
        }
    }

}