package com.icarostudio.becamobile.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarostudio.becamobile.databinding.RowFilmListBinding
import com.icarostudio.becamobile.service.listener.FilmListener
import com.icarostudio.becamobile.service.repository.model.TvModel
import com.icarostudio.becamobile.view.viewHolder.TvViewHolder

class TvAdapter : RecyclerView.Adapter<TvViewHolder>(){

    private var listFilms: List<TvModel> = arrayListOf()
    private lateinit var listener: FilmListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RowFilmListBinding.inflate(inflater, parent, false)
        return TvViewHolder(itemBinding,listener)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.binData(listFilms[position])
    }

    override fun getItemCount(): Int {
        return listFilms.count()
    }

    fun updateFilms(list: List<TvModel>){
        listFilms = list
        notifyDataSetChanged() // Função que avisa pro adapter que de fato foi atualizada a lista
    }


    fun attachListener(filmListener: FilmListener){
        listener = filmListener
    }

}