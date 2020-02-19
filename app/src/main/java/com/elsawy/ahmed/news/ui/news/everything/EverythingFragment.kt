package com.elsawy.ahmed.news.ui.news.everything

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.elsawy.ahmed.news.R
import com.elsawy.ahmed.news.data.db.entity.Article
import com.elsawy.ahmed.news.data.provider.*
import com.elsawy.ahmed.news.ui.news.ArticleAdapter
import com.elsawy.ahmed.news.ui.news.OnItemClickListener
import com.elsawy.ahmed.news.ui.news.detail.DetailActivity
import kotlinx.android.synthetic.main.everything_fragment.*

class EverythingFragment : Fragment(),OnItemClickListener {

    companion object {
        fun newInstance() =
            EverythingFragment()
    }

    private lateinit var viewModel: EverythingViewModel
    private lateinit var articleAdapter: ArticleAdapter
    private var titleQuery: String = ""
    private var sortBYFilter: String = "publishedAt"
    private var uploadDateFilter: String = getTime("today")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.everything_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Search"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null

        viewModel = ViewModelProviders.of(this).get(EverythingViewModel::class.java)
        initRecyclerView()
        initSearchView()
        initSortBySpinner()
        initUploadDateSpinner()

    }

    private fun initRecyclerView() {
        articleAdapter = ArticleAdapter(this)
        every_article_recycler_view.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = articleAdapter
        }
    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                titleQuery = newText
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                titleQuery = query
                getEveryNews()
                return false
            }
        })
    }

    private fun initSortBySpinner() {
        val sortByArray = resources.getStringArray(R.array.sortBy)
        val adapter =
            ArrayAdapter(context!!.applicationContext, android.R.layout.simple_spinner_item, sortByArray)
        sort_by_spinner.adapter = adapter

        sort_by_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                sortBYFilter = parent?.selectedItem.toString()
                if (titleQuery.isNotEmpty())
                    getEveryNews()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initUploadDateSpinner() {
        val uploadDateArray = resources.getStringArray(R.array.uploadDate)
        val adapter = ArrayAdapter(
            context!!.applicationContext,
            android.R.layout.simple_spinner_item,
            uploadDateArray
        )

        upload_date_spinner.adapter = adapter

        upload_date_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var choosedUploadDate = parent?.selectedItem.toString()
                if (titleQuery.isNotEmpty()) {
                    uploadDateFilter = getTime(choosedUploadDate)
                    getEveryNews()

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun getEveryNews() {
        viewModel.getEveryNews(titleQuery, sortBYFilter,uploadDateFilter)
        viewModel.everyNews.observe(viewLifecycleOwner, Observer {
            articleAdapter.setArticleList(it.articles)
        })
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

}