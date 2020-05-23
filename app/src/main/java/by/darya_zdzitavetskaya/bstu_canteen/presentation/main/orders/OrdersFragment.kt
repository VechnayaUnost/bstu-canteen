package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Order
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
        //TODO

        viewBinding.rvOrders.adapter = viewModel.ordersAdapter

        val tempList = listOf(Order(
            "123",
            "https://pay.stripe.com/receipts/acct_1GhPdIBoZxBiKkAe/ch_1GkabuBoZxBiKkAelEQAa8HC/rcpt_HJD1ryIPwx7GAVl80vaZGQ88SCVl546",
            "12 May 1203",
            false
        ))

        viewModel.ordersAdapter.submitList(tempList)
    }
}