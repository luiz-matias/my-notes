package com.luizmatias.mynotes.ui.notes.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.luizmatias.mynotes.R
import com.luizmatias.mynotes.ui.notes.addnote.AddNoteActivity
import com.luizmatias.mynotes.ui.notes.editnote.EditNoteActivity
import com.luizmatias.mynotes.utils.LinearLayoutManagerUtil
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
        val linearLayoutManager = LinearLayoutManagerUtil(this)
        recyclerViewNotes.adapter = adapter
        recyclerViewNotes.layoutManager = linearLayoutManager

        recyclerViewNotes.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerViewNotes.canScrollVertically(-1)) {
                    //Show elevation
                    toolbar.elevation = 8f
                } else {
                    //Remove elevation
                    toolbar.elevation = 0f
                }
            }
        })

        adapter.onRemoveClickListener = {
            notesViewModel.removeNote(it)
        }

        adapter.onEditClickListener = {
            startActivity(Intent(this@NotesActivity, EditNoteActivity::class.java).putExtra("noteId", it.id))
        }

    }

    private fun initObservers() {
        notesViewModel.getNotes()?.observe(this, Observer {
            if (it.isNotEmpty()){
                recyclerViewNotes.visibility = View.VISIBLE
                constraintLayoutEmpty.visibility = View.GONE
                lottieAnimationView.pauseAnimation()

                adapter.updateItens(ArrayList(it))
            } else {
                recyclerViewNotes.visibility = View.GONE
                constraintLayoutEmpty.visibility = View.VISIBLE
                lottieAnimationView.playAnimation()
            }
        })
    }
}
