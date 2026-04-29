package com.budgetwise.app.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.budgetwise.app.R
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.repository.CategoryRepository
import com.budgetwise.app.data.repository.ExpenseRepository
import com.budgetwise.app.data.repository.GoalRepository
import com.budgetwise.app.databinding.FragmentDashboardBinding
import com.budgetwise.app.utils.SessionManager
import java.text.NumberFormat
import java.util.Locale

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val fmt = NumberFormat.getCurrencyInstance(Locale("en", "ZA"))

    private val viewModel: DashboardViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db      = AppDatabase.getDatabase(requireContext())
                val expRepo = ExpenseRepository(db.expenseDao())
                val goalRepo = GoalRepository(db.goalDao())
                val catRepo = CategoryRepository(db.categoryDao(), db.expenseDao())
                @Suppress("UNCHECKED_CAST")
                return DashboardViewModel(expRepo, goalRepo, catRepo) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val session = SessionManager(requireContext())
        viewModel.init(session.getUserId())

        binding.tvWelcome.text = "Hello, ${session.getUsername()} \uD83D\uDC4B"

        viewModel.totalSpent.observe(viewLifecycleOwner) { total ->
            val safeTotal = total ?: 0.0
            binding.tvTotalSpent.text = fmt.format(safeTotal)
            updateProgress(safeTotal)
        }

        viewModel.goal.observe(viewLifecycleOwner) { goal ->
            if (goal != null) {
                binding.tvGoalRange.text =
                    "Goal: ${fmt.format(goal.minimumGoal)} – ${fmt.format(goal.maximumGoal)}"
                binding.progressBar.max = goal.maximumGoal.toInt().coerceAtLeast(1)
                updateProgress(viewModel.totalSpent.value ?: 0.0)
            } else {
                binding.tvGoalRange.text = "No goal set for this month. Tap Goals to add one."
            }
        }

        binding.fabAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_addExpense)
        }
    }

    private fun updateProgress(total: Double) {
        val max = binding.progressBar.max
        binding.progressBar.progress = total.toInt().coerceIn(0, max * 2)
        val pct = if (max > 0) (total / max * 100).toInt() else 0
        binding.tvProgressPct.text = "$pct% of monthly budget spent"
        if (pct >= 100) {
            binding.progressBar.progressTintList =
                android.content.res.ColorStateList.valueOf(Color.RED)
            binding.tvProgressPct.setTextColor(Color.RED)
        } else {
            binding.progressBar.progressTintList =
                android.content.res.ColorStateList.valueOf(
                    requireContext().getColor(R.color.purple_500)
                )
            binding.tvProgressPct.setTextColor(Color.BLACK)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
