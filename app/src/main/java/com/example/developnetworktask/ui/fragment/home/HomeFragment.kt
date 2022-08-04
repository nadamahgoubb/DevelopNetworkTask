package com.example.developnetworktask.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.developnetworktask.R
import com.example.developnetworktask.data.DataStoreManger
import com.example.developnetworktask.databinding.FragmentHomeBinding
import com.example.developnetworktask.ui.activity.login.LoginActivity
import com.example.developnetworktask.ui.adapter.ProductAdapter
import com.example.developnetworktask.ui.interfaces.ProductItemClickListener
import com.example.developnetworktask.util.Constants
import com.example.developnetworktask.utils.Extension.hideProgressBar
import com.example.developnetworktask.utils.Extension.init
import com.example.developnetworktask.utils.Extension.showProgressBar
import com.example.developnetworktask.utils.Extension.withFragment
import com.example.developnetworktask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ProductItemClickListener {


    private lateinit var binding: FragmentHomeBinding
    private val homeVm: HomeViewModel by viewModels()
    private lateinit var adapter: ProductAdapter
    var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setVm()
        return binding.root
    }

    private fun setVm() {
        with(binding) {
            withFragment(this)
            activity?.let { homeVm.setActivity(it) }
        }
        initViews()
        getData();
    }

    private fun getData() {

        homeVm.getProducts()
        homeVm._productsList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar(binding.progressBar)
                    isLoading = false
                    response.data?.let { newsResponse ->
                        adapter.setProductList(response.data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar(binding.progressBar)
                    isLoading = false

                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar(binding.progressBar)
                    isLoading = true
                }
            }
        })
    }

    private fun initViews() {
        adapter = activity?.let { ProductAdapter(it, this) }!!
        binding.RecHomeBooks.init(requireContext(), adapter)
        binding.logout.imageView.setImageDrawable(resources.getDrawable(R.drawable.logout))

        binding.logout.imageView.setOnClickListener {

            lifecycleScope.launch {
                DataStoreManger().saveBoolean(Constants.IS_LOGGED, false)
            }
            var intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()}
    }

    override fun onItemClickListener(view: View, itemId: Int) {
        showDialog(itemId)
    }

    fun showDialog(itemId: Int) {
        var product = adapter.getProductList()[itemId]!!
        val bundle = bundleOf(Constants.PRODUCT_KEY to product)
        findNavController().navigate(R.id.action_homeFragment_to_dialogProduct, bundle)
    }
}