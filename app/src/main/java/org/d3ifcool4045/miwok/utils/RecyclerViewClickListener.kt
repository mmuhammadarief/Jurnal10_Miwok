package org.d3ifcool4045.miwok.utils

import android.view.View
import org.d3ifcool4045.miwok.data.Miwok

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClicked(view: View, miwok: Miwok) {}

}