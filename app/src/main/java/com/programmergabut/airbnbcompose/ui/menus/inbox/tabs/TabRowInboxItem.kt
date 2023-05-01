package com.programmergabut.airbnbcompose.ui.menus.inbox.tabs

import androidx.compose.runtime.Composable

data class TabRowInboxItem(
    val title: String,
    val screen: @Composable () -> Unit,
)

class TabRowInboxItemList {

    val tabRowItems = listOf(
        TabRowInboxItem(
            title = "Messages",
            screen = {
                TabMessagesScreen()
            },
        ),
        TabRowInboxItem(
            title = "Notifications",
            screen = {
                TabNotificationsScreen()
            },
        ),
    )


}