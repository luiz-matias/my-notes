package com.luizmatias.mynotes.ui.notes.addnote

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.luizmatias.mynotes.R
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    private val addNoteViewModel: AddNoteViewModel by lazy {
        ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        buttonCancel.setOnClickListener {
            onBackPressed()
        }

        buttonSave.setOnClickListener {
            addNoteViewModel.validateNote(textInputEditTextTitle.text.toString(), textInputEditTextDescription.text.toString())
        }

        initObservers()

    }

    private fun initObservers() {
        addNoteViewModel.addNoteStateHandler.observe(this, Observer {
            when (it) {
                is AddNoteStateHandler.setTitleError -> {
                    textInputLayoutTitle.error = getString(R.string.invalid_title)
                    textInputLayoutTitle.isErrorEnabled = it.error
                }
                is AddNoteStateHandler.setDescriptionError -> {
                    textInputLayoutDescription.error = getString(R.string.invalid_description)
                    textInputLayoutDescription.isErrorEnabled = it.error
                }
                is AddNoteStateHandler.noteAdded -> {
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
