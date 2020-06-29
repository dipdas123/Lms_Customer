package tech.redltd.lmsCustomer.activities.loanList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.utils.setHomeToolbar

class LoanListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_list)
        setHomeToolbar("Loan List")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
