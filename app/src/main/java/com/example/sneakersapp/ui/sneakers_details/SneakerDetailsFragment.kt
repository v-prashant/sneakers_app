package com.example.sneakersapp.ui.sneakers_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sneakersapp.R
import com.example.sneakersapp.databinding.FragmentSneakerDetailsBinding
import com.example.sneakersapp.network.utils.SneakerFunction
import com.example.sneakersapp.ui.cart_details.CartItem
import com.example.sneakersapp.ui.home.HomeVM
import com.example.sneakersapp.ui.home.SneakerItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SneakerDetailsFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSneakerDetailsBinding

    @Inject
    lateinit var vm: HomeVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSneakerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.bundle = requireArguments()
        initView()
        onClickListener()
    }

    private fun initView() {
        vm.sneaker = vm.bundle.getSerializable("sneaker") as? SneakerItem

        with(binding){
            Glide.with(requireActivity())
                .load(vm.sneaker?.imageUrl)
                .placeholder(R.drawable.loading_image_placeholder)
                .into(ivSneaker)

            tvTitle.text = vm.sneaker?.name
            tvPrice.text = "Price : ₹" + vm.sneaker?.price
        }

    }

    private fun onClickListener() {
        binding.btnAddToCart.setOnClickListener {

            val cart = CartItem(vm.sneaker?.imageUrl, vm.sneaker?.price, vm.sneaker?.name, getChipSize(), getChipColor())
            SneakerFunction.addToCart(cart)

            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun getChipColor(): String {
        return if(binding.chipColor1.isChecked)
            "RED"
        else if(binding.chipColor2.isChecked)
            "YELLOW"
        else
            "LIGHT GREY"
    }

    private fun getChipSize(): String {
        val chipGroup: ChipGroup = view?.findViewById(R.id.cg_size)!!
        return chipGroup.checkedChipId.let { checkedChipId ->
            val selectedChip: Chip? = view?.findViewById(checkedChipId)
            selectedChip?.text.toString()
        }
    }

}