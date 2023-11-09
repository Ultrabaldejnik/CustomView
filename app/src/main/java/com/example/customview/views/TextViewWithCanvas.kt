package com.example.customview.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.R


class TextViewWithCanvas(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 100f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        textPaint.textAlign = Paint.Align.CENTER

        val xPos = width / 2
        val yPos = height / 2 - (textPaint.descent() + textPaint.ascent()) / 2

        canvas.drawText("Hello", xPos.toFloat(), yPos.toFloat(), textPaint)
        canvas.save()
    }
}