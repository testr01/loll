package com.example.artspace_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranOeuvresArt()
        }
    }
}

@Composable
fun EcranOeuvresArt() {

    val oeuvres = listOf(
        OeuvreArt("Kingsley", "Le chemin sinueux où chaque pas, marqué par la ténacité et la réflexion, mène à l'apogée de l'accomplissement.", "20 mai 1923", R.drawable.kingsley),
        OeuvreArt("Yvens", "Symbole du courage inébranlable, où l'intelligence éclaire le chemin vers des sommets inattendus.", "19 avril 1759", R.drawable.yvens),
        OeuvreArt("Jaylessa", "Un écho visuel de la persévérance, où la victoire n'est pas seulement une destination mais un processus continu.", "20 avril 1689", R.drawable.jayleesa),
        OeuvreArt("Dyana", "L'intelligence résiliente qui, face aux tempêtes, trace la voie de la lumière et du succès.", "29 août 1956", R.drawable.dyana),
        OeuvreArt("Andrea", "La force tranquille du courage, guidée par la lucidité, construisant des ponts vers l'avenir.", "29 juin 1835", R.drawable.andrea),
        OeuvreArt("Obstacle", "Dans chaque coup de pinceau, la force du rêve qui devient réalité grâce à la persévérance et à l'ingéniosité.", "13 janvier 1755", R.drawable.obstacle),
        OeuvreArt("Life", "La danse harmonieuse du courage et de l'intelligence, sculptant les contours d'un succès mérité.", "24 avril 1919", R.drawable.life)
    )

    var indiceOeuvreCourante by remember { mutableStateOf(0) }
    val oeuvreCourante = oeuvres[indiceOeuvreCourante]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        AfficherCarteOeuvre(oeuvreCourante)

        Spacer(modifier = Modifier.height(32.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {
                    indiceOeuvreCourante = if (indiceOeuvreCourante > 0) indiceOeuvreCourante - 1 else oeuvres.size - 1
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ) {
                Text("Précédent",
                    color = Color(0xFFFFFFFF)
                )
            }


            Spacer(modifier = Modifier.width(16.dp))


            Button(
                onClick = {
                    indiceOeuvreCourante = (indiceOeuvreCourante + 1) % oeuvres.size
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text("Suivant",
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}

@Composable
fun AfficherCarteOeuvre(oeuvre: OeuvreArt) {
    var estAime by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {


        Image(
            painter = painterResource(id = oeuvre.imageResId),
            contentDescription = oeuvre.nom,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    estAime = !estAime
                }
        )

        Spacer(modifier = Modifier.height(8.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = oeuvre.nom,
                color = Color(0xFF00008B),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )


            Icon(
                imageVector = if (estAime) ImageVector.vectorResource(id = R.drawable.like)
                else ImageVector.vectorResource(id = R.drawable.unlike),
                contentDescription = if (estAime) "Cœur rouge" else "Cœur vide",
                tint = if (estAime) Color.Red else Color.Gray,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { estAime = !estAime }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))


        Text(
            text = oeuvre.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(4.dp))


        Text(
            text = "Créé le ${oeuvre.dateCreation}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
            )
    }
}
