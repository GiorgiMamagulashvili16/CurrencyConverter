package com.example.currencyconverter_compose.presentation.curency_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.currencyconverter_compose.presentation.curency_screen.components.ErrorMessageDialog
import com.example.currencyconverter_compose.presentation.curency_screen.components.LoadingDialog
import com.example.currencyconverter_compose.presentation.curency_screen.states.CurrencyItem
import com.example.currencyconverter_compose.presentation.curency_screen.states.ErrorDialogState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel

@ExperimentalMaterialApi
@Composable
fun CurrencyScreen() {
    val vm = getViewModel<CurrenciesVm>()
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    val screenState = vm.screenState.value

    val showErrorDialog = remember {
        mutableStateOf(ErrorDialogState())
    }

    if (screenState.isLoading)
        LoadingDialog()
    screenState.fetchedCurrencies?.let {
        vm.setCurrenciesData(it)
    }

    screenState.errorMes?.let {
        showErrorDialog.value = ErrorDialogState(message = it, showDialog = true)
    }

    if (showErrorDialog.value.showDialog) {
        ErrorMessageDialog(message = showErrorDialog.value.message!!, setShowDialog = {
            showErrorDialog.value = ErrorDialogState(showDialog = it)
        })
    }


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(sheetList = vm.currenciesData.value, onItemClick = {
                vm.setCurrency(it)
                scope.launch { sheetState.collapse() }
            })
        },
        sheetPeekHeight = 0.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.18f)
                    .padding(top = 10.dp, bottom = 10.dp, start = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            CurrencyChooserSection(
                modifier = Modifier.fillMaxWidth(),
                sheetState = sheetState
            )
            Spacer(modifier = Modifier.height(10.dp))
            EnterAmountSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), vm.baseCurrency.value.code
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
}

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    sheetList: List<CurrencyItem>,
    onItemClick: (CurrencyItem) -> Unit
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            items(sheetList) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "${it.code} (${it.fullTitle})",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick.invoke(it)
                        },
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
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

@ExperimentalMaterialApi
@Composable
fun CurrencyChooserSection(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetState
) {
    val vm: CurrenciesVm by viewModel()
    val coroutineScope = rememberCoroutineScope()

    CurrencyChooser(
        title = stringResource(id = R.string.from),
        currentCurrency = vm.baseCurrency.value.fullTitle
    ) {
        coroutineScope.launch {
            sheetState.expand()
        }
        vm.isFirstCurrencyChecked.value = true
    }
    Spacer(modifier = Modifier.height(10.dp))
    CurrencyChooser(
        title = stringResource(id = R.string.to),
        currentCurrency = vm.secondCurrency.value.fullTitle
    ) {
        coroutineScope.launch {
            sheetState.expand()
        }
        vm.isFirstCurrencyChecked.value = false
    }
}

@Composable
fun EnterAmountSection(
    modifier: Modifier = Modifier,
    baseCurrency: String
) {
    val vm : CurrenciesVm by viewModel()

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
            value = if (vm.enteredAmount.value != null) vm.enteredAmount.value!! else "",
            onValueChange = { vm.setAmount(it) },
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
    val vm: CurrenciesVm by viewModel()

    Box(modifier = modifier) {
        Button(
            onClick = { vm.convert() },
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
    val vm :CurrenciesVm by viewModel()
    val convertedData = vm.convertedData.value
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.result), fontSize = 14.sp, color = Color.Gray)
            Text(text = convertedData?.actualCurrency ?: "", fontSize = 14.sp, color = Color.Gray)
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
                text = convertedData?.convertedAmount ?: stringResource(id = R.string.converted_amount),
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}