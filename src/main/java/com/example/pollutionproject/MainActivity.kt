package com.example.pollutionproject

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.Default.bolt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pollutionproject.ui.theme.PollutionProjectTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import com.google.accompanist.systemuicontroller.rememberSystemUiController


public var greenFactor = -100.0
public var money = 100000
public var numSolar = 0
public var numCoal = 1
public var numWind = 0
public var numHydro = 0
public var curPos = 1


class MainActivity : ComponentActivity() {



    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PollutionProjectTheme {
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(color = Color.White)
                systemUiController.setNavigationBarColor(color = Color.DarkGray)

                val mMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.audio)
                mMediaPlayer.start()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Factory",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Shop",
                                    route = "chat",
                                    icon = Icons.Default.List,
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings,
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)){
                        Navigation(navController = navController)
                    }

                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if(item.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Text(text = item.badgeCount.toString())
                                }
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home screen")


        var backGround = painterResource(R.drawable.ground)
        if(curPos == 1){
            backGround = painterResource(R.drawable.ground1)
        } else if (curPos == 2) {
            backGround = painterResource(R.drawable.ground2)
        } else if (curPos == 3){
            backGround = painterResource(R.drawable.ground3)
        } else if (curPos == 4){
            backGround = painterResource(R.drawable.ground4)
        } else if (curPos == 5){
            backGround = painterResource(R.drawable.ground5)
        } else if (curPos == 6){
            backGround = painterResource(R.drawable.ground8)
        } else if (curPos == 7){
            backGround = painterResource(R.drawable.ground6)
        }
        val backSky = painterResource(R.drawable.sky)
        val backSun = painterResource(R.drawable.sun)


        Column(){

            Row( modifier = Modifier.padding(10.dp, 10.dp)){
                Text("Green Factory", modifier = Modifier.padding(20.dp, 20.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
            Row(){
                Image(
                    painter = backSun,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(158.85.dp)
                        .clickable { money += 600 * numSolar; greenFactor += 1 * numSolar}
                )
                Image(
                    painter = backSky,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clickable { money += 200 * numWind; greenFactor += 0.5 * numWind }
                )
            }
            Image(
                painter = backGround,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clickable { money += 500 * numCoal; greenFactor -= 2 * numCoal }
            )


        }



//        Button(){
//
//        }


    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val coal = painterResource(R.drawable.factory_carbon_remover)
        val wind = painterResource(R.drawable.windmill)
        val solar = painterResource(R.drawable.solar_panel)
        val hydro = painterResource(R.drawable.hydro_energy)

        LazyColumn{
            item{
                Text("Shop", modifier = Modifier.padding(20.dp, 10.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }

            item{
                Box(modifier = Modifier.padding(10.dp, 10.dp).background(Color.Gray)) {
                    Box(modifier = Modifier.padding(10.dp, 10.dp)) {
                        Row() {
                            Column() {
                                Text("Coal Plant #1", color = Color.White)
                                Text("Status:", color = Color.White)
                                if (curPos > 0) {
                                    Text("Owned", color = Color.White)
                                }
                                Text("Price:", color = Color.White)
                                Text("$75000", color = Color.White)
                                Button(
                                    onClick = {
                                        var message = "Could not buy"
                                        if (money >= 75000 && numCoal < 1) {
                                            numCoal++;
                                            money -= 75000;
                                            greenFactor -= 300
                                            message = "Coal Plant #1 Purchased!";
                                            curPos = 1
                                        }

                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xFF229922)
                                    )
                                ) {
//                            AlertDialog(onDismissRequest = {  }, text = {Text(message)})
                                    if (numCoal > 0) {
                                        Text("Permanent")
                                    } else {
                                        Text("Buy", color = Color.White)
                                    }

                                }
                            }
                            Image(
                                painter = coal,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                            )
                        }


                    }
                }
            }

            item{
                Box(modifier = Modifier.padding(10.dp, 10.dp).background(Color.Gray)){

                    Box(modifier = Modifier.padding(10.dp, 10.dp)) {
                        Column() {
                            Row() {
                                Column() {
                                    Text("Coal Plant #2", color = Color.White)
                                    Text("Status:", color = Color.White)
                                    if (curPos == 2) {
                                        Text("Owned", color = Color.White)
                                    } else if (curPos < 2 && money >= 75000) {
                                        Text("Available", color = Color.White)
                                    } else {
                                        Text("Unavailable", color = Color.White)
                                    }
                                    Text("Price:", color = Color.White)
                                    Text("$75000", color = Color.White)

                                    Button(
                                        onClick = {
                                            var message = "Could not buy"
                                            if (money >= 75000 && curPos < 2) {
                                                numCoal++;
                                                money -= 75000;
                                                greenFactor -= 300
                                                message = "Coal Plant #2 Purchased!";
                                                curPos = 2
                                            } else if (curPos == 2) {
                                                numCoal--;
                                                greenFactor += 100;
                                                curPos = 1
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0xFF229922)
                                        )
                                    ) {
                                        if (numCoal > 1) {
                                            Text("Demolish")
                                        } else {
                                            Text("Buy", color = Color.White)
                                        }
                                    }
                                }
                                Image(
                                    painter = coal,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                )
                            }
                            Text(
                                "Coal power plants produce large amounts of carbon emissions. While they had been the method of choice to produce power in much of the world for many decades, governments are currently making efforts to remove their usage in order to reduce carbon emissions. Your Green Factory's green factor will suffer if you continue to use coal. Coal has gone from 37% of U.S. electricity production in 2012 to 19% in 2022, and usage continues to fall.", fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }

            }
            item{
                Box(modifier = Modifier.padding(10.dp, 10.dp).background(Color.Gray)) {
                    Box(modifier = Modifier.padding(10.dp, 10.dp)) {
                        Column() {
                            Row() {
                                Column() {
                                    Text("Wind #1", color = Color.White)
                                    Text("Status:", color = Color.White)
                                    if (numWind > 0) {
                                        Text("Owned", color = Color.White)
                                    } else if (numWind < 1 && money >= 150000 && curPos == 1) {
                                        Text("Available", color = Color.White)
                                    } else {
                                        Text("Unavailable", color = Color.White)
                                    }
                                    Text("Price:", color = Color.White)
                                    Text("$150000", color = Color.White)
                                    Button(
                                        onClick = {
                                            var message = "Could not buy"
                                            if (money >= 150000 && numWind == 0 && curPos == 1) {
                                                numWind++;
                                                money -= 150000;
                                                greenFactor += 200
                                                message = "Wind Plant #1 Purchased!";
                                                curPos = 3
                                            } else if (numWind == 1 && numSolar == 0) {
                                                numWind--;
                                                greenFactor -= 100;
                                                curPos = 2
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0xFF229922)
                                        )
                                    ) {
                                        if (numWind == 1 && numSolar == 0) {
                                            Text("Demolish")
                                        } else if (numWind == 2 || numSolar > 0) {
                                            Text("Demolish Other Items First")
                                        } else {
                                            Text("Buy", color = Color.White)
                                        }
                                    }
                                }
                                Image(
                                    painter = wind,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                )
                            }
                            Text(
                                "Wind power is a renewable energy source that generates electricity from the spinning of wind turbines. Installation of wind turbines can be expensive and disruptive to natural environments and wind turbines can be reliant on constant wind, which is hard to predict. Still, they are a leading choice in new methods of creating electricity without carbon emissions. In 2022, wind power made up 10.25% of U.S. electricity production, up from 3.48% in 2012.", fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }

            }

            item{
                Box(modifier = Modifier.padding(10.dp, 10.dp).background(Color.Gray)){
                Box(modifier = Modifier.padding(10.dp, 10.dp)) {
                    Column() {
                        Row() {
                            Column() {
                                Text("Wind #2", color = Color.White)
                                Text("Status:", color = Color.White)
                                if (numWind > 1) {
                                    Text("Owned", color = Color.White)
                                } else if (numWind == 1 && money >= 100000) {
                                    Text("Available", color = Color.White)
                                } else {
                                    Text("Unavailable", color = Color.White)
                                }
                                Text("Price:", color = Color.White)
                                Text("$100000", color = Color.White)
                                Button(
                                    onClick = {
                                        var message = "Could not buy"
                                        if (money >= 100000 && numWind == 1) {
                                            numWind++;
                                            money -= 100000;
                                            greenFactor += 200
                                            message = "Wind Plant #2 Purchased!";
                                            if (numSolar == 0) {
                                                curPos = 4
                                            } else {
                                                curPos = 6
                                            }
                                        } else if (numWind == 2) {
                                            numWind--;
                                            greenFactor -= 100;
                                            if (numSolar == 0) {
                                                curPos = 3
                                            } else {
                                                curPos = 5
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xFF229922)
                                    )
                                ) {
                                    if (numWind == 2 && numHydro < 1) {
                                        Text("Demolish")
                                    } else if (numHydro == 1) {
                                        Text("Demolish Other Items First")
                                    } else {
                                        Text("Buy", color = Color.White)
                                    }
                                }
                            }
                            Image(
                                painter = wind,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                            )
                        }
                        Text(
                            "As more renewable energy sources are built, the construction costs decrease! Over the past decade, renewable energy sources have become less expensive to build in the U.S. than fossil fuel based energy sources. The more investment that goes into renewable energy, the more the price drops in the future. In 2021, 41% of all new electricity generation facilities in the U.S. were based on wind.", fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                    }
                }
            }
            item{
                Box(modifier = Modifier.padding(10.dp, 10.dp).background(Color.Gray)){
                Box(modifier = Modifier.padding(10.dp, 10.dp)) {
                    Column() {
                        Row() {
                            Column() {
                                Text("Solar", color = Color.White)
                                Text("Status:", color = Color.White)
                                if (numSolar > 0) {
                                    Text("Owned", color = Color.White)
                                } else if (numSolar < 1 && numWind > 0 && money >= 300000) {
                                    Text("Available", color = Color.White)
                                } else {
                                    Text("Unavailable", color = Color.White)
                                }
                                Text("Price:", color = Color.White)
                                Text("$300000", color = Color.White)
                                Button(
                                    onClick = {
                                        var message = "Could not buy"
                                        if (money >= 300000 && numSolar == 0 && numWind > 0) {
                                            numSolar++;
                                            money -= 300000;
                                            greenFactor += 500
                                            message = "Solar Plant Purchased!";
                                            if (numWind == 1) {
                                                curPos = 5
                                            } else {
                                                curPos = 6
                                            }
                                        } else if (numSolar == 1 && numHydro < 1) {
                                            numSolar--;
                                            greenFactor -= 200;
                                            if (numWind == 1) {
                                                curPos = 3
                                            } else {
                                                curPos = 4
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xFF229922)
                                    )
                                ) {
                                    if (numSolar == 1 && numHydro < 1) {
                                        Text("Demolish")
                                    } else if (numHydro == 1) {
                                        Text("Demolish Other Items First")
                                    } else {
                                        Text("Buy", color = Color.White)
                                    }
                                }
                            }
                            Image(
                                painter = solar,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                            )
                        }
                        Text(
                            "Solar power uses the sun to create electricity in photovoltaic cells. The price of solar panels has dramatically fallen in the past 10 years, making them a feasible option for home installation and large scale commercial installation, particularly in places with reliable sunlight. Solar power provided 3.43% of U.S. energy production in 2022, up from 0.11% in 2012. 36% of new energy production installation in 2021 was from solar power.", fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
                }
            }
            item {
                Box(modifier = Modifier.padding(10.dp, 10.dp).background(Color.Gray)) {
                    Box(modifier = Modifier.padding(10.dp, 10.dp)) {
                        Column() {
                            Row() {
                                Column() {
                                    Text("Hydropower Plant", color = Color.White)
                                    Text("Status:", color = Color.White)
                                    if (numHydro > 0) {
                                        Text("Owned", color = Color.White)
                                    } else if (numWind == 2 && money >= 500000 && numSolar == 1 && numHydro < 1 && greenFactor >= 2000) {
                                        Text("Available", color = Color.White)
                                    } else {
                                        Text("Unavailable", color = Color.White)
                                    }
                                    Text("Price:", color = Color.White)
                                    Text("$500000", color = Color.White)
                                    Text("Requires:", color = Color.White)
                                    Text("Green Factor of at least 2000", color = Color.White)
                                    Button(
                                        onClick = {
                                            var message = "Could not buy"
                                            if (numWind == 2 && money >= 500000 && numSolar == 1 && numHydro < 1 && greenFactor >= 2000) {
                                                numHydro++;
                                                money -= 500000;
                                                greenFactor += 500
                                                message = "Hydropower Plant Purchased!";
                                                curPos = 7
                                            } else if (numHydro == 1) {
                                                numHydro--;
                                                greenFactor -= 0;
                                                curPos = 6
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0xFF229922)
                                        )
                                    ) {
                                        if (numHydro == 1) {
                                            Text("Demolish")
                                        } else {
                                            Text("Buy", color = Color.White)
                                        }
                                    }
                                }
                                Image(
                                    painter = hydro,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                )
                            }
                            Text(
                                "Hydropower uses flowing water to generation electricity. This concept has been used extensively for many decades. Due to the large environmental impact of building dams to create hydroelectric power sources and the limited feasible locations, construction of new hydroelectric power sources has been slow in recent years. Hydroelectricity provides 6.3% of total U.S. electricity in 2022.", fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Column(){
            Text("Green Factory", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
            Text(text = "Green Factor: " + greenFactor.toInt().toString(), textAlign = TextAlign.Center, style = TextStyle(
                fontSize = 15.sp
            ), modifier = Modifier.padding(6.dp, 6.dp)
            )
            Text(text = "Money: $" + money.toString(), textAlign = TextAlign.Center, style = TextStyle(
                fontSize = 15.sp
            ), modifier = Modifier.padding(6.dp, 6.dp) )
        }
    }
}

