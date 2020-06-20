package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.registration

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentRegistrationBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import com.jakewharton.rxbinding3.widget.textChanges
import javax.inject.Inject

class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<RegistrationViewModel> = RegistrationViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_registration

    override var titleId: Int? = R.string.registration_toolbar_title

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listenEmailField(viewBinding.etEmail.textChanges())
    }
}