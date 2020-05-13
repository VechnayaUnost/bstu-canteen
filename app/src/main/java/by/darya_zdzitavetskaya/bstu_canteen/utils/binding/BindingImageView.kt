package by.darya_zdzitavetskaya.bstu_canteen.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageUrl", "isCircle"], requireAll = false)
fun ImageView.setImage(imageUri: String?, isCircle: Boolean) {
    imageUri ?: return

    when {
        isCircle -> Glide
            .with(context)
            .load(imageUri)   //TODO
            //.load("https://trikky.ru/wp-content/blogs.dir/1/files/2019/02/17/foto.jpg")
            .apply(RequestOptions.circleCropTransform())
            .into(this)
        else -> Glide.with(context).load(imageUri).into(this)
    }
}