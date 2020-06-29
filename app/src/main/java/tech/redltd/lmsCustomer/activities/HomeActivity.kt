package tech.redltd.lmsCustomer.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.activities.checkEligibility.CheckEligibilityActivity
import tech.redltd.lmsCustomer.activities.installmentCheck.InstallmentCheckActivity
import tech.redltd.lmsCustomer.activities.loanStatus.LoanStatusActivity
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductOnClick
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductRecyclerAdapter
import tech.redltd.lmsCustomer.network.ApiService
import tech.redltd.lmsCustomer.network.lmsDto.RobiShopPorductRequest
import tech.redltd.lmsCustomer.network.lmsDto.RobiShopProductResponse
import tech.redltd.lmsCustomer.utils.errorToast
import tech.redltd.lmsCustomer.utils.loadingDialog

class HomeActivity : AppCompatActivity(),ProductOnClick {
    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    private lateinit var mDrawerToggle:ActionBarDrawerToggle
    lateinit var handsetRecycler:RecyclerView
    private val apiService:ApiService by inject()
    lateinit var dialog: AlertDialog
    private lateinit var productRecyclerAdapter:ProductRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        dialog = loadingDialog()
        mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        //recycler view
        productRecyclerAdapter = ProductRecyclerAdapter()
        productRecyclerAdapter.setProductOnClick(this)
        handsetRecycler = findViewById(R.id.handsetRecycler)
        handsetRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        handsetRecycler.adapter = productRecyclerAdapter

        getProducts()
        updateProfileParent.setOnClickListener {
            startActivity(Intent(this,UpdateProfileActivity::class.java))
        }
    }

    fun checkEligibility(view: View) {
        startActivity(Intent(this,CheckEligibilityActivity::class.java))
    }
    fun loadStatus(view: View) {
        startActivity(Intent(this,LoanStatusActivity::class.java))
    }
    fun payInstallment(view: View) {
        startActivity(Intent(this,InstallmentCheckActivity::class.java))
    }

    override fun onProductClick(sku: String) {
        Toasty.success(this,"$sku").show()
    }

    override fun onResume() {
        super.onResume()

    }


    private fun getProducts(){
        dialog.show()
        val robiShopProductRequest = RobiShopPorductRequest("50000","10")
        apiService.robiShopProducts(robiShopProductRequest).enqueue(object :Callback<RobiShopProductResponse> {
            override fun onFailure(call: Call<RobiShopProductResponse>, t: Throwable) {
                errorToast("error fetch api")
                dialog.hide()
            }

            override fun onResponse(call: Call<RobiShopProductResponse>,response: Response<RobiShopProductResponse>) {
                dialog.hide()
                if (response.isSuccessful){
                  val robiShopProductResponse: RobiShopProductResponse = response.body()!!
                  val products = robiShopProductResponse.payload.products
                  productRecyclerAdapter.setProducts(products)
                  productRecyclerAdapter.notifyDataSetChanged()
              }
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }

}
