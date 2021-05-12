package com.mine.wikisearchimages.ui.images

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mine.wikisearchimages.data.entities.Images
import com.mine.wikisearchimages.databinding.ElementImageBinding

class ImagesAdapter(private val listener: ImageItemListener) :
    RecyclerView.Adapter<ImageViewHolder>() {

    interface ImageItemListener {
        fun onClickedImage(imgUrl: String)
    }

    private val items = ArrayList<Images>()

    fun setItems(items: ArrayList<Images>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding: ElementImageBinding =
            ElementImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}

class ImageViewHolder(
    private val itemBinding: ElementImageBinding,
    private val listener: ImagesAdapter.ImageItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var img: Images

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Images) {
        this.img = item
        Glide.with(itemBinding.root)
            .load(item.imageUrl)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedImage(img.imageUrl)
    }
}