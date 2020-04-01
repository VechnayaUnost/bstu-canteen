package by.darya_zdzitavetskaya.bstu_canteen.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.BR
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, VDB : ViewDataBinding> : Fragment(),
    HasAndroidInjector {

    abstract val layoutId: Int

    protected lateinit var viewBinding: VDB

    protected lateinit var viewModel: VM

    abstract val viewModelFactory: ViewModelProvider.Factory

    abstract val viewModelClass: Class<VM>

    abstract val scope: ViewModelScope

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
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

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun getView(): View? = viewBinding.root

    enum class ViewModelScope {
        ACTIVITY,
        FRAGMENT
    }
}