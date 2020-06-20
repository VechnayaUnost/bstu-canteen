package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentMenuBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import javax.inject.Inject

class MenuFragment : BaseFragment<MenuViewModel, FragmentMenuBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<MenuViewModel> = MenuViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.ACTIVITY

    override val layoutId = R.layout.fragment_menu

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.rvCategories.adapter = viewModel.categoriesAdapter

    }
}