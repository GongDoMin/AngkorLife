package com.unionmobile.angkorlife.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.RobotoFontFamily

@Composable
fun CandidateInformation(
    name: String,
    candidateNumber: String,
    education: String,
    major: String,
    hobbies: String,
    talent: String,
    ambition: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
                lineHeight = 26.sp
            ),
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(vertical = 3.dp))

        Text(
            text = candidateNumber,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            color = Color(0xFF6F76FF)
        )

        Spacer(modifier = Modifier.padding(vertical = 13.dp))

        CandidateExtra(
            modifier = Modifier
                .background(Color(0xFF171717))
                .clip(RoundedCornerShape(8.dp))
                .padding(
                    vertical = 18.dp,
                    horizontal = 14.dp
                ),
            education = education,
            major = major,
            hobbies = hobbies,
            talent = talent,
            ambition = ambition
        )
    }
}

@Composable
fun CandidateExtra(
    education: String,
    major: String,
    hobbies: String,
    talent: String,
    ambition: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Extra(
            title = "Education",
            description = education
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        HorizontalDivider(modifier = Modifier.background(Color(0xFF1E1D1E)))

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Extra(
            title = "Major",
            description = major
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        HorizontalDivider(modifier = Modifier.background(Color(0xFF1E1D1E)))

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Extra(
            title = "Hobbies",
            description = hobbies
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        HorizontalDivider(modifier = Modifier.background(Color(0xFF1E1D1E)))

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Extra(
            title = "Talent",
            description = talent
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        HorizontalDivider(modifier = Modifier.background(Color(0xFF1E1D1E)))

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Extra(
            title = "Ambition",
            description = ambition
        )
    }
}

@Composable
fun Extra(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ExtraTitle(title = title)

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        ExtraDescription(description = description)
    }
}

@Composable
fun ExtraTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        style = TextStyle(
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        color = Color(0xFF7C7C7C)
    )
}

@Composable
fun ExtraDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = description,
        style = TextStyle(
            fontFamily = KantumruyFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 19.sp
        ),
        color = Color(0xFFF6F6F6)
    )
}

@Preview(showBackground = true)
@Composable
fun ExtraDescriptionPreview() {
    ExtraDescription(
        modifier = Modifier
            .background(Color(0xFF171717)),
        description = "Singing"
    )
}

@Preview(showBackground = true)
@Composable
fun ExtraTitlePreview() {
    ExtraTitle(
        modifier = Modifier
            .background(Color(0xFF171717)),
        title = "Education"
    )
}

@Preview(showBackground = true)
@Composable
fun ExtraPreview() {
    Extra(
        modifier = Modifier
            .background(Color(0xFF171717)),
        title = "Education",
        description = "Bashkir State Agrarian University"
    )
}

@Preview(showBackground = true)
@Composable
fun CandidateExtraPreview() {
    CandidateExtra(
        modifier = Modifier
            .background(Color(0xFF171717)),
        education = "Bashkir State Agrarian University",
        major = "Computer Engineering",
        hobbies = "Singing",
        talent = "Singing",
        ambition = "Singing"
    )
}

@Preview(showBackground = true)
@Composable
fun CandidateInformationPreview() {
    CandidateInformation(
        modifier = Modifier
            .background(Color(0xFF121212))
            .padding(
                vertical = 18.dp,
                horizontal = 14.dp
            ),
        name = "Prolomova Anastasia",
        candidateNumber = "Entry No.5",
        education = "Bashkir State Agrarian University",
        major = "Computer Engineering",
        hobbies = "Singing",
        talent = "Singing",
        ambition = "Singing"
    )
}
