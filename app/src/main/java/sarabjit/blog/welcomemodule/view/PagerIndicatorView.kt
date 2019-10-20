package sarabjit.blog.welcomemodule.view

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntRange
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import sarabjit.blog.groovytestapplication.whatsnew.adapter.WelcomeAdapter
import sarabjit.blog.welcomemodule.R

class PagerIndicatorView : View {
    private val mIndicatorMargin = 40f
    private var mIndicatorPositionOffset = 0f
    private val mIndicatorHeight = 35
    private var mViewHeight = 100f
    private var mViewWidth = 100f
    private var mPageCount = 0
    private var mIndicatorSpacing = 100
    private var mIndicatorPosition = 0
    private var mActiveIndicator: Drawable? = null
    private var mInactiveIndicator: Drawable? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.PagerIndicatorView)
            mActiveIndicator =
                typedArray.getDrawable(R.styleable.PagerIndicatorView_indicator_active)
            mInactiveIndicator = typedArray.getDrawable(
                R.styleable.PagerIndicatorView_indicator_inactive
            )
            mActiveIndicator!!.setBounds(0, 0, 35, 35)
            mInactiveIndicator!!.setBounds(0, 0, 35, 35)
        }
    }

    fun setPageCount(@IntRange(from = 0) pageCount: Int) {
        if (pageCount != mPageCount && mActiveIndicator != null && mInactiveIndicator != null) {
            if (pageCount > MAX_DOTS_TO_SHOW) {
                throw UnsupportedOperationException("Maximum dots count is $MAX_DOTS_TO_SHOW")
            } else {
                mPageCount = pageCount
            }
            requestLayout()
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (mPageCount > 0) {
            canvas.save()
            canvas.translate(
                (mViewWidth - suggestedMinimumWidth.toFloat()) / 2f,
                (mViewHeight - suggestedMinimumHeight.toFloat()) / 2f
            )
            for (i in 0 until mPageCount) {
                mInactiveIndicator!!.draw(canvas)
                canvas.translate(mIndicatorSpacing.toFloat(), 0f)
            }
            canvas.translate(
                (mIndicatorPosition + mIndicatorPositionOffset - mPageCount)
                        * mIndicatorSpacing, 0f
            )
            mActiveIndicator!!.draw(canvas)
            canvas.restore()
        }
    }

    private fun setIndicatorPosition(position: Int) {
        setIndicatorPosition(position, 0f)
    }

    private fun setIndicatorPosition(position: Int, positionOffset: Float) {
        val newPosition = Math.min(Math.max(0, position), mPageCount - 1)
        var invalidate = false
        if (mIndicatorPosition != newPosition) {
            mIndicatorPosition = newPosition
            invalidate = true
        }
        if (mIndicatorPositionOffset != positionOffset) {
            mIndicatorPositionOffset = positionOffset
            invalidate = true
        }
        if (invalidate) {
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mViewWidth = getMeasuredWidth(widthMeasureSpec).toFloat()
        mViewHeight = getMeasuredHeight(heightMeasureSpec).toFloat()
        setMeasuredDimension(Math.round(mViewWidth), Math.round(mViewHeight))
    }

    private fun getMeasuredWidth(widthMeasureSpec: Int): Int {
        val mode = MeasureSpec.getMode(widthMeasureSpec)
        return when (mode) {
            MeasureSpec.AT_MOST -> Math.min(
                suggestedMinimumWidth,
                MeasureSpec.getSize(widthMeasureSpec)
            )
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(widthMeasureSpec)
            MeasureSpec.UNSPECIFIED -> suggestedMinimumWidth
            else -> suggestedMinimumWidth
        }
    }

    private fun getMeasuredHeight(heightMeasureSpec: Int): Int {
        val mode = MeasureSpec.getMode(heightMeasureSpec)
        return when (mode) {
            MeasureSpec.AT_MOST -> Math.min(
                suggestedMinimumHeight,
                MeasureSpec.getSize(heightMeasureSpec)
            )
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(heightMeasureSpec)
            MeasureSpec.UNSPECIFIED -> suggestedMinimumHeight
            else -> suggestedMinimumHeight
        }
    }

    override fun getSuggestedMinimumWidth(): Int {
        return (Math.round(mPageCount * mIndicatorSpacing - mIndicatorMargin)
                + paddingLeft + paddingRight)
    }

    override fun getSuggestedMinimumHeight(): Int {
        return mIndicatorHeight + paddingTop + paddingBottom
    }


    private lateinit var mViewPagerChangeListener: ViewPagerChangeListener

    fun setViewPager(viewPager: ViewPager2) {
        mViewPagerChangeListener = ViewPagerChangeListener()
        viewPager.registerOnPageChangeCallback(mViewPagerChangeListener)
        val adapter: WelcomeAdapter? = viewPager.adapter as WelcomeAdapter?
        adapter?.let {
            setPageCount(it.itemCount)
        }
    }

    private inner class ViewPagerChangeListener : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            setIndicatorPosition(position)
        }
    }

    companion object {
        private const val MAX_DOTS_TO_SHOW = 10
    }
}