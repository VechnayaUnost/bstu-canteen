package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ShortProduct
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentEditProductBinding
import by.darya_zdzitavetskaya.bstu_canteen.navigation.CATEGORY_ID_EXTRA
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import com.jakewharton.rxbinding3.widget.textChanges
import javax.inject.Inject

class EditProductFragment : BaseFragment<EditProductViewModel, FragmentEditProductBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<EditProductViewModel> = EditProductViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_edit_product

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val product = arguments?.getParcelable<ShortProduct>(ShortProduct::class.java.name)
        viewModel.categoryId = arguments?.getString(CATEGORY_ID_EXTRA)
        if (product != null) {
            viewModel.getProduct(product.id)
            toolbar?.title = product.name
            viewModel.isNewProduct.postValue(false)
        } else {
            toolbar?.title = getString(R.string.edit_product_new_product_title)
            viewModel.isNewProduct.postValue(true)
        }

        viewModel.listenFields(
            viewBinding.etName.textChanges(),
            viewBinding.etDescription.textChanges(),
            viewBinding.etPrice.textChanges(),
            viewBinding.etQuantity.textChanges()
        )
    }
}