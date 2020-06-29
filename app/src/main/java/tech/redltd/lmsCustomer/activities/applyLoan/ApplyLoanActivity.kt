package tech.redltd.lmsCustomer.activities.applyLoan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.utils.setHomeToolbar

class ApplyLoanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_loan)
        setHomeToolbar("Apply For Loan")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
