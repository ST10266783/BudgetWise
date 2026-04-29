package com.budgetwise.app.ui.goals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.repository.GoalRepository
import com.budgetwise.app.databinding.FragmentGoalsBinding
import com.budgetwise.app.utils.SessionManager
import java.text.NumberFormat
import java.util.Locale

class GoalsFragment : Fragment() {

    private var _binding: FragmentGoalsBinding? = null
    private val binding get() = _binding!!
    private val fmt = NumberFormat.getCurrencyInstance(Locale("en", "ZA"))

    private val viewModel: GoalViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db     = AppDatabase.getDatabase(requireContext())
                val session = SessionManager(requireContext())
                val repo   = GoalRepository(db.goalDao())
                @Suppress("UNCHECKED_CAST")
                return GoalViewModel(repo, session.getUserId()) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentGoal.observe(viewLifecycleOwner) { goal ->
            if (goal != null) {
                binding.tvCurrentGoal.text =
                    "Current: ${fmt.format(goal.minimumGoal)} – ${fmt.format(goal.maximumGoal)}"
                binding.etMinGoal.setText(goal.minimumGoal.toString())
                binding.etMaxGoal.setText(goal.maximumGoal.toString())
            } else {
                binding.tvCurrentGoal.text = "No goal set for this month yet."
            }
        }

        binding.btnSaveGoal.setOnClickListener {
            viewModel.saveGoal(
                minStr = binding.etMinGoal.text.toString(),
                maxStr = binding.etMaxGoal.text.toString()
            )
        }

        viewModel.operationResult.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
