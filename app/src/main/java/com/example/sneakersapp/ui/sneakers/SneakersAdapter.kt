package com.example.sneakersapp.ui.sneakers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakersapp.R
import com.example.sneakersapp.databinding.ItemSneakersBinding
import com.example.sneakersapp.ui.home.SneakerItem
import com.example.sneakersapp.ui.sneakers_details.SneakerDetailsFragment

class SneakersAdapter(
    var context: Context,
    var dataList: ArrayList<SneakerItem>,
    val navController: NavController?,
    val requireActivity: FragmentActivity
) : RecyclerView.Adapter<SneakersAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SneakersAdapterViewHolder {
        val binding = DataBindingUtil.inflate<ItemSneakersBinding>(
            LayoutInflater.from(context), R.layout.item_sneakers,
            parent, false
        )
        return SneakersAdapterViewHolder(binding)
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

    override fun onBindViewHolder(holder: SneakersAdapterViewHolder, position: Int) {
         with(holder.binding){

             Glide.with(context)
                 .load(dataList[holder.adapterPosition].imageUrl)
                 .placeholder(R.drawable.loading_image_placeholder)
                 .into(ivImage)

             tvName.text = dataList[holder.adapterPosition].name
             tvPrice.text = "₹ " + dataList[holder.adapterPosition].price

             root.setOnClickListener {
                  val bundle = bundleOf("sneaker" to dataList[holder.adapterPosition])
                  val dialog = SneakerDetailsFragment()
                  dialog.arguments = bundle
                  dialog.show(requireActivity.supportFragmentManager, "SneakerDetailsBottomSheetFragment")
             }

         }
    }

    fun updateDataList(list: ArrayList<SneakerItem>){
        this.dataList = list
    }

}

class SneakersAdapterViewHolder(var binding: ItemSneakersBinding) :
    RecyclerView.ViewHolder(binding.root)



