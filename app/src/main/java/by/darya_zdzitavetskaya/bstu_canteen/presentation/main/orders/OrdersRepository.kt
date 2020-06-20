package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.api.response.OrdersResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface OrdersRepository {

    fun getOrders(): Single<OrdersResponse>
}

class OrdersRepositoryImpl @Inject constructor(
    private val api: Api
) : OrdersRepository {

    override fun getOrders(): Single<OrdersResponse> {
        return api.getOrders().subscribeOn(Schedulers.io())
    }
}