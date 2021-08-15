package edu.uoc.pac1.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import edu.uoc.pac1.model.Book
import edu.uoc.pac1.model.BooksFactory
import edu.uoc.pac1.ui.theme.PAC1Theme

/**
 * Created by alex on 15/8/21.
 */

// Shows a summarized version of a single book
@Composable
fun BookView(book: Book) {
    // TODO: Implement
}


@Preview(showBackground = true)
@Composable
fun BookViewPreview() {
    PAC1Theme {
        BookView(BooksFactory.books().first())
    }
}