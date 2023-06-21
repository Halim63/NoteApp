package com.noteapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.noteapp.R
import com.noteapp.models.NotesEntity
import dagger.hilt.android.AndroidEntryPoint
//import com.example.noteapp.worker.ReminderWorker
import kotlinx.android.synthetic.main.fragment_home.fb_add_note
import kotlinx.android.synthetic.main.fragment_home.recycler_view
import kotlinx.android.synthetic.main.fragment_home.tv_no_notes_available
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var adaptor: NotesAdapter
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      periodicRequest()
        initView()
        setupObservers()
    }

    private fun setupObservers() {
        homeViewModel.noteLiveData.observe(viewLifecycleOwner) { noteObserve ->
            adaptor.submitList(noteObserve)
            updateUI(noteObserve)
        }
    }

    private fun deleteNote(note: NotesEntity) = homeViewModel.deleteNote(note)

    private fun initView() {
        setUpNoteRecyclerView()
        fb_add_note.setOnClickListener {
            setUpNavigation()

        }
    }

    private fun setUpNavigation() {
        val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
        findNavController().navigate(action)
    }

    private fun setUpNoteRecyclerView() {
        recycler_view.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adaptor = NotesAdapter()
        recycler_view.adapter = adaptor
        setupRecyclerViewSwapActions()

    }

    private fun setupRecyclerViewSwapActions() {
        ItemTouchHelper(
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    deleteNote(adaptor.currentList[viewHolder.adapterPosition])
                }

            }).attachToRecyclerView(recycler_view)

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getNotes()
    }

    private fun updateUI(note: List<NotesEntity>) {
        isRecyclerViewVisible(note)

    }

    private fun isRecyclerViewVisible(note: List<NotesEntity>) {
        if (note.isNotEmpty()) {
            recycler_view.visibility = View.VISIBLE
            tv_no_notes_available.visibility = View.GONE
        } else {
            recycler_view.visibility = View.GONE
            tv_no_notes_available.visibility = View.VISIBLE
        }
    }

//    fun periodicRequest() {
//        val myWorkRequest =
//            PeriodicWorkRequest.Builder(
//                ReminderWorker::class.java,
//                60,
//                TimeUnit.MINUTES)
//                .addTag("my_id")
//                .build()
//        WorkManager.getInstance(requireContext())
//            .enqueueUniquePeriodicWork("my_id", ExistingPeriodicWorkPolicy.KEEP, myWorkRequest)
//
//    }


}