package tech.redltd.lmsCustomer.activities.checkEligibility

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_check_eligibility.*
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductOnClick
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductRecyclerAdapter

class CheckEligibilityActivity : AppCompatActivity(),ProductOnClick {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_eligibility)
        toolbar = findViewById(R.id.toolbar)
        setToolbar()

        val text ="<font color=#27C0A2>Congratulation!</font> <font color=#bbbcbcbc>You can apply for Loan</font>"
        can_Apply_LoanTv.text = HtmlCompat.fromHtml(text,HtmlCompat.FROM_HTML_MODE_LEGACY)
            //product recycler
        val productRecyclerAdapter =    ProductRecyclerAdapter()
        productRecyclerAdapter.setProductOnClick(this)
        handsetRecycler.layoutManager =LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        handsetRecycler.adapter =    productRecyclerAdapter

    }


    private fun setToolbar(){
        try {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp)
        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onProductClick(id: Int) {
        Toasty.success(this,"$id").show()
    }
}
