package es.iessaladillo.pedrojoya.stroop.ui.statsFragment


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository
import es.iessaladillo.pedrojoya.stroop.room.entities.StroopDatabase
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModel
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModelFactory
import kotlinx.android.synthetic.main.stats_fragment.*

class StatsFragment : Fragment(R.layout.stats_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(StroopDatabase.getInstance(requireContext()).playerDao, StroopDatabase.getInstance(requireContext()).gameDao),
            requireActivity().application
        )
    }

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
        sharedViewModel.showPlayerAvatar.let { logo.setImageResource(it) }
        lblActualPlayer.text = sharedViewModel.showPlayerName
        lblCorrectAnswers.text = sharedViewModel.correct.toString()
        lblPoints.text = sharedViewModel.points.toString()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.game_result_title)
        }
    }
}
