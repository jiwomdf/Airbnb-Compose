package com.programmergabut.airbnbcompose.ui.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programmergabut.airbnbcompose.ui.theme.Grey200
import com.programmergabut.airbnbcompose.ui.theme.Red500

@Preview(showBackground = true)
@Composable
fun PreviewSearchSettingScreen() {
    SearchSettingScreen(
        modifier = Modifier,
        orderByState = remember { mutableStateOf("") },
        colorState = remember { mutableStateOf("") },
        orientationState = remember { mutableStateOf("") },
        onClose = {}
    )
}

@Composable
fun SearchSettingScreen(
    modifier: Modifier,
    orderByState: MutableState<String>,
    colorState: MutableState<String>,
    orientationState: MutableState<String>,
    onClose: () -> Unit
) {

    Column(
        modifier = modifier
            .padding(top = 14.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Order By", fontWeight = FontWeight.SemiBold)
            OrderByDropDown(
                modifier = Modifier,
                orderByState = orderByState
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Color", fontWeight = FontWeight.SemiBold)
            OrderByColor(
                modifier = Modifier,
                colorState = colorState
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Orientation", fontWeight = FontWeight.SemiBold)
            OrientationDropDown(
                modifier = Modifier,
                orientationState = orientationState
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(20),
                onClick = {
                    onClose.invoke()
                }
            ) {
                Text(text = "Close", color = Color.Black)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderByColor(
    modifier: Modifier,
    colorState: MutableState<String>
) {
    val colorValues = arrayOf("black_and_white", "black", "white",
        "yellow", "orange", "red", "purple", "magenta", "green", "teal", "blue")

    Row(
        modifier = modifier
            .padding(top = 8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        colorValues.forEach {
            val isSelected = colorState.value.equals(it, true)
            Chip(
                modifier = modifier
                    .padding(end = 4.dp),
                onClick = {
                    colorState.value = it
                },
                colors = ChipDefaults.chipColors(
                    backgroundColor = if(isSelected) Red500 else Grey200
                ),

                ) {
                Text(text = it, color = if(isSelected) Color.White else Red500)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderByDropDown(
    modifier: Modifier,
    orderByState: MutableState<String>
) {
    val orderByValues = arrayOf("relevant", "latest")
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = orderByState.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                orderByValues.forEach { item ->
                    DropdownMenuItem(
                        content = { Text(text = item) },
                        onClick = {
                            orderByState.value = item
                            expanded = false
                            orderByState.value = item
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrientationDropDown(
    modifier: Modifier,
    orientationState: MutableState<String>
) {
    val orderByValues = arrayOf("landscape", "portrait")
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = orientationState.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                orderByValues.forEach { item ->
                    DropdownMenuItem(
                        content = { Text(text = item) },
                        onClick = {
                            expanded = false
                            orientationState.value = item
                        }
                    )
                }
            }
        }
    }
}
