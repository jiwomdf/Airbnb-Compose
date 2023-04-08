package com.programmergabut.airbnbcompose.ui.inbox.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun TabMessagesScreen() {
    Column(
        modifier = Modifier.padding(top = 16.dp),
    ) {
        Text(
            text = "You have no unread messages",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, end = 16.dp),
            text = "When you contact a host or send a reservation request, you'll see your messages here.",
            fontSize = 18.sp
        )
    }
}