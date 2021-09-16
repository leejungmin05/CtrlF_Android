package com.thinlineit.ctrlf.page

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.page.PageActivity.Companion.dpWidth

enum class ButtonsState {
    GONE, RIGHT_VISIBLE
}

class ItemTouchHelperCallback(private val listener: ItemTouchHelperListener?) :
    ItemTouchHelper.Callback() {
    private var buttonsShowedState = ButtonsState.GONE
    private var currenrtItemViewHolder: RecyclerView.ViewHolder? = null

    private var buttonDelete: RectF? = null
    private var buttonCorrection: RectF? = null

    private var swipeOn = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val drag_flags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipe_flags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(drag_flags, swipe_flags)
    }
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener!!.onItemSwipe(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        var dX = dX
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (buttonsShowedState != ButtonsState.GONE) {
                if (buttonsShowedState == ButtonsState.RIGHT_VISIBLE) dX = Math.min(dX, -2 * dpWidth)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            } else {
                setTouchListener(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
            if (buttonsShowedState == ButtonsState.GONE) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        currenrtItemViewHolder = viewHolder

        drawButtons(c, currenrtItemViewHolder!!)
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithOutPadding = dpWidth - 10
        val corners = 5f
        val itemView = viewHolder.itemView
        val buttonPaint = Paint()
        buttonDelete = null
        buttonCorrection = null

        if (buttonsShowedState == ButtonsState.RIGHT_VISIBLE) {
            val deleteButton = RectF(
                itemView.right - buttonWidthWithOutPadding,
                (itemView.top + 10).toFloat(),
                (itemView.right - 10).toFloat(),
                (itemView.bottom - 10).toFloat()
            )
            buttonPaint.color = Color.RED
            c.drawRoundRect(deleteButton, corners, corners, buttonPaint)
            drawText("삭제", c, deleteButton, buttonPaint)
            buttonDelete = deleteButton

            val correctionButton = RectF(
                itemView.right - ( 2 * buttonWidthWithOutPadding),
                (itemView.top + 10).toFloat(),
                (itemView.right - 10).toFloat() - buttonWidthWithOutPadding,
                (itemView.bottom - 10).toFloat()
            )
            buttonPaint.color = Color.BLUE
            c.drawRoundRect(correctionButton, corners, corners, buttonPaint)
            drawText("수정", c, correctionButton, buttonPaint)
            buttonCorrection = correctionButton
        }
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint) {
        val textSize = 25f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize
        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeOn) {
            swipeOn = false
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { v, event ->
            swipeOn = event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP
            if (swipeOn) {
                if (dX < -dpWidth) buttonsShowedState = ButtonsState.RIGHT_VISIBLE

                if (buttonsShowedState != ButtonsState.GONE) {
                    setTouchDownListener(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                    setItemsClickable(recyclerView, false)
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchDownListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchUpListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { v, event ->
            super@ItemTouchHelperCallback.onChildDraw(
                c,
                recyclerView,
                viewHolder,
                0f,
                dY,
                actionState,
                isCurrentlyActive
            )
            recyclerView.setOnTouchListener { v, event -> false }
            setItemsClickable(recyclerView, true)
            swipeOn = false
            if (listener != null && buttonDelete != null && buttonDelete!!.contains(
                    event.x,
                    event.y
                )
            ) { if (buttonsShowedState == ButtonsState.RIGHT_VISIBLE) {
                    listener.onDeleteClick(viewHolder.adapterPosition, viewHolder)
                }
            }
            if(listener != null && buttonCorrection != null && buttonCorrection!!.contains(
                    event.x,
                    event.y
                )
            ){
                if (buttonsShowedState == ButtonsState.RIGHT_VISIBLE) {
                    listener.onCorrectionClick(viewHolder.adapterPosition, viewHolder)
                }
            }
            buttonsShowedState = ButtonsState.GONE
            currenrtItemViewHolder = null
            false
        }
    }

    private fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {
        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }
}
