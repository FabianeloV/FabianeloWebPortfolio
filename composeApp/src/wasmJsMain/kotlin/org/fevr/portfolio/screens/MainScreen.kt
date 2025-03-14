package org.fevr.portfolio.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
             "Kotlin",
             "Jetpack compose",
             "Room",
             "Firebase",
           "Shared preferences",
           "Google maps API"
        ),
        Res.drawable.ecozo1,
        ""
    ),
    Project(
        "PERSONAL TRACKER",
        "Personal tracker for finances, workflows and habits",
        listOf(
             "Kotlin",
            "Jetpack compose",
             "Room",
             "Shared preferences"
        ),
        Res.drawable.personal1,
        "https://github.com/FabianeloV/Personal_Tracker"
    ),
    Project(
        "GARDEN FIORE",
        "ECommerce made for a local flower store",
        listOf(
            "Odoo"
        ),
        Res.drawable.garden1,
        "https://garden-fiore.odoo.com/"
    ),
    Project(
        "TORCA",
        "Landing page made for a local tools and wood store",
        listOf(
            "Compose Multiplatform",
            "Kotlin",
            "Web Assembly"
        ),
        Res.drawable.torca1,
        "https://fabianelov.github.io/TorcaWeb/"
    )

)


@Composable
fun mainScreen(scrollState: LazyListState) {
    val uriHandler = LocalUriHandler.current

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 128.dp, vertical = 16.dp),
        state = scrollState
    ) {
        item { introCard(uriHandler) }

        item {
            Text(
                "What I work with",
                fontFamily = rubikMono(),
                fontSize = 48.sp,
                modifier = Modifier.padding(vertical = 32.dp)
            )
        }

        item {
            techGrid()
        }

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
                uri = it.uri,
                onLearnMoreClick = {uriHandler.openUri(it.uri)},
                imageVector = it.image
            )
        }

        item {
            Text(
                "Contact me",
                fontFamily = rubikMono(),
                fontSize = 48.sp,
                modifier = Modifier.padding(vertical = 36.dp)
            )
        }

        item {
            contactCard(uriHandler)
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
fun introCard(uriHandler: UriHandler) {
    Card(shape = RoundedCornerShape(16.dp), elevation = 8.dp, modifier = Modifier.padding(5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ) {
            photoWithButtons(uriHandler)
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
fun photoWithButtons(uriHandler: UriHandler) {
    val socials = listOf(
        Socials(Res.drawable.mail, "https://mail.google.com/mail/?view=cm&fs=1&to=fevr.developer@gmail.com"),
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
                    onClick = { uriHandler.openUri(it.link) },
                    modifier = Modifier
                        .padding(6.dp)
                ) {
                    Icon(painter = painterResource(it.icon), null, modifier = Modifier.size(36.dp), tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun projectCard(
    projectName: String,
    projectDescription: String,
    techStack: List<String>,
    uri: String,
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
                items(techStack) { label ->
                    techBadge(label)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Project Description
            Text(
                text = projectDescription,
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Learn More Button
            if (uri.isNotEmpty()) {
                Button(
                    onClick = onLearnMoreClick,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "Learn more", fontFamily = rubikFamily(), fontSize = 28.sp, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun techBadge(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colors.primary, CircleShape)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {
            Icon(
                imageVector = Icons.Filled.Done,
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

@Composable
fun contactCard(uriHandler: UriHandler) {
    val secondaryTextColor = MaterialTheme.colors.primary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 220.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Subtitle
            Text(
                text = "You can contact me via Email, Linkedin or Github.",
                color = secondaryTextColor,
                fontSize = 48.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 38.dp),
                fontFamily = rubikFamily(),
                textAlign = TextAlign.Center
            )

            // Contact options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Linkedin
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable { uriHandler.openUri("https://www.linkedin.com/in/fabianverdesoto/") }
                            .background(MaterialTheme.colors.secondary, RoundedCornerShape(12.dp))
                            .size(130.dp)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.linkedin),
                            contentDescription = "Linkedin",
                            tint = Color.White,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Text(
                        text = "LinkeDin",
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp),
                        fontFamily = rubikMono()
                    )
                    Text(
                        text = "Fabian Verdesoto",
                        color = secondaryTextColor,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 4.dp),
                        fontFamily = rubikFamily()
                    )
                }

                // Email
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable { uriHandler.openUri("https://mail.google.com/mail/?view=cm&fs=1&to=fevr.developer@gmail.com") }
                            .background(MaterialTheme.colors.secondary, RoundedCornerShape(12.dp))
                            .size(130.dp)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = "Email",
                            modifier = Modifier.size(100.dp),
                            tint = Color.White,
                        )
                    }
                    Text(
                        text = "Email",
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp),
                        fontFamily = rubikMono()
                    )
                    Text(
                        text = "fevr.developer@gmail.com",
                        color = secondaryTextColor,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 4.dp),
                        fontFamily = rubikFamily()
                    )
                }

                // Github
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable { uriHandler.openUri("https://github.com/FabianeloV") }
                            .size(130.dp)
                            .background(MaterialTheme.colors.secondary, RoundedCornerShape(12.dp))
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.github),
                            contentDescription = "Github",
                            modifier = Modifier.size(100.dp),
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Github",
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp),
                        fontFamily = rubikMono()
                    )
                    Text(
                        text = "FabianeloV",
                        color = secondaryTextColor,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 4.dp),
                        fontFamily = rubikFamily()
                    )
                }
            }
        }
    }
}

@Composable
fun techGrid() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 220.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            techCard(
                iconRes = Res.drawable.Kotlin,
                name = "Kotlin",
                modifier = Modifier.weight(1f)
            )
            techCard(
                iconRes = Res.drawable.java,
                name = "Java",
                modifier = Modifier.weight(1f)
            )
            techCard(
                iconRes = Res.drawable.python,
                name = "Python",
                modifier = Modifier.weight(1f)
            )
            techCard(
                iconRes = Res.drawable.compose,
                name = "j. Compose",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            techCard(
                iconRes = Res.drawable.data,
                name = "SQL",
                modifier = Modifier.weight(1f)
            )
            techCard(
                iconRes = Res.drawable.firebase,
                name = "Firebase",
                modifier = Modifier.weight(1f)
            )
            techCard(
                iconRes = Res.drawable.ktor,
                name = "ktor",
                modifier = Modifier.weight(1f)
            )
            techCard(
                iconRes = Res.drawable.spring,
                name = "Spring boot",
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Composable
fun techCard(
    iconRes: DrawableResource,
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 6.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontFamily = rubikMono(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                fontSize = 24.sp
            )
        }
    }
}