package com.example.sneakersapp.network.utils

import com.example.sneakersapp.network.response.GetCartResponse
import com.example.sneakersapp.network.response.GetSneakerResponse
import com.example.sneakersapp.ui.cart_details.CartItem
import com.example.sneakersapp.ui.home.SneakerItem
import java.text.FieldPosition

object SneakerFunction {

    private val imageUrls = ArrayList<String?>()
    private val sneakerList = ArrayList<SneakerItem>()
    private val cartList = ArrayList<CartItem>()

    init {
        imageUrls.add("https://media.istockphoto.com/id/1425880214/photo/leather-mens-shoe-on-white-background.jpg?s=1024x1024&w=is&k=20&c=A181QXjB5X_Ny2ARjWO7QU6Q4Y1TxInrVX9WbodzBqE=")
        imageUrls.add("https://media.istockphoto.com/id/1308274455/photo/blue-sneakers-isolated-on-white-background.jpg?s=1024x1024&w=is&k=20&c=6Qb7x4ACdRZl16TzR_j8bnGDZIEfitY6Zo2R7b1ZTx0=")
        imageUrls.add("https://media.istockphoto.com/id/1436061606/photo/flying-colorful-womens-sneaker-isolated-on-white-background-fashionable-stylish-sports-shoe.jpg?s=1024x1024&w=is&k=20&c=anQJwG2c4-ZEqf9BgeIm3ph76JZWSU2-GbOE7b_OzcA=")
        imageUrls.add("https://media.istockphoto.com/id/1337191336/photo/black-fashion-sport-shoe-on-white-background.jpg?s=1024x1024&w=is&k=20&c=dIJOo4ladW8wVx04pv5PmCxxHljsct3hJxRDNcmuIOQ=")
        imageUrls.add("https://media.istockphoto.com/id/1324847242/photo/pair-of-white-leather-trainers-on-white-background.jpg?s=1024x1024&w=is&k=20&c=paCekjw8iHTIKD4jpPXPdZY60gtOgbXV3pO9k1OTASo=")

        sneakerList.add(SneakerItem(imageUrls[0], 200, "Nike Air"))
        sneakerList.add(SneakerItem(imageUrls[1], 300, "Adidas Air"))
        sneakerList.add(SneakerItem(imageUrls[2], 100, "Flats Pro"))
        sneakerList.add(SneakerItem(imageUrls[3], 500, "High Heels"))
        sneakerList.add(SneakerItem(imageUrls[4], 300, "Flip Flops"))
        sneakerList.add(SneakerItem(imageUrls[0], 200, "Nike Air"))
        sneakerList.add(SneakerItem(imageUrls[1], 500, "Uggs Air"))
        sneakerList.add(SneakerItem(imageUrls[2], 100, "Adidas Pro"))
        sneakerList.add(SneakerItem(imageUrls[3], 100, "Cone Heels"))
        sneakerList.add(SneakerItem(imageUrls[4], 600, "Nike Air"))
        sneakerList.add(SneakerItem(imageUrls[0], 700, "Nike Pro"))
        sneakerList.add(SneakerItem(imageUrls[1], 400, "Flate Air"))
        sneakerList.add(SneakerItem(imageUrls[2], 800, "Flate pro"))
        sneakerList.add(SneakerItem(imageUrls[3], 600, "Flate Air"))
    }

    fun getSneakerList(): GetSneakerResponse {
        return GetSneakerResponse(sneakerList)
    }

    fun addToCart(sneaker: CartItem) {
        cartList.add(sneaker)
    }

    fun deleteFromCart(position: Int){
        if(cartList.isEmpty() && position < cartList.size)
            cartList.removeAt(position)
    }

    fun checkOut() {
        cartList.clear()
    }

    fun getCartList(): GetCartResponse {
        return GetCartResponse(cartList)
    }

}