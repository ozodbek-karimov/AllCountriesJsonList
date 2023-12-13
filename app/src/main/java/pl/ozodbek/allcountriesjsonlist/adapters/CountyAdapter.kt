package pl.ozodbek.allcountriesjsonlist.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.ozodbek.allcountriesjsonlist.data.Country
import pl.ozodbek.allcountriesjsonlist.databinding.CountryRowItemBinding
import pl.ozodbek.allcountriesjsonlist.util.viewBinding

class CountyAdapter :
    ListAdapter<Country, CountyAdapter.AdapterViewHolder>(MyDiffCallback()) {

    private var itemClickListener: ((Country) -> Unit)? = null

    fun setItemClickListener(listener: (Country) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(parent.viewBinding(CountryRowItemBinding::inflate))
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val remoteData = getItem(position)
        holder.bind(remoteData, itemClickListener)
    }


    class AdapterViewHolder(private val binding: CountryRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            remoteData: Country,
            clickListener: ((Country) -> Unit)?,
        ) {

            binding.title.text = remoteData.countryName
            binding.root.setOnClickListener { clickListener?.invoke(remoteData) }
        }
    }

    private class MyDiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem.countryName == newItem.countryName

        override fun areContentsTheSame(oldItem: Country, newItem: Country) =
            oldItem == newItem
    }

}