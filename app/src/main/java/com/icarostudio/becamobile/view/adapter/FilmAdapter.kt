package com.icarostudio.becamobile.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarostudio.becamobile.databinding.RowFilmListBinding
import com.icarostudio.becamobile.service.listener.FilmListener
import com.icarostudio.becamobile.service.repository.model.FilmModel
import com.icarostudio.becamobile.view.viewHolder.FilmViewHolder


class FilmAdapter : RecyclerView.Adapter<FilmViewHolder>(){

    private var listFilms: List<FilmModel> = arrayListOf()
    private lateinit var listener: FilmListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RowFilmListBinding.inflate(inflater, parent, false)
        return FilmViewHolder(itemBinding,listener)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.binData(listFilms[position])
    }

    override fun getItemCount(): Int {
        return listFilms.count()
    }

    fun updateFilms(list: List<FilmModel>){
        listFilms = list
        notifyDataSetChanged() // Função que avisa pro adapter que de fato foi atualizada a lista
    }


    fun attachListener(filmListener: FilmListener){
        listener = filmListener
    }

}