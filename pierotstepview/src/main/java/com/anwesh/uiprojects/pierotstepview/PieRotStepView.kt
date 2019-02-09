package com.anwesh.uiprojects.pierotstepview

/**
 * Created by anweshmishra on 10/02/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.app.Activity
import android.content.Context

val nodes : Int = 5
val lines : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val sizeFactor : Float = 2.9f
val strokeFactor : Int = 90
val foreColor : Int = Color.parseColor("#1976D2")
val backColor : Int = Color.parseColor("#BDBDBD")
val rotDeg : Float = 60f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + (scaleFactor()) * b.inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * scGap * dir
fun Int.sf() : Float = 1f - 2 * this

fun Canvas.drawPRSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val hGap : Float = size * Math.cos(rotDeg * Math.PI / 180).toFloat()
    val wGap : Float = size * Math.sin(rotDeg * Math.PI / 180).toFloat()
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, gap * (i + 1))
    drawLine(-wGap * sc2, hGap, wGap * sc2, hGap, paint)
    for (j in 0..(lines - 1)) {
        save()
        translate(j * 2 * wGap, 0f)
        rotate(rotDeg * sc1.divideScale(j, lines) * j.sf())
        drawLine(0f, 0f, 0f, -size, paint)
        restore()
    }
    restore()
}