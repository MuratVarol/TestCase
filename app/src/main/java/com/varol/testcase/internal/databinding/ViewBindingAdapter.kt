package com.varol.testcase.internal.databinding

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.varol.testcase.R
import com.varol.testcase.internal.extension.toSpanned
import com.varol.testcase.internal.view.RootConstraintLayout


@BindingAdapter("visibility", "gone", requireAll = false)
fun View.setVisibility(visible: Boolean, isGone: Boolean = true) {
    visibility = if (visible) View.VISIBLE else {
        if (isGone) View.GONE else View.INVISIBLE
    }
}

@BindingAdapter("visibilityNot", requireAll = false)
fun setNotVisibility(view: View, invisible: Boolean) {
    view.visibility = if (invisible) View.GONE else {
        View.VISIBLE
    }
}

@BindingAdapter("hideIfNull")
fun View.hideIfNull(value: Any?) {
    visibility = if (value == null) View.GONE else View.VISIBLE
}

@BindingAdapter("hideIfNullOrEmpty")
fun View.setVisible(text: CharSequence?) {
    visibility = if (text != null && text.isNotEmpty()) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("progressVisibility")
fun setProgressStatus(rootConstraintLayout: RootConstraintLayout, isVisible: Boolean) {
    if (isVisible) rootConstraintLayout.showProgress() else rootConstraintLayout.hideProgress()
}

@BindingAdapter("drawableResource")
fun AppCompatImageView.setDrawable(resource: Drawable) {
    Glide.with(this.context)
        .load(resource)
        .into(this)
}

@BindingAdapter(requireAll = false, value = ["imageUrl", "fallbackDrawable"])
fun AppCompatImageView.loadFromUrl(url: String?, fallbackDrawable: Drawable) {

    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }

    val options: RequestOptions = RequestOptions()
        .placeholder(circularProgressDrawable)
        .error(fallbackDrawable)
        .priority(Priority.HIGH)

    url?.let {
        Glide.with(this.context)
            .load(it)
            .apply(options)
            .into(this)

    } ?: kotlin.run {
        setDrawable(fallbackDrawable)
    }
}


@BindingAdapter("setPriceText")
fun AppCompatTextView.setPriceText(price: String) {
    text = (context.getString(R.string.price_with_value, price)).toSpanned()
}