import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

// Modèle des œuvres d'art
data class ArtPiece(
    val name: String,
    val description: String,
    val creationDate: String,
    val imageResId: Int
)

// Liste des œuvres d'art
val artPieces = listOf(
    ArtPiece("Kingsley", "Le chemin sinueux...", "20 mai 1923", R.drawable.kingsley),
    ArtPiece("Yvens", "Symbole du courage...", "19 avril 1759", R.drawable.yvens),
    ArtPiece("Jaylessa", "Un écho visuel de la persévérance...", "20 avril 1689", R.drawable.jayleesa),
    ArtPiece("Dyana", "L'éclat du triomphe après l'adversité...", "29 août 1956", R.drawable.dyana),
    ArtPiece("Andrea", "La force tranquille du courage...", "29 juin 1835", R.drawable.andrea),
    ArtPiece("Obstacle", "Dans chaque coup de pinceau...", "13 janvier 1755", R.drawable.obstacle),
    ArtPiece("Life", "La danse harmonieuse du courage...", "24 avril 1919", R.drawable.life)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var currentIndex by remember { mutableStateOf(0) }
    val currentArtPiece = artPieces[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Affichage de l'œuvre d'art sélectionnée
        ArtPieceCard(artPiece = currentArtPiece)

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation pour changer d'œuvre avec les boutons Back et Next
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else artPieces.size - 1
            }) {
                Text("Back")
            }

            Button(onClick = {
                currentIndex = (currentIndex + 1) % artPieces.size
            }) {
                Text("Next")
            }
        }
    }
}

@Composable
fun ArtPieceCard(artPiece: ArtPiece) {
    var isExpanded by remember { mutableStateOf(false) }

    // Animation pour la taille de l'image
    val size by animateDpAsState(targetValue = if (isExpanded) 300.dp else 150.dp)

    // Animation pour l'opacité de l'image
    val alpha by animateFloatAsState(targetValue = if (isExpanded) 1f else 0.7f)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.Gray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Affichage de l'image avec animations
        Image(
            painter = painterResource(id = artPiece.imageResId),
            contentDescription = artPiece.name,
            modifier = Modifier
                .size(size)
                .alpha(alpha)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, Color.Gray)
                .clickable { isExpanded = !isExpanded }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Affichage du nom de l'œuvre d'art
        Text(text = artPiece.name, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)

        // Affichage de la description et de la date avec une animation de visibilité
        AnimatedVisibility(visible = isExpanded) {
            Column {
                Text(text = artPiece.description, style = MaterialTheme.typography.body1)
                Text(text = "Créé le ${artPiece.creationDate}", style = MaterialTheme.typography.body2)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bouton pour agrandir ou réduire l'image
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(if (isExpanded) "Réduire" else "Agrandir")
        }
    }
}
