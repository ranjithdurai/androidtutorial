package com.example.androidtutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.androidtutorial.MainActivity.Data


class ListAdapter(var stringList: List<Data>, var context: Context) : BaseAdapter() {

    var inflater: LayoutInflater? = null
    override fun getCount(): Int {
        return stringList.size;
    }

    override fun getItem(position: Int): Any? {
        return null;
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val convertView: View = LayoutInflater.from(context).inflate(R.layout.mylist_item, null)

        var name: TextView = convertView.findViewById(R.id.tvName)
        name?.text = stringList[position].employeeName
        return convertView

    }
}