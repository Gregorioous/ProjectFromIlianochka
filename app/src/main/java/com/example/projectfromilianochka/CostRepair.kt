package com.example.projectfromilianochka

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projectfromilianochka.databinding.ActivityCostRepairBinding

class CostRepair : AppCompatActivity() {
    private var binding: ActivityCostRepairBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCostRepairBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.costRepair?.text = getString(R.string.resCostRepair) + " " + intent.getStringExtra("costRepair") + getString(R.string.currency)
        binding?.costMaterials?.text = getString(R.string.resCostMaterials) + " " + intent.getStringExtra("costMaterials") + getString(R.string.currency)
        binding?.costLift?.text = getString(R.string.resCostLift) + " " + intent.getStringExtra("costLift") + getString(R.string.currency)
        binding?.costDelivery?.text = getString(R.string.resCostDelivery) + " " + intent.getStringExtra("costDelivery") + getString(R.string.currency)

        binding?.whatsapp?.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/igerasimov4"))
            startActivity(browserIntent)
        })
    }
}