package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.ui.UserListAdapter
import com.picpay.desafio.android.view_model.ListUsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val listUserViewModel: ListUsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        listUserViewModel.users.observe(this, Observer<List<User>> { users ->
            adapter.users = users
            progressBar.visibility = View.GONE
        })
    }

    override fun onResume() {
        super.onResume()
        listUserViewModel.componentsVisibility.observe(this, Observer<Int> {
            progressBar.visibility = it
            recyclerView.visibility = it
            val message = getString(R.string.error)

            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        })
    }
}
