package es.iessaladillo.pedrojoya.stroop.ui.game

import android.graphics.Color
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread


class GameViewModel : ViewModel() {
    @Volatile
    private var isGameFinished: Boolean = false
    @Volatile
    private var currentWordMillis: Int = 0
    @Volatile
    private var millisUntilFinished: Int = 0
    private val handler: Handler = Handler()

    var gamemode: String = "Time"
    var correct: MutableLiveData<Int> = MutableLiveData(0)

    var max: Int = 0

    //Establece el valor maximo de la progressBar que ira disminuyendo
    var progress: MutableLiveData<Int> = MutableLiveData(max)

    var attempts: MutableLiveData<Int> = MutableLiveData(5)
    var words: MutableLiveData<Int> = MutableLiveData(0)
    var points: MutableLiveData<Int> = MutableLiveData(0)

    private val text: List<String> = listOf("Red", "Green", "Blue", "Yellow")
    private val colors: List<Int> = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

    var actualColor: MutableLiveData<Int> = MutableLiveData(colors.shuffled()[0])
    var txt: MutableLiveData<String> = MutableLiveData(text.shuffled()[0])

    private fun onGameTimeTick(millisUntilFinished: Int) {
        progress.value = millisUntilFinished

    }

    fun onGameTimeFinish() {
        isGameFinished = true

    }

    private fun nextWord() {
        words.value = words.value!!.plus(1)
        txt.value = text.shuffled()[0]
        actualColor.value = colors.shuffled()[0]
    }

    fun checkRight() {
        currentWordMillis = 0
        if (gamemode == "Time") {
            when (txt.value) {
                "Green" -> {
                    if (actualColor.value == Color.GREEN) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
                "Yellow" -> {
                    if (actualColor.value == Color.YELLOW) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
                "Blue" -> {
                    if (actualColor.value == Color.BLUE) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
                "Red" -> {
                    if (actualColor.value == Color.RED) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
            }
        } else {
            when (txt.value) {
                "Green" -> {
                    if (actualColor.value == Color.GREEN) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
                "Yellow" -> {
                    if (actualColor.value == Color.YELLOW) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
                "Blue" -> {
                    if (actualColor.value == Color.BLUE) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
                "Red" -> {
                    if (actualColor.value == Color.RED) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
            }
        }
    }

    fun checkWrong() {
        currentWordMillis = 0
        if (gamemode == "Time") {
            when (txt.value) {
                "Green" -> {
                    if (actualColor.value != Color.GREEN) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
                "Yellow" -> {
                    if (actualColor.value != Color.YELLOW) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
                "Blue" -> {
                    if (actualColor.value != Color.BLUE) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
                "Red" -> {
                    if (actualColor.value != Color.RED) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        nextWord()
                    }
                }
            }
        } else {
            when (txt.value) {
                "Green" -> {
                    if (actualColor.value != Color.GREEN) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
                "Yellow" -> {
                    if (actualColor.value != Color.YELLOW) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
                "Blue" -> {
                    if (actualColor.value != Color.BLUE) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
                "Red" -> {
                    if (actualColor.value != Color.RED) {
                        points.value = points.value!!.plus(10)
                        correct.value = correct.value!!.plus(1)
                        nextWord()
                    } else {
                        attempts.value = attempts.value!!.minus(1)
                        nextWord()
                    }
                }
            }
        }
    }

    fun startGameThread(gameTime: Int, wordTime: Int) {
        millisUntilFinished = gameTime
        currentWordMillis = 0
        isGameFinished = false
        val checkTimeMillis: Int = wordTime / 2
        thread {
            try {
                while (!isGameFinished) {
                    Thread.sleep(checkTimeMillis.toLong())
                    // Check and publish on main thread.
                    handler.post {
                        if (!isGameFinished) {
                            if (currentWordMillis >= wordTime) {
                                currentWordMillis = 0
                                nextWord()
                            }
                            currentWordMillis += checkTimeMillis
                            millisUntilFinished -= checkTimeMillis
                            onGameTimeTick(millisUntilFinished)
                            if (millisUntilFinished <= 0) {
                                onGameTimeFinish()
                            }
                        }
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        isGameFinished = true
    }
}

