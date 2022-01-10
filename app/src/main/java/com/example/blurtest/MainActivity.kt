package com.example.blurtest

import android.R.id.message
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jgabrielfreitas.core.BlurImageView
import com.vansuita.gaussianblur.GaussianBlur
import jp.wasabeef.blurry.Blurry


class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var seekBar: SeekBar
    lateinit var bitmap: Bitmap
    lateinit var rotaion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageView = findViewById(R.id.image)

        seekBar = findViewById(R.id.seekBar)

        //imageView.setImageResource(R.drawable.gamer)

        Handler(Looper.getMainLooper()).postDelayed({
            bitmap = createBitmapFromView(imageView)
        },1500)

        seekBar.setOnSeekBarChangeListener(
                object : OnSeekBarChangeListener {
                    // When the progress value has changed
                    override fun onProgressChanged(
                            seekBar: SeekBar,
                            progress: Int,
                            fromUser: Boolean) {


                        // increment 1 in progress and
                        // increase the text size
                        // with the value of progress

                        if (fromUser) {

                            if (progress > 4) {
                                Blurry.with(this@MainActivity).radius(progress / 4).from(bitmap).into(imageView)
                            }

                        }


                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {

                        // This method will automatically
                        // called when the user touches the SeekBar
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {

                        // This method will automatically
                        // called when the user
                        // stops touching the SeekBar
                    }
                })


    }

    //*************************Convert any view into Bitmap*************************************//
    fun createBitmapFromView(v: View?): Bitmap {
        val bitmap = Bitmap.createBitmap(
                v!!.measuredWidth,
                v.measuredHeight,
                Bitmap.Config.ARGB_8888
        )
        val c = Canvas(bitmap)
        v.layout(v.left, v.top, v.right, v.bottom)
        v.draw(c)
        return bitmap
    }
}