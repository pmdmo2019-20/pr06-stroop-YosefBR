package es.iessaladillo.pedrojoya.stroop.ui.mainFragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

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
            navigateToGame()
        }
        setupAppBar()
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
