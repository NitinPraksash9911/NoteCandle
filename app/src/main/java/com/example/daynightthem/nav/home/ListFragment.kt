package com.example.daynightthem.nav.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.cardview.widget.CardView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.example.daynightthem.R
import com.example.daynightthem.R.integer
import com.example.daynightthem.R.string
import com.example.daynightthem.databinding.FragmentListBinding
import com.example.daynightthem.nav.datasource.NoteStore
import com.example.daynightthem.nav.datasource.model.NoteInfo
import com.example.daynightthem.nav.datasource.model.NoteInfo.Calendar
import com.example.daynightthem.nav.datasource.model.NoteInfo.Note
import com.example.daynightthem.nav.datasource.model.NoteInfo.Reminder
import com.example.daynightthem.nav.datasource.model.NoteInfo.TodoData
import com.example.daynightthem.nav.home.adapter.MyNoteAdapter
import com.example.daynightthem.nav.home.adapter.MyRecyclerViewAnimator
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import kotlin.math.absoluteValue

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private var tracker: SelectionTracker<Long>? = null

    private val binding get() = _binding!!

    lateinit var emailAdapter: MyNoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Postpone enter transitions to allow shared element transitions to run.
        // https://github.com/googlesamples/android-architecture-components/issues/495
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        initEmailRecyclerView()


        binding.searchCardView.setOnClickListener {

            navigateToSearch(binding.searchCardView)
        }

        binding.fab.setOnClickListener {
            MenuBottomSheetDialogFragment
                .newInstance(R.menu.email_bottom_sheet_menu)
                .show(parentFragmentManager, null)
        }

        initFilterChips()

    }

    private fun initFilterChips() {

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {

                R.id.all_note -> {

                    NoteStore.getNoteFilteredList<NoteInfo>().apply {
                        emailAdapter.submitList(this)
                    }

                }
                R.id.notes -> {

                    NoteStore.getNoteFilteredList<Note>().apply {

                        emailAdapter.submitList(this)
                    }
                }
                R.id.calender -> {
                    NoteStore.getNoteFilteredList<Calendar>().apply {
                        emailAdapter.submitList(this)
                    }
                }
                R.id.todo -> {
                    NoteStore.getNoteFilteredList<TodoData>().apply {
                        emailAdapter.submitList(this)
                    }
                }
                R.id.reminder -> {

                    NoteStore.getNoteFilteredList<Reminder>().apply {
                        emailAdapter.submitList(this)
                    }
                }

            }

        }

    }

    private fun initEmailRecyclerView() {

        emailAdapter = MyNoteAdapter { cardView, noteInfo ->

            // Set exit and reenter transitions here as opposed to in onCreate because these transitions
            // will be set and overwritten on HomeFragment for other navigation actions.
            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(integer.reply_motion_duration_large).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(integer.reply_motion_duration_large).toLong()
            }

            val emailCardDetailTransitionName = getString(string.email_card_detail_transition_name)
            val extras = FragmentNavigatorExtras(cardView to emailCardDetailTransitionName)
            val directions = ListFragmentDirections.actionListFragmentToEmailFragment(noteInfo!!.id)
            findNavController().navigate(directions, extras)
        }


        binding.recyclerView.apply {

            itemAnimator = MyRecyclerViewAnimator()
            adapter = emailAdapter



            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val offset = recyclerView.computeVerticalScrollOffset()
                    val extent = recyclerView.computeVerticalScrollExtent()
                    val range = recyclerView.computeHorizontalScrollRange()

                    val percentage = offset.toFloat() / (range.toFloat() - extent.toFloat()).absoluteValue

                    Log.d("asdsf", "onScrolled: $percentage")
                    binding.motionLayout.progress = percentage.times(2)
                }
            })

        }

        NoteStore.notes.observe(viewLifecycleOwner) {
            emailAdapter.submitList(it)
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        tracker?.onSaveInstanceState(outState)
//        outState.putFloat("currentState", binding.motionLayout.progress)
    }

    fun hide() {
        binding.searchCardView.animate().translationY(-binding.searchCardView.height.toFloat().times(5))
            .setInterpolator(AccelerateInterpolator(2f)).setDuration(500).start()

        binding.fab.animate().translationX(binding.fab.height.toFloat().times(5))
            .setInterpolator(AccelerateInterpolator(2f)).setDuration(500).start()

    }

    fun show() {
        binding.searchCardView.animate().translationY(0f).setInterpolator(DecelerateInterpolator(2f)).setDuration(500).start()

        binding.fab.animate().translationX(0f).setInterpolator(DecelerateInterpolator(2f)).setDuration(500).start();

    }

    private fun navigateToSearch(view: CardView) {

        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        val searchBarTransitionName = getString(R.string.tansitionNameSearchBar)
        val extras = FragmentNavigatorExtras(view to searchBarTransitionName)
        val directions = ListFragmentDirections.actionListFragmentToSearchFragment()
        findNavController().navigate(directions, extras)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}