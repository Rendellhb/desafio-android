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
import com.picpay.desafio.android.view_model.ListUsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val listUserViewModel: ListUsersViewModel by viewModel()
    private lateinit var users: LiveData<List<User>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        users = listUserViewModel.users

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        users.observe(this, Observer<List<User>> { users ->
            if (users != null) {
                adapter.users = users
                progressBar.visibility = View.GONE
            } else {
                val message = getString(R.string.error)

                progressBar.visibility = View.GONE
                recyclerView.visibility = View.GONE

                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
