import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baidu.androidlearn.home.R
import com.baidu.androidlearn.news.NewsTopic
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = mutableListOf<NewsTopic>()

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val imageView: ImageView = itemView.findViewById(R.id.cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.component_news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.titleTextView.text = news.title
        Glide.with(holder.itemView)
            .load(news.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNewsList(newNewsList: List<NewsTopic>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }
}
