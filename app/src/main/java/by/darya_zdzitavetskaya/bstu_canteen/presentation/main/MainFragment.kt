package by.darya_zdzitavetskaya.bstu_canteen.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentMainBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.cart.CartFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.MenuFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.OrdersFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<BaseViewModel> = BaseViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_main

    private var activeItemId = R.id.menu

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomNavigation.selectedItemId = activeItemId
        selectFragment(activeItemId)

        viewBinding.bottomNavigation.setOnNavigationItemSelectedListener {
            selectFragment(it.itemId)
            activeItemId = it.itemId
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun selectFragment(menuId: Int) {
        showFragment(
            when (menuId) {
                R.id.menu -> MenuFragment()
                R.id.orders -> OrdersFragment()
                R.id.cart -> CartFragment()
                R.id.profile -> ProfileFragment()
                else -> throw Exception("No fragment for bottom menu item")
            }
        )
    }

    private fun showFragment(selectedFragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, selectedFragment)?.commit()
    }
}