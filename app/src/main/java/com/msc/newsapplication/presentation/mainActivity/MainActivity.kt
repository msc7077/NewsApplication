package com.msc.newsapplication.presentation.mainActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.msc.newsapplication.data.local.NewsDao
import com.msc.newsapplication.domain.model.Article
import com.msc.newsapplication.domain.model.Source
import com.msc.newsapplication.presentation.PhotoViewModel
import com.msc.newsapplication.presentation.navgraph.NavGraph
import com.msc.newsapplication.ui.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val photoViewModel: PhotoViewModel by viewModels()

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var dao: NewsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            dao.delete(
                Article(
                    author = "",
                    title = "zzzz Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                    description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                    content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
                    publishedAt = "2023-06-16T22:24:33Z",
                    source = Source(
                        id = "", name = "msc"
                    ),
                    url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=3",
                    urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
                )
            )
            Log.d("TAG", "onCreate: ${dao.getArticles() }}")
        }


        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { mainViewModel.splashCondition } )
        }

        setContent {
            NewsApplicationTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val startDestination = mainViewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
