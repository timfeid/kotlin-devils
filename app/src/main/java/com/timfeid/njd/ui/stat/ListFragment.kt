package com.timfeid.njd.ui.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timfeid.njd.R
import com.timfeid.njd.ui.FontSpinnerAdapter
import com.timfeid.njd.ui.media.StatsAdapter

abstract class ListFragment() : Fragment() {
    var statsAdapter: StatsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.media)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = fragmentManager?.let { getAdapter(it) }

        val spinner: Spinner = view.findViewById(R.id.spinner)
        val arrayAdapter = FontSpinnerAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            getSortOptions()
        )
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = onItemSelected()
    }

    fun onItemSelected(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                statsAdapter!!.sortBy(pos)
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }

        }
    }

    abstract fun getSortOptions(): List<String>

    fun getAdapter(fragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        statsAdapter = withAdapter(fragmentManager)

        return statsAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
    }

    abstract fun withAdapter(fragmentManager: FragmentManager): StatsAdapter
}