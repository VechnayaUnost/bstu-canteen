package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import com.stripe.android.ApiResultCallback
import com.stripe.android.PaymentIntentResult
import com.stripe.android.Stripe
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.model.StripeIntent
import kotlinx.android.synthetic.main.pay_fragment.*
import javax.inject.Inject

class PayFragment : BaseFragment<PayViewModel, ViewDataBinding>() {

    override val layoutId: Int = R.layout.pay_fragment

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<PayViewModel> = PayViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    private lateinit var stripe: Stripe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.preparePayment()

        viewModel.payHelpDate.observe(viewLifecycleOwner, Observer { payResponse ->
            payButton.setOnClickListener {
                cardInputWidget.paymentMethodCreateParams?.let { params ->
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

        stripe.onPaymentResult(requestCode, data, object : ApiResultCallback<PaymentIntentResult> {
            override fun onSuccess(result: PaymentIntentResult) {
                val paymentIntent = result.intent
                val status = paymentIntent.status
                if (status == StripeIntent.Status.Succeeded) {
                    Log.e("Pay","SUCC")
                    //success
                } else if (status == StripeIntent.Status.RequiresPaymentMethod) {
                    //failed
                    Log.e("Pay","FAIL")
                }
            }

            override fun onError(e: Exception) {
                Log.e("Pay","FAIL")
                // filed
            }
        })
    }


}

