package co.develhope.meteoapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class ErrorPageFragmentDialog : DialogFragment() {
    private var performAction: (() -> Unit)? = null

    companion object {
        private const val FRAGMENT_TAG = "gift_dialog"

        fun show(fragmentManager: FragmentManager, performAction: () -> Unit): ErrorPageFragmentDialog {
            val dialog = ErrorPageFragmentDialog()
            dialog.performAction = performAction
            dialog.show(fragmentManager, FRAGMENT_TAG)
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireActivity(), theme) {
            override fun onBackPressed() {

            }

            override fun setCancelable(flag: Boolean) {
                super.setCancelable(false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return requireActivity().layoutInflater.inflate(R.layout.fragment_error_page, container)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.ThemeOverlay_Material_Light)
    }
}
