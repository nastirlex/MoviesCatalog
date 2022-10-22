package com.example.moviescatalog.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Banner() {
    Box(modifier = Modifier) {
        Image(
            painter = painterResource(com.example.moviescatalog.R.drawable.banner_foregr),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(onClick = {}, modifier = Modifier
            .padding(top = 279.dp)
            .padding(start = 123.dp)
            .width(183.dp)
            .height(50.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffEF3A01))
        ) {
            Text("Смотреть", color = Color(0xffFFFFFF))
        }
    }
}