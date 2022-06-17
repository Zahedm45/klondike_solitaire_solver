package cdio.group21.litaire.utils

interface MutableMemoryList<E>: MutableList<E> {
	fun remembers(element: E): Boolean
	fun forget(element: E): Result<Boolean>
	fun memories(): Collection<E>
}