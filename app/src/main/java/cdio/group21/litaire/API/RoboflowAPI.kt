package cdio.group21.litaire.API

import android.graphics.Bitmap
import android.os.Build
import android.util.Base64
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.io.ByteArrayOutputStream


interface RoboflowService {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @Multipart
    @POST("1?api_key=UeJ5YANadgDkeozKB2F4&name=image.jpg/")
    fun getPrediction(@Part imagePart : MultipartBody.Part ): Call<RoboflowResult?>?
}


class RoboflowAPI{
    companion object{




        public suspend fun getPrediction(image: Bitmap): RoboflowResult? {
             var retrofit = Retrofit.Builder()
                .baseUrl("https://detect.roboflow.com/playing-cards-ow27d/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(OkHttpClient())
                .build()
             var service: RoboflowService = retrofit.create(RoboflowService::class.java)

            val byteArray = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 70, byteArray)
            val byteArr = byteArray.toByteArray()
            val b64 = Base64.encodeToString(byteArr, Base64.URL_SAFE)

            print(b64)
            val imagePart = MultipartBody.Part.createFormData(
                "image",
                "image.jpg",
                RequestBody.create(
                    MediaType.parse("image/jpeg"),
                    b64
                )
            )

            val res = service.getPrediction(imagePart)?.execute()
            if(res?.errorBody() != null){
                val data = res.errorBody()!!.string();
                val jObjError = JSONObject(data)
                //Log.e("Roboflow", "Base64 image: ${b64}")
                //Log.e("Roboflow",  jObjError.getString("error"))
            }
            return res?.body()
        }
    }


}