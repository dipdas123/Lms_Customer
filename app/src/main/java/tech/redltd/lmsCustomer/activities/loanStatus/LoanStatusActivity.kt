package tech.redltd.lmsCustomer.activities.loanStatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.utils.setHomeToolbar

class LoanStatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_status)
        setHomeToolbar("Loan Status")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
