package com.thinlineit.ctrlf.page

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.TextView
import com.thinlineit.ctrlf.R
import kotlinx.android.synthetic.main.fragment_topic_title_dialog.*

class TopicFragmentDialog(context: Context) {
    private val dialog = Dialog(context)
    fun topicDialog() {
        dialog.setContentView(R.layout.fragment_topic_title_dialog)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.topicCorrectDeleteDialog.setText("토픽 이름 수정")
        dialog.editTextDialog.setHint("수정 요청 사유")
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()
    }
}