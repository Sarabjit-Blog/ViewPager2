package sarabjit.blog.welcomemodule.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import sarabjit.blog.groovytestapplication.whatsnew.WelcomeScreensItem
import sarabjit.blog.groovytestapplication.whatsnew.adapter.WelcomeFragment
import sarabjit.blog.welcomemodule.R
import sarabjit.blog.welcomemodule.model.WelcomeModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val whatsNewDomainModel = WelcomeModel()
        val featureList = mutableListOf<WelcomeScreensItem>()
        featureList.add(WelcomeScreensItem("animation_one"))
        featureList.add(WelcomeScreensItem("animation_two"))
        whatsNewDomainModel.mNewFeatures = featureList
        whatsNewDomainModel.primaryButtonText = "Login"
        whatsNewDomainModel.secondaryButtonText = "Register"

        val bundle: Bundle = Bundle()
        bundle.putParcelable("domainModel", whatsNewDomainModel)
        val whatNewFragment = WelcomeFragment.newInstance(bundle);
        loadFragment(false, whatNewFragment)
    }

    /**
     * loads  Fragment to the View
     *
     * @param shouldAdd Whether Fragment needs to be added in backstack- true/false
     * @param fragment  Fragment to be Added
     */
    fun loadFragment(shouldAdd: Boolean, fragment: Fragment) {
        val mFragmentManager = supportFragmentManager
        val transaction = mFragmentManager.beginTransaction()
        if (shouldAdd) {
            transaction.addToBackStack(fragment.tag)
        }
        transaction.replace(R.id.fragmentContainer, fragment).commit()
    }


}