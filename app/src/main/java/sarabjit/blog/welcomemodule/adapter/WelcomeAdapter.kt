package sarabjit.blog.groovytestapplication.whatsnew.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.airbnb.lottie.LottieDrawable
import sarabjit.blog.groovytestapplication.whatsnew.WelcomeScreensItem

class WelcomeAdapter(@NonNull val welcomeScreenItemResponseList: List<WelcomeScreensItem>) :
    Adapter<WelcomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelcomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WelcomeViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return welcomeScreenItemResponseList.size
    }

    override fun onBindViewHolder(holder: WelcomeViewHolder, position: Int) {
        val welcomeScreensItem: WelcomeScreensItem =
            welcomeScreenItemResponseList[holder.adapterPosition]
        holder.setAnimation(welcomeScreensItem)
    }

    override fun onViewAttachedToWindow(holder: WelcomeViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.showAnimation(true, LottieDrawable.INFINITE)
    }
}