package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.cart

import androidx.databinding.ObservableField
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.adapter.CartItemClickListener
import by.darya_zdzitavetskaya.bstu_canteen.adapter.CartRecyclerListAdapter
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.CartCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.CartItem
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val router: Router,
    val cartCache: CartCache
) : BaseViewModel(), CartItemClickListener {

    val totalItemsObservable = ObservableField<Int>()
    val totalPriceObservable = ObservableField<Double>()

    val cartAdapter = CartRecyclerListAdapter(R.layout.item_cart, this)

    override fun onDeleteClicked(cartItem: CartItem) {
        cartCache.removeProduct(cartItem)
    }

    fun makeOrder() {
        router.navigateTo(Screens.PayScreen())
    }
}