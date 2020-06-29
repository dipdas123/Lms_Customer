package tech.redltd.lmsCustomer.activities.profileActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.utils.setHomeToolbar

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setHomeToolbar("Profile")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
