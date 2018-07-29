package com.example.ttask

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class UsersAdapter(private val users: List<User>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemV = LayoutInflater.from(p0?.context).inflate(R.layout.list_item, p0, false)
        return ViewHolder(itemV)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindViews(users[p1])
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindViews(user: User) {
            Picasso.get().load(user.avatar).into(itemView.avatar)  //use Picasso lib to download images
            itemView.name.text = user.name
            itemView.similarity.text = user.similarity.toString()
            itemView.status.text = user.status
            itemView.unread_messages.text = user.unreadMessages.toString()
        }
    }

}