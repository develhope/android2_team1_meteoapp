package co.develhope.meteoapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class QuitAppFragmentDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder
            .setTitle("Exit")
            .setMessage("Are you sure to quit MeteoApp?")
            .setPositiveButton("ok"){_, _ -> activity?.finish()}
            .setNegativeButton("dismiss"){dialog, _ -> dialog.dismiss()}
        return builder.create()

    }


}