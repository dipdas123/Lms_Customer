package tech.redltd.lmsCustomer.activities.otpActivity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.mukesh.OtpView
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.activities.HomeActivity
import tech.redltd.lmsCustomer.activities.loginActivity.LoginActivity
import tech.redltd.lmsCustomer.network.ApiService
import tech.redltd.lmsCustomer.utils.AppUtils
import tech.redltd.lmsCustomer.utils.CommonConstant
import tech.redltd.lmsCustomer.utils.loadingDialog
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class OtpActivity : AppCompatActivity() {
    lateinit var otpView: OtpView
    lateinit var verifyOtpBtn:Button
    var otpString:String = ""
    lateinit var dialog: AlertDialog
    private val apiService:ApiService by inject()
    private val appUtils :AppUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        verifyOtpBtn = findViewById(R.id.verifyOtpBtn)
        verifyOtpBtn.isClickable = false
        otpView = findViewById(R.id.otp_view)
        dialog = loadingDialog()
        otpView.setOtpCompletionListener {
            otpString = it
            verifyOtpBtn.isClickable = true
            postOtp(it)

        }
    }

    fun verifyOtp(view: View) {
        //postOtp(otpString)
        startActivity(Intent(this@OtpActivity,HomeActivity::class.java))
        finish()
    }
    //startActivity(Intent(this,HomeActivity::class.java))
    private fun postOtp(otp:String){
        dialog.show()
        val otpBody = OtpBody(CommonConstant.phoneNumber,otp,null)
      apiService.customerOtp(otpBody).enqueue(object : Callback<OtpResponse>{
          override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
              dialog.hide()
          }

          override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {
              dialog.hide()
              try {
                  if (response.isSuccessful){
                      val otpResponse: OtpResponse? = response.body()
                      if (otpResponse != null){
                          if (otpResponse.payload != null){
                              val token:String = otpResponse.payload.token
                              val userInfo: UserInfo = otpResponse.payload.userInfo
                              appUtils.saveDataIntoPreference(CommonConstant.TOKEN,token)
                              appUtils.saveDataIntoPreference(CommonConstant.AGENT_ID,userInfo.agentId)
                              startActivity(Intent(this@OtpActivity,HomeActivity::class.java))
                              finish()
                          }else{
                              startActivity(Intent(this@OtpActivity,LoginActivity::class.java))
                              finish()
                          }

                      }


                  }
              }catch (ex:Exception){

              }

          }
      })


    }

    override fun onDestroy() {
        super.onDestroy()

    }


}
