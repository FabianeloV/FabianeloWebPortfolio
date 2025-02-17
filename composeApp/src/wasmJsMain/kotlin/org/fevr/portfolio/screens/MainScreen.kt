package org.fevr.portfolio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fabianelowebportfolio.composeapp.generated.resources.*
import fabianelowebportfolio.composeapp.generated.resources.Res
import fabianelowebportfolio.composeapp.generated.resources.foto
import fabianelowebportfolio.composeapp.generated.resources.mail
import org.fevr.portfolio.dataClasses.Socials
import org.fevr.portfolio.visuals.rubikFamily
import org.fevr.portfolio.visuals.rubikMono
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen() {
    LazyColumn {
        item { introCard() }
    }
}

@Composable
fun introCard() {
    Card(elevation = 8.dp, modifier = Modifier.padding(5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ) {
            photoWithButtons()
            nameColumn()
        }
    }
}

@Composable
fun nameColumn() {
    Column {
        Text("Fabian Verdesoto", fontFamily = rubikFamily(), fontWeight = FontWeight.Black, fontSize = 32.sp)
        Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Place, null,tint = MaterialTheme.colors.primary)
            Text("Cuenca - Ecuador", fontFamily = rubikFamily())
        }
        Text("Mobile developer and CS student", fontFamily = rubikFamily(), fontSize = 24.sp, fontWeight = FontWeight.Bold)

    }
}

@Composable
fun photoWithButtons() {
    val socials = listOf(
        Socials(Res.drawable.mail, "fevr.developer@gmail.com"),
        Socials(Res.drawable.github, "https://github.com/FabianeloV"),
        Socials(Res.drawable.linkedin, "https://www.linkedin.com/in/fabianverdesoto/")
    )

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Circular photo
        Image(
            painter = painterResource(Res.drawable.foto), // Replace with your drawable resource
            contentDescription = "Profile Photo",
            modifier = Modifier.size(200.dp).clip(CircleShape).border(2.dp, MaterialTheme.colors.primary, CircleShape),

            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row of rectangle buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            socials.forEach {
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .padding(6.dp)
                ) {
                    Image(painter = painterResource(it.icon), null, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}