package com.example.day15_profilepage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.AttributeSet
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap

class DiagonalImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attributeSet, defStyleAttr) {

    companion object {
        private const val DEFAULT_ANGLE_SIZE = 0
    }

    @Px
    var angle: Float = context.dpToPx(DEFAULT_ANGLE_SIZE)
    var angleDirection: String = "right"

    private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewRect = Rect()

    private lateinit var resultBtm: Bitmap
    private lateinit var mascBtm: Bitmap
    private lateinit var srcBtm: Bitmap

    init {
        attributeSet?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.DiagonalImageView)
            angle = ta.getDimension(
                R.styleable.DiagonalImageView_heightDiagonal,
                context.dpToPx(DEFAULT_ANGLE_SIZE)
            )

            angleDirection = ta.getString(R.styleable.DiagonalImageView_angleDirection) ?: "right"
            ta.recycle()
        }
        scaleType = ScaleType.CENTER_CROP
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (w == 0) return

        with(viewRect) {
            left = 0
            top = 0
            right = w
            bottom = h
        }
        prepareBitmap(w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(resultBtm, viewRect, viewRect, null)
    }

    private fun prepareBitmap(w: Int, h: Int) {

        mascBtm = Bitmap.createBitmap(w, h, Bitmap.Config.ALPHA_8)
        resultBtm = mascBtm.copy(Bitmap.Config.ARGB_8888, true)
        val maskCanvas = Canvas(mascBtm)

        val path = Path()

        if (angleDirection == "left") {
            path.moveTo(0f, 0f)
            path.lineTo(w.toFloat(), 0f)
            path.lineTo(w.toFloat(), h.toFloat())
            path.lineTo(0f, h.toFloat() - angle)
        } else {
            path.moveTo(0f, 0f)
            path.lineTo(w.toFloat(), 0f)
            path.lineTo(w.toFloat(), h.toFloat() - angle)
            path.lineTo(0f, h.toFloat())
        }

        path.close()

        maskCanvas.drawPath(path, maskPaint)

        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        srcBtm = drawable.toBitmap(w, h, Bitmap.Config.ARGB_8888)

        val resultCanvas = Canvas(resultBtm)

        resultCanvas.drawBitmap(mascBtm, viewRect, viewRect, null)
        resultCanvas.drawBitmap(srcBtm, viewRect, viewRect, maskPaint)

    }

    fun Context.dpToPx(dp: Int): Float{
        return dp.toFloat() * this.resources.displayMetrics.density
    }
}