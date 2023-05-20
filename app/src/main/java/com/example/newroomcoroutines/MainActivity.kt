package com.example.newroomcoroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newroomcoroutines.adapter.SubscriberRowAdapter
import com.example.newroomcoroutines.database.Subscriber
import com.example.newroomcoroutines.database.SubscriberDatabase
import com.example.newroomcoroutines.database.SubscriberRepository
import com.example.newroomcoroutines.databinding.ActivityMainBinding
import com.example.newroomcoroutines.viewmodel.SubscriberViewModel
import com.example.roomcoroutine.viewmodel.SubscriberViewModelFactory


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val subscriberDAO = SubscriberDatabase.getInstance(this).subscriberDAO
        val repository = SubscriberRepository(subscriberDAO)
        val factory = SubscriberViewModelFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]

        binding.subscriberVM = subscriberViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.subscriberView.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i(TAG, "displaySubscribersList: $it")
            binding.subscriberView.adapter = SubscriberRowAdapter(it, {selectedItem: Subscriber -> listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(subscriber: Subscriber) {
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

