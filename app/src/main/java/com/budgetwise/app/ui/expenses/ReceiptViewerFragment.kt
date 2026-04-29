package com.budgetwise.app.ui.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.budgetwise.app.databinding.FragmentReceiptViewerBinding
import java.io.File

class ReceiptViewerFragment : Fragment() {

    private var _binding: FragmentReceiptViewerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReceiptViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val path = arguments?.getString("imagePath")
        if (!path.isNullOrBlank()) {
            Glide.with(this).load(File(path)).into(binding.ivReceipt)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
