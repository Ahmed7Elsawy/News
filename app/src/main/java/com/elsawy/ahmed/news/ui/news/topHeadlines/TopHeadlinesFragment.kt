package com.elsawy.ahmed.news.ui.news.topHeadlines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elsawy.ahmed.news.R

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
    }

}