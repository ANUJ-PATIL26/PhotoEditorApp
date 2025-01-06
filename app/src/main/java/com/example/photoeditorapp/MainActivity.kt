package com.example.photoeditorapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnApplyFilter: Button
    private lateinit var btnSave: Button
    private lateinit var originalBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        btnApplyFilter = findViewById(R.id.btnApplyFilter)
        btnSave = findViewById(R.id.btnSave)

        // Initialize with a bitmap (you can load an image from resources or storage)
        originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.sample_image) // Replace with your image
        imageView.setImageBitmap(originalBitmap)

        btnApplyFilter.setOnClickListener {
            val grayscaleBitmap = applyGrayscale(originalBitmap)
            imageView.setImageBitmap(grayscaleBitmap)
        }

        btnSave.setOnClickListener {
            saveImage() // Implement save logic if needed
        }
    }

    private fun applyGrayscale(original: Bitmap): Bitmap {
        val grayscaleBitmap = Bitmap.createBitmap(original.width, original.height, Bitmap.Config.RGB_565)

        for (x in 0 until original.width) {
            for (y in 0 until original.height) {
                val pixel = original.getPixel(x, y)
                val red = (pixel shr 16) and 0xFF
                val green = (pixel shr 8) and 0xFF
                val blue = pixel and 0xFF

                // Calculate the grayscale value
                val gray = (0.3 * red + 0.59 * green + 0.11 * blue).toInt()
                val newPixel = (0xFF shl 24) or (gray shl 16) or (gray shl 8) or gray

                grayscaleBitmap.setPixel(x, y, newPixel)
            }
        }

        return grayscaleBitmap
    }

    private fun saveImage() {
        // Implement the logic to save the grayscale image, for example, saving it to internal storage
    }
}
