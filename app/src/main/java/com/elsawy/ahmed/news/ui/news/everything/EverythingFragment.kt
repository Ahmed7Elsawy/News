package com.elsawy.ahmed.news.ui.news.everything

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.Entity.Article
import com.elsawy.ahmed.news.ui.news.ArticleAdapter
import com.elsawy.ahmed.news.ui.news.OnItemClickListener
import com.elsawy.ahmed.news.ui.news.detail.DetailActivity
import kotlinx.android.synthetic.main.everything_fragment.*
import kotlinx.android.synthetic.main.top_headlines_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EverythingFragment : Fragment(),OnItemClickListener {

    companion object {
        fun newInstance() =
            EverythingFragment()
    }

    private lateinit var viewModel: EverythingViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.everything_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EverythingViewModel::class.java)
        initRecyclerView()

            viewModel.getEvertNews(context!!)
            viewModel.everyNews.observe(viewLifecycleOwner, Observer {
                articleAdapter.setArticleList(it.articles)
            })

    }

    private fun initRecyclerView() {
        articleAdapter = ArticleAdapter(this)
        every_article_recycler_view.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = articleAdapter
        }
    }

    override fun onItemClicked(article: Article) {
        activity?.let{
            val intent = Intent (it, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("article", article)
            intent.putExtra("Bundle", bundle)
            it.startActivity(intent)
        }
    }
}
