package com.anwesh.uiprojects.linkedpiestepview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.pierotstepview.PieRotStepView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PieRotStepView.create(this)
    }
}
