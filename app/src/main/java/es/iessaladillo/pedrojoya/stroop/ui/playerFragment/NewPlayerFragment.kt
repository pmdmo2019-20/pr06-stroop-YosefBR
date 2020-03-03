package es.iessaladillo.pedrojoya.stroop.ui.playerFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository
import es.iessaladillo.pedrojoya.stroop.room.entities.StroopDatabase
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.new_player_fragment.*

class NewPlayerFragment : Fragment(R.layout.new_player_fragment) {

    private val viewModel: NewPlayerFragmentViewModel by viewModels {
        NewPlayerFragmentViewModelFactory(
            LocalRepository(StroopDatabase.getInstance(requireContext()).playerDao),
            requireActivity().application
        )
    }

    private val listAdapter: NewPlayerFragmentAdapter = NewPlayerFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            val avatar:Int = it.getItem(position)
            logo.setImageDrawable(resources.getDrawable(avatar))
            viewModel.avatar = avatar
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_activity, menu)
        return super.onCreateOptionsMenu(menu, inflater)

    }

    private fun setupViews() {
        setupAppBar()
        setupRecyclerView()
        fabSave.setOnClickListener{
            addPlayer()
            navController.navigateUp()
        }
    }

    private fun addPlayer() {
        viewModel.addPlayer(txtPlayerName.text.toString())
    }

    private fun setupRecyclerView() {
        lstAvatar.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = listAdapter
        }
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_creation_title)
        }
    }

}
