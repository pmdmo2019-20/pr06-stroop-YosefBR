package es.iessaladillo.pedrojoya.stroop.ui.mainFragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
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

    lateinit var sharedPreferences: SharedPreferences

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
        sharedPreferences= requireContext().getSharedPreferences("app_pref", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("first","yes").equals("yes")){
            navController.navigate(R.id.assistantFragment)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("first", "no")
            editor.apply()
        }
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
