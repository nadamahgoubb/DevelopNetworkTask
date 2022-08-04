package com.example.developnetworktask.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.developnetworktask.data.entity.Product
import com.example.developnetworktask.databinding.RowProductStockOverFiftyBinding
import com.example.developnetworktask.ui.interfaces.ProductItemClickListener

 class ViewHolderStockOverFifty(
    private val binding: RowProductStockOverFiftyBinding,
    private val mOnItemClickListener: ProductItemClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    private var mItemClickListener: ProductItemClickListener? = null

    fun bind(products: Product) {
        binding.txtTitle.text = products.title
        binding.txtDesc.text = products.description
        binding.txtPrice.text = products.price.toString() + "   LE"

        mItemClickListener = mOnItemClickListener
        binding.lytparentProudct.setOnClickListener(this@ViewHolderStockOverFifty)
    }

    override fun onClick(p0: View?) {
        p0?.let { mItemClickListener?.onItemClickListener(it, layoutPosition) }
    }

}