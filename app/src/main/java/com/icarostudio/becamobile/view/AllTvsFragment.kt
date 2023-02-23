package com.icarostudio.becamobile.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.icarostudio.becamobile.databinding.FragmentFilmsListBinding
import com.icarostudio.becamobile.databinding.FragmentTvsListBinding
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.listener.FilmListener
import com.icarostudio.becamobile.view.adapter.FilmAdapter
import com.icarostudio.becamobile.view.adapter.TvAdapter
import com.icarostudio.becamobile.viewmodel.TvListViewModel

class AllTvsFragment : Fragment() {

    private lateinit var viewModel: TvListViewModel
    private var _binding: FragmentTvsListBinding? = null
    private val binding get() = _binding!!

    private val adapter = TvAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View? {

        viewModel = ViewModelProvider(this).get(TvListViewModel::class.java)
        _binding = FragmentTvsListBinding.inflate(inflater,container,false)

        binding.recyclerAllFilms.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllFilms.adapter = adapter

        val listener = object : FilmListener {
            @SuppressLint("SuspiciousIndentation")
            override fun onListClick(id: Int) {
                val intent = Intent(context, TvFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(FilmsConstants.BUNDLE.TVID, id)
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
        viewModel.tv.observe(viewLifecycleOwner){
            adapter.updateFilms(it.results)
        }
    }

}


