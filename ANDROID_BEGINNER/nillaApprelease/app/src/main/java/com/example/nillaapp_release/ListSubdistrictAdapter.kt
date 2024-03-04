package com.example.nillaapp_release
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSubdistrictAdapter(private val listSubdistrict: ArrayList<subdistrict>) : RecyclerView.Adapter<ListSubdistrictAdapter.SubdistrictViewHolder>() {

    private var itemClickListener: ((subdistrict) -> Unit)? = null

    fun setOnItemClickListener(listener: (subdistrict) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubdistrictViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_subdistrictofjember, parent, false)
        return SubdistrictViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubdistrictViewHolder, position: Int) {
        val (name,description,photo) = listSubdistrict[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(listSubdistrict[position])
        }
    }

    override fun getItemCount(): Int = listSubdistrict.size

    class SubdistrictViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}
