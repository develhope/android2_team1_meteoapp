package co.develhope.meteoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import co.develhope.meteoapp.databinding.FragmentErrorPageBinding
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding

class ErrorPageFragment : DialogFragment() {
    companion object {
        private const val FRAGMENT_TAG = "gift_dialog"

        fun newInstance() = ErrorPageFragment()

        fun show(fragmentManager: FragmentManager): ErrorPageFragment {
            val dialog = newInstance()
            dialog.show(fragmentManager, FRAGMENT_TAG)
            return dialog
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return requireActivity().layoutInflater.inflate(R.layout.fragment_error_page,container)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.ThemeOverlay_Material_Light)
    }
}
