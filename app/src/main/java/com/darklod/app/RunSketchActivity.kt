package com.darklod.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import processing.android.CompatUtils
import processing.android.PFragment
import processing.core.PApplet


class RunSketchActivity : AppCompatActivity() {

    companion object {
        var sketch: PApplet? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sketch)

        val frame = FrameLayout(this)
        frame.id = CompatUtils.getUniqueViewId()

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
        if (sketch != null) {
            sketch?.onRequestPermissionsResult(
                requestCode, permissions, grantResults
            )
        }
    }

    public override fun onNewIntent(intent: Intent) {
        if (sketch != null) {
            sketch?.onNewIntent(intent)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (sketch != null) {
            sketch?.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        if (sketch != null) {
            sketch?.onBackPressed()
        }
    }
}
