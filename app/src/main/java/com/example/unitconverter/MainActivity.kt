package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember{mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val OconversionFactor  = remember { mutableStateOf(1.0) }
    val conversionFactor  = remember { mutableStateOf(1.0) }
    fun  convertUnits(){
        //?: elvis operator smart if satement
        //either value aygi kya 0.0 aayaga
       val inputValueDouble = inputValue.toDoubleOrNull()?: 0.0
       val result = (inputValueDouble * conversionFactor.value * 100.0/OconversionFactor.value).roundToInt()/ 100.0
        outputValue = result.toString()


    }

    Column(
        modifier= Modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        //UI under each other
          Text(text = "Unit Converter")
          Spacer(modifier = Modifier.height(16.dp))
          OutlinedTextField(value = inputValue, onValueChange = {
              inputValue= it
              convertUnits()
                                                                //here comes what will happen when we change value
          }, label = {Text(text= "Enter the value ")})

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // input box
            Box(){
                //input button
               Button(onClick = { iExpanded= true }) {
                   Text(text = inputUnit)

                   Icon(Icons.Default.ArrowDropDown ,
                       contentDescription = " " //for visually impaired say out loud
                   )



                   }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded= false }) {
                    DropdownMenuItem(text ={Text("Centimeters")}, onClick = {
                        iExpanded = false
                        inputUnit= "Centimeters"
                        conversionFactor.value = 0.01
                    })
                    DropdownMenuItem(text ={Text("Meters")}, onClick = {
                        iExpanded = false
                        inputUnit= "Meters"
                        conversionFactor.value = 1.0
                    })
                    DropdownMenuItem(text ={Text("Feet")}, onClick = {
                        iExpanded = false
                        inputUnit= "Feet"
                        conversionFactor.value = 0.3048
                    })
                    DropdownMenuItem(text ={Text("Inches")}, onClick = {
                        iExpanded = false
                        inputUnit= "Inches"
                        conversionFactor.value =0.0254
                    })


                }


            }
  Spacer(modifier = Modifier.width(16.dp))


            Box() {
                Button(onClick = { oExpanded= true }) {
                    Text(text = outputUnit)

                    Icon(Icons.Default.ArrowDropDown ,
                        contentDescription = "" //for visually impaired say out loud
                    )




                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded= false }) {
                    DropdownMenuItem(text ={Text("Centimeters")}, onClick = {
                        oExpanded = false
                        outputUnit= "Centimeters"
                        OconversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text ={Text("Meters")}, onClick = {
                        oExpanded = false
                        outputUnit= "Meters"
                        OconversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text ={Text("Feet")}, onClick = {
                        oExpanded = false
                        outputUnit= "Feet"
                        OconversionFactor.value = 0.03048
                        convertUnits() })
                    DropdownMenuItem(text ={Text("Inches")}, onClick = {
                        oExpanded = false
                        outputUnit= "Inches"
                        OconversionFactor.value = 0.0254
                        convertUnits()
                    })

                }



            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text="Result:$outputValue $outputUnit")
    }
    
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
   UnitConverter()
}