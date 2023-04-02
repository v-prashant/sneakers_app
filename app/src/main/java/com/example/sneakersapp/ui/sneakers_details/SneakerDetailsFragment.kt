package com.example.sneakersapp.ui.sneakers_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.sneakersapp.R
import com.example.sneakersapp.databinding.FragmentHomeBinding
import com.example.sneakersapp.databinding.FragmentSneakerDetailsBinding
import com.example.sneakersapp.network.utils.SneakerFunction
import com.example.sneakersapp.ui.cart_details.CartItem
import com.example.sneakersapp.ui.home.HomeVM
import com.example.sneakersapp.ui.home.SneakerItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.clp_toast_with_icon.view.*
import kotlinx.coroutines.channels.ProducerScope
import javax.inject.Inject

@AndroidEntryPoint
class SneakerDetailsFragment: Fragment() {

    private var navController: NavController? = null
    private lateinit var binding: FragmentSneakerDetailsBinding
    private var sneaker: SneakerItem? = null

    @Inject
    lateinit var vm: HomeVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSneakerDetailsBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.bundle = requireArguments()
        navController = Navigation.findNavController(view)
        initView()
        onClickListener()
    }

    private fun initView() {
        sneaker = vm.bundle.getSerializable("sneaker") as? SneakerItem

        with(binding){
            Glide.with(requireActivity())
                .load(sneaker?.imageUrl)
                .placeholder(R.drawable.loading_image_placeholder)
                .into(ivSneaker)

            tvTitle.text = sneaker?.name
            tvPrice.text = "Price: ₹" + sneaker?.price
        }

    }

    private fun onClickListener() {
        binding.btnAddToCartButton.setOnClickListener {

            val cart = CartItem(sneaker?.imageUrl, sneaker?.price, sneaker?.name, getChipSize(), getChipColor())
            SneakerFunction.addToCart(cart)

            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
            navController?.navigateUp()
        }
    }

    private fun getChipColor(): String {
        val chipGroup: ChipGroup = view?.findViewById(R.id.cg_size)!!
        return chipGroup.checkedChipId.let { checkedChipId ->
            val selectedChip: Chip? = view?.findViewById(checkedChipId)
            selectedChip?.chipBackgroundColor.toString()
        }
    }

    private fun getChipSize(): String {
        val chipGroup: ChipGroup = view?.findViewById(R.id.cg_size)!!
        return chipGroup.checkedChipId.let { checkedChipId ->
            val selectedChip: Chip? = view?.findViewById(checkedChipId)
            selectedChip?.text.toString()
        }
    }

}