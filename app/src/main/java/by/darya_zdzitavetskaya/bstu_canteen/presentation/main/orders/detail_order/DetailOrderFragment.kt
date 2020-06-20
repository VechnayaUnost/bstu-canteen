package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.detail_order

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Order
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentDetailOrderBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import javax.inject.Inject

class DetailOrderFragment : BaseFragment<DetailOrderViewModel, FragmentDetailOrderBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<DetailOrderViewModel> = DetailOrderViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_detail_order

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val order = arguments?.getParcelable<Order>(Order::class.java.name)
        order?.let {
            viewModel.orderLiveData.postValue(it)
            viewBinding.wvOrder.loadUrl(it.url)
            viewModel.isIssuedObservable.set(it.issued)
        }
    }
}