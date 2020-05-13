package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Category
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentCategoryBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import javax.inject.Inject

class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<CategoryViewModel> = CategoryViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_category

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val category = arguments?.getParcelable<Category>(Category::class.java.name)
        category?.let {
            viewModel.getCategory(it.id)
            toolbar?.title = it.name
            viewModel.category = it
        }

        viewBinding.rvProducts.adapter = viewModel.productsAdapter
    }
}