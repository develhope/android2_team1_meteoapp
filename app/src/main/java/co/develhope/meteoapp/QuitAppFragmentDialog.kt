package co.develhope.meteoapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class QuitAppFragmentDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder
            .setTitle(getString(R.string.title_dialog_quit))
            .setMessage(getString(R.string.alert_dialog_message))
            .setPositiveButton(getString(R.string.alert_dialog_positive)){_, _ -> activity?.finish()}
            .setNegativeButton(getString(R.string.alert_dialog_negative)){dialog, _ -> dialog.dismiss()}
        return builder.create()

    }


}