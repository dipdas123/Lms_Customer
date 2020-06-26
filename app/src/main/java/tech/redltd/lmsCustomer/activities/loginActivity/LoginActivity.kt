package tech.redltd.lmsCustomer.activities.loginActivity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.activities.otpActivity.OtpActivity
import tech.redltd.lmsCustomer.activities.createAccount.CreateAccountActivity
import tech.redltd.lmsCustomer.network.ApiService
import tech.redltd.lmsCustomer.utils.CommonConstant
import tech.redltd.lmsCustomer.utils.loadingDialog

class LoginActivity : AppCompatActivity() {
    lateinit var userPhone : EditText
    lateinit var userPassword :EditText
    lateinit var buttonLogin:Button
    private val apiService:ApiService by inject()
    lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userPhone = findViewById(R.id.userMobileNo)
        userPassword = findViewById(R.id.userPin)
        buttonLogin = findViewById(R.id.buttonLogin)
        dialog = loadingDialog()
        buttonLogin.setOnClickListener {
            val phoneNumber = "88"+userPhone.text.toString()
            val password = userPassword.text.toString()
            CommonConstant.phoneNumber=phoneNumber
            val loginBody = LoginBody(phoneNumber,password,"","")
            fetchLogin(loginBody)
            }


    }

    fun startCreateAccount(view: View) {
        startActivity(Intent(this,CreateAccountActivity::class.java))
    }

    private fun fetchLogin(loginBody: LoginBody){
        try {
            dialog.show()
             apiService.customerLogin(loginBody).enqueue(object : Callback<LoginResponse> {
                 override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.i("Login",t.message)
                     dialog.hide()
                 }

                 override fun onResponse(
                     call: Call<LoginResponse>,
                     response: Response<LoginResponse>
                 ) {
                     dialog.hide()
                     if (response.isSuccessful){
                         startActivity(Intent(this@LoginActivity,
                             OtpActivity::class.java))
                         finish()
                     }
                 }
             })

        }catch (ex:Exception){

        }
    }

}
