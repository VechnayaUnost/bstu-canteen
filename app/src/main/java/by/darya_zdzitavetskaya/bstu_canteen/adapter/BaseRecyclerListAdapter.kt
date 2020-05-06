package by.darya_zdzitavetskaya.bstu_canteen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BaseRecyclerListAdapter<T : ItemRecyclerModel>(
    @LayoutRes private val itemContainer: Int,
    private val click: (T.(View) -> Unit)? = null
) : ListAdapter<T, DataBindingViewHolder<T>>(diffUtil<T>()) {

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {
        holder.bind(getItem(position), click)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, itemContainer, parent, false)

        return DataBindingViewHolder(binding)
    }
}

fun <D : ItemRecyclerModel> diffUtil() = object : DiffUtil.ItemCallback<D>() {
    override fun areItemsTheSame(oldItem: D, newItem: D): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: D, newItem: D): Boolean {
        return oldItem == newItem
    }
}