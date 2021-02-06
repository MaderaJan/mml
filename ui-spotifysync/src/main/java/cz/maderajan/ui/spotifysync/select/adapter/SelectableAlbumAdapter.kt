package cz.maderajan.ui.spotifysync.select.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cz.maderajan.ui.spotifysync.SelectableAlbum
import cz.maderajan.ui.spotifysync.databinding.ItemSelectableAlbumBinding
import cz.maderajan.ui.spotifysync.select.adapter.viewholder.SelectableAlbumViewHolder

class SelectableAlbumAdapter(private val onAlbumClicked: (SelectableAlbum) -> Unit) :
    ListAdapter<SelectableAlbum, SelectableAlbumViewHolder>(SelectableAlbumDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectableAlbumViewHolder {
        val itemBinding = ItemSelectableAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectableAlbumViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SelectableAlbumViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item, onAlbumClicked)
    }
}