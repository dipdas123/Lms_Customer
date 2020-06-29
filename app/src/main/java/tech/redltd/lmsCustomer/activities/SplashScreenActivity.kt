package tech.redltd.lmsCustomer.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.koin.android.ext.android.inject
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.activities.loginActivity.LoginActivity
import tech.redltd.lmsCustomer.utils.AppUtils
import tech.redltd.lmsCustomer.utils.CommonConstant

class SplashScreenActivity : AppCompatActivity() {
    private val appUtils : AppUtils by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_splash)
        Handler().postDelayed({
            val isLogin :String? = appUtils.getDataFromPreference(CommonConstant.TOKEN)
            if (isLogin!!.isNotEmpty()){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }

        },2000)
    }
}
