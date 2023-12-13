package pl.ozodbek.allcountriesjsonlist.util


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

fun Fragment.changeFragmentTo(destination: Any) {
    val navController = this.findNavController()

    navController.safeNavigate(destination)
}

fun Fragment.changeFragmentTo(destination: Any, navController: NavController?) {

    when (destination) {
        is Int,is NavDirections-> {
            navController?.safeNavigate(destination)
        }
    }
}

fun NavController.safeNavigate(direction: Any) {
    when (direction) {
        is Int -> {
            if (currentDestination?.id == direction) {
                return
            }
            navigate(direction)
        }

        is NavDirections -> {
            var isNavigationEnabled = true
            CoroutineScope(Dispatchers.Main).launch {
                if (isNavigationEnabled) {
                    isNavigationEnabled = false
                    currentDestination?.getAction(direction.actionId)?.run {
                        navigate(direction)
                    }
                    delay(1000)
                    isNavigationEnabled = true
                }
            }
        }


        else -> {
        }
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.onClick(clickListener: (View) -> Unit) {
    setOnClickListener(clickListener)
}


fun Fragment.onBackPressed(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        onBackPressed()
    }
}

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}


fun Fragment.activityFor(): AppCompatActivity {
    return requireActivity() as AppCompatActivity
}



fun Fragment.getColorFragment(color: Int): Int {
    return ContextCompat.getColor(this.requireContext(), color)
}


