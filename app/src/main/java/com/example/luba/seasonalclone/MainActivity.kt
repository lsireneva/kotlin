package com.example.luba.seasonalclone

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.luba.seasonalclone.adapters.ProductAdapter
import com.example.luba.seasonalclone.models.ProduceItem
import com.example.luba.seasonalclone.source.getProduceItemsFromJson
import com.example.luba.seasonalclone.utils.bindView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by bindView(R.id.rvProducts)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //set up LLM and add to RV
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        //set up test data
        /*val items = listOf(ProduceItem("Apples", "Fruit", "Apples are red."),
                ProduceItem("Bananas", "Fruit", "Bananas are tasty."),
                ProduceItem("Broccoli", "Vegetable", "Broccoli looks like little trees."),
                ProduceItem("Carrot", "Vegetable", "Bunnies like carrots."))*/
        //items from Json
        val items = getProduceItemsFromJson(this)

        //create adapter
        val adapter = ProductAdapter(items)

        //add adapter to RV
        recyclerView.adapter = adapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
