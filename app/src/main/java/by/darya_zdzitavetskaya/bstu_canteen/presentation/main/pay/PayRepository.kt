package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.api.request.PayRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.response.PayResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface PayRepository {

    fun preparePayment(payRequest: PayRequest): Single<PayResponse>
}

class PayRepositoryImpl @Inject constructor(
    private val api: Api
) : PayRepository {

    override fun preparePayment(payRequest: PayRequest): Single<PayResponse> {
        return api.preparePayment(payRequest).subscribeOn(Schedulers.io())
    }

}