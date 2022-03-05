package com.example.fitkit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeAdapter(private val shoeList: ArrayList<ShoeModel>,
                  private val listener: onShoeItemClickListener
): RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    inner class ShoeViewHolder(itemView : View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        val imgView : ImageView = itemView.shoe_logo

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onShoeClick(position)
        }

    }

    interface onShoeItemClickListener{
        fun onShoeClick(shoePosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.shoe_item,
            parent,false)
        return ShoeViewHolder(itemView)
    }

    override fun onBindViewHolder(currholder: ShoeViewHolder, currPosition: Int) {
        val currShoe = shoeList.get(currPosition)
        currholder.imgView.setImageResource(currShoe.imgRes)
//        currholder.imgView.setBackgroundColor(currShoe.BD)


    }

    override fun getItemCount() = shoeList.size

}