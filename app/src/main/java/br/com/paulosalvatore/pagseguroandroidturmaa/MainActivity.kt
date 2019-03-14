package br.com.paulosalvatore.pagseguroandroidturmaa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLoadImage.setOnClickListener {
            loadImage()
        }
    }

    private fun loadImage() {
        val pickIntent = Intent(Intent.ACTION_GET_CONTENT)
        pickIntent.type = "image/*"

        val pickGalleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        val chooserIntent = Intent.createChooser(pickIntent, "Select an image")
        chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                arrayOf(pickGalleryIntent)
        )

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(chooserIntent)
        }
    }
}
