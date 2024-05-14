package com.example.taskmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db :ToDosDatabaseHelper
    private lateinit var notesAdapter: ToDosAdapter
    private var originalNotes: List<Note> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= ToDosDatabaseHelper(this)
        notesAdapter=ToDosAdapter(db.getAllNotes(),this )

        binding.notesRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.notesRecyclerView.adapter=notesAdapter

        binding.addButton.setOnClickListener{
            val  intent = Intent(this,AddToDoActivity::class.java)
            startActivity(intent)
        }

        // Set up search functionality
        binding.searchView.setOnQueryTextListener(object :OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchNotes(it) }
                return true
            }
        })
        binding.searchView.setOnCloseListener {
            // Restore original notes when search is closed
            notesAdapter.refreshData(db.getAllNotes())
            false
        }
    }

    private fun searchNotes(query: String) {
        val filteredNotes = if (query.isNotEmpty()) {
            db.searchNotes(query)
        } else {
            db.getAllNotes()
        }
        notesAdapter.refreshData(filteredNotes)
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}