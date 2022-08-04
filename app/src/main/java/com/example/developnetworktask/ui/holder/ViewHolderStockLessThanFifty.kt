package com.example.developnetworktask.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.developnetworktask.data.entity.Product
import com.example.developnetworktask.databinding.RowProductBinding
import com.example.developnetworktask.ui.interfaces.ProductItemClickListener

 class ViewHolderStockLessThanFifty(
    private val binding: RowProductBinding,
    private val mOnItemClickListener: ProductItemClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    private var mItemClickListener: ProductItemClickListener? = null

    fun bind(products: Product) {
        binding.txtTitle.text = products.title
        binding.txtDesc.text = products.description
        binding.txtPrice.text = products.price.toString() + "   LE"

        mItemClickListener = mOnItemClickListener
        binding.lytparentProudct.setOnClickListener(this@ViewHolderStockLessThanFifty)
    }

    override fun onClick(p0: View?) {
        p0?.let { mItemClickListener?.onItemClickListener(it, layoutPosition) }
    }

}