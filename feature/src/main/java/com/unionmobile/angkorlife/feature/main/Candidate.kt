package com.unionmobile.angkorlife.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.unionmobile.angkorlife.design.KantumruyFontFamily

@Composable
fun Candidate(
    url: String,
    id: Int,
    name: String,
    voteCount: String,
    isVoted: Boolean,
    onClickImage: (candidateId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onClickImage(id)
                },
            model = url,
            contentDescription = null
        )

        Spacer(modifier = Modifier.padding(vertical = 9.dp))

        Text(
            text = name,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            color = Color(0xFFF6F6F6),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.padding(vertical = 2.dp))

        Text(
            text = "$voteCount voted",
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            ),
            color = Color(0xFF6F76FF),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        VoteHandlerButton(
            isVoted = isVoted
        )
    }
}

@Composable
fun VoteHandlerButton(
    isVoted: Boolean,
    modifier: Modifier = Modifier
) {
    if (isVoted) VotedButton(modifier)
    else VoteButton(modifier)
}

@Composable
fun VoteButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp),
        colors = ButtonColors(
            containerColor = Color(0xFF4232D5),
            contentColor = Color.White,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
        ),
        onClick = { TODO("Not yet implemented") },
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 12.dp
        )
    ) {
        Text(text = "Vote")
    }
}

@Composable
fun VotedButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp),
        enabled = false,
        colors = ButtonColors(
            containerColor = Color.Unspecified,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.White,
            disabledContentColor = Color(0xFF4232D5),
        ),
        onClick = { TODO("Not yet implemented") },
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 12.dp
        )
    ) {
        Text(text = "Voted")
    }
}

@Preview(showBackground = true)
@Composable
fun VoteButtonPreview() {
    VoteButton()
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun VotedButtonPreview() {
    VotedButton()
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun CandidatePreview() {
    Candidate(
        url = "",
        id = 0,
        name = "kazakova Julia",
        voteCount = "1,200 voted",
        isVoted = false,
        onClickImage = {}
    )
}