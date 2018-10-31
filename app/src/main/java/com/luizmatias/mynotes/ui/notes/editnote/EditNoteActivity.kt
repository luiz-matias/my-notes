package com.luizmatias.mynotes.ui.notes.editnote

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.luizmatias.mynotes.R
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {

    private val editNoteViewModel: EditNoteViewModel by lazy {
        ViewModelProviders.of(this).get(EditNoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        buttonCancel.setOnClickListener {
            onBackPressed()
        }

        buttonSave.setOnClickListener {
            editNoteViewModel.validateNote(
                textInputEditTextTitle.text.toString(),
                textInputEditTextDescription.text.toString()
            )
        }

        initObservers()

    }

    private fun initObservers() {
        val noteId: Int = intent.getIntExtra("noteId", -1)

        editNoteViewModel.getNote(noteId)?.observe(this, Observer {
            textInputEditTextTitle.setText(it.title)
            textInputEditTextDescription.setText(it.description)
        })

        editNoteViewModel.editNoteStateHandler.observe(this, Observer {
            when (it) {
                is EditNoteStateHandler.setTitleError -> {
                    textInputLayoutTitle.error = getString(R.string.invalid_title)
                    textInputLayoutTitle.isErrorEnabled = it.error
                }
                is EditNoteStateHandler.setDescriptionError -> {
                    textInputLayoutDescription.error = getString(R.string.invalid_description)
                    textInputLayoutDescription.isErrorEnabled = it.error
                }
                is EditNoteStateHandler.noteEdited -> {
                    Toast.makeText(this, getString(R.string.note_saved), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }
}
