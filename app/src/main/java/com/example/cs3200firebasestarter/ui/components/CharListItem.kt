package com.example.cs3200firebasestarter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CharListItem(
    name: String? = null,
    race: String? = null,
    class_: String? = null,
    onEditPressed: () -> Unit = {}
){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Race: $race",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Name: $name",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Class: $class_",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onEditPressed,
            content = {
                Text("Edit")
            }
        )
    }
}