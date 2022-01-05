package com.example.daynightthem.nav.email

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.daynightthem.R
import com.example.daynightthem.databinding.FragmentEmailBinding
import com.example.daynightthem.nav.datasource.EmailStore
import com.example.daynightthem.util.themeColor
import com.google.android.material.transition.MaterialContainerTransform
import kotlin.LazyThreadSafetyMode.NONE

class EmailFragment : Fragment() {

    lateinit var binding: FragmentEmailBinding
    private val args: EmailFragmentArgs by navArgs()

    private val noteId: Long by lazy(NONE) { args.noteId }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEmailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationIcon.setOnClickListener {
            findNavController().navigateUp()
        }

        val email = EmailStore.get(noteId)
        if (email == null) {
            showError()
            return
        }

        binding.run {
            this.email = email

        }
    }

    private fun showError() {

    }

}