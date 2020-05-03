package com.bsktech.codingassignmentjet2travel

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bsktech.codingassignmentjet2travel.adaptors.ArticlesAdapter
import com.bsktech.codingassignmentjet2travel.models.Articles
import com.bsktech.codingassignmentjet2travel.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity(), (Articles) -> Unit {

    lateinit var viewModel: MainViewModel
    private val TAG = "MainActivity"

    private var articles = LinkedList<Articles>()
    private lateinit var articlesAdapter: ArticlesAdapter
    var PAGE_SIZE = 10
    var currentPage = 1
    var isLoading = false

    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        showProgressBar()
        viewModel.getArticles(currentPage)

        viewModel.articles!!.observe(this, Observer {
            for (item in it) {
                articles.add(item)
                articlesAdapter.notifyDataSetChanged()
                isLoading = false
                hideProgressBar()
            }
        })

        recycler_view_articles.apply {
            linearLayoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager = linearLayoutManager
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            articlesAdapter = ArticlesAdapter(articles, this@MainActivity)
            addOnScrollListener(recyclerViewOnScrollListener)
            adapter = articlesAdapter
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = linearLayoutManager.childCount
                val totalItemCount: Int = linearLayoutManager.itemCount
                val firstVisibleItemPosition: Int =
                    linearLayoutManager.findFirstVisibleItemPosition()
                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        isLoading = true
                        currentPage += 1
                        Log.d(TAG, "onScrolled: Load More $currentPage")
                        viewModel.getArticles(currentPage)
                        showProgressBar()
                        viewModel.articles!!.observe(this@MainActivity, Observer {
                            for (item in it) {
                                articles.add(item)
                                articlesAdapter.notifyDataSetChanged()
                                isLoading = false
                            }
                            hideProgressBar()
                            Log.d(TAG, "onScrolled: Load data ${articles.size}")
                        })
                    }
                }
            }
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun invoke(p1: Articles) {
        TODO("Not yet implemented")
    }
}
