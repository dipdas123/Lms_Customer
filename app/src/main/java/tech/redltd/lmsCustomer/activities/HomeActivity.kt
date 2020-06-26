package tech.redltd.lmsCustomer.activities

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
import tech.redltd.lmsCustomer.R
import tech.redltd.lmsCustomer.activities.checkEligibility.CheckEligibilityActivity
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductOnClick
import tech.redltd.lmsCustomer.adapter.productAdapter.ProductRecyclerAdapter

class HomeActivity : AppCompatActivity(),ProductOnClick {
    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    private lateinit var mDrawerToggle:ActionBarDrawerToggle
    lateinit var handsetRecycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        //recycler view
        val productRecyclerAdapter = ProductRecyclerAdapter()
        productRecyclerAdapter.setProductOnClick(this)
        handsetRecycler = findViewById(R.id.handsetRecycler)
        handsetRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        handsetRecycler.adapter = productRecyclerAdapter
    }

    fun checkEligibility(view: View) {
        startActivity(Intent(this,CheckEligibilityActivity::class.java))
    }
    fun loadStatus(view: View) {
        Toasty.success(this,"loadStatus").show()
    }
    fun payInstallment(view: View) {
        Toasty.success(this,"payInstallment").show()
    }

    override fun onProductClick(id: Int) {
        Toasty.success(this,"$id").show()
    }


}
