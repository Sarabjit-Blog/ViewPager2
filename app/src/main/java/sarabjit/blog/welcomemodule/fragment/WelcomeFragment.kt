package sarabjit.blog.groovytestapplication.whatsnew.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.layout_what_new.*
import sarabjit.blog.welcomemodule.R
import sarabjit.blog.welcomemodule.model.WelcomeModel

class WelcomeFragment : Fragment() {
    private var mWelcomeModel: WelcomeModel? = null

    companion object {
        fun newInstance(bundle: Bundle?): WelcomeFragment {
            val whatNewFragment = WelcomeFragment()
            whatNewFragment.arguments = bundle
            return whatNewFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_what_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mWelcomeModel = requireArguments().getParcelable("domainModel")
        mWelcomeModel?.let {
            whatNewPages.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            whatNewPages.adapter = WelcomeAdapter(it.mNewFeatures)
            pageIndicator.setViewPager(whatNewPages)
            primary_button_text.text = it.primaryButtonText
            secondary_button_text.text = it.secondaryButtonText
        }
    }
}