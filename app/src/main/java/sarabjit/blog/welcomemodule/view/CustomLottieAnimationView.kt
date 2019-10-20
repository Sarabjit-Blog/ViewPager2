package sarabjit.blog.groovytestapplication.whatsnew.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import com.airbnb.lottie.LottieAnimationView

class CustomLottieAnimationView : LottieAnimationView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun prepareAnimation(animation: Int) {
        setAnimation(animation)
    }

    /**
     * Sets up the animation
     *
     * @param playAnimation Pass true to play the animation after set up
     * @param repeatCount   Loops the animation, pass `LottieDrawable.INFINITE` to loop indefinitely
     */
    fun showAnimation(@NonNull playAnimation: Boolean, repeatCount: Int) {
        cancelAnimation()
        setRepeatCount(repeatCount)
        if (playAnimation) {
            playAnimation()
        }
    }
}