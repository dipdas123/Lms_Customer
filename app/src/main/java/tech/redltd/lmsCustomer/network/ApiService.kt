package tech.redltd.lmsCustomer.network

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import tech.redltd.lmsCustomer.activities.createAccount.CreateAccountBody
import tech.redltd.lmsCustomer.activities.createAccount.CreateAccountResponse
import tech.redltd.lmsCustomer.activities.loginActivity.LoginBody
import tech.redltd.lmsCustomer.activities.loginActivity.LoginResponse
import tech.redltd.lmsCustomer.activities.otpActivity.OtpBody
import tech.redltd.lmsCustomer.activities.otpActivity.OtpResponse
import tech.redltd.lmsCustomer.utils.CommonUrl

interface ApiService {

    @POST("api/auth/customerRegistration")
     fun customerRegistration(@Body createAccountBody: CreateAccountBody):Call<CreateAccountResponse>

    @POST("api/auth/customerLogin")
     fun customerLogin(@Body loginBody: LoginBody):Call<LoginResponse>

    @POST("api/auth/customerAuthOtpChecked")
    fun customerOtp(@Body otpBody: OtpBody):Call<OtpResponse>

    companion object{
        operator fun invoke():ApiService{
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = Level.BODY
            val okHttpClient:OkHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(AuthInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(CommonUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}