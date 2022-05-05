package com.example.myfirstapplication.children

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapplication.MyAppDataBase
import com.example.myfirstapplication.R
import com.example.myfirstapplication.addchild.RecycleAdapter
import com.example.myfirstapplication.addchild.ClickOnRecyclerView
import com.example.myfirstapplication.databinding.FragmentChildrenBinding
import kotlinx.coroutines.launch


class ChildrenFragment : Fragment() {


    private lateinit var binding: FragmentChildrenBinding
    private val viewModel: ChildrenViewModel by viewModels()

    private val recycleAdapter = RecycleAdapter(object :ClickOnRecyclerView{
        override fun clickOnDeleteButton(data: ChildrenData) {
            viewModel.deleteChildData(data)
        }

        override fun clickOnCard(data: ChildrenData) {
            //Toast.makeText(binding.root.context, "click on card", Toast.LENGTH_SHORT).show()
            val action = ChildrenFragmentDirections.actionChildrenFragmentToAddChildFragment(data)
            findNavController().navigate(action)
        }

    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_children, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        binding.recycleViewInChildren.apply {
            this.layoutManager = LinearLayoutManager(binding.root.context)
            this.setHasFixedSize(true)
            this.adapter = recycleAdapter
        }

       upDateList()

        binding.backButtonInChildren.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.addAnotherChild.setOnClickListener {
            val action = ChildrenFragmentDirections.actionChildrenFragmentToAddChildFragment(null)
            findNavController().navigate(action)
        }



        return binding.root
    }

    private fun upDateList()
    {
        lifecycle.coroutineScope.launch {
            viewModel.getAllData().collect() {
                recycleAdapter.submitList(it)
            }
        }

    }



}