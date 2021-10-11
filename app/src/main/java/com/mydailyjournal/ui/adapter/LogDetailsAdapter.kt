package com.mydailyjournal.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydailyjournal.R
import com.mydailyjournal.database.LogDetails
import kotlinx.android.synthetic.main.item_journal_view.view.*


class LogDetailsAdapter: RecyclerView.Adapter<LogDetailsAdapter.MyViewHolder>() {
    private var userList = emptyList<LogDetails>()

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_journal_view, parent, false))
    }

    override fun getItemCount(): Int {
       return userList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.log_title.text = currentItem.log_title
        holder.itemView.logged_date.text = currentItem.log_added_date
        holder.itemView.logged_by.text = currentItem.log_added_by
        holder.itemView.journal_content.text = currentItem.log_description
    }

    fun setData(user: List<LogDetails>){
        this.userList = user
        notifyDataSetChanged()
    }
}