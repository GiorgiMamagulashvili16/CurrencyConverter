package com.example.currencyconverter_compose.presentation.curency_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter_compose.R

@Composable
fun CurrencyChooser(
    modifier: Modifier = Modifier,
    title: String,
    currentCurrency: String,
    onDropDownClick: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 15.dp),
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 15.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            CurrencyChooserSpinner(
                modifier = Modifier.fillMaxSize(),
                currentCurrency = currentCurrency,
            ) { onDropDownClick.invoke() }
        }

    }
}

@Composable
fun CurrencyChooserSpinner(
    modifier: Modifier = Modifier,
    currentCurrency: String,
    onDropDownClick: () -> Unit
) {
    Box(
        modifier = modifier.background(colorResource(id = R.color.currency_text_background)),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentCurrency,
                fontSize = 16.sp,
                color = Color.Gray
            )
            IconButton(
                onClick = { onDropDownClick.invoke() },
                modifier = Modifier
                    .size(34.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Gray
                )
            }
        }
    }
}