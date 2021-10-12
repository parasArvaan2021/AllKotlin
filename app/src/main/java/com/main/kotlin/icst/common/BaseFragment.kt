package com.main.kotlin.icst.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

class BaseFragment:Fragment() {
    fun navigateToOtherFragment(destinationId: Int, bundle: Bundle?) {
        lifecycleScope.launchWhenResumed {
            val navOptions1 = NavOptions.Builder().setPopUpTo(destinationId, true).build()
            findNavController().navigate(destinationId, bundle, navOptions1)
        }
    }
}