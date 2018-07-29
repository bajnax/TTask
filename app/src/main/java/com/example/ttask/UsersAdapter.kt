package com.example.ttask

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class UsersAdapter(private val users: List<User>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemV = LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)
        return ViewHolder(itemV)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindViews(users[p1])
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindViews(user: User) {

            Picasso.get().load(user.avatar).into(itemView.avatar)

            itemView.name.text = user.name
            itemView.age.text = user.age.toString() + " лет, "
            itemView.unread_messages.text = user.unreadMessages.toString()

            // TODO: modify according to requirements
            itemView.last_seen.text = user.lastSeen

            itemView.similarity.text = user.similarity.toString() + "%"
            when {
                user.similarity < 40 -> itemView.similarity.setTextColor(Color.RED)
                user.similarity < 70 -> itemView.similarity.setTextColor(Color.YELLOW)
                else -> itemView.similarity.setTextColor(Color.GREEN)
            }

            when {
                user.status == "dnd" -> itemView.status.setColorFilter(Color.RED)
                user.status == "away" -> itemView.status.setColorFilter(Color.YELLOW)
                else -> itemView.status.setColorFilter(Color.GREEN)
            }

        }
    }

}