package es.iessaladillo.pedrojoya.stroop.ui.mainFragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository
import es.iessaladillo.pedrojoya.stroop.room.entities.StroopDatabase
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModel
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModelFactory
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(StroopDatabase.getInstance(requireContext()).playerDao, StroopDatabase.getInstance(requireContext()).gameDao),
            requireActivity().application
        )
    }

    private val navController: NavController by lazy {
        findNavController(navHostFragment)
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
        aboutCard.setOnClickListener{
            navigateToAbout()
        }
        assistantCard.setOnClickListener{
            navigateToAssistant()
        }
        settingsCard.setOnClickListener{
            navigateToSettings()
        }
        playerCard.setOnClickListener{
            navigateToPlayer()
        }
        playCard.setOnClickListener{
            if (sharedViewModel.actualPlayer?.value == null) {
                navigateToPlayer()
            }
            else {
                navigateToGame()
            }
        }
        rankingCard.setOnClickListener {
            navigateToRanking()
        }

        if (sharedViewModel.actualPlayer?.value == null) {
            lblActualPlayer.text = getString(R.string.player_selection_no_player_selected)
        }else {
            sharedViewModel.actualPlayer?.value?.avatar?.let { logo.setImageResource(it) }
            lblActualPlayer.text = sharedViewModel.actualPlayer?.value?.name
        }

        setupAppBar()

    }

    private fun navigateToRanking() {
        navController.navigate(R.id.rankingFragment)
    }

    private fun navigateToAssistant() {
        navController.navigate(R.id.assistantFragment)
    }

    private fun navigateToAbout() {
        navController.navigate(R.id.aboutFragment)
    }

    private fun navigateToSettings() {
        navController.navigate(R.id.showSettingsWithToolbar)
    }

    private fun navigateToPlayer() {
        navController.navigate(R.id.playerFragment)
    }

    private fun navigateToGame() {
        navController.navigate(R.id.gameFragment)
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.dashboard_title)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}
