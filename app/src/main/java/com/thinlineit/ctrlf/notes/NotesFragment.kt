package com.thinlineit.ctrlf.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    private val noteViewModel by viewModels<NotesViewModel>()
    private val noteAdapter = NotesAdapter { noteId ->
        this.findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToPageFragment(noteId)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            (DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_notes,
                container,
                false
            ) as FragmentNotesBinding).apply {
                this.noteViewModel = this@NotesFragment.noteViewModel
                lifecycleOwner = this@NotesFragment
                noteListRecyclerView.adapter = noteAdapter
            }
        noteViewModel.alertLiveData.observe(viewLifecycleOwner) {
            //TODO: Check if response body is empty
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            Log.e("loadNote Exception", it)
        }
        return binding.root
    }
}