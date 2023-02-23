package com.icarostudio.becamobile.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.icarostudio.becamobile.R
import com.icarostudio.becamobile.databinding.ActivityFilmFormBinding
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.repository.model.ResultsVideos
import com.icarostudio.becamobile.viewmodel.TvFormViewModel
import com.squareup.picasso.Picasso

class TvFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: TvFormViewModel
    private lateinit var binding: ActivityFilmFormBinding
    private var tvIdentification = 0
    private var officialResultVideo = ResultsVideos()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TvFormViewModel::class.java)
        binding = ActivityFilmFormBinding.inflate(layoutInflater)

        binding.imageLike.setOnClickListener(this)
        binding.imagePlay.setOnClickListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadDataFromActivity()

        observe()

        setContentView(binding.root)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.image_like) {
            viewModel.handleLike(tvIdentification)

        }else if(v.id == R.id.image_play){
            goLink()
        }

    }

    private fun loadDataFromActivity() {
        val bundle = intent.extras
        if (bundle != null) {
            tvIdentification = bundle.getInt(FilmsConstants.BUNDLE.TVID)
            viewModel.load(tvIdentification)
        }
    }

    private fun observe() {
        viewModel.tv.observe(this) {
            val url = "${FilmsConstants.REQUEST.IMAGE_W500}${it.backdropPath}"
            Picasso.get().load(url).into(binding.imageFilmimage)

            binding.textName.text = it.originalName

            val year = it.firstAirDate.substring(0, 4)
            binding.textYear.text = year

            var genders = ""
            if(it.genres.count()>= 3){
                for (gender in it.genres.subList(0, 3)) {
                    genders += if (gender == it.genres.subList(0, 3).last()) {
                        gender.name
                    } else {
                        gender.name + " - "
                    }
                }
            }else if(it.genres.count() == 2) {
                for (gender in it.genres.subList(0, 2)) {
                    genders += if (gender == it.genres.subList(0, 2).last()) {
                        gender.name
                    } else {
                        gender.name + " - "
                    }
                }
            } else {
                for (gender in it.genres.subList(0, 1)) {
                    genders += if (gender == it.genres.subList(0, 1).last()) {
                        gender.name
                    } else {
                        gender.name + " - "
                    }
                }
            }
            binding.textGender.text = genders


            binding.textDescription.text = it.overview

            if (viewModel.verifyLike(tvIdentification)) {
                binding.imageLike.setImageResource(R.drawable.ic_baseline_thumb_up_off_alt_24)
            } else {
                binding.imageLike.setImageResource(R.drawable.ic_baseline_thumb_up_off_alt_white)
            }

            if(it.videos.results.filter{it.name == "Official Trailer"}.any()){
                officialResultVideo = it.videos.results.first {it.name == "Official Trailer"}
            }

        }

        viewModel.like.observe(this){
            if (it) {
                binding.imageLike.setImageResource(R.drawable.ic_baseline_thumb_up_off_alt_24)
            } else {
                binding.imageLike.setImageResource(R.drawable.ic_baseline_thumb_up_off_alt_white)
            }
        }
    }

    private fun goLink(){
        if(officialResultVideo.name != ""){
            val url = "https://youtube.com/watch?v=" + officialResultVideo.key
            val uri: Uri = Uri.parse(url)
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        } else {
            Toast.makeText(this,"Trailer não disponível", Toast.LENGTH_SHORT).show()
        }
    }
}