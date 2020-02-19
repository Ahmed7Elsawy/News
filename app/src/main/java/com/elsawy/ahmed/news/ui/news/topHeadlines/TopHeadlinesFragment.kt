package com.elsawy.ahmed.news.ui.news.topHeadlines

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.db.ArticleDao
import com.elsawy.ahmed.news.data.db.ArticleRoomDatabase
import com.elsawy.ahmed.news.data.db.entity.Article
import com.elsawy.ahmed.news.ui.news.ArticleAdapter
import com.elsawy.ahmed.news.ui.news.OnItemClickListener
import com.elsawy.ahmed.news.ui.news.detail.DetailActivity
import kotlinx.android.synthetic.main.top_headlines_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopHeadlinesFragment : Fragment() ,OnItemClickListener{

    companion object {
        fun newInstance() =
            TopHeadlinesFragment()
    }

    private lateinit var viewModel: TopHeadlinesViewModel
    private lateinit var articleAdapter: ArticleAdapter
    lateinit var articleDao: ArticleDao

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


        articleDao = ArticleRoomDatabase(context!!).articleDao()

//        articleDao.getAllArticles().observe(viewLifecycleOwner, Observer {
//            Log.i("article", it.size.toString())
//            for (article in it)
//                Log.i("articls", article.title)
//        })

        initRecyclerView()
        viewModel = ViewModelProviders.of(this).get(TopHeadlinesViewModel::class.java)
        viewModel.topNews.observe(viewLifecycleOwner, Observer {
            articleAdapter.setArticleList(it.articles)

            setDB(it.articles)
        })

    }

    private fun setDB(articles: List<Article>)
    {
        Log.i("articleInsert", articles.size.toString())
        GlobalScope.launch(Dispatchers.IO) {
//            for (article in articles) {
//                articleDao.insert(article)
//                Log.i("articlsIns", article.title)
//            }
//            val s = articleDao.insertAll(articles)
//            Log.i("articlsSize", s.size.toString())

        }
    }

    private fun initRecyclerView() {
        articleAdapter = ArticleAdapter(this)
        top_article_recycler_view.apply {
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
