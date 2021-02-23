package com.articles.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.articles.R

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

object DialogUtils {

    fun showProgressLoader(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.window?.run {
            this.requestFeature(Window.FEATURE_NO_TITLE)
            this.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            this.setDimAmount(0.3f)
        }
        dialog.setContentView(R.layout.dialog_progress_loader)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        return dialog
    }
}