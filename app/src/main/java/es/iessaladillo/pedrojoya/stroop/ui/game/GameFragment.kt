package es.iessaladillo.pedrojoya.stroop.ui.game


import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository
import es.iessaladillo.pedrojoya.stroop.room.entities.StroopDatabase
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModel
import es.iessaladillo.pedrojoya.stroop.ui.SharedViewModelFactory
import kotlinx.android.synthetic.main.game_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

class GameFragment : Fragment(R.layout.game_fragment) {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    private val viewModel: GameViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(StroopDatabase.getInstance(requireContext()).playerDao, StroopDatabase.getInstance(requireContext()).gameDao),
            requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkGameMode()
        observeProgress()
        setupViews()
        startGame()
        observeWord()
        observeCorrectAnswers()
        observeGameTime()
    }

    private fun checkGameMode() {
        if (settings.getString(getText(R.string.prefGameMode_key).toString(),"Time") == "Time") {
            viewModel.gamemode = "Time"
            lblAttempsOrPoints.text = "Points"
            observePoints()
        }
        else {
            viewModel.gamemode = "Attemps"
            lblAttempsOrPoints.text = "Attemps"
            viewModel.attempts = MutableLiveData(settings.getString(getText(R.string.prefAttempts_key).toString(),"5")!!.toInt())
            observeAttemps()
        }
    }

    private fun setupViews() {
        setupAppBar()
        viewModel.max = settings.getString(getText(R.string.prefGameTime_key).toString(),"60000")!!.toInt()
        progressBar.max = viewModel.max
        rightAnswer.setOnClickListener{
            checkRight()
        }
        wrongAnswer.setOnClickListener{
            checkWrong()
        }
    }

    private fun checkWrong() {
        viewModel.checkWrong()
    }

    private fun checkRight() {
        viewModel.checkRight()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }
    }

    private fun observeProgress() {
        viewModel.progress.observe(this){
            progressBar.progress = it
        }
    }

    private fun observeWord() {
        viewModel.txt.observe(this){
            lblActualWord.text = it
        }
        viewModel.actualColor.observe(this) {
            lblActualWord.setTextColor(it)
        }
        viewModel.words.observe(this) {
            lblWordsNumber.text = it.toString()
        }
    }

    private fun observePoints() {
        viewModel.points.observe(this) {
            lblAttempsOrPointsNumber.text = it.toString()
        }
    }

    private fun observeCorrectAnswers() {
        viewModel.correct.observe(this) {
            lblCorrectNumber.text = it.toString()
        }
    }

    private fun observeAttemps() {
        viewModel.attempts.observe(this) {
            lblAttempsOrPointsNumber.text = it.toString()
            if (it == 0){
                viewModel.onGameTimeFinish()

                sharedViewModel.correct = viewModel.correct.value!!
                sharedViewModel.words = viewModel.words.value!!
                sharedViewModel.points = viewModel.points.value!!
                sharedViewModel.minutes = settings.getString(getText(R.string.prefGameTime_key).toString(),"60000")!!.toInt()
                sharedViewModel.gameMode = viewModel.gamemode

                sharedViewModel.addGame()

                navController.navigate(R.id.fromStatsToDashboard)
            }
        }
    }

    private fun observeGameTime() {
        viewModel.currentGameTime.observe(this) {
            if (it == 0) {

                viewModel.onGameTimeFinish()
                sharedViewModel.correct = viewModel.correct.value!!
                sharedViewModel.words = viewModel.words.value!!
                sharedViewModel.points = viewModel.points.value!!
                sharedViewModel.gameMode = viewModel.gamemode
                sharedViewModel.minutes = settings.getString(getText(R.string.prefGameTime_key).toString(),"60000")!!.toInt()

                sharedViewModel.addGame()

                navController.navigate(R.id.fromStatsToDashboard)
            }
        }
    }

    private fun startGame() {
        viewModel.startGameThread(settings.getString(getText(R.string.prefGameTime_key).toString(),"60000")!!.toInt(),
                                    settings.getString(getText(R.string.prefWordTime_key).toString(),"60000")!!.toInt())
    }

}
