package com.example.stockwatch_assistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockwatch_assistant.databinding.FragmentAllStocksBinding
import com.example.stockwatch_assistant.databinding.FragmentHomeBinding

//import com.example.stockwatch_assistant.MainActivity.hideKeyboard

//import package com.example.stockwatch_assistant.*

class AllStocksFragment: Fragment(R.layout.fragment_all_stocks) {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: StockRowAdapter

    private var _binding: FragmentAllStocksBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private fun initRecyclerViewDividers(rv: RecyclerView) {
        // Let's have dividers between list items
        val dividerItemDecoration = DividerItemDecoration(
            rv.context, LinearLayoutManager.VERTICAL )
        rv.addItemDecoration(dividerItemDecoration)


    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllStocksBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        Log.d(TAG, "onCreateView ${viewModel.selected}")

//        binding.hello.text = "test"



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.observeUserName().observe(viewLifecycleOwner){
//            binding.hello.text = it
//        }

        adapter = StockRowAdapter(viewModel = viewModel, context = requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context)
        binding.recyclerView.adapter = adapter

        initRecyclerViewDividers(binding.recyclerView)

        viewModel.stockMetaListLiveData.observe(viewLifecycleOwner){
                list -> adapter.submitList(list)
            adapter.notifyDataSetChanged()
        }

//        binding.searchBar.addTextChangedListener(){
//
////            if (it.toString().isEmpty())
////            {
////                hideKeyboard()
////            }
//
//            viewModel.searchStock(it.toString())
//        }

        binding.searchBar.queryHint = "Search"

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchBar.clearFocus()
//                if (viewModel.searchStock(query.toString())) {
//
//                }

                viewModel.searchStock(query.toString())

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchStock(newText.toString())
                return false
            }

        })






    }


}