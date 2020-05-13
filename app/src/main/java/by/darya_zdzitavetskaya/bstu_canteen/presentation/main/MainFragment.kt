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
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileFragment
import javax.inject.Inject

class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<BaseViewModel> = BaseViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showFragment(MenuFragment())

        viewBinding.bottomNavigation.setOnNavigationItemSelectedListener {
            showFragment(
                when (it.itemId) {
                    R.id.menu -> MenuFragment()
                    R.id.orders -> ProfileFragment()
                    R.id.cart -> CartFragment()
                    R.id.profile -> ProfileFragment()
                    else -> throw Exception("No fragment for bottom menu item")
                }
            )
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun showFragment(selectedFragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, selectedFragment)?.commit()
    }
}