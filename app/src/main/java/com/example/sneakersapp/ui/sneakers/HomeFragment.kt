package com.example.sneakersapp.ui.sneakers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sneakersapp.databinding.FragmentHomeBinding
import com.example.sneakersapp.network.response.GetSneakerResponse
import com.example.sneakersapp.ui.home.HomeVM
import com.example.sneakersapp.ui.home.SneakerItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var navController: NavController? = null
    private var binding: FragmentHomeBinding? = null

    @Inject
    lateinit var vm: HomeVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initView()
        initSearchBar()
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        vm.getSneakerList()
    }

    private fun observeLiveData() {
         vm.getSneakerLiveData.observe(requireActivity()){
             getSneakerResponse(it.data)
         }
    }

    private fun getSneakerResponse(data: GetSneakerResponse?) {
         if(data != null){
             vm.dataList.clear()
             vm.dataList.addAll(data.items)
             binding?.rvSneakers?.adapter = SneakersAdapter(requireContext(), vm.dataList, navController)
         }
    }

    private fun initView() {
        vm.getSneakerList()
    }

    private fun initSearchBar() {
        binding?.searchBar?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchProduct(s)
            }
            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun searchProduct(s: CharSequence?) {
        vm.tempList.clear()
        for(i in vm.dataList){
            if(s != null && i.name!!.contains(s, ignoreCase = true))
                vm.tempList.add(i)
        }
        (binding?.rvSneakers?.adapter as? SneakersAdapter)?.updateDataList(vm.tempList)
        binding?.rvSneakers?.adapter?.notifyDataSetChanged()
    }

}