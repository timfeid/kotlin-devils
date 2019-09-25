package com.timfeid.njd.ui.media

import androidx.recyclerview.widget.RecyclerView

class NewsMediaViewer(val topicId: String) : MediaViewer() {
    override fun getAdapter(): RecyclerView.Adapter<*> {
        return NewsAdapter(fragmentManager!!, topicId)
    }
}