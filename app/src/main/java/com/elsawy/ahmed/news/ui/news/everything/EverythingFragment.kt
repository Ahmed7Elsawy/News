package com.elsawy.ahmed.news.ui.news.everything

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.elsawy.ahmed.news.R
import kotlinx.android.synthetic.main.everything_fragment.*
import kotlinx.android.synthetic.main.top_headlines_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EverythingFragment : Fragment() {

    companion object {
        fun newInstance() =
            EverythingFragment()
    }

    private lateinit var viewModel: EverythingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.everything_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EverythingViewModel::class.java)

            viewModel.getEvertNews(context!!)
            viewModel.everyNews.observe(viewLifecycleOwner, Observer {
                for (article in it.articles)
                    every_textview.text = every_textview.text.toString() + "\n" + article.content
            })

    }

}
