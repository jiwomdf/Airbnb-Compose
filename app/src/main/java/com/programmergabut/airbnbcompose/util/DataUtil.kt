package com.programmergabut.airbnbcompose.util

import com.programmergabut.airbnbcompose.R
import com.programmergabut.airbnbcompose.domain.model.FeatureModel
import com.programmergabut.airbnbcompose.domain.model.SettingModel


enum class DebounceStatus {
    FromSearch,
    FromTab,
    Loading
}

fun generateFeature(): List<FeatureModel> {
    val list = listOf(
        FeatureModel(
            icon = R.drawable.ic_home_type_lake,
            title = "Close to beautiful place",
            dsc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
        ),
        FeatureModel(
            icon = R.drawable.ic_chef_black,
            title = "Kitchen with all needed food",
            dsc = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur",
        ),
        FeatureModel(
            icon = R.drawable.ic_bed,
            title = "Comfort bed room",
            dsc = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
        ),
        FeatureModel(
            icon = R.drawable.ic_security,
            title = "security guaranteed",
            dsc = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur",
        ),
        FeatureModel(
            icon = R.drawable.ic_wifi,
            title = "Wifi available",
            dsc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
        ),
        FeatureModel(
            icon = R.drawable.ic_workspaces,
            title = "Dedicated workspace",
            dsc = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
        )
    )

    return list.shuffled().take(3)
}

fun generateSetting(): List<SettingModel> {
    return listOf(
        SettingModel(
            icon = R.drawable.ic_profile_white,
            title = "Personal Information"
        ),
        SettingModel(
            icon = R.drawable.ic_security,
            title = "Login & Securiy"
        ),
        SettingModel(
            icon = R.drawable.ic_money,
            title = "Payment and Layouts"
        ),
        SettingModel(
            icon = R.drawable.ic_settings_suggest,
            title = "Accessibility"
        ),
        SettingModel(
            icon = R.drawable.ic_insert_drive_file,
            title = "Taxes"
        ),
        SettingModel(
            icon = R.drawable.ic_translate,
            title = "Translation"
        ),
        SettingModel(
            icon = R.drawable.ic_notification,
            title = "Notifications"
        ),
        SettingModel(
            icon = R.drawable.ic_card_travel,
            title = "Travel for work"
        ),
    )
}