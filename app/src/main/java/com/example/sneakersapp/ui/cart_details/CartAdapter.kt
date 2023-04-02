package com.example.sneakersapp.ui.cart_details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakersapp.R
import com.example.sneakersapp.databinding.ItemCartBinding
import com.example.sneakersapp.network.utils.SneakerFunction

class CartAdapter(
    var context: Context,
    private var dataList: ArrayList<CartItem>,
    private val cartFragment: CartFragment,
) : RecyclerView.Adapter<CartAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapterViewHolder {
        val binding = DataBindingUtil.inflate<ItemCartBinding>(
            LayoutInflater.from(context), R.layout.item_cart,
            parent, false
        )
        return CartAdapterViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
         return dataList.size
    }

    override fun onBindViewHolder(holder: CartAdapterViewHolder, position: Int) {
         with(holder.binding){

             Glide.with(context)
                 .load(dataList[holder.adapterPosition].imageUrl)
                 .placeholder(R.drawable.loading_image_placeholder)
                 .into(ivImage)

             tvName.text = dataList[holder.adapterPosition].name
             tvPrice.text = "₹" + dataList[holder.adapterPosition].price
             tvSize.text = "Size   : "+dataList[holder.adapterPosition].size
             tvColor.text = "Color : "+dataList[holder.adapterPosition].color

             ivDelete.setOnClickListener {
                 dataList.removeAt(holder.adapterPosition)
                 cartFragment.updateUI(dataList)
                 SneakerFunction.deleteFromCart(holder.adapterPosition)
                 Toast.makeText(context, "Removed from Cart", Toast.LENGTH_SHORT).show()
                 notifyItemRemoved(holder.adapterPosition)
             }

         }
    }

}

class CartAdapterViewHolder(var binding: ItemCartBinding) :
    RecyclerView.ViewHolder(binding.root)



