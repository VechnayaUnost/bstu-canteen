package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ShortProduct
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentDetailProductBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import javax.inject.Inject

class DetailProductFragment : BaseFragment<DetailProductViewModel, FragmentDetailProductBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<DetailProductViewModel> = DetailProductViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_detail_product

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val product = arguments?.getParcelable<ShortProduct>(ShortProduct::class.java.name)

        if (product != null) {
            viewModel.getProduct(product.id)
            toolbar?.title = product.name
        }

        viewModel.productLiveData.observe(viewLifecycleOwner, Observer {
            viewBinding.numberPicker.apply {
                minValue = 1
                maxValue = it.count ?: 1
                setOnValueChangedListener { picker, oldVal, newVal ->
                    viewModel.selectedQuantity = newVal
                }
            }
        })
    }
}