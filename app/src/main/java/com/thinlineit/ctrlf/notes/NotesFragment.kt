package com.thinlineit.ctrlf.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thinlineit.ctrlf.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    private val noteViewModel by viewModels<NotesViewModel>()
    private val noteAdapter = NotesAdapter(TYPE_VERTICAL) { noteId ->
        this.findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToPageActivity(noteId)
        )
    }
    // TODO : 앱바 버튼 클릭 시 로그아웃액티비티로 전환하는 로직 구현

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotesBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            this.noteViewModel = this@NotesFragment.noteViewModel
            lifecycleOwner = this@NotesFragment
            noteListRecyclerView.adapter = noteAdapter
        }
        noteViewModel.alertLiveData.observe(viewLifecycleOwner) {
            // TODO: Check if response body is empty
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            Log.e("loadNote Exception", it)
        }
        return binding.root
    }

    companion object {
        private const val TYPE_VERTICAL = 1
        private const val TYPE_HORIZONTAL = 2
    }
}
