package com.timfeid.njd.ui.media

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timfeid.njd.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView




abstract class MediaViewer : Fragment() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_media_list, container, false)

        populateView(rootView)

        return rootView
    }

    fun populateView(view: View) {
        mRecyclerView = view.findViewById(R.id.media)

        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView!!.layoutManager = mLayoutManager

        mAdapter = getAdapter()
        mRecyclerView!!.adapter = mAdapter
    }

    abstract fun getAdapter () : RecyclerView.Adapter<*>
}