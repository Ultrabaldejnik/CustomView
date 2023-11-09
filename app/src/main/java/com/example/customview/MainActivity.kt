package com.example.customview

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.customview.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isSpinning = false
    private var degree = 0

    private val sectors = mutableListOf(
        "red" to TEXT, "orange" to PICTURE, "yellow" to TEXT,
        "green" to PICTURE, "goluboy" to TEXT, "blue" to PICTURE,
        "purple" to TEXT
    )
    private val random = Random()
    private val sectorDegrees = mutableListOf(sectors.size)
    private val pictures = mutableListOf(
        "https://placekitten.com/640/360",
        "https://baconmockup.com/640/360",
        "https://placebeard.it/640x360",
        "https://placebear.com/640/360"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        getDegreeForSectors()

        binding.spinBtn.setOnClickListener {
            if (!isSpinning) {
                spin()
                isSpinning = true
            }
        }
    }

    private fun spin() {
        degree = random.nextInt(sectors.size)

        val resultSector = sectors[sectors.size - (degree + 1)]

        val rotateAnimation = RotateAnimation(
            0f,
            (360 * sectors.size) + sectorDegrees[degree].toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )

        with(rotateAnimation) {
            duration = 3600
            fillAfter = true
            interpolator = DecelerateInterpolator()

            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    hideUI()
                }

                override fun onAnimationEnd(p0: Animation?) {
                    Toast.makeText(
                        this@MainActivity,
                        "Got ${resultSector.first + " " + resultSector.second}",
                        Toast.LENGTH_SHORT
                    ).show()
                    isSpinning = false
                    setImageOrText(resultSector)
                }

                override fun onAnimationRepeat(p0: Animation?) {}
            })
        }


        binding.dialView.startAnimation(rotateAnimation)
    }

    private fun hideUI(){
        with(binding){
            text.visibility = View.GONE
            image.visibility = View.GONE
        }
    }
    private fun getDegreeForSectors() {
        val sectorDegree = 360 / sectors.size
        for (i in 1..sectors.size) {
            sectorDegrees.add((i + 1) * sectorDegree)
        }
    }

    private fun setImageOrText(item: Pair<String, String>) {
        when (item.second) {
            PICTURE -> setImage()
            TEXT -> setText()
        }
    }

    private fun setImage() {
        val image = pictures.random()
        binding.image.visibility = View.VISIBLE
        Glide.with(this)
            .load(image)
            .fitCenter()
            .into(binding.image)
    }

    private fun setText() {
        binding.text.visibility = View.VISIBLE
    }

    companion object {
        private const val PICTURE = "picture"
        private const val TEXT = "text"
    }
}