package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.detail_order

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Order
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import javax.inject.Inject

class DetailOrderViewModel @Inject constructor(
    userCache: IUserCache,
    private val detailOrderRepository: DetailOrderRepository
) : BaseViewModel() {

    val isIssuedObservable = ObservableBoolean()
    val isAdminObservable = ObservableBoolean()

    val orderLiveData = MutableLiveData<Order>()

    init {
        userCache.user.subscribe {
            isAdminObservable.set(it.isAdmin)
        }.addDisposable()
    }

    fun setIssued() {
        detailOrderRepository.makeIssued(orderLiveData.value?.id ?: "")
            .applySchedulers()
            .subscribe({
                isIssuedObservable.set(!isIssuedObservable.get())
            }, {
                it.printStackTrace()
            }).addDisposable()
    }
}