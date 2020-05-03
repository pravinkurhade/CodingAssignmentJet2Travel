package com.bsktech.codingassignmentjet2travel.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bsktech.codingassignmentjet2travel.R
import com.bsktech.codingassignmentjet2travel.models.Articles
import com.bsktech.codingassignmentjet2travel.utils.AppUtils
import com.bsktech.codingassignmentjet2travel.utils.GlideApp
import com.bumptech.glide.request.RequestOptions
import java.util.*

internal class ArticlesAdapter(
    private val articles: LinkedList<Articles>,
    private val clickListener: (Articles) -> Unit
) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesFieldViewHolder>() {

    internal class ArticlesFieldViewHolder private constructor(view: View) :
        RecyclerView.ViewHolder(view) {

        private val userName: TextView = view.findViewById(R.id.textView_user_name)
        private val useDesignation: TextView = view.findViewById(R.id.textView_user_designation)
        private val articleContent: TextView = view.findViewById(R.id.textView_article_content)
        private val articleTitle: TextView = view.findViewById(R.id.textView_article_title)
        private val articleURL: TextView = view.findViewById(R.id.textView_article_url)
        private val articleLikes: TextView = view.findViewById(R.id.textView_likes)
        private val articleComments: TextView = view.findViewById(R.id.textView_comments)
        private val time: TextView = view.findViewById(R.id.textView_time)
        private val articleImage: ImageView = view.findViewById(R.id.imageView_article)
        private val userImage: ImageView = view.findViewById(R.id.imageView_user)

        fun bindArticleField(
            article: Articles,
            clickListener: (Articles) -> Unit
        ) {
            userName.text = article.user[0].name + " " + article.user[0].lastname
            useDesignation.text = article.user[0].designation
            articleContent.text = article.content
            time.text = AppUtils.getStringTime(time.context, article.createdAt)
            articleLikes.text =
                AppUtils.getStringCount(article.likes) + " " + articleLikes.context.getString(R.string.likes)
            articleComments.text =
                AppUtils.getStringCount(article.comments) + " " + articleComments.context.getString(
                    R.string.comments
                )

            GlideApp.with(userImage.context)
                .load(article.user[0].avatar)
                .apply(RequestOptions().circleCrop())
                .into(userImage)

            if (article.media.isNotEmpty()) {
                articleTitle.text = article.media[0].title
                articleURL.text = article.media[0].url
                GlideApp.with(articleImage.context)
                    .load(article.media[0].image)
                    .into(articleImage)
                articleTitle.visibility = View.VISIBLE
                articleURL.visibility = View.VISIBLE
                articleImage.visibility = View.VISIBLE
            } else {
                articleTitle.visibility = View.GONE
                articleURL.visibility = View.GONE
                articleImage.visibility = View.GONE
            }

            itemView.setOnClickListener {
                clickListener.invoke(article)
            }

        }

        companion object {
            fun create(parent: ViewGroup): ArticlesFieldViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_article_item, parent, false)
                return ArticlesFieldViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesFieldViewHolder =
        ArticlesFieldViewHolder.create(parent)

    override fun onBindViewHolder(holder: ArticlesFieldViewHolder, position: Int) =
        holder.bindArticleField(articles[position], clickListener)

    override fun getItemCount(): Int = articles.size
}
