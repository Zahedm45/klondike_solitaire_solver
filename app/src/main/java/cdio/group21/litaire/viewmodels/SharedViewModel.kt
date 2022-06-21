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


class SharedViewModel : ViewModel() {
	private val suggestion = MutableLiveData<Pair<Card, Card>>()
	private val moves = MutableLiveData<MutableList<Move>>(mutableListOf()) // Used by the app

	private val previewBitmap = MutableLiveData<Bitmap>()

	private val detectionList = MutableLiveData<List<DetectionResult>>(emptyList())

	private val gameState = MutableLiveData(Solitaire.EMPTY_GAME)

	private var cardObjectToReveal: Card? = null

	private var ai: Ai = Ai()
	private var lastMoves: insaneMoveMemory = HashMap() // Used by the solver

	fun setPreviewBitmap(bitmap: Bitmap) {
		previewBitmap.value = bitmap
	}

	fun getPreviewBitmap(): LiveData<Bitmap> {
		return previewBitmap
	}

	fun getMoves(): MutableLiveData<MutableList<Move>> {
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
			cardObjectToReveal = null
			gameState.value = ObjectRecognition.initGame(list)
			runSolver()
			return Result.success(Unit)
		}

		if (list.size != 1 || gameState.value == Solitaire.EMPTY_GAME) {
			return Result.failure(
				IllegalArgumentException("Error: Inappropriate number of cards: " + list.size)
			)
		}

		val card = cardObjectToReveal
			?: return Result.failure(IllegalStateException("Error: cardObjectToReveal not set!"))
		cardObjectToReveal = null
		replaceCardInGame(card, list[0].card)

		runSolver()
		return Result.success(Unit)
	}

	fun setCardObjectToReveal(cardObject: Card?) {
		Log.i("SharedViewModel", "Set cardObjectToReveal: $cardObject")
		cardObjectToReveal = cardObject
	}

	fun runSolver() {
		Log.i("SharedViewModel", "Run solver")
		val gameState_ = gameState.value ?: return
		if (cardObjectToReveal != null) {
			Log.e("SharedViewModel", "Error: cardObjectToReveal not null!")
			return // Todo: report
		}

		val game = Game.fromSolitaire(gameState_, lastMoves)
		val move = ai.findBestMove(game) ?: return // TODO: Handle this?
		if (Game.move_(game, move))
			lastMoves = game.lastMoves
		setCardObjectToReveal(gameState_.performMove(move).getOrThrow())
		gameState.postValue(gameState_)
		val mοves = moves.value ?: return
		mοves.add(move)
		moves.postValue(mοves)
	}


}

