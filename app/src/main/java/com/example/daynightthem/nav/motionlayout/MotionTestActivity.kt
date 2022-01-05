package com.example.daynightthem.nav.motionlayout

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.daynightthem.R
import com.example.daynightthem.databinding.ActivityMotionTestBinding

class MotionTestActivity : AppCompatActivity() {
    lateinit var binding:ActivityMotionTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotionTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val display = windowManager.defaultDisplay;
        val point= Point();
        display.getSize(point);
        val  width = point.x
       val halfW = width/2.0f

       val lftToRgt = ObjectAnimator.ofFloat( binding.btn,"translationX",width.toFloat(), 0f)
            .setDuration(300); // to animate left to right
        val rgtToLft = ObjectAnimator.ofFloat( binding.btn,"translationX",0f ,-width.toFloat() )
            .setDuration(300); // to animate right to left


        binding.btn.setOnClickListener {

            AnimatorSet().apply {
                interpolator = LinearInterpolator()
                play(rgtToLft).before(lftToRgt)
                start()
            }
        }
    }
}