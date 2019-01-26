package com.darklod.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import processing.android.PFragment
import java.io.File


class RunSketchActivity : AppCompatActivity() {

    private lateinit var sketch : Sketch
    private var isFabOpen = false

    private lateinit var menuButton : FloatingActionButton
    private lateinit var backButton : FloatingActionButton
    private lateinit var redrawButton : FloatingActionButton
    private lateinit var photoButton : FloatingActionButton
    private lateinit var homeButton : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sketch)

        // Get the current sketch
        sketch = intent.getSerializableExtra("sketch") as Sketch

        requestedOrientation = sketch.orientation

        menuButton = findViewById(R.id.fab_menu)
        backButton = findViewById(R.id.back)
        redrawButton = findViewById(R.id.reload)
        homeButton = findViewById(R.id.home)
        photoButton = findViewById(R.id.photo)

        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)

        val fragment = PFragment(sketch)
        fragment.setView(frameLayout, this)

        // TODO: GITHUB link in the app
        // TODO: CHANGE FRAGMENT TRANSITION IF POSSIBLE

        menuButton.setOnClickListener {
            if (!isFabOpen)
                showFabMenu()
            else
                closeFabMenu()
        }

        redrawButton.setOnClickListener {
            closeFabMenu()
            reload()
        }

        backButton.setOnClickListener {
            closeFabMenu()
            finish()
        }

        photoButton.setOnClickListener {
            val dir = getPublicAlbumStorageDir("sketches")

            val timestamp = System.currentTimeMillis() / 1000
            val filename = dir?.absolutePath + File.separator + timestamp.toString() + ".png"

            val file = File(filename)

            sketch.save(filename)

            if (file.exists())
               Toast.makeText(applicationContext, "Picture Saved", Toast.LENGTH_SHORT).show()

            closeFabMenu()
        }
    }

    private fun getPublicAlbumStorageDir(albumName: String): File? {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName)

        if (isStoragePermissionGranted()) {
            if (!file.exists() && !file.mkdir()) {
                Log.e("LOG", "Directory not created.")
            }
        } else {
            Log.e("LOG", "Missing Permission.")
        }


        return file
    }

    private fun isStoragePermissionGranted() : Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
                )
                false
            }
        } else {
            //permission is automatically granted on sdk < 23 upon installation
            true
        }
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

    private fun showFabMenu() {
        isFabOpen = true

        homeButton.visibility = View.VISIBLE
        redrawButton.visibility = View.VISIBLE
        photoButton.visibility = View.VISIBLE
        backButton.visibility = View.VISIBLE

        homeButton.animate()
            .setDuration(100)
            .alpha(1f)

        photoButton.animate()
            .setDuration(200)
            .alpha(1f)

        redrawButton.animate()
            .setDuration(300)
            .alpha(1f)

        backButton.animate()
            .setDuration(400)
            .alpha(1f)
    }

    private fun closeFabMenu() {
        isFabOpen = false

        homeButton.animate()
            .setDuration(400)
            .alpha(0f)
            .withEndAction {
                homeButton.visibility = View.INVISIBLE
            }

        photoButton.animate()
            .setDuration(300)
            .alpha(0f)
            .withEndAction {
                photoButton.visibility = View.INVISIBLE
            }

        redrawButton.animate()
            .setDuration(200)
            .alpha(0f)
            .withEndAction {
                redrawButton.visibility = View.INVISIBLE
            }

        backButton.animate()
            .setDuration(100)
            .alpha(0f)
            .withEndAction {
                backButton.visibility = View.INVISIBLE
            }
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
