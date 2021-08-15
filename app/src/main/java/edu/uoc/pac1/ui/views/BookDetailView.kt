package edu.uoc.pac1.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
@Composable
fun BookDetailView(book: Book) {
//    Text(text = book.description)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(book.urlImage),
                contentDescription = "Book Image",
                modifier = Modifier.size(144.dp),
                alignment = Alignment.CenterStart,
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(book.title, fontWeight = FontWeight.Bold)
                Text(book.author, style = MaterialTheme.typography.caption)
                Text("Published ${book.publicationDate}", style = MaterialTheme.typography.overline)
            }
        }
        Text(book.description, modifier = Modifier.padding(top = 16.dp))
    }
}

// @Preview function
@Preview(showBackground = true)
@Composable
fun BookDetailPreview() {
    PAC1Theme {
        BookDetailView(BooksFactory.books().first())
    }
}