package com.icarostudio.becamobile.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.icarostudio.becamobile.databinding.RowFilmListBinding
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.listener.FilmListener
import com.icarostudio.becamobile.service.repository.model.FilmModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class FilmViewHolder(private val itemBinding: RowFilmListBinding, val listener: FilmListener) :
RecyclerView.ViewHolder(itemBinding.root) {


fun binData(film: FilmModel){

    itemBinding.textDatefilm.text = film.releaseDateFormat
    itemBinding.textFilmname.text = film.originalTitle
    itemBinding.textGender.text = "Genre: ${film.genreDescription}"

    var url = "${FilmsConstants.REQUEST.IMAGE_W185}${film.posterPath}"
    Picasso.get().load(url).into(itemBinding.imageFilm)

    itemBinding.imageRight.setOnClickListener{
        listener.onListClick(film.id)
    }

}







}