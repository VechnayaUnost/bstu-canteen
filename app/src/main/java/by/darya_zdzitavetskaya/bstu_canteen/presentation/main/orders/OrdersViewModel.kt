package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders

import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.adapter.BaseRecyclerListAdapter
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Order
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class OrdersViewModel @Inject constructor(
    private val ordersRepository: OrdersRepository,
    private val router: Router
) : BaseViewModel() {

    val ordersAdapter = BaseRecyclerListAdapter<Order>(R.layout.item_order) {
        router.navigateTo(Screens.DetailOrderScreen(this))
    }

    fun getOrders() {
        ordersRepository.getOrders()
            .applySchedulers()
            .subscribe({
                ordersAdapter.submitList(it.orders)
            }, {
                it.printStackTrace()
            }).addDisposable()
    }
}