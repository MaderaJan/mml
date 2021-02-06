package cz.maderajan.ui.spotifysync.select.adapter

import androidx.recyclerview.widget.DiffUtil
import cz.maderajan.ui.spotifysync.SelectableAlbum

class SelectableAlbumDiffUtil : DiffUtil.ItemCallback<SelectableAlbum>() {

    override fun areItemsTheSame(oldItem: SelectableAlbum, newItem: SelectableAlbum): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SelectableAlbum, newItem: SelectableAlbum): Boolean =
        oldItem.isSelected == newItem.isSelected &&
                oldItem.image == newItem.image &&
                oldItem.id == newItem.id &&
                oldItem.name == newItem.name

}