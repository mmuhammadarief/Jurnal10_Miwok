package org.d3ifcool4045.miwok.view.wordlist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3ifcool4045.miwok.R
import org.d3ifcool4045.miwok.data.Miwok
import org.d3ifcool4045.miwok.databinding.RecyclerviewWordlistBinding
import org.d3ifcool4045.miwok.utils.RecyclerViewClickListener

@Suppress("SpellCheckingInspection")
class WordListAdapter(
    private val miwok: List<Miwok>
) : RecyclerView.Adapter<WordListAdapter.WordListViewHolder>() {

    var listener: RecyclerViewClickListener? = null

    inner class WordListViewHolder(
        val recyclerviewWordlistBinding: RecyclerviewWordlistBinding
    ) : RecyclerView.ViewHolder(recyclerviewWordlistBinding.root)

    override fun getItemCount() = miwok.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WordListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recyclerview_wordlist, parent, false)
    )

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        holder.recyclerviewWordlistBinding.listWordList.setBackgroundColor(Color.parseColor(miwok[position].background))
        holder.recyclerviewWordlistBinding.tvInggris.text = miwok[position].defaultWord
        holder.recyclerviewWordlistBinding.tvMiwok.text = miwok[position].miwokWord

        if (miwok[position].image == "") {
            Glide.with(holder.itemView.context).clear(holder.recyclerviewWordlistBinding.image)
            holder.recyclerviewWordlistBinding.image.setImageDrawable(null)
            holder.recyclerviewWordlistBinding.image.visibility = View.GONE
        } else {
            Glide.with(holder.itemView.context)
                .load("http://dif.indraazimi.com/miwok/${miwok[position].image}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .dontAnimate()
                .into(holder.recyclerviewWordlistBinding.image)
        }

        holder.recyclerviewWordlistBinding.listWordList.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, miwok[position])
        }
    }
}