package com.elsawy.ahmed.news.ui.news.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.Entity.Article
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.getBundleExtra("Bundle")
        val article = bundle.getParcelable<Article>("article")

        detail_textview.text = article?.title + article?.description
    }
}
