package com.example.currencyconverter_compose.presentation.curency_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ErrorMessageDialog(
    message: String,
    setShowDialog: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = { setShowDialog.invoke(false) },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            modifier = Modifier
                .height(250.dp)
                .clip(
                    RoundedCornerShape(12.dp)
                )
        ) {
            Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center) {
                Column() {
                    IconButton(onClick = {
                        setShowDialog.invoke(false)
                    }, modifier = Modifier.align(End)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                        Text(text = message, color = Color.Blue)
                    }
                }
            }
        }
    }
}