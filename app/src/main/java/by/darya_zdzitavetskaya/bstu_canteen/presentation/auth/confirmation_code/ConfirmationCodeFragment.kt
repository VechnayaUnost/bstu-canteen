package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.confirmation_code

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentConfirmationCodeBinding
import by.darya_zdzitavetskaya.bstu_canteen.navigation.EMAIL_EXTRA
import by.darya_zdzitavetskaya.bstu_canteen.navigation.USER_ID_EXTRA
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import by.darya_zdzitavetskaya.bstu_canteen.utils.custom_views.PinEntryEditText
import by.darya_zdzitavetskaya.bstu_canteen.utils.makeKeyboardVisible
import javax.inject.Inject

class ConfirmationCodeFragment :
    BaseFragment<ConfirmationCodeViewModel, FragmentConfirmationCodeBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<ConfirmationCodeViewModel> =
        ConfirmationCodeViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_confirmation_code

    override var titleId: Int? = R.string.confirmation_code_toolbar_title

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.userId = arguments?.getString(USER_ID_EXTRA) ?: ""
        viewModel.userEmail.set(arguments?.getString(EMAIL_EXTRA))

        viewBinding.etCode.apply {
            makeKeyboardVisible(true)
            setOnPinEnteredListener(object : PinEntryEditText.OnPinEnteredListener {
                override fun onPinEntered(view: View, str: CharSequence) {
                    makeKeyboardVisible(false)
                    viewModel.verifyEmail(str.toString().toInt())
                }
            })
        }
    }
}