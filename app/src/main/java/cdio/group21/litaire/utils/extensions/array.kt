package cdio.group21.litaire.utils.extensions

import kotlinx.coroutines.*


suspend inline fun <T, reified R> Array<T>.pmap(
	dispatcher: CoroutineDispatcher = Dispatchers.Default,
	crossinline function: suspend (T) -> R
): Array<R> {
	return withContext(dispatcher) {
		map { async { function(it) } }.awaitAll().toTypedArray()
	}
}

suspend inline fun <T, reified R> Array<T>.pmapIndexed(
	dispatcher: CoroutineDispatcher = Dispatchers.Default,
	crossinline function: suspend (Int, T) -> R
): Array<R> {
	return withContext(dispatcher) {
		mapIndexed { index, value ->
			async { function(index, value) }
		}.awaitAll().toTypedArray()
	}
}

