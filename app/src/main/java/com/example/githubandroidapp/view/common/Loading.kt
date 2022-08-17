package com.example.githubandroidapp.view.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.githubandroidapp.R

class Loading {
    companion object {
        private var mDialog: Dialog? = null

        fun setContext(context: Context) {
            mDialog = Dialog(context)
            mDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mDialog?.setCanceledOnTouchOutside(false)
            mDialog?.setContentView(R.layout.loading_full_screen)
        }

        fun clearContext() {
            mDialog = null
        }

        fun show() {
            mDialog?.show()
        }

        fun dismiss() {
            if (mDialog != null && mDialog?.isShowing == true) {
                mDialog?.dismiss()
            }
        }
    }
}