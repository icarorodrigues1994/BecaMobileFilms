package com.icarostudio.becamobile.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icarostudio.becamobile.R
import com.icarostudio.becamobile.databinding.FragmentFilmsListBinding
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.listener.FilmListener
import com.icarostudio.becamobile.view.adapter.FilmAdapter
import com.icarostudio.becamobile.viewmodel.FilmListViewModel


/**
 * A fragment representing a list of Items.
 */
class AllFilmsFragment : Fragment() {

    private lateinit var viewModel: FilmListViewModel
    private var _binding: FragmentFilmsListBinding? = null
    private val binding get() = _binding!!

    private val adapter = FilmAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View? {

        viewModel = ViewModelProvider(this).get(FilmListViewModel::class.java)
        _binding = FragmentFilmsListBinding.inflate(inflater,container,false)

        binding.recyclerAllFilms.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllFilms.adapter = adapter


        val listener = object : FilmListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onListClick(id: Int) {
                val intent = Intent(context, FilmFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(FilmsConstants.BUNDLE.FILMID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

        }
        adapter.attachListener(listener)

        observe()

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        viewModel.list()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.films.observe(viewLifecycleOwner){
            adapter.updateFilms(it.results)
        }
    }

}