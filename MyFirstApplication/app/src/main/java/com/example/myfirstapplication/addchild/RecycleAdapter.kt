package com.example.myfirstapplication.addchild

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.children.ChildrenData
import com.example.myfirstapplication.databinding.RecycleviewLayoutBinding

class RecycleAdapter(private val onclick:ClickOnRecyclerView) : ListAdapter<ChildrenData, RecycleAdapter.ViewHolder>(DiffUtil()) {


     inner class ViewHolder(val myView: RecycleviewLayoutBinding) :
        RecyclerView.ViewHolder(myView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecycleviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.myView.data = data

        holder.myView.deleteButtonInRecycleLayout.setOnClickListener {
         onclick.clickOnDeleteButton(data)
        }

        holder.myView.cardViewRecycler.setOnClickListener {
            onclick.clickOnCard(data)
        }

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ChildrenData>() {
        override fun areItemsTheSame(oldItem: ChildrenData, newItem: ChildrenData): Boolean {
            return oldItem.childName == newItem.childName
        }

        override fun areContentsTheSame(oldItem: ChildrenData, newItem: ChildrenData): Boolean {
            return oldItem == newItem
        }

    }

}


interface ClickOnRecyclerView{
    fun clickOnDeleteButton(data: ChildrenData)
    fun clickOnCard(data: ChildrenData)
}
