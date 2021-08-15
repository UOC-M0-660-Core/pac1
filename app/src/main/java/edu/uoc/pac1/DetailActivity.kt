package edu.uoc.pac1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import edu.uoc.pac1.data.FavouritesStorage
import edu.uoc.pac1.model.Book
import edu.uoc.pac1.model.BooksFactory
import edu.uoc.pac1.ui.theme.PAC1Theme
import edu.uoc.pac1.ui.views.BookDetailView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by alex on 15/8/21.
 */

// Shows detailed information for a given Book
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
            // TODO: Implement
            BookDetailView(book = book)
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