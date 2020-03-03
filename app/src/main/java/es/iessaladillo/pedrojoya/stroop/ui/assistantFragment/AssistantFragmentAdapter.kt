package es.iessaladillo.pedrojoya.stroop.ui.assistantFragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUMBER_OF_FRAGMENTS = 8

class AssistantFragmentAdapter(parent: Fragment) : FragmentStateAdapter(parent) {
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> Assistant0Fragment.newInstance()
            1 -> Assistant1Fragment.newInstance()
            2 -> Assistant2Fragment.newInstance()
            3 -> Assistant3Fragment.newInstance()
            4 -> Assistant4Fragment.newInstance()
            5 -> Assistant5Fragment.newInstance()
            6 -> Assistant6Fragment.newInstance()
            7 -> Assistant7Fragment.newInstance()
            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS
}