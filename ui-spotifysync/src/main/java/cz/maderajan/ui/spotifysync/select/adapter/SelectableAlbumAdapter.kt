package cz.maderajan.ui.spotifysync.select.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.SelectableAlbum
import cz.maderajan.ui.spotifysync.databinding.ItemSelectableAlbumBinding

class SelectableAlbumAdapter : ListAdapter<SelectableAlbum, SelectableAlbumViewHolder>(SelectableAlbumDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectableAlbumViewHolder {
        val itemBinding = ItemSelectableAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectableAlbumViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SelectableAlbumViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}

class SelectableAlbumDiffUtil : DiffUtil.ItemCallback<SelectableAlbum>() {

    override fun areItemsTheSame(oldItem: SelectableAlbum, newItem: SelectableAlbum): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SelectableAlbum, newItem: SelectableAlbum): Boolean =
        oldItem.isSelected == newItem.isSelected &&
                oldItem.image == newItem.image &&
                oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.genres == newItem.artists

}

class SelectableAlbumViewHolder(private val binding: ItemSelectableAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SelectableAlbum) {
        binding.albumNameTextView.text = item.name
        binding.artistTextView.text = item.presentableArtist

        val selectedDrawable = if (item.isSelected) R.drawable.ic_check_selected else R.drawable.ic_check_not_selected
        binding.checkImageView.setImageResource(selectedDrawable)

        binding.albumCoverImageView.load(item?.image) {
            placeholder(R.drawable.ic_music)
            transformations(CircleCropTransformation())
        }
    }
}