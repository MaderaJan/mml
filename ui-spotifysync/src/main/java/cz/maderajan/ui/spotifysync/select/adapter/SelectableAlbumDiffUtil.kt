package cz.maderajan.ui.spotifysync.select.adapter

import androidx.recyclerview.widget.DiffUtil
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum

class SelectableAlbumDiffUtil : DiffUtil.ItemCallback<ISelectableAlbum>() {

    override fun areItemsTheSame(oldItem: ISelectableAlbum, newItem: ISelectableAlbum): Boolean =
        when {
            oldItem is SelectableAlbum && newItem is SelectableAlbum -> oldItem.id == newItem.id
            oldItem is AlphabetLetter && newItem is AlphabetLetter -> oldItem.letter == newItem.letter
            else -> false
        }

    override fun areContentsTheSame(oldItem: ISelectableAlbum, newItem: ISelectableAlbum): Boolean =
        when {
            oldItem is SelectableAlbum && newItem is SelectableAlbum -> {
                oldItem.isSelected == newItem.isSelected &&
                        oldItem.image == newItem.image &&
                        oldItem.id == newItem.id &&
                        oldItem.name == newItem.name
            }
            oldItem is AlphabetLetter && newItem is AlphabetLetter -> oldItem.letter == newItem.letter
            else -> false
        }

}