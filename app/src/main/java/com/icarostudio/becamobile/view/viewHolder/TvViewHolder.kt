package com.icarostudio.becamobile.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.icarostudio.becamobile.databinding.RowFilmListBinding
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.listener.FilmListener
import com.icarostudio.becamobile.service.repository.model.FilmModel
import com.icarostudio.becamobile.service.repository.model.TvModel
import com.squareup.picasso.Picasso

class TvViewHolder(private val itemBinding: RowFilmListBinding, val listener: FilmListener) :
    RecyclerView.ViewHolder(itemBinding.root) {


    fun binData(tv: TvModel) {

        itemBinding.textDatefilm.text = tv.releaseDateFormat
        itemBinding.textFilmname.text = tv.originalName
        itemBinding.textGender.text = "Genre: ${tv.genreDescription}"

        var url = "${FilmsConstants.REQUEST.IMAGE_W185}${tv.posterPath}"
        Picasso.get().load(url).into(itemBinding.imageFilm)

        itemBinding.imageRight.setOnClickListener {
            listener.onListClick(tv.id)
        }

    }

}