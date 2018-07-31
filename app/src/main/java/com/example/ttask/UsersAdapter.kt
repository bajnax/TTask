package com.example.ttask

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.format.DateTimeFormat

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

            if(user.unreadMessages != 0)
                itemView.unread_messages.text = user.unreadMessages.toString()
            else {
                itemView.unread_messages.visibility = View.INVISIBLE
            }


            val sDateFormatter = DateTimeFormat.forPattern("DD.MM.yyyy HH:mm")
            val outputFormatter = DateTimeFormat.forPattern("HH:mm")
            val today = DateTime()

            val receivedDate = sDateFormatter.parseDateTime(user.lastSeen)

            when {
                Days.daysBetween(receivedDate, today).days == 0 -> itemView.last_seen.text = "Сегодня, " + outputFormatter.print(receivedDate)
                Days.daysBetween(receivedDate, today).days == 1 -> itemView.last_seen.text = "Вчера, " + outputFormatter.print(receivedDate)
                else -> itemView.last_seen.text = user.lastSeen
            }


            itemView.similarity.text = user.similarity.toString() + "%"
            when {
                user.similarity < 40 -> itemView.similarity.setTextColor(Color.RED)
                user.similarity < 70 -> itemView.similarity.setTextColor(Color.parseColor("#ffcc80"))
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