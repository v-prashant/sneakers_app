package com.example.sneakersapp.ui.cart_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sneakersapp.R
import com.example.sneakersapp.databinding.FragmentCartBinding
import com.example.sneakersapp.network.utils.SneakerFunction
import com.example.sneakersapp.ui.home.HomeVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment: Fragment() {

    private var navController: NavController? = null
    private var binding: FragmentCartBinding? = null
    private lateinit var dataList: ArrayList<CartItem>

    @Inject
    lateinit var vm: HomeVM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        vm.getCartList()
        initObserver()
        onClickListener()
    }

    private fun initObserver() {
         vm.getCartLiveData.observe(requireActivity()){
             onGetCartResponse(it.data?.items)
         }
    }

    override fun onResume() {
        super.onResume()
        vm.getCartList()
    }

    private fun onGetCartResponse(items: ArrayList<CartItem>?) {
        if (items != null) {
            dataList = items
        }

        var subTotal = 0.0
        if(dataList.isEmpty()){
            subTotal = items!!.sumOf {
                it.price?.toDouble() ?: 0.0
            }
            val taxes = 0.18*subTotal
            binding?.rvSneakers?.adapter = CartAdapter(requireActivity(), dataList)
            binding?.tvSubtotal?.text = "subtotal : ₹"+subTotal.toString()
            binding?.tvTaxes?.text = "Taxes and Charges : ₹"+taxes.toString()
            binding?.tvTotalAmount?.text = "Total : ₹"+(subTotal+taxes).toString()
        }
    }

    private fun onClickListener() {
            binding?.btnAddToCartButton?.setOnClickListener {
                dataList.clear()
                SneakerFunction.checkOut()
                Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_SHORT).show()
                navController?.navigate(R.id.homeFragment)
            }
    }

}