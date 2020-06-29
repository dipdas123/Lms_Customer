package tech.redltd.lmsCustomer.activities.checkEligibility

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_check_eligibility.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductOnClick
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductRecyclerAdapter
import tech.redltd.lmsCustomer.dialog.AgreementDialog
import tech.redltd.lmsCustomer.network.AspService
import tech.redltd.lmsCustomer.network.aspDto.LoanQuery
import tech.redltd.lmsCustomer.network.aspDto.LoanQueryRequest
import tech.redltd.lmsCustomer.network.aspDto.LoanQueryResponse
import tech.redltd.lmsCustomer.utils.AppUtils
import tech.redltd.lmsCustomer.utils.CommonConstant
import tech.redltd.lmsCustomer.utils.loadingDialog
import tech.redltd.lmsCustomer.utils.setHomeToolbar

class CheckEligibilityActivity : AppCompatActivity(),ProductOnClick {

    private val aspService : AspService by inject()
    private val appUtils : AppUtils by inject()

    //6 month Text view
    lateinit var month6installment : TextView
    lateinit var month6Amount : TextView
    lateinit var month6downpayment : TextView

    //12 month text view
    lateinit var month12installment : TextView
    lateinit var month12Amount : TextView
    lateinit var month12downpayment : TextView

    lateinit var dialog: AlertDialog

    lateinit var productRecyclerAdapter: ProductRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_eligibility)
        setHomeToolbar("Check Eligibility")
        dialog = loadingDialog()
        init()
        val text ="<font color=#27C0A2>Congratulation!</font> <font color=#bbbcbcbc>You can apply for Loan</font>"
        can_Apply_LoanTv.text = HtmlCompat.fromHtml(text,HtmlCompat.FROM_HTML_MODE_LEGACY)
            //product recycler
        productRecyclerAdapter = ProductRecyclerAdapter()
        productRecyclerAdapter.setProductOnClick(this)
        handsetRecycler.layoutManager =LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        handsetRecycler.adapter = productRecyclerAdapter

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onProductClick(sku: String) {
        Toasty.success(this,"$sku").show()
    }

    private fun init(){
        //6 month
        month6installment = findViewById(R.id.txtinstallment6)
        month6Amount = findViewById(R.id.txtamount6)
        month6downpayment = findViewById(R.id.txtdownpayment6)

        //12 month

        month12installment = findViewById(R.id.txtinstallment12)
        month12Amount = findViewById(R.id.txtamount12)
        month12downpayment = findViewById(R.id.txtdownpayment12)
    }

    override fun onResume() {
        super.onResume()
        checkLoanQuery()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }

    private fun checkLoanQuery(){
        dialog.show()
        val agentId : String = appUtils.getDataFromPreference(CommonConstant.AGENT_ID)!!
        val customer_msisdn : String = appUtils.getDataFromPreference(CommonConstant.CUSTOMER_MSISDN)!!
        val agent_pass :String = appUtils.getDataFromPreference(CommonConstant.AGENT_PASS)!!
        val loanQueryRequest = LoanQueryRequest(agentId.toInt(),customer_msisdn,agent_pass)
        aspService.loanQuery(loanQueryRequest).enqueue(object : Callback<LoanQueryResponse>  {
            override fun onFailure(call: Call<LoanQueryResponse>, t: Throwable) {
                dialog.hide()
                Log.i("onFailure",""+t.message)

            }

            override fun onResponse(call: Call<LoanQueryResponse>,response: Response<LoanQueryResponse>) {
                dialog.hide()
                if (response.isSuccessful){
                    try{
                        val loanQueryResponse:LoanQueryResponse = response.body()!!
                        val loanQuery:LoanQuery = loanQueryResponse.loanQuery
                        // 6 month
                        month6installment.text = loanQuery.month6Installment
                        month6Amount.text = loanQuery.month6
                        month6downpayment.text = loanQuery.month6DownPayment

                        // 12 month
                        month12installment.text = loanQuery.month12Installment
                        month12Amount.text = loanQuery.month12
                        month12downpayment.text = loanQuery.month12DownPayment

                    }catch (ex:Exception){

                    }

                }

            }

        })
    }

    fun applyForLoanOnClick(view: View) {
        view.requestFocus()
        val fragmentManager : FragmentManager = supportFragmentManager
        val agreementDialog = AgreementDialog()
        agreementDialog.show(fragmentManager,"hello world")
    }
}
