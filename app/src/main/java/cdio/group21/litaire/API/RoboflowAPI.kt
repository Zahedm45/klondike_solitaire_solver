package cdio.group21.litaire.API

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import io.matthewnelson.component.base64.Base64
import io.matthewnelson.component.base64.encodeBase64
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import uy.klutter.core.uri.UrlEncoding
import java.io.ByteArrayOutputStream
import java.io.File


interface RoboflowService {
    // @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("1?api_key=UeJ5YANadgDkeozKB2F4&name=image.jpg/")
    fun getPrediction(@Body body: String): Call<RoboflowResult?>?
}


class RoboflowAPI {
    companion object {

        val storage = Firebase.storage

        private fun toRequestBody(value: String): RequestBody {
            return value.toRequestBody("text/plain".toMediaTypeOrNull())
        }

        fun getPrediction(context: Context, image: Bitmap): RoboflowResult? {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
// add your other interceptors …
// add logging as last interceptor
// add your other interceptors …
// add logging as last interceptor
            httpClient.addInterceptor(logging)
            var retrofit = Retrofit.Builder()
                .baseUrl("https://detect.roboflow.com/playing-cards-ow27d/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()
            var service: RoboflowService = retrofit.create(RoboflowService::class.java)

            val byteArray = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 0, byteArray)
            val byteArr = byteArray.toByteArray()
            val base64: Base64 = Base64.UrlSafe(pad = true)
            var b64 = byteArr.encodeBase64(base64 = base64)


            val path = context.getExternalFilesDir(null)
            val letDirectory = File(path, "CDIO 21")
            letDirectory.mkdirs()
            val file = File(letDirectory, "Image64.txt")
            b64 = UrlEncoding.encodeQueryNameOrValue(b64)
            file.writeText(b64)

            val map: MutableMap<String, RequestBody> = mutableMapOf()
            map.put("image", toRequestBody(b64));

            val request = service.getPrediction(b64)
            val res = request?.execute()
            if (res?.errorBody() != null) {
                val data = res.errorBody()!!.string();
                val jObjError = JSONObject(data)
                Log.e("Roboflow", jObjError.getString("error"))
                Log.e("Roboflow", res.message())
                Log.e("Roboflow", res.raw().request.body?.contentType().toString())

            }
            println(res?.body().toString())
            return res?.body()
        }
    }


}