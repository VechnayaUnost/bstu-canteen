package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login

import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentLoginBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_login
}