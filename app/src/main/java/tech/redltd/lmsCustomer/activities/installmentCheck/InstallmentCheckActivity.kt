package tech.redltd.lmsCustomer.activities.installmentCheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.utils.setHomeToolbar

class InstallmentCheckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_installment_check)
        setHomeToolbar("Installment Check")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
