package sarabjit.blog.groovytestapplication.whatsnew.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.recyclerview.widget.RecyclerView
import sarabjit.blog.groovytestapplication.whatsnew.WelcomeScreensItem
import sarabjit.blog.welcomemodule.R

class WelcomeViewHolder(inflater: LayoutInflater, private val parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.layout_whats_news_pages,
            parent, false
        )
    ) {

    private var lottieView: CustomLottieAnimationView = itemView.findViewById(R.id.newPageSnapShot)

    fun setAnimation(welcomeScreenItem: WelcomeScreensItem) {
        val res: Resources = parent.context.resources
        @RawRes val animationID = res.getIdentifier(
            welcomeScreenItem.animationName, "raw",
            parent.context.packageName
        )
        lottieView.prepareAnimation(animationID)
    }

    fun showAnimation(showAnimation: Boolean, animationType: Int) {
        lottieView.showAnimation(showAnimation, animationType)
    }
}