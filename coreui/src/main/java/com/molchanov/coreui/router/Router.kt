package com.molchanov.coreui.router

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import javax.inject.Inject

class Router @Inject constructor() : IRouter {

    override fun addFragment(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String
    ) {

        val oldFragment = fragmentManager.findFragmentByTag(tag)

        if (oldFragment == null) {
            fragmentManager.beginTransaction()
                .add(fragmentRepId, fragment, tag)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, oldFragment, tag)
                .commit()
        }
    }

    override fun replaceFragment(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String
    ) {

        val oldFragment = fragmentManager.findFragmentByTag(tag)

        if (oldFragment == null) {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, fragment, tag)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, oldFragment, tag)
                .commit()
        }
    }

    override fun replaceFragmentWithMessage(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String,
        message: Bundle
    ) {

        val oldFragment = fragmentManager.findFragmentByTag(tag)

        try {
            if (oldFragment == null) {
                fragmentManager.beginTransaction()
                    .add(fragmentRepId, fragment::class.java, message, tag)
                    .commit()
            } else {
                fragmentManager.beginTransaction()
                    .replace(fragmentRepId, oldFragment::class.java, message, tag)
                    .commit()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun deleteFragment(
        fragmentManager: FragmentManager, fragmentRepId: Int,
        fragment: Fragment, tag: String
    ) {

        fragmentManager.findFragmentByTag(tag)?.let {
            fragmentManager.beginTransaction()
                .remove(it)
                .commit()
        }
    }
}