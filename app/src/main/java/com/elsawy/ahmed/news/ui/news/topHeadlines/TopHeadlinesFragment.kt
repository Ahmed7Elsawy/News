package com.elsawy.ahmed.news.ui.news.topHeadlines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.network.ConnectivityInterceptorImpl
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.data.network.NewsNetworkDataSource
import com.elsawy.ahmed.news.data.network.NewsNetworkDataSourceImpl
import kotlinx.android.synthetic.main.top_headlines_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
        // TODO: Use the ViewModel

        val newsAPIService = NewsAPIService(ConnectivityInterceptorImpl(this.context!!))
        val newsNetworkDataSource = NewsNetworkDataSourceImpl(newsAPIService)

        newsNetworkDataSource.downloadedTopNews.observe(this, Observer {
            top_textview.text = it.totalResults.toString() + "\n" + it.articles.get(0).content

        })

        GlobalScope.launch(Dispatchers.Main){
            newsNetworkDataSource.fetchTopNews()
        }

    }

}
