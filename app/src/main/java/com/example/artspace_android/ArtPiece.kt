package com.example.artspace_android

data class ArtPiece(
    val name: String,
    val description: String,
    val creationDate: String,
    val imageResId: Int
)

val artPieces = listOf(
    ArtPiece("Kingsley", "Le chemin sinueux...", "20 mai 1923", R.drawable.kingsley),
    ArtPiece("Yvens", "Symbole du courage...", "19 avril 1759", R.drawable.yvens),
    ArtPiece("Jaylessa", "Un écho visuel de la persévérance...", "20 avril 1689", R.drawable.jayleesa),
    ArtPiece("Dyana", "L'éclat du triomphe après l'adversité...", "29 août 1956", R.drawable.dyana),
    ArtPiece("Andrea", "La force tranquille du courage...", "29 juin 1835", R.drawable.andrea),
    ArtPiece("Obstacle", "Dans chaque coup de pinceau...", "13 janvier 1755", R.drawable.obstacle),
    ArtPiece("Life", "La danse harmonieuse du courage...", "24 avril 1919", R.drawable.life)
)
