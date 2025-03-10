package org.fevr.portfolio.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
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
        "Landing page made for a local tools and wood store",
        listOf(
            Pair(Icons.Filled.Place, "Compose Multiplatform"),
            Pair(Icons.Filled.Place, "Kotlin")
        ),
        Res.drawable.torca1
    )

)


@Composable
fun mainScreen(scrollState: LazyListState) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 128.dp, vertical = 16.dp),
        state = scrollState
    ) {
        item { introCard() }

        item {
            Text(
                "Experience",
                fontFamily = rubikMono(),
                fontSize = 48.sp,
                modifier = Modifier.padding(vertical = 32.dp)
            )
        }

        item {
            experienceTimeline()
        }

        item {
            Text(
                "My projects",
                fontFamily = rubikMono(),
                fontSize = 48.sp,
                modifier = Modifier.padding(vertical = 36.dp)
            )
        }

        items(projects) {
            projectCard(
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

    val uriHandler = LocalUriHandler.current

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
                    onClick = { uriHandler.openUri(it.link) },
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
fun projectCard(
    projectName: String,
    projectDescription: String,
    techStack: List<Pair<ImageVector, String>>,
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
                    techBadge(icon, label)
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
fun techBadge(icon: ImageVector, label: String) {
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

@Composable
fun experienceTimeline() {
    Column {
        // First Experience Item
        experienceItem(
            companyName = "Sociedad Latinoamericana de Nutrición (SLAN)",
            position = "Full-Stack Android Developer",
            period = "February, 2023 - October, 2023",
            description = "Developed a fully functional mobile app for the international congress SLAN EC 2023"
        )

        // Second Experience Item
        experienceItem(
            companyName = "Prosaf",
            position = "Full-Stack Odoo Intern",
            period = "July, 2024 - January, 2025",
            description = "Full-Stack Odoo Intern, maintaining the Prosaf's odoo based ERP OLYMPO"
        )

        // Third Experience Item
        experienceItem(
            companyName = "Freelance",
            position = "Full-Stack Developer",
            period = "July 2024 - Present",
            description = "I actively try to solve the needs of multiple clients"
        )
    }
}

@Composable
fun experienceItem(
    companyName: String,
    position: String,
    period: String,
    description: String
) {
    val accentColor = MaterialTheme.colors.primary

    Row(modifier = Modifier.fillMaxWidth()) {
        // Left side - Timeline
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(24.dp)
        ) {
            // Circle dot
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(accentColor)
            )

            // Vertical line
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(100.dp)
                    .background(accentColor)
            )

        }

        // Right side - Content
        Column(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            // Company name
            Text(
                text = companyName,
                color = accentColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )

            // Position
            if (position.isNotEmpty()) {
                Text(
                    text = position,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Period
            Text(
                text = period,
                color = Color.Gray,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )

            // Description
            Text(
                text = description,
                fontSize = 24.sp,
                lineHeight = 20.sp
            )
        }
    }
}