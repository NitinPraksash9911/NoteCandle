package com.example.daynightthem.nav.home.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener


abstract class RvScrollListener: OnScrollListener() {

    private val HIDE_THRESHOLD = 100f
    private val SHOW_THRESHOLD = 50f

    private var scrollDist = 0
    private var isVisible = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        //  Check scrolled distance against the minimum
        if (isVisible && scrollDist > HIDE_THRESHOLD) {
            //  Hide fab & reset scrollDist
            onHide()
            scrollDist = 0
            isVisible = false
        } else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
            //  Show fab & reset scrollDist
            onShow()
            scrollDist = 0
            isVisible = true
        }

        //  Whether we scroll up or down, calculate scroll distance
        if (isVisible && dy > 0 || !isVisible && dy < 0) {
            scrollDist += dy
        }
    }

    abstract fun onShow()

    abstract fun onHide()

}