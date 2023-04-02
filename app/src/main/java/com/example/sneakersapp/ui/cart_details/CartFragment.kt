package com.example.sneakersapp.ui.cart_details

import android.os.Bundle
import android.view.*
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
            vm.cartList = items
        }
        updateUI(vm.cartList)
    }

    private fun onClickListener() {
            binding?.btnCheckOut?.setOnClickListener {
                vm.cartList.clear()
                SneakerFunction.checkOut()
                Toast.makeText(requireContext(), "Check Out Successfully", Toast.LENGTH_SHORT).show()
                navController?.navigate(R.id.homeFragment)
            }
    }

    fun updateUI(dataList: ArrayList<CartItem>) {
        this.vm.cartList = dataList
        with(binding!!){
            if(dataList.isEmpty()){
                tvOrder.visibility = View.GONE
                tvSubtotal.visibility = View.GONE
                tvTaxes.visibility = View.GONE
                tvTotalAmount.visibility = View.GONE
                btnCheckOut.visibility = View.GONE
                tvEmptyMsg.visibility = View.VISIBLE
            }else{
                tvOrder.visibility = View.VISIBLE
                tvSubtotal.visibility = View.VISIBLE
                tvTaxes.visibility = View.VISIBLE
                tvTotalAmount.visibility = View.VISIBLE
                btnCheckOut.visibility = View.VISIBLE
                tvEmptyMsg.visibility = View.GONE
            }

            if(dataList.isNotEmpty()){
                val subTotal = dataList.sumOf {
                    it.price ?: 0
                }
                val taxes = 0.18*subTotal
                rvSneakers.adapter = CartAdapter(requireActivity(), dataList, this@CartFragment)
                tvSubtotal.text = "Subtotal : ₹"+subTotal.toString()
                tvTaxes.text = "Taxes and Charges : ₹"+taxes.toInt().toString()
                tvTotalAmount.text = "Total : ₹"+(subTotal+taxes).toInt().toString()
            }

        }
    }

}