package org.fevr.portfolio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fabianelowebportfolio.composeapp.generated.resources.*
import fabianelowebportfolio.composeapp.generated.resources.Res
import fabianelowebportfolio.composeapp.generated.resources.foto
import fabianelowebportfolio.composeapp.generated.resources.mail
import org.fevr.portfolio.dataClasses.Project
import org.fevr.portfolio.dataClasses.Socials
import org.fevr.portfolio.visuals.rubikFamily
import org.fevr.portfolio.visuals.rubikMono
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.painterResource

val projects = listOf(
    Project(
        "ECOZO",
        "Social network where Cuenca's people can post impactful events from the city",
        listOf(
            Pair(Icons.Filled.Place, "Kotlin"),
            Pair(Icons.Filled.Place, "Jetpack compose"),
            Pair(Icons.Filled.Place, "Room"),
            Pair(Icons.Filled.Place, "Firebase"),
            Pair(Icons.Filled.Place, "Shared preferences"),
            Pair(Icons.Filled.Place, "Google maps API"),
        ),
         Res.drawable.ecozo1
    ),
    Project(
        "PERSONAL TRACKER",
        "Personal tracker for finances, workflows and habits",
        listOf(
            Pair(Icons.Filled.Place, "Kotlin"),
            Pair(Icons.Filled.Place, "Jetpack compose"),
            Pair(Icons.Filled.Place, "Room"),
            Pair(Icons.Filled.Place, "Shared preferences")
        ),
        Res.drawable.personal1
    ),
    Project(
        "GARDEN FIORE",
        "ECommerce made for a local flower store",
        listOf(
            Pair(Icons.Filled.Place, "Odoo")
        ),
        Res.drawable.garden1
    ),
    Project(
        "TORCA",
        "ECommerce made for a local tools and wood store",
        listOf(
            Pair(Icons.Filled.Place, "Compose Multiplatform"),
            Pair(Icons.Filled.Place, "Kotlin")
        ),
        Res.drawable.torca1
    )

)


@Composable
fun MainScreen() {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 128.dp)) {
        item { introCard() }
        item {
            Text(
                "My projects",
                fontFamily = rubikMono(),
                fontSize = 48.sp,
                modifier = Modifier.padding(vertical = 26.dp)
            )
        }

        items(projects) {
            ProjectCard(
                projectName = it.name,
                projectDescription = it.description,
                techStack = it.stack,
                onLearnMoreClick = {},
                imageVector = it.image
            )
        }
    }
}

@Composable
fun introCard() {
    Card(shape = RoundedCornerShape(16.dp), elevation = 8.dp, modifier = Modifier.padding(5.dp)) {
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
            Icon(Icons.Filled.Place, null, tint = MaterialTheme.colors.primary)
            Text("Cuenca - Ecuador", fontFamily = rubikFamily())
        }
        Text(
            "Mobile developer and CS student",
            fontFamily = rubikFamily(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

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
                    Image(painter = painterResource(it.icon), null, modifier = Modifier.size(36.dp))
                }
            }
        }
    }
}

@Composable
fun ProjectCard(
    projectName: String,
    projectDescription: String,
    techStack: List<Pair<ImageVector, String>>, // Icon resource and label
    onLearnMoreClick: () -> Unit,
    imageVector: DrawableResource
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column {
            // Project Image
            Image(
                painter = painterResource(imageVector), // Replace with actual image
                contentDescription = "Project Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Project Name
            Text(
                text = projectName,
                fontSize = 34.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontFamily = rubikMono()
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Tech Stack Icons
            LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(techStack) { (icon, label) ->
                    TechBadge(icon, label)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Project Description
            Text(
                text = projectDescription,
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Learn More Button
            Button(
                onClick = onLearnMoreClick,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = "Learn more", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TechBadge(icon: ImageVector, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colors.primary, CircleShape)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = label, fontSize = 18.sp, color = Color.White)
        }

    }
}
