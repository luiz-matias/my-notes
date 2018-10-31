package com.luizmatias.mynotes.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.luizmatias.mynotes.R
import com.luizmatias.mynotes.ui.notes.notes.NotesActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        textViewTitle.visibility = View.VISIBLE

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, NotesActivity::class.java))
            finish()
        }, 1000)

    }
}
