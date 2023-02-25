package com.example.testtask

import android.graphics.Camera
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardStackAdapter: RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    private val events = mutableListOf<Event>()

    fun setData( newEvents: ArrayList<Event>){
        events.addAll(newEvents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.card_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        putImage(holder, events[position].idImage)
        holder.textOfEvent.text = events[position].text
        holder.author.text = events[position].author
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
    }

    override fun getItemCount(): Int = events.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.imageView)
        val textOfEvent = view.findViewById<TextView>(R.id.text_of_event)
        val author = view.findViewById<TextView>(R.id.author)
    }

    fun putImage( holder: ViewHolder , idPhoto: Int){
        when(idPhoto){
            1 -> holder.image.setImageResource(R.drawable.kino)
            2 -> holder.image.setImageResource(R.drawable.solder_general)
            3 -> holder.image.setImageResource(R.drawable.secretar)
            4 -> holder.image.setImageResource(R.drawable.volcano)
            //5 -> holder.image.setImageResource(R.drawable.)
            6 -> holder.image.setImageResource(R.drawable.skull)
            7 -> holder.image.setImageResource(R.drawable.execution)
            8 -> holder.image.setImageResource(R.drawable.war)
            9 -> holder.image.setImageResource(R.drawable.virus)
        }
    }

    companion object{
        val TAG = "mylogs"
    }
}

