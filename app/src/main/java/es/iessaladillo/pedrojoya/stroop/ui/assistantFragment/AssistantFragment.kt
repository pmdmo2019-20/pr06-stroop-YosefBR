package es.iessaladillo.pedrojoya.stroop.ui.assistantFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.assistant_fragment.*

class AssistantFragment : Fragment(R.layout.assistant_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_activity, menu)
        return super.onCreateOptionsMenu(menu, inflater)

    }

    private fun setupViews() {
        setupAppBar()
        setupViewPager()
        setupTabLayoutMediator()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.assistant_title)
        }
    }

    private fun setupViewPager() {
        viewPager.adapter = AssistantFragmentAdapter(this)
    }

    private fun setupTabLayoutMediator() {
        TabLayoutMediator(tabLayout, viewPager) { _, _ ->
        }.attach()
    }

}