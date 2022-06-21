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

	var _cardObjectToReveal: Card? = null

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
			_cardObjectToReveal = null
			gameState.value = ObjectRecognition.initGame(list)
			runSolver()
			return Result.success(Unit)
		}

		if (list.size != 1 || gameState.value == Solitaire.EMPTY_GAME) {
			return Result.failure(
				IllegalArgumentException("Error: Inappropriate number of cards: " + list.size)
			)
		}

		val card = _cardObjectToReveal
			?: return Result.failure(IllegalStateException("Error: cardObjectToReveal not set!"))
		_cardObjectToReveal = null
		replaceCardInGame(card, list[0].card)

		runSolver()
		return Result.success(Unit)
	}

	fun setCardObjectToReveal(cardObject: Card?) {
		Log.i("SharedViewModel", "Set cardObjectToReveal: $cardObject")
		_cardObjectToReveal = cardObject
	}

	fun undoSolverRun(){
		Log.i("SharedViewModel", "Undo solver run")
		if (priorGameStates.isEmpty()) return
		lastMoves.clear()
		moves.value?.clear()
		moves.postValue(moves.value)
		gameState.value = priorGameStates.pop()
		gameState.postValue(gameState.value)
	}

	fun runSolver() {
		Log.i("SharedViewModel", "Run solver")
		val gameState_ = gameState.value ?: return
		priorGameStates.add(gameState_)

		val movesList = moves.value ?: return
		movesList.clear()
		if (_cardObjectToReveal != null) {
			Log.e("SharedViewModel", "Error: cardObjectToReveal not null!")
			return // Todo: report
		}

		var safetyLimit = 100

		do {
			Log.i("SharedViewModel", "Safety limit: $safetyLimit")
			val game = Game.fromSolitaire(gameState_, lastMoves)
			val move = ai.findBestMove(game, 500u)
			val revealedCard = gameState_.performMove(move).getOrThrow()
			setCardObjectToReveal(revealedCard)
			gameState.postValue(gameState_)
			movesList.add(move)
			moves.postValue(movesList)
			if (move == null) {
				//lastMoves.clear()
			}
			else if (Game.move_(game, move))
				lastMoves = game.lastMoves
			safetyLimit--
		} while(revealedCard == null && !gameState_.isWon() && safetyLimit > 0)

	}


}

