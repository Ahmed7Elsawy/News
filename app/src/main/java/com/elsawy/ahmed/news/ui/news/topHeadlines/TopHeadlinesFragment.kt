package com.elsawy.ahmed.news.ui.news.topHeadlines

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.db.entity.Article
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityReceiver
import com.elsawy.ahmed.news.ui.news.ArticleAdapter
import com.elsawy.ahmed.news.ui.news.OnItemClickListener
import com.elsawy.ahmed.news.ui.news.detail.DetailActivity
import kotlinx.android.synthetic.main.top_headlines_fragment.*

class TopHeadlinesFragment : Fragment() ,OnItemClickListener,
    ConnectivityReceiver.ConnectivityReceiverListener{

    companion object {
        fun newInstance() =
            TopHeadlinesFragment()
    }

    private lateinit var viewModel: TopHeadlinesViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_headlines_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Top haedlines"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null

        initRecyclerView()
        viewModel = ViewModelProviders.of(this).get(TopHeadlinesViewModel::class.java)
        viewModel.topNews.observe(viewLifecycleOwner, Observer {
            articleAdapter.setArticleList(it)
        })

        activity?.registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    private fun initRecyclerView() {
        articleAdapter = ArticleAdapter(this)
        top_article_recycler_view.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = articleAdapter
        }
    }

    override fun onItemClicked(article: Article) {
        activity?.let {
            val intent = Intent(it, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("article", article)
            intent.putExtra("Bundle", bundle)
            it.startActivity(intent)
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        Toast.makeText(context,"$isConnected",Toast.LENGTH_LONG).show()
        viewModel.updateTopArticlesFromServer()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

}
