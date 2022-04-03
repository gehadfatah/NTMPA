package com.goda.npmoa.domain.common

import android.graphics.Typeface
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.goda.npmoa.R
import com.goda.npmoa.presentation_layer.ui.base.adapters.BaseRecyclerViewAdapter


object CustomBindingAdapter {
    @JvmStatic
    @BindingAdapter("typeFace")
    fun setTypeFace(view: View?, type: String) {
        if (view != null && !TextUtils.isEmpty(type)) {
            (view as TextView).typeface = Typeface.createFromAsset(view.context.assets, type)
        }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("adapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<T>?) {
        items?.let {
            (recyclerView.adapter as? BaseRecyclerViewAdapter<T>)?.apply {
                clearItems()
                addItems(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUrlCrop")
    fun setImageUrlCrop(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_primary_knode2)
            .into(imageView)
    }

  /*  @BindingAdapter("imageRound")
    @JvmStatic
    fun setImageRound(view: RoundedImageView?, url: String?) {
        if (view != null && !TextUtils.isEmpty(url)) {
            Glide.with(view.context).load(url)

                .placeholder(R.drawable.ic_empty_car)
                .error(R.drawable.ic_empty_car)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        //on load failed
                        Log.d(
                            "TAG",
                            " RoundedImageView: failed"
                        )
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        //on load success
                        Log.d(
                            "TAG",
                            " RoundedImageView: success "
                        )
                        return false
                    }
                })
                .into(view)
        }
    }*/


}