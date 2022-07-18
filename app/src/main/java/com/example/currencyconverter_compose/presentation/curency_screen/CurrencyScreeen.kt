package com.example.currencyconverter_compose.presentation.curency_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter_compose.R
import com.example.currencyconverter_compose.presentation.curency_screen.components.CurrencyChooser

@Composable
fun CurrencyScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.18f)
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CurrencyChooserSection(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        EnterAmountSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp), "GEL"
        )
        Spacer(modifier = Modifier.height(15.dp))
        ConvertButtonSection(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 15.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        ResultAmountSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.CenterStart) {
        Text(
            text = stringResource(id = R.string.app_name_header),
            fontSize = 31.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(R.color.header_text),
            fontFamily = FontFamily(Font(R.font.roboto_bold))
        )
    }
}

@Composable
fun CurrencyChooserSection(
    modifier: Modifier = Modifier
) {
    CurrencyChooser(title = stringResource(id = R.string.from), currentCurrency = "Algeria") {

    }
    Spacer(modifier = Modifier.height(10.dp))
    CurrencyChooser(title = stringResource(id = R.string.to), currentCurrency = "Brazil") {

    }
}

@Composable
fun EnterAmountSection(
    modifier: Modifier = Modifier,
    baseCurrency: String
) {
    Column(modifier = modifier) {
        Text(
            text = baseCurrency,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = {
                Text(text = stringResource(id = R.string.amount))
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Gray,
                focusedBorderColor = colorResource(id = R.color.header_text),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = colorResource(id = R.color.header_text),
                cursorColor = colorResource(id = R.color.header_text)
            )
        )
    }
}

@Composable
fun ConvertButtonSection(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_green)
            )
        ) {
            Text(
                text = stringResource(id = R.string.convert),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                textAlign = TextAlign.Center,
                fontSize = 21.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun ResultAmountSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.result), fontSize = 14.sp, color = Color.Gray)
            Text(text = "GEL", fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.currency_text_background)),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                text = stringResource(id = R.string.converted_amount),
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}