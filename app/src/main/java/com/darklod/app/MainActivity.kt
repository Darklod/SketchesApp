package com.darklod.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        initRecyclerView(LinearLayoutManager(applicationContext))
    }

    private fun initRecyclerView(layout : RecyclerView.LayoutManager) {
        val onItemClickInterface : GridAdapter.OnItemClickListener = object : GridAdapter.OnItemClickListener {
            override fun onItemClick(item: Sketch) {
                RunSketchActivity.sketch = item
                startActivity(Intent(applicationContext, RunSketchActivity::class.java))
            }
        }

        val adapter = GridAdapter(Sketches.list, onItemClickInterface)

        recyclerView.layoutManager = layout
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        val linearLayout = LinearLayoutManager(applicationContext)
        val gridLayout = GridLayoutManager(applicationContext, 3)

        val layoutManager = when (newConfig?.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> linearLayout
            Configuration.ORIENTATION_LANDSCAPE -> gridLayout
            else -> linearLayout
        }

        initRecyclerView(layoutManager)
    }
}
