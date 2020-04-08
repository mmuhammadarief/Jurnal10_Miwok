package org.d3ifcool4045.miwok.view.miwok

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool4045.miwok.R
import org.d3ifcool4045.miwok.data.Miwok
import org.d3ifcool4045.miwok.databinding.RecyclerviewMiwokBinding
import org.d3ifcool4045.miwok.utils.RecyclerViewClickListener

@Suppress("SpellCheckingInspection")
class MiwokAdapter(
    private val miwok: List<Miwok>
) : RecyclerView.Adapter<MiwokAdapter.MiwokViewHolder>() {

    var listener: RecyclerViewClickListener? = null

    inner class MiwokViewHolder(
        val recyclerviewMiwokBinding: RecyclerviewMiwokBinding
    ) : RecyclerView.ViewHolder(recyclerviewMiwokBinding.root)

    override fun getItemCount() = miwok.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiwokViewHolder (
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recyclerview_miwok, parent, false)
    )

    override fun onBindViewHolder(holder: MiwokViewHolder, position: Int) {
        holder.recyclerviewMiwokBinding.tvCategory.text = miwok[position].category
        holder.recyclerviewMiwokBinding.listMiwok.setBackgroundColor(Color.parseColor(miwok[position].background))
        holder.recyclerviewMiwokBinding.listMiwok.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, miwok[position])
        }
    }
}