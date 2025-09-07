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
import androidx.compose.ui.text.style.TextOverflow
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
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState
    ) {
        item { introCard(uriHandler) }

        item {
            Text(
                "What I work with",
                fontFamily = rubikMono(),
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 24.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            techGrid()
        }

        item {
            Text(
                "Experience",
                fontFamily = rubikMono(),
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 24.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            experienceTimeline()
        }

        item {
            Text(
                "My projects",
                fontFamily = rubikMono(),
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 24.dp),
                textAlign = TextAlign.Center
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
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 24.dp),
                textAlign = TextAlign.Center
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
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            photoWithButtons(uriHandler)
            Spacer(modifier = Modifier.height(16.dp))
            nameColumn()
        }
    }
}

@Composable
fun nameColumn() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Fabian Verdesoto",
            fontFamily = rubikFamily(),
            fontWeight = FontWeight.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Place, null, tint = MaterialTheme.colors.primary)
            Text("Cuenca - Ecuador", fontFamily = rubikFamily(), fontSize = 16.sp)
        }
        Text(
            "Mobile and backend developer - CS engineering student",
            fontFamily = rubikFamily(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            "National #8 IEEExtreme 17.0",
            fontFamily = rubikFamily(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            "National #12 IEEExtreme 18.0",
            fontFamily = rubikFamily(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
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
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Responsive circular photo
        Image(
            painter = painterResource(Res.drawable.foto),
            contentDescription = "Profile Photo",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colors.primary, CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row of rectangle buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            socials.forEach {
                Button(
                    onClick = { uriHandler.openUri(it.link) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(it.icon),
                        null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
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
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column {
            // Responsive Project Image
            Image(
                painter = painterResource(imageVector),
                contentDescription = "Project Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Project Name
            Text(
                text = projectName,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontFamily = rubikMono()
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Tech Stack Icons - Responsive
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(techStack) { label ->
                    techBadge(label)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Project Description
            Text(
                text = projectDescription,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                lineHeight = 24.sp
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
                    Text(
                        text = "Learn more",
                        fontFamily = rubikFamily(),
                        fontSize = 16.sp,
                        color = Color.White
                    )
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
            .padding(2.dp)
            .background(MaterialTheme.colors.primary, CircleShape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = label,
                modifier = Modifier.size(16.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = label, fontSize = 14.sp, color = Color.White)
        }
    }
}

@Composable
fun experienceTimeline() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
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
                    .height(80.dp)
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
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            // Position
            if (position.isNotEmpty()) {
                Text(
                    text = position,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Period
            Text(
                text = period,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )

            // Description
            Text(
                text = description,
                fontSize = 16.sp,
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
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Contact options - Responsive layout
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Linkedin
                contactItem(
                    iconRes = Res.drawable.linkedin,
                    title = "LinkedIn",
                    subtitle = "Fabian Verdesoto",
                    onClick = { uriHandler.openUri("https://www.linkedin.com/in/fabianverdesoto/") },
                    secondaryTextColor = secondaryTextColor
                )

                // Email
                contactItem(
                    iconVector = Icons.Rounded.Email,
                    title = "Email",
                    subtitle = "fevr.developer@gmail.com",
                    onClick = { uriHandler.openUri("https://mail.google.com/mail/?view=cm&fs=1&to=fevr.developer@gmail.com") },
                    secondaryTextColor = secondaryTextColor
                )

                // Github
                contactItem(
                    iconRes = Res.drawable.github,
                    title = "Github",
                    subtitle = "FabianeloV",
                    onClick = { uriHandler.openUri("https://github.com/FabianeloV") },
                    secondaryTextColor = secondaryTextColor
                )
            }
        }
    }
}

@Composable
fun contactItem(
    iconRes: DrawableResource? = null,
    iconVector: androidx.compose.ui.graphics.vector.ImageVector? = null,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    secondaryTextColor: Color
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary, RoundedCornerShape(12.dp))
                .size(60.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (iconRes != null) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            } else if (iconVector != null) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = title,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White,
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Text content
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                fontFamily = rubikMono()
            )
            Text(
                text = subtitle,
                color = secondaryTextColor,
                fontSize = 14.sp,
                fontFamily = rubikFamily()
            )
        }
    }
}

@Composable
fun techGrid() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First row
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(listOf(
                Pair(Res.drawable.Kotlin, "Kotlin"),
                Pair(Res.drawable.java, "Java"),
                Pair(Res.drawable.python, "Python"),
                Pair(Res.drawable.compose, "jetpack Compose")
            )) { (iconRes, name) ->
                techCard(iconRes = iconRes, name = name)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Second row
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(listOf(
                Pair(Res.drawable.data, "PostgreSQL"),
                Pair(Res.drawable.firebase, "Firebase"),
                Pair(Res.drawable.ktor, "ktor"),
                Pair(Res.drawable.spring, "Spring")
            )) { (iconRes, name) ->
                techCard(iconRes = iconRes, name = name)
            }
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
            .width(120.dp)
            .padding(4.dp),
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
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontFamily = rubikMono(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}