package com.luizmatias.mynotes.ui.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.luizmatias.mynotes.R
import kotlinx.android.synthetic.main.activity_notes.*
import java.util.*

class NotesActivity : AppCompatActivity() {

    private val notesViewModel: NotesViewModel by lazy {
        ViewModelProviders.of(this).get(NotesViewModel::class.java)
    }

    private val adapter: NotesAdapter by lazy {
        NotesAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        initRecyclerView()
        initObservers()

        fabNewNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = adapter
        recyclerViewNotes.layoutManager = linearLayoutManager
    }

    private fun initObservers() {
        notesViewModel.getNotes()?.observe(this, Observer {
            adapter.replaceItens(ArrayList(it))
        })
    }
}
