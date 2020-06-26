package tech.redltd.lmsCustomer.adapter.productAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.redltd.lmsCustomer.R

class ProductRecyclerAdapter : RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {
    lateinit var mContext:Context
    private lateinit var productOnClick: ProductOnClick

    fun setProductOnClick(productOnClick: ProductOnClick){
        this.productOnClick = productOnClick
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view:View = LayoutInflater.from(mContext).inflate(R.layout.product_list_item,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return 40
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            productOnClick.onProductClick(1)
        }

    }
}