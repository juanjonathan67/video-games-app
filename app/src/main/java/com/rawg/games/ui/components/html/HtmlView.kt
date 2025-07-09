package com.rawg.games.ui.components.html

import android.graphics.Color
import android.text.TextUtils
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun HtmlView(
    html: String,
    modifier: Modifier = Modifier,
){
    val spannedText = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)

    AndroidView(
        modifier = modifier,
        factory = { TextView(it) },
        update = {
            it.text = spannedText
            it.setTextColor(Color.BLACK)
            it.ellipsize = TextUtils.TruncateAt.END
        }
    )
}