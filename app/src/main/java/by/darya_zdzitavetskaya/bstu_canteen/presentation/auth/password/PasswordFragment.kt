package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.password

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentPasswordBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import com.jakewharton.rxbinding3.widget.textChanges
import javax.inject.Inject

class PasswordFragment : BaseFragment<PasswordViewModel, FragmentPasswordBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<PasswordViewModel> = PasswordViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_password

    override var titleId: Int? = R.string.password_toolbar_title

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listenPasswordField(viewBinding.etPassword.textChanges())
    }
}