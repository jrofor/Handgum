package com.example.roman.handgum.feature.revdlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.roman.handgum.commonentity.ui.models.ReviewModel
import com.example.roman.handgum.feature.revlist.R
import com.example.roman.handgum.feature.revlist.databinding.ItemReviewBinding


/**
 * @author rofor
 */
class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    var items: List<ReviewModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: ((url: String) -> Unit)? = null

    private val imageOption: RequestOptions = RequestOptions()
        .placeholder(R.drawable.image_placeholder)
        .centerCrop()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReviewViewHolder(
            ItemReviewBinding.inflate(inflater, parent, false),
            parent.measuredWidth,
            items[0]
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ReviewViewHolder(
        private val binding: ItemReviewBinding,
        parentWidth: Int,
        item: ReviewModel?
    ) : RecyclerView.ViewHolder(binding.root) {

        private var imageLoader: RequestManager =
            Glide.with(itemView.context).applyDefaultRequestOptions(imageOption)

        init {
            binding.apply {
                btnReadMore.setOnClickListener {
                    onItemClickListener?.invoke(items[absoluteAdapterPosition].url)
                }
                // setting resolution image
                if (item != null && item.src != "") {
                    ivMultimedia.layoutParams.width = parentWidth
                    ivMultimedia.layoutParams.height =
                        ((parentWidth * (item.height).toInt()) / (item.width).toInt())
                }
            }
        }

        fun bind(item: ReviewModel) {
            binding.apply {
                tvDisplayTitle.text = item.displayTitle
                tvMpaaRating.text = item.mpaaRating
                tvPublicationDate.text = item.publicationDate
                tvHeadline.text = item.headline
                tvSummaryShort.text = item.summaryShort
                imageLoader.load(item.src)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                    .thumbnail(imageLoader.asDrawable().sizeMultiplier(0.3f))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivMultimedia)
            }
        }
    }

}