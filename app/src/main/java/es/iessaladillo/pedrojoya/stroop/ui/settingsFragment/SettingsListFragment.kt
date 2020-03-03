package es.iessaladillo.pedrojoya.stroop.ui.settingsFragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import es.iessaladillo.pedrojoya.stroop.R

class SettingsListFragment : PreferenceFragmentCompat() {

    companion object {
        fun newInstance() = SettingsListFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}