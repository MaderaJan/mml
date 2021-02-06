package cz.maderajan.ui.spotifysync.select.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.SelectableAlbum
import cz.maderajan.ui.spotifysync.databinding.ItemSelectableAlbumBinding

class SelectableAlbumViewHolder(private val binding: ItemSelectableAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(album: SelectableAlbum, onAlbumClicked: (SelectableAlbum) -> Unit) {
        binding.albumNameTextView.text = album.name
        binding.artistTextView.text = album.presentableArtist

        val selectedDrawable = if (album.isSelected) R.drawable.ic_check_selected else R.drawable.ic_check_not_selected
        binding.checkImageView.setImageResource(selectedDrawable)

        binding.albumCoverImageView.load(album?.image) {
            placeholder(R.drawable.ic_music)
            transformations(CircleCropTransformation())
        }

        binding.root.setOnClickListener {
            onAlbumClicked.invoke(album)
        }
    }
}