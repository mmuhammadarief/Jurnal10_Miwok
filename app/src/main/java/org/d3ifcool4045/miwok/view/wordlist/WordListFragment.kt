package org.d3ifcool4045.miwok.view.wordlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.d3ifcool4045.miwok.MainActivity
import org.d3ifcool4045.miwok.R
import org.d3ifcool4045.miwok.data.Miwok
import org.d3ifcool4045.miwok.databinding.FragmentWordListBinding
import org.d3ifcool4045.miwok.utils.RecyclerViewClickListener
import org.d3ifcool4045.miwok.view.miwok.MiwokViewModel

@Suppress("SpellCheckingInspection")
class WordListFragment : Fragment(),
    RecyclerViewClickListener {

    private lateinit var binding: FragmentWordListBinding
    private lateinit var viewModel: MiwokViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        title()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false)
        val application = activity?.application
        val factory = MiwokViewModel.Factory(application!!)
        viewModel = ViewModelProvider(this, factory).get(MiwokViewModel::class.java)
        binding.lifecycleOwner = this
        binding.miwokVM = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val listData = mutableListOf<Miwok>()
            val category = arguments?.getString("category")

            viewModel.miwok.observe(viewLifecycleOwner, Observer {
                it.map {  miwok ->
                    if (miwok.category == category) {
                        listData.add(miwok)
                    }
                }

                val dataFix = listData.distinct()
                val adapter = WordListAdapter(dataFix)
                val recyclerview = binding.rvWordList
                recyclerview.adapter = adapter
                recyclerview.layoutManager = LinearLayoutManager(this.requireContext())
                adapter.listener = this
            })

            viewModel.response.observe(viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                }
            })

        }

    }

    override fun onRecyclerViewItemClicked(view: View, miwok: Miwok) {
        super.onRecyclerViewItemClicked(view, miwok)
        Snackbar.make(requireView(), miwok.miwokWord, Snackbar.LENGTH_SHORT).show()
    }

    private fun title() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = arguments!!.getString("category")
    }

}
