package edu.uoc.pac1.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.uoc.pac1.model.Book
import edu.uoc.pac1.model.BooksFactory
import edu.uoc.pac1.ui.theme.PAC1Theme

/**
 * Created by alex on 15/8/21.
 */
@Composable
fun BookList(books: List<Book>, favouriteBookIds: List<Int>, onBookSelected: (Book) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        items(books) { book ->
            BookView(
                book = book,
                isFavourite = favouriteBookIds.contains(book.uid),
                onBookSelected = onBookSelected
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BookListPreview() {
    PAC1Theme {
        BookList(BooksFactory.books(), favouriteBookIds = listOf(2, 4), onBookSelected = {})
    }
}