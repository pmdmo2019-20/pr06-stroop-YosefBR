package es.iessaladillo.pedrojoya.stroop.ui.aboutFragment


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.stroop.R

class AboutFragment : Fragment(R.layout.about_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_activity, menu)
        return super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.mnuHelp -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.help_title))
                    .setMessage(getString(R.string.dashboard_help_description))
                    .setPositiveButton(getString(R.string.main_ok)) { _, _ ->
                        //Se mantiene
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun setupViews() {
        setupAppBar()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.about_title)
        }
    }
}
