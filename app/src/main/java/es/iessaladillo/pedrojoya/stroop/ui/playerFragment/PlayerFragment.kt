package es.iessaladillo.pedrojoya.stroop.ui.playerFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.invisibleUnless
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository
import es.iessaladillo.pedrojoya.stroop.room.entities.Player
import es.iessaladillo.pedrojoya.stroop.room.entities.StroopDatabase
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.new_player_fragment.*
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.android.synthetic.main.player_fragment.logo

class PlayerFragment : Fragment(R.layout.player_fragment) {

    private val viewModel: PlayerFragmentViewModel by viewModels {
        PlayerFragmentViewModelFactory(
            LocalRepository(StroopDatabase.getInstance(requireContext()).playerDao),
            requireActivity().application
        )
    }

    private val listAdapter: PlayerFragmentAdapter = PlayerFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            val player: Player = it.getItem(position)
            logo.setImageDrawable(resources.getDrawable(player.avatar))
            lblActualPlayer.text = player.name
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
        setupRecyclerView()
        observePlayers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_activity, menu)
        return super.onCreateOptionsMenu(menu, inflater)

    }

    private fun setupViews() {
        setupAppBar()
        lblEmptyView.setOnClickListener{
            navigateToNewPlayer()
        }
        floating_action_button.setOnClickListener {
            navigateToNewPlayer()
        }
    }

    private fun navigateToNewPlayer() {
        navController.navigate(R.id.newPlayerFragment)
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_selection_title)
        }
    }

    private fun setupRecyclerView() {
        lstPlayer.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = listAdapter
        }
    }

    private fun observePlayers() {
        viewModel.players.observe(this) {
            showPlayers(it)
        }
    }

    private fun showPlayers(newList: List<Player>) {
        lstPlayer.post {
            listAdapter.submitList(newList)
            lblEmptyView.invisibleUnless(newList.isEmpty())
        }
    }
}
