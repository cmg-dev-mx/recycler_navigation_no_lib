package mx.dev.shellcore.android.recyclernavigation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.dev.shellcore.android.recyclernavigation.core.model.Info
import mx.dev.shellcore.android.recyclernavigation.databinding.ItemInfoBinding

class ListAdapter(
    private val list: List<Info>,
    private val listener: (Info) -> Unit
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size

    inner class ViewHolder(
        private val binding: ItemInfoBinding,
        private val listener: (Info) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info) {
            binding.itemInfoTitle.text = info.title
            binding.itemInfoDescription.text = info.description
            binding.itemInfoContainer.setOnClickListener {
                listener(info)
            }
        }
    }
}