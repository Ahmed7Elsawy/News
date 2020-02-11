package com.elsawy.ahmed.news.ui.news.topHeadlines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import kotlinx.android.synthetic.main.top_headlines_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TopHeadlinesFragment : Fragment() {

    companion object {
        fun newInstance() =
            TopHeadlinesFragment()
    }

    private lateinit var viewModel: TopHeadlinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_headlines_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopHeadlinesViewModel::class.java)

            viewModel.getTopNews(context!!)
            viewModel.topNews.observe(viewLifecycleOwner, Observer {
                for (article in it.articles)
                    top_textview.text = top_textview.text.toString() + "\n" + article.content
            })

    }

}
