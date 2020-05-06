package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentLoginBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import com.jakewharton.rxbinding3.widget.textChanges
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_login

    override var titleId: Int? = R.string.login_toolbar_title

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listenFields(
            viewBinding.etEmail.textChanges(),
            viewBinding.etPassword.textChanges()
        )
    }
}