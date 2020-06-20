package by.darya_zdzitavetskaya.bstu_canteen.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.BR
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.utils.custom_views.ProgressDialog
import by.darya_zdzitavetskaya.bstu_canteen.utils.showToast
import dagger.android.support.DaggerFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : DaggerFragment() {

    abstract val layoutId: Int

    protected lateinit var viewBinding: VDB

    protected lateinit var viewModel: VM

    abstract val viewModelFactory: ViewModelProvider.Factory

    abstract val viewModelClass: Class<VM>

    abstract val scope: ViewModelScope

    open var titleId: Int? = null

    var toolbar: Toolbar? = null

    private var progressDialog: ProgressDialog? = null

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)
        progressDialog = ProgressDialog(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = when (scope) {
            ViewModelScope.ACTIVITY -> ViewModelProvider(activity!!, viewModelFactory).get(viewModelClass)
            ViewModelScope.FRAGMENT -> ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        }

        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()
        viewBinding.lifecycleOwner = viewLifecycleOwner

        return viewBinding.root
    }

    enum class ViewModelScope {
        ACTIVITY,
        FRAGMENT
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = setupToolbar(titleId, router)

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it.data?.let { message ->
                context?.showToast(message)
            }
        })

        viewModel.isLoading.observe(activity!!, Observer {
            it.data?.let { isLoading ->
                if (isLoading) progressDialog?.show() else progressDialog?.dismiss()
            }
        })
    }

    fun Fragment.setupToolbar(
        titleId: Int?,
        router: Router?
    ): Toolbar? {

        val toolbar = view?.findViewById<Toolbar?>(R.id.toolbar)
        toolbar?.let {
            titleId?.let {
                toolbar.title = getString(titleId)
            }
        }

        toolbar?.setNavigationOnClickListener {
            router?.exit()
        }
        return toolbar
    }
}