package cdio.group21.litaire.viewmodels


import Card
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.Solitaire
import cdio.group21.litaire.tflite.DetectionConfig
import cdio.group21.litaire.tflite.ObjectRecognition
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.insaneMoveMemory
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap


class SharedViewModel : ViewModel() {
	private val suggestion = MutableLiveData<Pair<Card, Card>>()
	private val moves = MutableLiveData<MutableList<Move?>>(mutableListOf()) // Used by the app

	private val previewBitmap = MutableLiveData<Bitmap>()

	private val detectionList = MutableLiveData<List<DetectionResult>>(emptyList())

	private val gameState = MutableLiveData(Solitaire.EMPTY_GAME)
	private var priorGameStates: Stack<Solitaire> = Stack()

	private var ai: Ai = Ai()
	private var lastMoves: insaneMoveMemory = HashMap() // Used by the solver

	fun setPreviewBitmap(bitmap: Bitmap) {
		previewBitmap.value = bitmap
	}

	fun getPreviewBitmap(): LiveData<Bitmap> {
		return previewBitmap
	}

	fun getMoves(): MutableLiveData<MutableList<Move?>> {
		return moves
	}

	fun getDetectionList(): LiveData<List<DetectionResult>> {
		return detectionList
	}

	fun getGameState(): MutableLiveData<Solitaire> {
		return gameState
	}

	fun getSuggestion(): LiveData<Pair<Card, Card>> {
		return suggestion
	}

	@RequiresApi(Build.VERSION_CODES.O)
	fun processImage(bitmap: Bitmap) {
		viewModelScope.launch(IO) {
			Log.i("SharedViewModel", "Process Image")
			detectionList.postValue(
				ObjectRecognition.processImage(
					bitmap,
					DetectionConfig(2u, 2u, 0.15F)
				)
			)

		}
	}

	fun replaceCardInGame(from: Card, to: Card) {
		Log.i("SharedViewModel", "Replacing card")
		gameState.value?.replaceCardObject(from, to)
		gameState.postValue(gameState.value)
	}

	var lastList: List<DetectionResult>? = null
	fun updateGame(list: List<DetectionResult>): Result<Unit> {
		if (list == lastList) {
			return Result.success(Unit)
		}
		lastList = list

		Log.i("SharedViewModel", "Update Game: $list")
		Log.i("SharedViewModel", "Update Game: List size: " + list.size)

		if (list.isEmpty()) return Result.failure(Exception("No detection results found!"))

		if (list.size == 7) {
			lastMoves.clear()
			moves.value?.clear()
			priorGameStates.clear()
			gameState.value = ObjectRecognition.initGame(list)
			return runSolver()
		}

		if (list.size != 1 || gameState.value == Solitaire.EMPTY_GAME) {
			return Result.failure(
				IllegalArgumentException("Error: Inappropriate number of cards: " + list.size)
			)
		}

		val gameState = gameState.value ?: return Result.failure(IllegalStateException("Error: gameState not set!"))
		priorGameStates.add(gameState.copy())
		gameState.revealCard(list[0].card).getOrElse { return Result.failure(it) }

		return runSolver()
	}


	fun undoSolverRun(){
		if (priorGameStates.isEmpty()) return
		Log.i("SharedViewModel", "Undo solver run")
		lastMoves.clear()
		moves.value?.clear()
		moves.postValue(moves.value)
		val oldState = priorGameStates.pop()
		gameState.postValue(oldState)
	}

	fun runSolver(): Result<Unit> {
		Log.i("SharedViewModel", "Run solver")
		val gameState_ = gameState.value ?: return Result.failure(IllegalStateException("Error: gameState not set!"))
		val movesList = moves.value ?: return Result.failure(IllegalStateException("Error: moves not set!"))

		movesList.clear()

		var safetyLimit = 100
		do {
			Log.i("SharedViewModel", "Safety limit: $safetyLimit")
			val game = Game.fromSolitaire(gameState_, lastMoves)
			val move = ai.findBestMove(game, 500u)
			val revealedCard = gameState_.performMove(move).getOrElse { return Result.failure(it) }
			gameState.postValue(gameState_)
			movesList.add(move)
			moves.postValue(movesList)
			if (move == null) {
				lastMoves.clear()
			}
			else if (Game.move_(game, move))
				lastMoves = game.lastMoves
			safetyLimit--
		} while(revealedCard == null && !gameState_.isWon() && safetyLimit > 0)

		return Result.success(Unit)
	}


}

