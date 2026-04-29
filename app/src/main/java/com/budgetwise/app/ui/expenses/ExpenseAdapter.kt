package com.budgetwise.app.ui.expenses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budgetwise.app.data.entities.Expense
import com.budgetwise.app.databinding.ItemExpenseBinding
import com.budgetwise.app.utils.DateUtils
import java.text.NumberFormat
import java.util.Locale

class ExpenseAdapter(
    private val onItemClick: (Expense) -> Unit,
    private val onReceiptClick: (Expense) -> Unit
) : ListAdapter<Expense, ExpenseAdapter.ViewHolder>(DIFF_CALLBACK) {

    private val fmt = NumberFormat.getCurrencyInstance(Locale("en", "ZA"))

    inner class ViewHolder(private val b: ItemExpenseBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(expense: Expense) {
            b.tvDescription.text = expense.description
            b.tvAmount.text      = fmt.format(expense.amount)
            b.tvDate.text        = DateUtils.toDisplayFormat(expense.date)
            b.tvTime.text        = "${expense.startTime} – ${expense.endTime}"

            if (expense.receiptPath != null) {
                b.ivReceiptIcon.visibility = View.VISIBLE
                b.ivReceiptIcon.setOnClickListener { onReceiptClick(expense) }
            } else {
                b.ivReceiptIcon.visibility = View.GONE
            }

            b.root.setOnClickListener { onItemClick(expense) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Expense>() {
            override fun areItemsTheSame(a: Expense, b: Expense) = a.id == b.id
            override fun areContentsTheSame(a: Expense, b: Expense) = a == b
        }
    }
}
