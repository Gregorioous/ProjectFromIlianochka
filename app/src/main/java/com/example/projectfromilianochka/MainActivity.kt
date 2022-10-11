package com.example.projectfromilianochka

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.example.projectfromilianochka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnKeyListener, RadioGroup.OnCheckedChangeListener {
    private var binding:ActivityMainBinding? = null

    var valueRepairCost = 0
    var valueCostMaterials = 0

    var valueSelectTypeRepair = 0
    var valueSelectTypeLift = 0
    var valueSelectLocation = 0

    var valuePlaceObject = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding?.enterPlace?.setOnKeyListener(this)
        binding?.enterDistanceObject?.setOnKeyListener(this)

        binding?.sectTypeRepair?.setOnCheckedChangeListener(this)
        binding?.sectLift?.setOnCheckedChangeListener(this)
        binding?.sectLocation?.setOnCheckedChangeListener(this)

        binding?.calculations?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {

            R.id.calculations -> {


                if (valueSelectTypeRepair > 0 && valuePlaceObject > 0) {

                    valueRepairCost = (valueSelectTypeRepair * valuePlaceObject ) + valueSelectTypeLift + valueSelectLocation
                    val startCostRepair = Intent(this, CostRepair::class.java)
                    startCostRepair.putExtra("costRepair", valueRepairCost.toString())
                    startCostRepair.putExtra("costMaterials", (valueCostMaterials*valuePlaceObject).toString())
                    startCostRepair.putExtra("costLift", valueSelectTypeLift.toString())
                    startCostRepair.putExtra("costDelivery", valueSelectLocation.toString())
                    startActivity(startCostRepair)

                }

                else {
                    binding?.error?.text = getString(R.string.error)
                }


            }
        }
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when(view.id) {

            R.id.enterPlace -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    val place:String = binding?.enterPlace?.text?.toString()!!
                    valuePlaceObject = place.toInt()
                    binding?.enterPlace?.setText("")
                    return true
                }
            }

            R.id.enterDistanceObject -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    val countKM:String = binding?.enterDistanceObject?.text?.toString()!!
                    valueSelectLocation = countKM.toInt()*15
                    binding?.enterDistanceObject?.setText("")
                    binding?.sectionEnterDistanceObject?.visibility = View.GONE


                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    return true
                }
            }

        }

        return false
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedID: Int) {
        when(checkedID) {
            R.id.draftRepair -> {
                valueSelectTypeRepair=21
                valueCostMaterials=7
            }
            R.id.cosmetiqRepair -> {
                valueSelectTypeRepair=31
                valueCostMaterials=71
            }
            R.id.capitalRepair -> {
                valueSelectTypeRepair=46
                valueCostMaterials=80
            }
            R.id.euroRepair -> {
                valueSelectTypeRepair=58
                valueCostMaterials=90
            }
            R.id.notLift -> valueSelectTypeLift=50
            R.id.passengerLift -> valueSelectTypeLift = 100
            R.id.allLift -> valueSelectTypeLift = 0
            R.id.locationRostov -> {
                valueSelectLocation = 0

                if (binding?.sectionEnterDistanceObject?.visibility == View.VISIBLE) {
                    binding?.sectionEnterDistanceObject?.visibility = View.GONE

                }
            }
            R.id.locationRostovRegion ->
                binding?.sectionEnterDistanceObject?.visibility = View.VISIBLE


        }
    }


}