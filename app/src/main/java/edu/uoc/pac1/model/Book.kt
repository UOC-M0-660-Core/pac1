package edu.uoc.pac1.model

/**
 * Created by alex on 15/8/21.
 */

// Book Model. Contains information about a given book.
data class Book(
    val uid: Int,
    val author: String,
    val description: String,
    val publicationDate: String,
    val title: String,
    val urlImage: String,
)