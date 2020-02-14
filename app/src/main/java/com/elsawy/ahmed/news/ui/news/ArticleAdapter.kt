package com.elsawy.ahmed.news.ui.news

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.Entity.Article
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleAdapter (var itemClickListener : OnItemClickListener): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private val articleList = ArrayList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArticleViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(textView)
    }



    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articleList.get(position),itemClickListener)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setArticleList(articles: List<Article>) {
        articleList.clear()
        articleList.addAll(articles)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val articleTitle : TextView = itemView.article_title
        private val articleDescription : TextView = itemView.article_description
        private val articleImage : ImageView = itemView.article_image

        fun bind(article : Article, itemClickListener : OnItemClickListener) {

            articleTitle.text = article.title
            articleDescription.text = article.description
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .into(articleImage)

            itemView.setOnClickListener {
                itemClickListener.onItemClicked(article)
            }

        }

    }

}

interface OnItemClickListener{
    fun onItemClicked(article: Article)
}