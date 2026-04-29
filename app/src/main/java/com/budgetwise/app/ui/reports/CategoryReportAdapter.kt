package com.budgetwise.app.ui.reports

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.budgetwise.app.databinding.ItemCategoryReportBinding
import java.text.NumberFormat
import java.util.Locale

class CategoryReportAdapter(
    private val items: List<CategoryReport>
) : RecyclerView.Adapter<CategoryReportAdapter.VH>() {

    private val fmt = NumberFormat.getCurrencyInstance(Locale("en", "ZA"))

    inner class VH(val b: ItemCategoryReportBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemCategoryReportBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.b.tvCategoryName.text = item.category.name
        holder.b.tvTotal.text        = fmt.format(item.total)
        try {
            holder.b.vColour.setBackgroundColor(Color.parseColor(item.category.colour))
        } catch (e: Exception) {
            holder.b.vColour.setBackgroundColor(Color.GRAY)
        }
    }
}
