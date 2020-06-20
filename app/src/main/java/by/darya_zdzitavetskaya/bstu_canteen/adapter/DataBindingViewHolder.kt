package by.darya_zdzitavetskaya.bstu_canteen.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import by.darya_zdzitavetskaya.bstu_canteen.BR

class DataBindingViewHolder<T : ItemRecyclerModel>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: T, click: (T.(View) -> Unit)?) {
        binding.setVariable(BR.item, model)
        binding.executePendingBindings()
        binding.root.setOnClickListener { view ->
            click?.let { model.it(view) }
        }
    }
}