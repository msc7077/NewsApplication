package com.msc.application.presentation.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.msc.application.data.remote.AlternativeSlugs
import com.msc.application.R
import com.msc.application.data.remote.UnsplashPhoto
import com.msc.application.data.remote.Urls
import com.msc.application.presentation.Dimens.MediumPadding1
import com.msc.application.presentation.Dimens.MediumPadding2
import com.msc.application.ui.theme.NewsApplicationTheme

@Composable
fun OnBoardingPage(
    photo: UnsplashPhoto,
    modifier: Modifier = Modifier,
) {
    //Log.d("TAG", "OnBoardingPage: photo -> $photo")
    Column(
        modifier = modifier
    ) {
        photo.let {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                model = it.urls.regular,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                text = it.slug,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = MediumPadding2),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.display_small)
            )
            Text(
                text = it.id,
                modifier = Modifier.padding(horizontal = MediumPadding2),
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_medium)
            )
        }
    }

}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingPagePreview() {
    NewsApplicationTheme {
        OnBoardingPage(
            photo = UnsplashPhoto(
                "sdflsdfsdf",
                "ql soflsms",
                AlternativeSlugs("비 내리는 다리"),
                Urls("https://images.unsplash.com/photo-1719861032503-225fac307c59?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2MjgyOTl8MHwxfGFsbHwxfHx8fHx8Mnx8MTcyMDQyNTIwNnw&ixlib=rb-4.0.3&q=80&w=1080")
            )
        )
    }
}