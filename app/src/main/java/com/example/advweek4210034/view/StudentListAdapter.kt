package com.example.advweek4210034.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4210034.R
import com.example.advweek4210034.databinding.StudentListItemBinding
import com.example.advweek4210034.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
    , ButtonDetailClickListener
{
    class StudentViewHolder(var view: StudentListItemBinding)
        : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        //val binding = StudentListItemBinding.inflate(
        //    LayoutInflater.from(parent.context), parent, false)
        //return StudentViewHolder(binding)
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater,
            R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections
                        .actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        /*
        // dengan menggunakan databinding,
        // 2 baris ini bisa digantikan 1 baris di atas
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name
        */

        holder.view.listener = this
        /*
        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
