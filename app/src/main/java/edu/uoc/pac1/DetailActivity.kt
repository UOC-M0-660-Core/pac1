package edu.uoc.pac1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import edu.uoc.pac1.data.FavouritesStorage
import edu.uoc.pac1.model.Book
import edu.uoc.pac1.model.BooksFactory
import edu.uoc.pac1.ui.theme.PAC1Theme
import edu.uoc.pac1.ui.views.BookDetailView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by alex on 15/8/21.
 */
class DetailActivity : ComponentActivity() {

    // Get Book from the Activity Intent Extra
    private val book: Book by lazy {
        val bookId = intent?.getIntExtra(bookIdKey, -1)
        return@lazy BooksFactory.books().first { it.uid == bookId }
    }

    // Favourites Storage Instance
    private val favouritesStorage by lazy {
        return@lazy FavouritesStorage(this)
    }

    // Flow that emits when the current book favourite changes
    private val isFavouriteFlow: Flow<Boolean> by lazy {
        return@lazy favouritesStorage.favouritesList.map { it.contains(book.uid) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO: Add
//            BookDetailView(book = book)
            // TODO: Remove
            PAC1Theme {
                // Basic screen structure with AppBar and Body
                Scaffold(
                    topBar = {
                        TopAppBar(
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        finish()
                                    },
                                ) {
                                    Icon(Icons.Filled.ArrowBack, "Back")
                                }
                            },
                            title = {
                                Text(
                                    text = book.title,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                            },
                        )
                    },
                    floatingActionButton = {
                        val isFavourite by isFavouriteFlow.collectAsState(initial = false)
                        FloatingActionButton(
                            onClick = {
                                lifecycleScope.launch {
                                    if (!isFavourite) {
                                        favouritesStorage.setFavourite(book.uid)
                                    } else {
                                        favouritesStorage.removeFavourite(book.uid)
                                    }
                                }
                            },
                        ) {
                            Icon(
                                if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                "Save to favourites"
                            )
                        }
                    },
                ) {
                    BookDetailView(
                        book = book
                    )
                }
            }
        }
    }


    companion object {
        private const val bookIdKey = "bookId"

        // Use this function to create a new Activity
        // This helps enforce passing a "bookId" to this Activity, otherwise we cannot show
        // any book
        fun newIntent(context: Context, bookId: Int): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(bookIdKey, bookId)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailActivityPreview() {
    PAC1Theme {
        BookDetailView(book = BooksFactory.books().last())
    }
}