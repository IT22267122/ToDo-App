 package com.example.taskmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagement.databinding.ActivityAddToDoBinding
import com.example.taskmanagement.databinding.ActivityMainBinding

 class AddToDoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddToDoBinding
    private  lateinit var db:ToDosDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ToDosDatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val title=binding.titleEditText.text.toString()
            val content =binding.contentEditText.text.toString()
            val todo = ToDo(0,title,content)
            db.insertToDo(todo)
            finish()
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()
        }
    }
}