package com.example.tacotuesdays

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tacotuesdays.databinding.ActivityTacoBinding

class TacoActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTacoBinding

    lateinit var tacoShopTextView: TextView
    lateinit var feedbackEditText: EditText

    private var tacoShopName: String? = null
    private var tacoShopUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTacoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        /*
        val navController = findNavController(R.id.nav_host_fragment_content_taco)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
         */

        //view
        tacoShopTextView = findViewById(R.id.tacoShopTextView)
        feedbackEditText = findViewById(R.id.feedbackEditText)

        //intent
        tacoShopName = intent.getStringExtra("tacoShopName")
        tacoShopUrl = intent.getStringExtra("tacoShopURL")

        tacoShopName?.let {Log.i("shop received", it)};
        tacoShopUrl?.let { Log.i("url received", it)};

        tacoShopName?.let {tacoShopTextView.text = "You should get tacos at $tacoShopName."}

        binding.fab.setOnClickListener { view -> loadWebsite()}
    }

    override fun onBackPressed()
    {
        val data = Intent()
        data.putExtra("feedback", feedbackEditText.text.toString())
        setResult(Activity.RESULT_OK, data)
        super.onBackPressed()
        finish()
    }

    private fun loadWebsite()
    {
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = tacoShopUrl?.let{ Uri.parse(tacoShopUrl)}

        //Verify the intent will resolve to an activity
        if (intent.resolveActivity(packageManager) != null)
        {
            startActivity(intent)
        }

        else
        {
            //message
        }
    }

    /*
    override fun onSupportNavigateUp(): Boolean
    {
        val navController = findNavController(R.id.nav_host_fragment_content_taco)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
     */
}