/**
 * Class for all types of alerts.
 */
package com.finalhints.mymandirdemo.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Html

object MtAlertsUtils {

    /**
     * AlertDialog creation generic method
     *
     * @param strTitle:                title of the alertDialog
     * @param strMessage:              message inside alert dialog
     * @param cancelable:              dialog is cancelable on touch or not.
     * @param strPositiveButtonText:   text of positive button
     * @param strNegativeButtonText:   text of negative button
     * @param strNeutralButtonText:    text of neutral button
     * @param onPositiveClickListener: positive click listener
     * @param onNegativeClickListener: negative click listener
     * @param onNeutralClickListener:  neutral click listener
     * @return alertDialog
     */
    fun showAlert(context: Context, strTitle: String, strMessage: Html, cancelable: Boolean,
                  strPositiveButtonText: String?,
                  strNegativeButtonText: String?,
                  strNeutralButtonText: String?, onPositiveClickListener: DialogOnClickListener?, onNegativeClickListener: DialogOnClickListener?, onNeutralClickListener: DialogOnClickListener?): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(strTitle)
        builder.setMessage(strMessage as CharSequence)

        if (strPositiveButtonText != null) {
            builder.setPositiveButton(strPositiveButtonText, null)
        }

        if (strNegativeButtonText != null) {
            builder.setNegativeButton(strNegativeButtonText, null)
        }

        if (strNeutralButtonText != null) {
            builder.setNeutralButton(strNeutralButtonText, null)
        }

        builder.setCancelable(cancelable)

        val alert = builder.create()
        alert.setOnShowListener {
            val btnPositive = alert.getButton(AlertDialog.BUTTON_POSITIVE)
            val btnNegative = alert.getButton(AlertDialog.BUTTON_NEGATIVE)
            val btnNeutral = alert.getButton(AlertDialog.BUTTON_NEUTRAL)


            btnPositive?.setOnClickListener {
                if (onPositiveClickListener != null) {
                    onPositiveClickListener.onClick(alert, AlertDialog.BUTTON_POSITIVE)
                } else {
                    alert.dismiss()
                }
            }
            btnNegative?.setOnClickListener {
                if (onNegativeClickListener != null) {
                    onNegativeClickListener.onClick(alert, AlertDialog.BUTTON_NEGATIVE)
                } else {
                    alert.dismiss()
                }
            }
            btnNeutral?.setOnClickListener {
                if (onNeutralClickListener != null) {
                    onNeutralClickListener.onClick(alert, AlertDialog.BUTTON_NEUTRAL)
                } else {
                    alert.dismiss()
                }
            }
        }
        alert.show()

        return alert
    }

    fun showAlert(context: Context, strTitle: String, strMessage: String, cancelable: Boolean,
                  strPositiveButtonText: String?,
                  strNegativeButtonText: String?,
                  strNeutralButtonText: String?, onPositiveClickListener: DialogOnClickListener?, onNegativeClickListener: DialogOnClickListener?, onNeutralClickListener: DialogOnClickListener?): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(strTitle)
        builder.setMessage(strMessage)

        if (strPositiveButtonText != null) {
            builder.setPositiveButton(strPositiveButtonText, null)
        }

        if (strNegativeButtonText != null) {
            builder.setNegativeButton(strNegativeButtonText, null)
        }

        if (strNeutralButtonText != null) {
            builder.setNeutralButton(strNeutralButtonText, null)
        }

        builder.setCancelable(cancelable)

        val alert = builder.create()
        alert.setOnShowListener {
            val btnPositive = alert.getButton(AlertDialog.BUTTON_POSITIVE)
            val btnNegative = alert.getButton(AlertDialog.BUTTON_NEGATIVE)
            val btnNeutral = alert.getButton(AlertDialog.BUTTON_NEUTRAL)


            btnPositive?.setOnClickListener {
                if (onPositiveClickListener != null) {
                    onPositiveClickListener.onClick(alert, AlertDialog.BUTTON_POSITIVE)
                } else {
                    alert.dismiss()
                }
            }
            btnNegative?.setOnClickListener {
                if (onNegativeClickListener != null) {
                    onNegativeClickListener.onClick(alert, AlertDialog.BUTTON_NEGATIVE)
                } else {
                    alert.dismiss()
                }
            }
            btnNeutral?.setOnClickListener {
                if (onNeutralClickListener != null) {
                    onNeutralClickListener.onClick(alert, AlertDialog.BUTTON_NEUTRAL)
                } else {
                    alert.dismiss()
                }
            }
        }
        alert.show()

        return alert
    }

    /**
     * @param strTitle:              title of the alertDialog
     * @param strMessage:            message inside alert dialog
     * @param cancelable:            dialog is cancelable on touch or not.
     * @param strPositiveButtonText: text  of positive button
     * @param strNegativeButtonText: text  of negative button
     * @param positiveClickListener: Click listener for positive button
     * @param negativeClickListener: Click listener for negative button
     * @return alertDialog
     */
    fun showAlert(context: Context, strTitle: String, strMessage: String, cancelable: Boolean,
                  strPositiveButtonText: String,
                  strNegativeButtonText: String, positiveClickListener: DialogOnClickListener, negativeClickListener: DialogOnClickListener): AlertDialog {
        return showAlert(context, strTitle, strMessage, cancelable, strPositiveButtonText, strNegativeButtonText, null, positiveClickListener, negativeClickListener, null)
    }

    /**
     * @param strTitle:             title of the alertDialog
     * @param strMessage:           message inside alert dialog
     * @param cancelable:           dialog is cancelable on touch or not.
     * @param strNeutralButtonText: text  of neutral button
     * @param alertClickListener:   neutral button click listener.
     * @return alertDialog
     */
    fun showAlert(context: Context, strTitle: String, strMessage: String,
                  cancelable: Boolean, strNeutralButtonText: String, alertClickListener: DialogOnClickListener): AlertDialog {
        return showAlert(context, strTitle, strMessage, cancelable, null, null,
                strNeutralButtonText, null, null, alertClickListener)
    }


    /**
     * @param strTitle:             title of the alertDialog
     * @param strMessage:           message inside alert dialog
     * @param strNeutralButtonText: text  of neutral button
     * @param alertClickListener:   neutral button click listener.
     * @return alertDialog
     */
    fun showAlert(context: Context, strTitle: String, strMessage: String,
                  strNeutralButtonText: String, alertClickListener: DialogOnClickListener): AlertDialog {
        return showAlert(context, strTitle, strMessage, false, null, null, strNeutralButtonText, null, null, alertClickListener)
    }

    fun showAlert(context: Context, strMessage: String): AlertDialog {

        val defaultTitle = context.getString(R.string.alertDefaultTitle)
        val defaultButtonText = context.getString(R.string.alertDefaultButtonText)

        return showAlert(context, defaultTitle, strMessage, false, null, null, defaultButtonText, null, null, null)
    }


    fun showAlert(context: Context, strTitle: String, strMessage: String): AlertDialog {

        val defaultButtonText = context.getString(R.string.alertDefaultButtonText)
        return showAlert(context, strTitle, strMessage, false, null, null, defaultButtonText, null, null, null)
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item on the dialog is clicked..
     */
    interface DialogOnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog The dialog that received the click.
         */
        fun onClick(dialog: DialogInterface, which: Int)
    }
}
