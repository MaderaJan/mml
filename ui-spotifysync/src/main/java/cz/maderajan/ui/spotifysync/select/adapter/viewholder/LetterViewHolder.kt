package cz.maderajan.ui.spotifysync.select.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.databinding.ItemLettterBinding
import java.util.*

class LetterViewHolder(private val binding: ItemLettterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AlphabetLetter) {
        binding.letterTextView.text = item.letter.toString().toUpperCase(Locale.getDefault())
    }
}