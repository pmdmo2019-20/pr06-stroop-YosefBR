package es.iessaladillo.pedrojoya.stroop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupAppBar()
    }

    private fun setupAppBar() {
        setSupportActionBar(toolbar)
    }
}
