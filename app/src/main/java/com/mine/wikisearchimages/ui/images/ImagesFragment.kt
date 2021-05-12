package com.mine.wikisearchimages.ui.images.imagedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mine.wikisearchimages.R
import com.mine.wikisearchimages.databinding.FragmentImagesBinding
import com.mine.wikisearchimages.ui.images.ImagesAdapter
import com.mine.wikisearchimages.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment(), ImagesAdapter.ImageItemListener {
    private val viewModel: ImagesViewModel by viewModels()
    private lateinit var binding: FragmentImagesBinding
    private lateinit var adapter: ImagesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.search.setOnClickListener {
            viewModel.fetchImages(binding.searchEt.text.toString())
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                        }
                        Resource.Status.ERROR ->
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                        Resource.Status.LOADING -> binding.progressBar.visibility = View.VISIBLE

                    }
                })
        }
    }

    private fun setupRecyclerView() {
        adapter = ImagesAdapter(this)
        binding.imagesContainer.layoutManager = LinearLayoutManager(requireContext())
        binding.imagesContainer.adapter = adapter
    }


    override fun onClickedImage(imgUrl: String) {
    }
}