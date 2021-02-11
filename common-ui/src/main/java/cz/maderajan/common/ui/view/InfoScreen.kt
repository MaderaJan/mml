package cz.maderajan.common.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import cz.maderajan.common.ui.R
import cz.maderajan.common.ui.databinding.ViewInfoScreenBinding

class InfoScreen @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewInfoScreenBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.withStyledAttributes(attrs, R.styleable.InfoScreen) {
            binding.titleTextView.text = getString(R.styleable.InfoScreen_titleText)
            binding.descriptionTextView.text = getString(R.styleable.InfoScreen_descriptionText)
            binding.actionButton.text = getString(R.styleable.InfoScreen_buttonText)

            val icon = getDrawable(R.styleable.InfoScreen_topIcon)
            if (icon != null) {
                binding.iconImageView.setImageDrawable(icon)
            }
        }
    }

    fun setActionButtonClick(callback: () -> Unit) {
        binding.actionButton.isVisible = true
        binding.actionButton.setOnClickListener {
            callback.invoke()
        }
    }
}