package com.budgetwise.app.ui.categories

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budgetwise.app.data.entities.Category
import com.budgetwise.app.databinding.ItemCategoryBinding
import java.text.NumberFormat
import java.util.Locale

class CategoryAdapter(
    private val onEdit: (Category) -> Unit,
    private val onDelete: (Category) -> Unit
) : ListAdapter<Category, CategoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    private val fmt = NumberFormat.getCurrencyInstance(Locale("en", "ZA"))

    inner class ViewHolder(private val b: ItemCategoryBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(cat: Category) {
            b.tvCategoryName.text = cat.name
            b.tvMonthlyLimit.text = if (cat.monthlyLimit != null)
                "Limit: ${fmt.format(cat.monthlyLimit)}" else "No limit set"
            try {
                b.vColour.setBackgroundColor(Color.parseColor(cat.colour))
            } catch (e: Exception) {
                b.vColour.setBackgroundColor(Color.GRAY)
            }
            b.btnEdit.setOnClickListener { onEdit(cat) }
            b.btnDelete.setOnClickListener { onDelete(cat) }
            b.btnDelete.isEnabled = !cat.isDefault
            b.btnDelete.alpha = if (cat.isDefault) 0.4f else 1.0f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(a: Category, b: Category) = a.id == b.id
            override fun areContentsTheSame(a: Category, b: Category) = a == b
        }
    }
}
