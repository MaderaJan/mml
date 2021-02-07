package cz.maderajan.common.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import cz.maderajan.common.ui.R
import cz.maderajan.common.ui.databinding.ViewBannerBinding

class BannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewBannerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.withStyledAttributes(attrs, R.styleable.BannerView) {
            binding.descriptionTextView.text = getString(R.styleable.BannerView_description)

            val positiveButtonText = getString(R.styleable.BannerView_positiveButton)
            binding.positiveButton.isVisible = positiveButtonText?.isNotEmpty() ?: false
            binding.positiveButton.text = positiveButtonText

            val negativeButtonText = getString(R.styleable.BannerView_negativeButton)
            binding.negativeButton.isVisible = negativeButtonText?.isNotEmpty() ?: false
            binding.negativeButton.text = negativeButtonText

            binding.startIconImageView.setImageDrawable(getDrawable(R.styleable.BannerView_startIcon))
        }
    }

    fun setPositiveButtonClick(clickCallback: () -> Unit) {
        binding.positiveButton.setOnClickListener {
            clickCallback.invoke()
        }
    }

    fun setNegativeButtonClick(clickCallback: () -> Unit) {
        binding.negativeButton.setOnClickListener {
            clickCallback.invoke()
        }
    }

}