package com.arcapp.weatherapp.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arcapp.weatherapp.R
import com.arcapp.weatherapp.constant.Constants
import com.arcapp.weatherapp.data.preference.AppPref
import com.arcapp.weatherapp.data.preference.SharedPref
import com.arcapp.weatherapp.databinding.FragmentSettingsBinding
import com.arcapp.weatherapp.enum.AppTheme
import com.arcapp.weatherapp.enum.UnitsOfMeasure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {

    // using view binding
    private lateinit var bnd:FragmentSettingsBinding
    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // view binding inflate design
        bnd = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return bnd.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        sharedPref = SharedPref(requireContext())
        showSharedData()

        bnd.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_weatherFragment)
        }

        bnd.llAppTheme.setOnClickListener {
            appThemeSelection()
        }

        bnd.llUnitsOfMeasure.setOnClickListener{
            unitsOfMeasureSelection()
        }

    }

    // Setting for system, dark and light themes
    // Custom AlertDialog and Custom Layout
    private fun appThemeSelection(){

        val alertDialogBuilder = AlertDialog.Builder(requireContext())

        val customLayout: View = layoutInflater.inflate(R.layout.layout_app_theme, null)

        val rbSystem = customLayout.findViewById<RadioButton>(R.id.rbSystem)
        val rbLight = customLayout.findViewById<RadioButton>(R.id.rbLight)
        val rbDark = customLayout.findViewById<RadioButton>(R.id.rbDark)

        // select current theme
        when(sharedPref.getAppTheme()){

            AppTheme.System.toString() -> {
                rbSystem.isChecked = true
            }

            AppTheme.Light.toString() -> {
                rbLight.isChecked = true
            }

            AppTheme.Dark.toString() -> {
                rbDark.isChecked = true
            }

            else -> rbSystem.isChecked = true

        }

        // save the selected theme and active
        rbSystem.setOnClickListener{
            sharedPref.setAppTheme(rbSystem.text.toString())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

        // save the selected theme and active
        rbLight.setOnClickListener{
            sharedPref.setAppTheme(rbLight.text.toString())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // save the selected theme and active
        rbDark.setOnClickListener{
            sharedPref.setAppTheme(rbDark.text.toString())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        alertDialogBuilder.setView(customLayout)
        alertDialogBuilder.show()

    }

    // Setting for units of measure -> Centigrade, Fahrenheit, Kelvin
    private fun unitsOfMeasureSelection(){

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        var alertDialog = alertDialogBuilder.create()

        val customLayout: View = layoutInflater.inflate(R.layout.layout_units_of_measure, null)

        val rbCentigrade = customLayout.findViewById<RadioButton>(R.id.rbCentigrade)
        val rbFahrenheit = customLayout.findViewById<RadioButton>(R.id.rbFahrenheit)
        val rbKelvin = customLayout.findViewById<RadioButton>(R.id.rbKelvin)

        // select the current unit
        when(sharedPref.getUnitOfMeasure()){

            UnitsOfMeasure.STANDART.toString() -> {
                rbKelvin.isChecked = true
            }

            UnitsOfMeasure.METRIC.toString() -> {
                rbCentigrade.isChecked = true
            }

            UnitsOfMeasure.IMPERIAL.toString() -> {
                rbFahrenheit.isChecked = true
            }

            else -> rbCentigrade.isChecked = true

        }

        // save the selected unit
        rbKelvin.setOnClickListener{
            sharedPref.setUnitOfMeasure(UnitsOfMeasure.STANDART.toString())
            showSharedData()
            alertDialog.cancel()
        }

        // save the selected unit
        rbCentigrade.setOnClickListener{
            sharedPref.setUnitOfMeasure(UnitsOfMeasure.METRIC.toString())
            showSharedData()
            alertDialog.cancel()
        }

        // save the selected unit
        rbFahrenheit.setOnClickListener{
            sharedPref.setUnitOfMeasure(UnitsOfMeasure.IMPERIAL.toString())
            showSharedData()
            alertDialog.cancel()
        }

        alertDialogBuilder.setView(customLayout)
        alertDialog = alertDialogBuilder.show()

    }

    // Get the theme and temperature units in memory
    private fun showSharedData(){

        bnd.tvThemeName.text = sharedPref.getAppTheme()

        when(sharedPref.getUnitOfMeasure()){

            UnitsOfMeasure.STANDART.toString() -> {
                bnd.tvUnitsOfMeasureName.text = requireContext().getString(R.string.kelvin)
            }

            UnitsOfMeasure.METRIC.toString() -> {
                bnd.tvUnitsOfMeasureName.text = requireContext().getString(R.string.centigrade)
            }

            UnitsOfMeasure.IMPERIAL.toString() -> {
                bnd.tvUnitsOfMeasureName.text = requireContext().getString(R.string.fahrenheit)
            }

            else -> bnd.tvUnitsOfMeasureName.text = requireContext().getString(R.string.centigrade)

        }

    }

}