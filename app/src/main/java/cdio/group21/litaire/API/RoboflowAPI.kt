package cdio.group21.litaire.API

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*

class RoboflowAPI {
	companion object {

		@RequiresApi(Build.VERSION_CODES.O)
		@OptIn(ExperimentalStdlibApi::class)
		fun getPrediction(image: Bitmap): RoboflowResult? {
			var jsonString = ""
			val bytes = ByteArrayOutputStream()
			image.compress(Bitmap.CompressFormat.JPEG, 30, bytes)
			val base64 =
				String(Base64.getEncoder().encode(bytes.toByteArray()), StandardCharsets.US_ASCII)

			val API_KEY = "UeJ5YANadgDkeozKB2F4" // Your API Key
			val MODEL_ENDPOINT =
				"playing-cards-ow27d/1" // Set model endpoint (Found in Dataset URL)

			// Construct the URL
			val uploadURL =
				"https://detect.roboflow.com/" + MODEL_ENDPOINT + "?api_key=" + API_KEY + "&confidence=45"

			val moshi = Moshi.Builder().build()
			val adapter: JsonAdapter<RoboflowResult> = moshi.adapter<RoboflowResult>()


			// Http Request
			var connection: HttpURLConnection? = null
			try {
				// Configure connection to URL
				val url = URL(uploadURL)
				connection = url.openConnection() as HttpURLConnection
				connection.requestMethod = "POST"
				connection.setRequestProperty(
					"Content-Type",
					"application/x-www-form-urlencoded"
				)
				connection.setRequestProperty(
					"Content-Length",
					Integer.toString(base64.toByteArray().size)
				)
				connection.useCaches = false
				connection.doOutput = true

				//Send request
				val wr = DataOutputStream(
					connection.outputStream
				)
				wr.writeBytes(base64)
				wr.close()

				// Get Response
				val stream = connection.inputStream
				val reader = BufferedReader(InputStreamReader(stream))
				var line: String?

				while (reader.readLine().also { line = it } != null) {
					jsonString += line
				}
				reader.close()


			} catch (e: Exception) {
				e.printStackTrace()
			} finally {
				connection?.disconnect()
			}
			return adapter.fromJson(jsonString)
		}
	}


}