package com.example.newroomcoroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newroomcoroutines.R
import com.example.newroomcoroutines.database.Subscriber
import com.example.newroomcoroutines.databinding.SubscriberRowBinding


class SubscriberRowAdapter(private val subscribers: List<Subscriber>,
                           private val clickListener: (Subscriber) -> Unit
): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SubscriberRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.subscriber_row, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(subscribers[position], clickListener)
    }
}

class ViewHolder(val binding: SubscriberRowBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.nameText.text = subscriber.name
        binding.emailText.text = subscriber.email
        binding.subscriberView.setOnClickListener {
            clickListener(subscriber)
        }
    }
}
