package cdio.group21.litaire.utils

import android.os.Parcel
import android.os.Parcelable

class ArrayHashmapMemoryList<E>() : MutableMemoryList<E>, ArrayList<E>() {
	private val map = HashMap<Int, E>()

	override fun add(element: E): Boolean {
		map.put(element.hashCode(), element)
		return super.add(element)
	}

	override fun remembers(element: E): Boolean {
		return map.containsKey(element.hashCode())
	}

	override fun forget(element: E): Result<Boolean> {
		if (super.contains(element)) {
			return Result.failure(IllegalArgumentException("Error: Element cannot be forgotten as it is still in the list!"));
		}
		return Result.success(map.remove(element.hashCode()) != null)
	}

	override fun memories(): Collection<E> {
		return map.values
	}

}