package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentPayBinding
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import by.darya_zdzitavetskaya.bstu_canteen.utils.makeKeyboardVisible
import by.darya_zdzitavetskaya.bstu_canteen.utils.toEvent
import com.stripe.android.ApiResultCallback
import com.stripe.android.PaymentIntentResult
import com.stripe.android.Stripe
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.model.StripeIntent
import javax.inject.Inject

class PayFragment : BaseFragment<PayViewModel, FragmentPayBinding>() {

    override val layoutId: Int = R.layout.fragment_pay

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<PayViewModel> = PayViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override var titleId: Int? = R.string.payment_title

    private lateinit var stripe: Stripe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.preparePayment()
        viewBinding.cardInputWidget.postalCodeEnabled = false

        viewModel.payHelpData.observe(viewLifecycleOwner, Observer { payResponse ->
            viewBinding.payButton.setOnClickListener {
                viewBinding.cardInputWidget.paymentMethodCreateParams?.let { params ->
                    viewModel.isLoading.postValue(true.toEvent())
                    stripe = Stripe(requireContext(), payResponse.publishableKey)
                    val confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams(params, payResponse.clientSecret)
                    stripe.confirmPayment(this, confirmParams)
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val builder = AlertDialog.Builder(context!!)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }

        builder.create()

        stripe.onPaymentResult(requestCode, data, object : ApiResultCallback<PaymentIntentResult> {
            override fun onSuccess(result: PaymentIntentResult) {
                viewModel.isLoading.postValue(false.toEvent())
                val paymentIntent = result.intent
                val status = paymentIntent.status
                if (status == StripeIntent.Status.Succeeded) {
                    Log.e("Pay", "SUCC")
                    builder.setOnDismissListener {
                        viewModel.finishPayment()
                        viewBinding.cardInputWidget.makeKeyboardVisible(false)
                    }
                    builder.setMessage(getString(R.string.payment_successful_payment_message))
                        .show()

                } else if (status == StripeIntent.Status.RequiresPaymentMethod) {
                    builder.setMessage(getString(R.string.payment_failed_payment_message)).show()
                    Log.e("Pay", "FAIL")
                }
            }

            override fun onError(e: Exception) {
                viewModel.isLoading.postValue(false.toEvent())
                builder.setMessage(getString(R.string.payment_failed_payment_message)).show()
                Log.e("Pay", "FAIL")
            }
        })
    }
}

