package com.elsawy.ahmed.news.ui.news.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.db.entity.Article
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.getBundleExtra("Bundle")
        val article = bundle?.getParcelable<Article>("article")

        title_detail_tv.text = article?.title
        published_at_tv.text = article?.publishedAt
        description_detail_tv.text = article?.description
        content_detail_tv.text = article?.content
        source_detail_tv.text = article?.source?.name
        author_detail_tv.text = article?.author

        Glide.with(this)
            .load(article?.urlToImage)
            .into(image_detail)

    }


}
