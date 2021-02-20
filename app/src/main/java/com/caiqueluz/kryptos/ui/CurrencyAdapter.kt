package com.caiqueluz.kryptos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caiqueluz.kryptos.databinding.CurrencyListItemBinding
import com.caiqueluz.kryptos.ui.viewmodel.CurrencyItemVO

class CurrencyAdapter(
    private val currencies: List<CurrencyItemVO>
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CurrencyViewHolder {
        val binding = CurrencyListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = currencies[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = currencies.size

    inner class CurrencyViewHolder(
        private val binding: CurrencyListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CurrencyItemVO) {
            binding.currencyItemName.text = data.name
            binding.currencyItemSymbol.text = data.symbol

            data.image?.let {
                binding.currencyItemImage.setImageBitmap(it)
            }
        }
    }
}
