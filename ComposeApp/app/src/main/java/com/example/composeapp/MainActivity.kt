package com.example.composeapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TextFieldAndButtons()
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TextFieldAndButtons() {
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember {
        mutableStateOf(
            ""
        )
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        )
        {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart,
            ) {
                TextField(
                    value = textFieldState,
                    label = {
                        Text("Example")
                    },
                    onValueChange = {
                        textFieldState = it
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart,
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("TestTes")
                    }
                }
                ) {
                    Text(text = "Success")
                }
            }
        }
    }
}

@Composable
fun ColorBoxExternalState() {
    val color = remember {
        mutableStateOf(Color.Magenta) //External STATE
    }
    Column(
        Modifier.fillMaxSize()
    ) {
        ColorBox(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            color.value = it
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .background(color.value)
                .fillMaxSize()
        )
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier, updateColor: (Color) -> Unit) {
    /*val color = remember {
        mutableStateOf(Color.Magenta) // Basic STATE
    }*/

    Box(modifier = modifier
        .background(Color.Black)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        })
}

@Composable
fun ColumnsAndRows() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight(0.5f)
            .background(Color.Black)
//                .padding(top = 20.dp)
            .border(width = 15.dp, color = Color.Gray)
            .padding(10.dp)
            .border(width = 15.dp, color = Color.White)
            .padding(10.dp)
            .border(width = 15.dp, color = Color.Gray)
            .padding(10.dp)
            .border(width = 15.dp, color = Color.White)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Code",
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Culture", color = Color.White,
            modifier = Modifier.clickable {
                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
            })
    }
}

@Composable
fun ComposeTextAndStyle() {
    val fontFam = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.SemiBold),
        Font(R.font.ubuntu_bolditalic, FontWeight.ExtraLight)
    )
    val painter = painterResource(id = R.drawable.sample)
    val descp = "Compose Sample App"
    val title = "Compose Sample App Development"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 40.sp
                    )
                ) {
                    append("C")
                }
                append("ode")
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 40.sp,
                        fontFamily = fontFam
                    )
                ) {
                    append("C")
                }
                append("ulture")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = fontFam,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(10.dp)
        ) {
            ImageCard(
                painter = painter,
                contentDescp = descp,
                title = title
            )
        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescp: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier.height(300.dp),
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescp,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Green
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}

