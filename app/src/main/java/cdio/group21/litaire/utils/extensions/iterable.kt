package cdio.group21.litaire.utils.extensions

import kotlinx.coroutines.*

suspend inline fun <T, reified R> Iterable<T>.pmap(
	dispatcher: CoroutineDispatcher = Dispatchers.Default,
	crossinline function: suspend (T) -> R): List<R> {
	return withContext(dispatcher) {
		map { async { function(it) } }.awaitAll()
	}
}



suspend inline fun <T, reified R> Iterable<T>.pmapIndexed(
	dispatcher: CoroutineDispatcher = Dispatchers.Default,
	crossinline function: suspend (Int, T) -> R): List<R> {
	return withContext(dispatcher) {
		mapIndexed { index, value ->
			async {function(index, value) }
		}.awaitAll()
	}
}