package es.iessaladillo.pedrojoya.stroop.ui.rankingFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.invisibleUnless
import es.iessaladillo.pedrojoya.stroop.room.entities.Game
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository
import es.iessaladillo.pedrojoya.stroop.room.entities.Player
import es.iessaladillo.pedrojoya.stroop.room.entities.StroopDatabase
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModel
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModelFactory
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.android.synthetic.main.ranking_fragment.*
import kotlinx.android.synthetic.main.ranking_fragment.lblEmptyView

class RankingFragment : Fragment(R.layout.ranking_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(StroopDatabase.getInstance(requireContext()).playerDao, StroopDatabase.getInstance(requireContext()).gameDao),
            requireActivity().application
        )
    }

    private val listAdapter: RankingFragmentAdapter = RankingFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            val game: Game = it.getItem(position)
            var player = sharedViewModel.players.value!!.find { x -> x.id == game.playerID }


            sharedViewModel.correct = game.correct
            sharedViewModel.points = game.points

            navController.navigate(R.id.statsFragment)


        }
    }

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeGames()
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
        setupRecyclerView()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.ranking_title)
        }
    }

    private fun setupRecyclerView() {
        lstRanking.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    private fun observeGames() {
        sharedViewModel.games.observe(this) {
            showGame(it)
        }
        sharedViewModel.players.observe(this) {
            savePlayer(it)
        }
    }

    private fun showGame(newList: List<Game>) {
        lstRanking.post {
            listAdapter.submitList(newList)
            lblEmptyView.invisibleUnless(newList.isEmpty())
        }
    }

    private fun savePlayer(newList: List<Player>) {
        lstRanking.post {
            listAdapter.submitPlayerList(newList)
            lblEmptyView.invisibleUnless(newList.isEmpty())
        }
    }
}
