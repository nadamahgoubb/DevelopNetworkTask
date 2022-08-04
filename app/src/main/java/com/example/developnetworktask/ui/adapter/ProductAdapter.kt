package com.example.developnetworktask.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.developnetworktask.data.entity.Product
import com.example.developnetworktask.databinding.RowProductBinding
import com.example.developnetworktask.databinding.RowProductStockOverFiftyBinding
import com.example.developnetworktask.ui.holder.ViewHolderStockLessThanFifty
import com.example.developnetworktask.ui.holder.ViewHolderStockOverFifty
import com.example.developnetworktask.ui.interfaces.ProductItemClickListener
import com.example.developnetworktask.util.ProductStockOverFifty.hasStockOverThanFifty
import com.example.developnetworktask.util.ProductStockOverFifty.hasStocklessthanFifty


class ProductAdapter constructor(
    val context: Context,
    private val onClick: ProductItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private var productsList: ArrayList<Product> = ArrayList()


    var hasStockOverFifty = 0//false

    fun getProductList(): List<Product?> {
        return productsList
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(ProductList: List<Product>) {
        productsList = ProductList as ArrayList<Product>
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val li = LayoutInflater.from(context)
        return if (viewType == hasStocklessthanFifty) {

            ViewHolderStockLessThanFifty(
                RowProductBinding.inflate(li), onClick
            )
        } else {

            ViewHolderStockOverFifty(
                RowProductStockOverFiftyBinding.inflate(li), onClick
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = productsList[position]
        if (getItemViewType(position) == hasStocklessthanFifty) {
            (holder as ViewHolderStockLessThanFifty).bind(productsList.get(position))
        } else {
            (holder as ViewHolderStockOverFifty).bind(productsList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (productsList[position].stock > 50 == true) hasStockOverFifty = hasStockOverThanFifty
        else hasStockOverFifty = hasStocklessthanFifty
        return hasStockOverFifty
    }


}
