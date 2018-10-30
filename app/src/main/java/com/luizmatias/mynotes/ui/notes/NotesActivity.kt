package com.luizmatias.mynotes.ui.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.luizmatias.mynotes.R

class NotesActivity : AppCompatActivity() {

    private val notesViewModel: NotesViewModel by lazy {
        ViewModelProviders.of(this).get(NotesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        initObservers()

    }

    private fun initObservers() {
        notesViewModel.getNotes().observe(this, Observer {

        })
    }

    override fun onResume() {
        super.onResume()
        notesViewModel.getNotes()
    }
}
