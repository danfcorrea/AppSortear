package com.danfcorrea.appsortear.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danfcorrea.appsortear.R
import java.util.ArrayList

class ResultAdapter internal constructor(context: Context?, data: ArrayList<Int>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    private val resultList: ArrayList<Int>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    init {
        mInflater = LayoutInflater.from(context)
        resultList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultAdapter.ViewHolder {
        val view: View = mInflater.inflate(R.layout.result_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.resultId.text = (position+1).toString()
        holder.resultNumber.text = resultList[position].toString()
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var resultNumber: TextView
        var resultId: TextView

        init {
            resultNumber = itemView.findViewById(R.id.resultNumber)
            resultId = itemView.findViewById(R.id.resultId)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Int {
        return resultList[id]
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}


