package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.detail_order

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface DetailOrderRepository {

    fun makeIssued(orderId: String): Completable
}

class DetailOrderRepositoryImpl @Inject constructor(
    private val api: Api
) : DetailOrderRepository {

    override fun makeIssued(orderId: String): Completable {
        return api.makeIssued(orderId).subscribeOn(Schedulers.io())
    }
}