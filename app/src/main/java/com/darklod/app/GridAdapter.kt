package com.darklod.app

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.squareup.picasso.Picasso


class GridAdapter(private var sketches: ArrayList<Sketch>,
                  private var listener: OnItemClickListener)
    : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Sketch)
    }

    inner class ViewHolder(var layout: View) : RecyclerView.ViewHolder(layout) {
        var title : TextView = layout.findViewById(R.id.title)
        var image : ImageView = layout.findViewById(R.id.image)

        fun bind(item: Sketch, listener: OnItemClickListener) {
            title.text = item.title
            Picasso.with(layout.context).load(item.image).into(image)
            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sketch = sketches[position]
        holder.bind(sketch, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.sketch_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return sketches.size
    }
}