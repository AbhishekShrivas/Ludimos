package com.ludimosdemo.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


class StumplineView@JvmOverloads
constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr){
    var path = Path()
    private var paint = Paint().apply {
        color = Color.parseColor("#7562D3")
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
//        path.moveTo(0.49f * width, 0.365f * height)
//        path.lineTo(0.462f * width, 0.575f * height)
//        path.lineTo(0.535f * width, 0.575f * height)
//        path.lineTo(0.512f * width, 0.365f * height)
        Log.e("stumpline","$width $height")
        path.moveTo(0.490267639902676f * width,  0.368229166666667f * height)
        path.lineTo(0.461070559610706f * width, 0.571875f * height)
        path.lineTo(0.542579075425791f * width, 0.571875f * height)
        path.lineTo(0.51338199513382f * width, 0.368229166666667f * height)

        canvas?.drawPath(path, paint)
    }

}
