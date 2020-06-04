package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay

import androidx.lifecycle.MutableLiveData
import by.darya_zdzitavetskaya.bstu_canteen.api.request.PayItem
import by.darya_zdzitavetskaya.bstu_canteen.api.request.PayRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.response.PayResponse
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.CartCache
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PayViewModel @Inject constructor(
    private val payRepository: PayRepository,
    private val cartCache: CartCache,
    private val router: Router
) : BaseViewModel() {

    val payHelpData = MutableLiveData<PayResponse>()

    fun preparePayment() {
        val tempList = mutableListOf<PayItem>()
        cartCache.cartLiveData.value?.forEach { tempList.add(PayItem(it.id, it.quantity)) }
        payRepository.preparePayment(
            PayRequest(tempList)
        ).subscribe({
            payHelpData.postValue(it)
        }, {
            it.printStackTrace()
        }).addDisposable()
    }

    fun finishPayment() {
        cartCache.cartLiveData.postValue(mutableSetOf())
        router.exit()
    }
}