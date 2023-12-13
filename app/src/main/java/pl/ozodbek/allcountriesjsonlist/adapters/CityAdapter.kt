package pl.ozodbek.allcountriesjsonlist.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.ozodbek.allcountriesjsonlist.data.City
import pl.ozodbek.allcountriesjsonlist.data.Country
import pl.ozodbek.allcountriesjsonlist.databinding.CountryRowItemBinding
import pl.ozodbek.allcountriesjsonlist.util.viewBinding

class CityAdapter :
    ListAdapter<City, CityAdapter.AdapterViewHolder>(MyDiffCallback()) {

    private var itemClickListener: ((City) -> Unit)? = null

    fun setItemClickListener(listener: (City) -> Unit) {
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
            remoteData: City,
            clickListener: ((City) -> Unit)?,
        ) {
            binding.title.text = remoteData.stateName
            binding.root.setOnClickListener { clickListener?.invoke(remoteData) }
        }
    }

    private class MyDiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City) =
            oldItem.stateName == newItem.stateName

        override fun areContentsTheSame(oldItem: City, newItem: City) =
            oldItem == newItem
    }

}