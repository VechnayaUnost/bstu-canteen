package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentOrdersBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import javax.inject.Inject

class OrdersFragment : BaseFragment<OrdersViewModel, FragmentOrdersBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<OrdersViewModel> = OrdersViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_orders

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.rvOrders.adapter = viewModel.ordersAdapter

        viewModel.getOrders()
    }
}