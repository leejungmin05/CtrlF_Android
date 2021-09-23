package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityPageBinding
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.activity_page.slidingPaneLayout
import kotlinx.android.synthetic.main.fragment_topic_title.titleListToolBar

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
        pageViewModel.openSlidingPane.observe(this) {
            if (it == true && slidingPaneLayout.isSlideable) {
                slidingPaneLayout.open()
                pageViewModel.closeSliding()
            }
        }

        setSupportActionBar(titleListToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // TODO: Do not use deprecated methods.
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        dpWidth = if (outMetrics.widthPixels > 1080) (outMetrics.widthPixels / density) / 6
        else (outMetrics.widthPixels / density) / 3
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.userCircleBtn -> {
            /*
            findNavController().navigate(
                PageActivityDirections.actionPageFragmentToLogoutFragment()
            )
             */
            true
        }
        else -> super.onOptionsItemSelected(item)
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
        var dpWidth by Delegates.notNull<Float>()
    }
}
