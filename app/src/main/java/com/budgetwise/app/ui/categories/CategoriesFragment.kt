package com.budgetwise.app.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgetwise.app.R
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.repository.CategoryRepository
import com.budgetwise.app.databinding.FragmentCategoriesBinding
import com.budgetwise.app.utils.SessionManager

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter

    private val viewModel: CategoryViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db     = AppDatabase.getDatabase(requireContext())
                val session = SessionManager(requireContext())
                val repo   = CategoryRepository(db.categoryDao(), db.expenseDao())
                @Suppress("UNCHECKED_CAST")
                return CategoryViewModel(repo, session.getUserId()) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CategoryAdapter(
            onEdit = { category ->
                val bundle = Bundle().apply { putInt("categoryId", category.id) }
                findNavController().navigate(R.id.action_categories_to_addCategory, bundle)
            },
            onDelete = { category ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete Category")
                    .setMessage("Expenses in \"${category.name}\" will be moved to Uncategorized. Continue?")
                    .setPositiveButton("Delete") { _, _ -> viewModel.deleteCategory(category) }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        )

        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = adapter

        viewModel.categories.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.tvEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.operationResult.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.fabAddCategory.setOnClickListener {
            findNavController().navigate(R.id.action_categories_to_addCategory)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
