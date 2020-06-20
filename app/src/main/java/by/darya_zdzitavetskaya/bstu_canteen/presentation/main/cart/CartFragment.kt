package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.cart

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentCartBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import javax.inject.Inject

class CartFragment : BaseFragment<CartViewModel, FragmentCartBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<CartViewModel> = CartViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.ACTIVITY

    override val layoutId = R.layout.fragment_cart

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.rvOrders.adapter = viewModel.cartAdapter

        viewModel.cartCache.cartLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.cartAdapter.submitList(it.toList())
            viewModel.totalItemsObservable.set(viewModel.cartCache.getShoppingCartSize())
            viewModel.totalPriceObservable.set(viewModel.cartCache.getShoppingCartPrice())
        })
    }
}