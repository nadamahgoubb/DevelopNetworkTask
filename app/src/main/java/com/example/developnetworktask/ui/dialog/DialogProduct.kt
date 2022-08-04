package com.example.developnetworktask.ui.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.developnetworktask.data.entity.Product
import com.example.developnetworktask.databinding.DialogProductBinding
import com.example.developnetworktask.util.Constants
import com.example.developnetworktask.utils.Extension.loadImage


class DialogProduct : DialogFragment() {

    private var binding: DialogProductBinding? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = DialogProductBinding.inflate(LayoutInflater.from(context))

        var productData = arguments?.getParcelable<Product>(Constants.PRODUCT_KEY)!!
        setData(productData)
        return AlertDialog.Builder(requireActivity())
            .setView(binding?.root)
            .create()
    }

    @SuppressLint("ResourceType")
    private fun setData(productData: Product) {
        binding?.apply {
            txtName?.text = productData.title
            txtDesc?.text = productData.description
            tvDiscountPercent?.text = productData.discountPercentage.toString() + " % "
            txtCategory?.text = productData.category
            txtPrice?.text = productData.price.toString() + " LE"
            txtRating?.text = "Rate:  " + productData.rating.toString()
            context?.let {
                imgProduct.loadImage(productData.images.get(0), it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}