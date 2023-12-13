package pl.ozodbek.allcountriesjsonlist.util


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.MaterialToolbar

inline fun <reified T : ViewBinding> ViewGroup.viewBinding(
    crossinline inflate: (LayoutInflater, ViewGroup, Boolean) -> T,
): T {
    return inflate(LayoutInflater.from(context), this, false)
}

fun Fragment.setupActionBarWith(
    toolbar: MaterialToolbar,
    toolbarText: String? = null,
    block: () -> Unit,
) {
    activityFor().title = toolbarText
    activityFor().setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener {
        block()
    }
}


fun View.onClick(clickListener: (View) -> Unit) {
    setOnClickListener(clickListener)
}


fun Fragment.onBackPressed(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        onBackPressed()
    }
}


fun Fragment.activityFor(): AppCompatActivity {
    return requireActivity() as AppCompatActivity
}



