package com.thinlineit.ctrlf.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityPageBinding
import com.thinlineit.ctrlf.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_page.slidingPaneLayout

class PageActivity : AppCompatActivity() {
    private val binding: ActivityPageBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_page
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteId = intent.getIntExtra(NOTE_ID, 0)
        val viewModelFactory = PageViewModelFactory(noteId)
        val pageViewModel = ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java)
        binding.apply {
            this.pageViewModel = pageViewModel
            lifecycleOwner = this@PageActivity
        }
        pageViewModel.slidingOpen.observe(
            this,
            Observer {
                if (it == true && slidingPaneLayout.isSlideable) {
                    slidingPaneLayout.open()
                    pageViewModel.closeSliding()
                }
            }
        )

        val loadingDialog = LoadingDialog(this)

        pageViewModel.isLoading.observe(
            this,
            Observer {
                if (it) loadingDialog.show()
                else loadingDialog.dismiss()
            }
        )

        /*
        // floating button for 0.2.1 version
        binding.fabButton.setOnClickListener {
            val intent = Intent(this, PageEditorActivity::class.java)
            intent.putExtra(PAGEINFO, pageViewModel.pageInfo.value)
            startActivity(intent)
        }
         */
    }

    override fun onBackPressed() {
        if (slidingPaneLayout.isOpen && slidingPaneLayout.isSlideable) {
            slidingPaneLayout.close()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val NOTE_ID = "noteId"
        const val PAGEINFO = "pageInfo"
    }
}
