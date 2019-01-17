package com.darklod.app

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import processing.android.CompatUtils
import processing.android.PFragment
import processing.core.PApplet


class RunSketchActivity : AppCompatActivity() {

    private lateinit var sketch : Sketch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the current sketch
        sketch = intent.getSerializableExtra("sketch") as Sketch

        // Initialize the layout
        val frame = FrameLayout(this)
        frame.id = CompatUtils.getUniqueViewId()

        // Create the back button for the sketch
        val backButton = FloatingActionButton(frame.context)

        backButton.setImageResource(R.drawable.back)
        backButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
        backButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        backButton.setOnClickListener {
            this.finish()
        }

        val params =
            RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(16, 16, 16, 16)
        params.addRule(RelativeLayout.ALIGN_BOTTOM)
        params.addRule(RelativeLayout.ALIGN_END)

        backButton.layoutParams = params

        frame.addView(backButton, params)

        setContentView(
            frame, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        val fragment = PFragment(sketch)
        fragment.setView(frame, this)
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
