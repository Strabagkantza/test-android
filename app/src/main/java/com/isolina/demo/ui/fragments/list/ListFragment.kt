package com.isolina.demo.ui.fragments.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.isolina.demo.databinding.FragmentListBinding
import com.isolina.demo.ui.fragments.list.adapter.ListItemAdapter
import com.isolina.demo.usecases.UseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var  binding: FragmentListBinding
    private lateinit var adapter: ListItemAdapter
    private val viewModel: ListViewModel by viewModels()

    @Inject
    lateinit var useCase: UseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            isActiveSearch(binding.search.text.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListItemAdapter { beer ->
            findNavController().navigate(ListFragmentDirections.actionListToDetail(beer))
        }
        binding.list.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                binding.list.scrollToPosition(0)
            }
        })

        viewModel.progress.observe(viewLifecycleOwner) {
            binding.progress.visibility = if (it)  View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        ).apply {
            binding.list.layoutManager = this
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                isActiveSearch(s.toString())
            }
        })
    }

    private fun isActiveSearch(search: String) {
        if (search.isNotEmpty())
            viewModel.searchBeers(search)
        else
            viewModel.beers()
    }

}