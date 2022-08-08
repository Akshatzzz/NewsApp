package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false) //converts item_news.xml to  a view which we need to return
        val vieHolder=NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[vieHolder.adapterPosition])
        }
        return vieHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItems=items[position]
        holder.titleView.text=currentItems.title
        holder.author.text=currentItems.author
        Glide.with(holder.itemView.context).load(currentItems.imageUrl).into(holder.imageView)
    }

    fun updateNews(updatedItems: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedItems)

        notifyDataSetChanged()   //will call all the  adapter functions again with updated item
    }
}

class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
{
    val titleView: TextView=itemView.findViewById(R.id.title)
    val imageView:ImageView=itemView.findViewById(R.id.image)
    val author:TextView=itemView.findViewById(R.id.author)

}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}