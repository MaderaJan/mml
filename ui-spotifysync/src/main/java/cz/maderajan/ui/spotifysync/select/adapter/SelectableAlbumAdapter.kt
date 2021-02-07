package cz.maderajan.ui.spotifysync.select.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.data.select.ISelectableAlbum
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import cz.maderajan.ui.spotifysync.databinding.ItemLettterBinding
import cz.maderajan.ui.spotifysync.databinding.ItemSelectableAlbumBinding
import cz.maderajan.ui.spotifysync.select.adapter.viewholder.LetterViewHolder
import cz.maderajan.ui.spotifysync.select.adapter.viewholder.SelectableAlbumViewHolder

class SelectableAlbumAdapter(private val onAlbumClicked: (SelectableAlbum) -> Unit) :
    ListAdapter<ISelectableAlbum, RecyclerView.ViewHolder>(SelectableAlbumDiffUtil()) {

    companion object {
        private const val TYPE_ALBUM = 0
        private const val TYPE_LETTER = 1
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SelectableAlbum -> TYPE_ALBUM
        else -> TYPE_LETTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        TYPE_ALBUM -> {
            val itemBinding = ItemSelectableAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SelectableAlbumViewHolder(itemBinding)
        }
        else -> {
            val itemBinding = ItemLettterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LetterViewHolder(itemBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        when {
            item is SelectableAlbum && holder is SelectableAlbumViewHolder -> holder.bind(item, onAlbumClicked)
            item is AlphabetLetter && holder is LetterViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException("Unknown item type or ViewHolder")
        }
    }
}