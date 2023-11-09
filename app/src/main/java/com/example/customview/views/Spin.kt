package com.example.customview.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

private enum class FanSpeed(val label: Int) {
    OFF(1),
    LOW(2),
    MEDIUM(3),
    HIGH(4);

    fun next() = when(this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}
/**
 * You'll use these as part of drawing the dial indicators and labels.
 * */
private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35
/**
 * Create a new Kotlin class called DialView. Modify the class definition to extend View. Click on View and then
 * click the red bulb. Choose Add Android View constructors using '@JvmOverloads'. Android Studio adds the
 * constructor from the View class. The @JvmOverloads annotation instructs the Kotlin compiler to generate
 * overloads for this function that substitute default parameter values.
 * */
class Spin @JvmOverloads constructor(
    context: Context, attrs: AttributeSet
) : View(context, attrs) {


    private val oval = RectF(200F, 200F, 450F, 450F)


    private val paint = Paint()
    override fun onDraw(canvas: Canvas) {
        paint.style = Paint.Style.FILL

        paint.color = Color.RED
        canvas.drawArc(oval, 0F, 51F, true, paint)
        paint.color = resources.getColor(R.color.orange)
        canvas.drawArc(oval, 51F, 51F, true, paint)
        paint.color = resources.getColor(R.color.yellow)
        canvas.drawArc(oval, 102F, 51F, true, paint)
        paint.color = Color.GREEN
        canvas.drawArc(oval, 153F, 51F, true, paint)
        paint.color = resources.getColor(R.color.goluboy)
        canvas.drawArc(oval, 204F, 51F, true, paint)
        paint.color = Color.BLUE
        canvas.drawArc(oval, 255F, 51F, true, paint)
        paint.color = resources.getColor(R.color.purple )
        canvas.drawArc(oval, 306F, 54F, true, paint)
    }
}