package by.darya_zdzitavetskaya.bstu_canteen.shared

import androidx.lifecycle.MutableLiveData
import by.darya_zdzitavetskaya.bstu_canteen.adapter.ItemRecyclerModel
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartCache @Inject constructor() : ICartCache {

    override val cartLiveData = MutableLiveData<MutableSet<CartItem>>()

    override fun addProduct(cartItem: CartItem) {
        val tempSet = cartLiveData.value ?: mutableSetOf()
        val targetItem = tempSet.firstOrNull { it.product.id == cartItem.product.id }
        if (targetItem == null) {
            tempSet.add(cartItem)
        } else {
            tempSet.remove(targetItem)
            targetItem.quantity += cartItem.quantity
            tempSet.add(targetItem)
        }
        cartLiveData.postValue(tempSet)
    }

    override fun removeProduct(cartItem: CartItem) {
        val tempSet = cartLiveData.value ?: mutableSetOf()
        val targetItem = tempSet.firstOrNull { it.product.id == cartItem.product.id }
        if (targetItem != null) {
            tempSet.remove(targetItem)
        }
        cartLiveData.postValue(tempSet)
    }

    override fun getShoppingCartSize(): Int {
        var cartSize = 0
        cartLiveData.value?.forEach {
            cartSize += it.quantity
        }

        return cartSize
    }

    override fun getShoppingCartPrice(): Double {
        var cartPrice = 0.0
        cartLiveData.value?.forEach {
            cartPrice += it.quantity * (it.product.price ?: 0.0)
        }

        return cartPrice
    }
}

interface ICartCache {

    val cartLiveData: MutableLiveData<MutableSet<CartItem>>

    fun addProduct(cartItem: CartItem)

    fun removeProduct(cartItem: CartItem)

    fun getShoppingCartSize(): Int

    fun getShoppingCartPrice(): Double
}

data class CartItem(var product: Product, var quantity: Int = 0) : ItemRecyclerModel {
    override val id: String
        get() = product.id ?: ""
}