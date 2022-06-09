package cdio.group21.litaire.API

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.annotation.UiThread
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import io.matthewnelson.component.base64.Base64
import io.matthewnelson.component.base64.encodeBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.wait
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import uy.klutter.core.uri.UrlEncoding
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*


interface RoboflowService {
    // @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("1")
    fun getPrediction(
        @Query("api_key") apiKey: String,
        @Query("image") imageUrl: String
    ): Call<RoboflowResult?>?
}


class RoboflowAPI {
    companion object {

        val storage = Firebase.storage

        private fun toRequestBody(value: String): RequestBody {
            return value.toRequestBody("text/plain".toMediaTypeOrNull())
        }

        suspend fun getPrediction(context: Context, image: Bitmap): RoboflowResult? {
            // Init logging
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            // Init retrofit and service
            var retrofit = Retrofit.Builder()
                .baseUrl("https://detect.roboflow.com/playing-cards-ow27d/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()

            var service: RoboflowService = retrofit.create(RoboflowService::class.java)

            // Create a byte array from bitmap
            val byteArray = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 10, byteArray)
            val byteArr = byteArray.toByteArray()

            // Create a storage reference for the image using a random uuid. We keep the images on the cloud for data collection purposes.
            var storageRef = storage.reference
            var imagesRef: StorageReference? =
                storageRef.child(UUID.randomUUID().toString() + ".jpg")

            // Upload to firebase storage and get the url of the image
            var uploadTask = imagesRef?.putBytes(byteArr)?.await()
            val url = uploadTask?.storage?.downloadUrl?.await()

            // execute the request from the api
            val request =
                service.getPrediction(apiKey = "UeJ5YANadgDkeozKB2F4", imageUrl = url.toString())
            var res: Response<RoboflowResult?>?
            try {
                res = request?.execute()
            } catch (e: Exception) {
                // FAIL
                Log.e("Roboflow API: ", e.message ?: "")
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG)
                }
                return null
            }
            // SUCCESS
            println("Got detection successfully!: " + res?.body().toString())
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Got detection successfully!: " + res?.body().toString(),
                    Toast.LENGTH_LONG
                )
            }


            return res?.body()
        }
    }


}