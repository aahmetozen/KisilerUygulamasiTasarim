package com.jetpackcomposeileandroiduyggelistirmekursuudmy.kisileruygtasarim

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.jetpackcomposeileandroiduyggelistirmekursuudmy.kisileruygtasarim.entity.Kisiler
import com.jetpackcomposeileandroiduyggelistirmekursuudmy.kisileruygtasarim.ui.theme.KisilerUygTasarimTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            sayfagecisleri()
        }
    }
}

@Composable
fun sayfagecisleri(){ // navController ile sayfa gecislerini olusturduk
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa"){
            Anasayfa(navController = navController)
        }
        composable("kisikayitsayfa"){
            Kisikayitsayfa()
        }
        composable("kisidetaysayfa/{kisi}", arguments = listOf(
            navArgument("kisi"){type= NavType.StringType}
        )){
            val json=it.arguments?.getString("kisi")
            val nesne=Gson().fromJson(json,Kisiler::class.java)
            Kisidetaysayfa(nesne)
        } //Gson kutuphanesi ile veri transferi yapiyoruz
        
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController){
    val aramayapiliyormu= remember { mutableStateOf(false) }
    val tf= remember { mutableStateOf("") }
    val kisilerlistesi= remember { mutableStateListOf<Kisiler>() }

    LaunchedEffect(key1 = true) {// LaunchedEffect:sayfa acilirken calisan kisim
        val k1=Kisiler(1,"ahmet ozen","05064187555")
        val k2=Kisiler(2,"eyup can yilmaz","05357074344")
        val k3=Kisiler(3,"zeynep yilmaz","22222")
        kisilerlistesi.add(k1)
        kisilerlistesi.add(k2)
        kisilerlistesi.add(k3)
    }
    Scaffold(
        topBar = {
         TopAppBar(
             title = {
                 if (aramayapiliyormu.value){
                     TextField(
                         value = tf.value,
                         onValueChange ={tf.value=it} ,
                         label = { Text(text = "Ara") },
                         colors = TextFieldDefaults.textFieldColors(
                          containerColor = Color.Transparent,
                         focusedIndicatorColor = Color.White,
                         unfocusedIndicatorColor = Color.White,
                     ))
                 }else{
                     Text(text = "Kisiler")
                 }
             },
             actions = {
                 if (aramayapiliyormu.value){
                     IconButton(onClick = {
                        aramayapiliyormu.value=false
                         tf.value=""
                     }) {
                         Icon(
                             painter = painterResource(id = R.drawable.kapat_resim),
                             contentDescription = "")

                     }
                 }else{
                     IconButton(onClick = {
                        aramayapiliyormu.value=true
                     }) {
                         Icon(
                             painter = painterResource(id = R.drawable.arama_resim),
                             contentDescription = "")

                     }

                 }
             }
         )
        },
        floatingActionButton = { // fab buttonla sayfa gecisi tasarladik
            FloatingActionButton(
                onClick = { navController.navigate("kisikayitsayfa") },
                containerColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription = "",
                        tint = Color.White)
                }
                )


        }

    )
    {paddingValues -> Column(modifier = Modifier.padding(paddingValues)) { // burada liste tasarimi gerceklestirdik
        LazyColumn {
            items(count = kisilerlistesi.count(),
                itemContent = {
                    val kisi=kisilerlistesi[it]
                    Card(modifier = Modifier
                        .padding(all = 5.dp)
                        .fillParentMaxWidth()) {
                        Row(modifier = Modifier.clickable { // cardlara daha duzgun calisan tiklama ozelligi verebilmek icin row a clickable ozelligi verdik
                            val kisijson=Gson().toJson(kisi)
                            navController.navigate("kisidetaysayfa/${kisijson}")
                        }) {
                            Row(modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "${kisi.kisi_ad} - ${kisi.kisi_tel}")
                                IconButton(onClick = {
                                    Log.e("Kisi sil","${kisi.kisi_id}")
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.sil_resim),
                                        contentDescription = "", tint = Color.Gray)

                                }
                            }
                        }

                    }
                })
        }

    }

    }



}

@Preview(showBackground = true)
@Composable
fun Anasayfapreview() {
    
}