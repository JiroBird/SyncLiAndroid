package com.github.jirobird.syncli.screens.syncli.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.github.jirobird.syncli.R
import com.github.jirobird.syncli.common.Dp

class SyncLiItemDecoration: RecyclerView.ItemDecoration() {


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val corner = Dp.toPx(16).toFloat()

        parent.children.forEach { child ->
            c.drawRoundRect(
                child.left.toFloat(),
                child.top.toFloat(),
                child.right.toFloat(),
                child.bottom.toFloat(),

                corner, corner,

                Paint().apply {
                    style = Paint.Style.FILL

                    @Suppress("DEPRECATION")
                    color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        parent.context.resources.getColor(R.color.primaryColor, null)
                    } else {
                        parent.context.resources.getColor(R.color.primaryColor)
                    }
                }
            )
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.indexOfChild(view)) {
//            0 -> {
//                outRect.bottom = Dp.toPx(8) //TODO: заменить на дименшены
//            }
//
//            lastItemIndex -> {
//                outRect.top = Dp.toPx(8) //TODO: заменить на дименшены
//            }
            else -> {
                outRect.bottom = Dp.toPx(8) //TODO: заменить на дименшены
                outRect.top = Dp.toPx(8) //TODO: заменить на дименшены
            }
        }

        outRect.left = Dp.toPx(8) //TODO: заменить на дименшены
        outRect.right = Dp.toPx(8) //TODO: заменить на дименшены
    }
}