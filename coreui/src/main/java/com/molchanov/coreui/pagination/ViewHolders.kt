package com.molchanov.coreui.pagination

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.coreui.databinding.PaginationRvItemCheckedBinding
import com.molchanov.coreui.databinding.PaginationRvItemUncheckedBinding

abstract class PaginationBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: Pair<Int, Boolean>)
}

class PaginationUncheckViewHolder(view: View) : PaginationBaseViewHolder(view) {

    override fun bind(data: Pair<Int, Boolean>) {
        PaginationRvItemUncheckedBinding.bind(itemView).also {
            it.tvPageNum.text = data.first.toString()
        }
    }
}

class PaginationCheckViewHolder(view: View) : PaginationBaseViewHolder(view) {

    override fun bind(data: Pair<Int, Boolean>) {
        PaginationRvItemCheckedBinding.bind(itemView).also {
            it.tvPageNum.text = data.first.toString()
        }
    }
}