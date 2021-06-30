package com.example.covid19_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_list.view.*

class StateAdapter (val list: List<StatewiseItem>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int)= list[position]

    override fun getItemId(position: Int)= position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //?: = elvis operater jab use hota hai jab hume null safety check karni hoti hai
         //      or jaise yaha agar convertview null hua to vo layoutinflater se naya view create kar dega
        // Elvis operator (?:) is used to return the not null value even the conditional expression is null. It is also used to check the null safety of values.
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_list,parent,false)
        val item=list[position]
        view.confirmedtv2.text=SpannableDelta("${item.confirmed}\n ↑${item.deltaconfirmed ?: "0"}",
                "#D32F2F",item.confirmed?.length ?: 0
        )
        view.recoveredtv2.text=SpannableDelta(
                "${item.recovered}\n ↑${item.deltarecovered ?: "0"}",
                    "#1976D2",
                item.recovered?.length ?: 0
        )
        view.activetv2.text=SpannableDelta(
                "${item.active}\n  ↑${item.deltaactive ?: "0"}",
                "#388E3C",
                item.active?.length ?: 0
        )
        view.deathtv2.text=SpannableDelta(
                "${item.deaths}\n ↑${item.deltadeaths ?: "0"}",
                "#FBC02D",
                item.deaths?.length ?: 0
        )
        view.statetv2.text=item.state

        return view


    }

}


