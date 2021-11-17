package com.timfeid.njd.ui.schedule

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timfeid.njd.R
import com.timfeid.njd.ui.BoldFontTextView


class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)
    var contentWrapper: LinearLayout = itemView.findViewById(R.id.cellContent)
    var abbreviation: BoldFontTextView = itemView.findViewById(R.id.abbreviation)
    var description: TextView = itemView.findViewById(R.id.description)
}