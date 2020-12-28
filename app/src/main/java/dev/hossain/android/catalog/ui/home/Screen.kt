package dev.hossain.android.catalog.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes

/**
 * Screens available for display in the main screen, with their respective titles,
 * icons, and menu item IDs and fragments.
 */
enum class Screen(
    @IdRes val menuItemId: Int,
    @DrawableRes val menuItemIconId: Int,
    @StringRes val titleStringId: Int
) {
    HOME(
        dev.hossain.android.catalog.R.id.navigation_home,
        dev.hossain.android.catalog.R.drawable.ic_home_black_24dp,
        dev.hossain.android.catalog.R.string.title_home
    ),
    DASHBOARD(
        dev.hossain.android.catalog.R.id.navigation_dashboard,
        dev.hossain.android.catalog.R.drawable.ic_dashboard_black_24dp,
        dev.hossain.android.catalog.R.string.title_dashboard
    ),
    NOTIFICATION(
        dev.hossain.android.catalog.R.id.navigation_notifications,
        dev.hossain.android.catalog.R.drawable.ic_notifications_black_24dp,
        dev.hossain.android.catalog.R.string.title_notifications
    )
}

fun getMainScreenForMenuItem(menuItemId: Int): Screen? {
    for (mainScreen in Screen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}
