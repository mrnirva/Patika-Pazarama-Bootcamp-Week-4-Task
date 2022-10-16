package com.arcapp.weatherapp.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.arcapp.retrofitexample.api.ApiClient
import com.arcapp.weatherapp.R
import com.arcapp.weatherapp.databinding.FragmentSearchLocationBinding
import com.arcapp.weatherapp.data.model.location.LocationModel
import com.arcapp.weatherapp.data.preference.AppPref
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchLocationFragment : Fragment() {

    // using view binding
    private lateinit var bnd:FragmentSearchLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // view binding inflate design
        bnd = FragmentSearchLocationBinding.inflate(layoutInflater, container, false)
        return bnd.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        bnd.ivSearch.setOnClickListener {
            prepareSearch(bnd.etSearch.text.toString())
        }

        // Method that makes a search when you press the search key on the keyboard
        bnd.etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                prepareSearch(bnd.etSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

    }

    // Check search data
    private fun prepareSearch(locationName: String){

        if(locationName.isEmpty()){
            Toast.makeText(requireContext(), resources.getText(R.string.location_cannot_empty), Toast.LENGTH_SHORT).show()
            return
        }

        getLocation(locationName)

    }

    // Service that brings the values of the location such as lat lon according to the entered data
    private fun getLocation(locationName: String){

        ApiClient.getApiService().getLocation(locationName, 1).enqueue(object:
            Callback<List<LocationModel>> {

            override fun onResponse(call: Call<List<LocationModel>>, response: Response<List<LocationModel>>) {

                if(response.isSuccessful){

                    Log.e("getLocation","Response: ${response.body()}")

                    if(!response.body().isNullOrEmpty()){

                        response.body().let {

                            if(it != null){
                                saveData(it[0])
                            }

                        }

                    }else{
                        Log.e("getLocation","response.body() empty")
                        Toast.makeText(requireContext(), resources.getText(R.string.location_not_found), Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Log.e("getLocation","response.code() -> ${response.code()}")
                    Log.e("getLocation","response.code() -> ${response.message()}")
                }

            }

            override fun onFailure(call: Call<List<LocationModel>>, t: Throwable) {

                t.printStackTrace()
                Log.e("getLocation","onFailure -> ${t.message}")

            }

        })

    }

    // Method that writes location data to memory
    private fun saveData(locationModel: LocationModel){

        val ap = AppPref(requireContext())

        CoroutineScope(Dispatchers.Main).launch {

            locationModel.name?.let { ap.setLocationName(it) }
            locationModel.lat?.let { ap.setLat(it) }
            locationModel.lon?.let { ap.setLon(it) }

            findNavController().navigate(R.id.action_searchLocationFragment_to_weatherFragment)

        }

    }

}