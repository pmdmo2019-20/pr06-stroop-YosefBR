package es.iessaladillo.pedrojoya.stroop.ui.settingsFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.stroop.R

class ShowSettingsWithToolbar : Fragment(R.layout.settings_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null){
            showInitialDestination()
        }
        setHasOptionsMenu(true)
    }
    private fun showInitialDestination() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcSettingsList, SettingsListFragment.newInstance())
            .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_activity, menu)
        return super.onCreateOptionsMenu(menu, inflater)

    }

    private fun setupViews() {
        setupAppBar()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.settings_title)
        }
    }
}