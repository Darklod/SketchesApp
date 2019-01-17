package com.darklod.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class MainActivity : AppCompatActivity() {

    private val LIST_STATE_KEY = "recycler_list_state"
    private var listState: Parcelable? = null

    private lateinit var recyclerView : RecyclerView
    private lateinit var layoutManager : RecyclerView.LayoutManager
    private lateinit var spinner : Spinner

    private var sketches = Sketches.list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        layoutManager = LinearLayoutManager(applicationContext)
        spinner = findViewById(R.id.spinner)

        val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item)
        adapter.addAll(listOf("Sort By Date", "Sort By Title"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                when (pos) {
                    0 -> sketches.sortBy { x -> x.date }
                    1 -> sketches.sortBy { x -> x.title }
                }
                recyclerView.adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {  }
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val onItemClickInterface : GridAdapter.OnItemClickListener = object : GridAdapter.OnItemClickListener {
            override fun onItemClick(item: Sketch) {
                val intent = Intent(applicationContext, RunSketchActivity::class.java)
                intent.putExtra("sketch", item)
                startActivity(intent)
            }
        }

        val adapter = GridAdapter(sketches, onItemClickInterface)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        val linearLayout = LinearLayoutManager(applicationContext)
        val gridLayout = GridLayoutManager(applicationContext, 3)

        layoutManager = when (newConfig?.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> linearLayout
            Configuration.ORIENTATION_LANDSCAPE -> gridLayout
            else -> linearLayout
        }

        // Save layout state
        val state = Bundle()
        listState = layoutManager.onSaveInstanceState()
        state.putParcelable(LIST_STATE_KEY, listState)
        onSaveInstanceState(state)

        initRecyclerView()
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        // Save list state
        listState = layoutManager.onSaveInstanceState()
        state.putParcelable(LIST_STATE_KEY, listState)
    }

    override fun onRestoreInstanceState(state: Bundle?) {
        super.onRestoreInstanceState(state)
        // Retrieve list state and list/item positions
        if (state != null) {
            listState = state.getParcelable(LIST_STATE_KEY)
        }
    }

    override fun onResume() {
        super.onResume()
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState)
        }
    }
}
