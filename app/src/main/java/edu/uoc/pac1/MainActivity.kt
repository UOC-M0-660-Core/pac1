package edu.uoc.pac1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import edu.uoc.pac1.data.FavouritesStorage
import edu.uoc.pac1.ui.theme.PAC1Theme
import kotlinx.coroutines.flow.Flow

// Shows a summarized list of Books
class MainActivity : ComponentActivity() {

    // For Advanced Exercises:
    // Flow that emits when the current book favourite changes
    private val favouriteIdsFlow: Flow<List<Int>> by lazy {
        return@lazy FavouritesStorage(this).favouritesList
    }


    // Activity entry-point
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAC1Theme {
                // Scaffold: Basic screen structure with AppBar and Body
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = getString(R.string.app_name))
                            },
                        )
                    },
                ) {
                    // Screen Content
                    Greeting(name = "Android")
                }
            }
        }
    }
}


// Default example Composable
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

// Current file preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PAC1Theme {
        // TODO: Update as the PAC progresses
        Greeting("Android")
    }
}