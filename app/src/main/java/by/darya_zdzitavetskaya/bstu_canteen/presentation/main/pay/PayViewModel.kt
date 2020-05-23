package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay

import android.content.Context
import androidx.lifecycle.MutableLiveData
import by.darya_zdzitavetskaya.bstu_canteen.api.request.PayItem
import by.darya_zdzitavetskaya.bstu_canteen.api.request.PayRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.response.PayResponse
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import com.stripe.android.Stripe
import javax.inject.Inject

class PayViewModel @Inject constructor(
    private val payRepository: PayRepository,
    private val context: Context
) : BaseViewModel() {

    val payHelpDate = MutableLiveData<PayResponse>()

    fun preparePayment() {
        payRepository.preparePayment(
            PayRequest(
                "Rub", listOf(
                    PayItem(1)
                )
            )
        ).subscribe({
            payHelpDate.postValue(it)
        }, {

        }).addDisposable()

    }

}