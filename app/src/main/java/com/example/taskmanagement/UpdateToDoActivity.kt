package com.example.taskmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagement.databinding.ActivityUpdateTodoBinding

class UpdateToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateTodoBinding
    private lateinit var  db:ToDosDatabaseHelper
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ToDosDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }
        //methanin thama eya bracket ekak vahanne
        val note = db.getNoteById(noteId)
        binding.updateTitleEdit.setText(note.title)
        binding.UpdateContentEdit.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEdit.text.toString()
            val newContent = binding.UpdateContentEdit.text.toString()
            val updatedNote = Note(noteId, newTitle,newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saved" , Toast.LENGTH_SHORT).show()
        }

    }
}

