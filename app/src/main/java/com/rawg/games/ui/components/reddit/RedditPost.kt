package com.rawg.games.ui.components.reddit

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.rawg.games.domain.model.GameRedditPost
import com.rawg.games.ui.components.html.HtmlView

@Composable
fun RedditPost(
    redditPost: GameRedditPost,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    ElevatedCard (
        modifier = modifier
            .fillMaxSize()
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, redditPost.url.toUri())
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = redditPost.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Posted by u/${redditPost.author} • ${redditPost.created}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (redditPost.text.isNotBlank()) {
                HtmlView(
                    html = redditPost.text,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RedditPostPreview() {
    val samplePost = GameRedditPost(
        id = 1,
        title = "New gameplay trailer just dropped!",
        text = "The graphics look insane. Can't wait to try it out.",
        url = "https://www.reddit.com/r/gaming/comments/abcdef/new_gameplay_trailer/",
        author = "gamer42",
        created = "2 hours ago"
    )

    RedditPost(
        redditPost = samplePost,
        modifier = Modifier.padding(16.dp)
    )
}
