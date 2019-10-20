package sarabjit.blog.welcomemodule.model

import android.os.Parcel
import android.os.Parcelable
import sarabjit.blog.groovytestapplication.whatsnew.WelcomeScreensItem

class WelcomeModel() : Parcelable {
    lateinit var mNewFeatures: List<WelcomeScreensItem>

    lateinit var secondaryButtonText: String

    lateinit var primaryButtonText: String

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<WelcomeModel> = object : Parcelable.Creator<WelcomeModel> {
            override fun createFromParcel(source: Parcel): WelcomeModel =
                WelcomeModel(source)
            override fun newArray(size: Int): Array<WelcomeModel?> = arrayOfNulls(size)
        }
    }
}