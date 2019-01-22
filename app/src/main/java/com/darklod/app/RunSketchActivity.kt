package com.darklod.app

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import processing.android.CompatUtils
import processing.android.PFragment
import processing.core.PConstants.LANDSCAPE


class RunSketchActivity : AppCompatActivity() {

    private lateinit var sketch : Sketch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the current sketch
        sketch = intent.getSerializableExtra("sketch") as Sketch

        requestedOrientation = sketch.orientation

        // Initialize the layout
        val relative = RelativeLayout(this)
        val frame = FrameLayout(this)
        val backButton = FloatingActionButton(this)
        val redrawButton = FloatingActionButton(this)

        relative.id = CompatUtils.getUniqueViewId()
        frame.id = CompatUtils.getUniqueViewId()
        backButton.id = CompatUtils.getUniqueViewId()
        redrawButton.id = CompatUtils.getUniqueViewId()

        redrawButton.setImageResource(R.drawable.more)
        redrawButton.setOnClickListener {
            this.reload()
        }

        // TODO: GITHUB link in the app

        // TODO: HORIZONTAL EXPAND BUTTON
        // TODO: More -> Back, Redraw
        // TODO: CHANGE FRAGMENT TRANSITION IF POSSIBLE

        backButton.setImageResource(R.drawable.back)
        backButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        backButton.setOnClickListener {
            this.finish()
        }

        val params =
            RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(16, 16, 16, 16)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, backButton.id)
        params.addRule(RelativeLayout.ALIGN_PARENT_START, backButton.id)

        relative.addView(frame)
        relative.addView(backButton, params)
        relative.addView(redrawButton)


        //requestedOrientation =  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // set content view
        setContentView(
            relative, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        val fragment = PFragment(sketch)
        fragment.setView(relative, this)
    }

    private fun reload() {
        //if (Build.VERSION.SDK_INT >= 11) {
        //    sketch.activity.recreate()
        //    sketch.dispose()
        //}
        //else {
            overridePendingTransition(0, 0)
            intent.putExtra("sketch", MainActivity.selectedSketch)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            sketch.activity.finish()
            overridePendingTransition(0, 0)
            sketch.activity.startActivity(intent)
        //}
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        sketch.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    public override fun onNewIntent(intent: Intent) {
        sketch.onNewIntent(intent)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        sketch.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        sketch.onBackPressed()
    }
}
