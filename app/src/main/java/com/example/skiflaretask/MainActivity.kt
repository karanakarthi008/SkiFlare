package com.example.skiflaretask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skiflaretask.Retrofit.ApiClient
import com.example.skiflaretask.Retrofit.ApiHelperImpl
import com.example.skiflaretask.data.Users
import com.example.skiflaretask.repository.UserRepository
import com.example.skiflaretask.ui.theme.SkiFlareTaskTheme
import com.example.skiflaretask.viewModel.UserViewModel
import com.example.skiflaretask.viewModel.UserViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(repository = UserRepository(
            ApiHelperImpl(ApiClient.apiService),(application as UserApplication).database
        ))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUser()
        setContent {
            SkiFlareTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListScreen(viewModel, enter = {false} )
                }
            }
        }
    }
}

@Composable
fun ListScreen(viewModel: UserViewModel, modifier: Modifier = Modifier, enter : () -> Unit ) {
    var enter by rememberSaveable {
        mutableStateOf(false)
    }
   val userResponse =  viewModel.userResponse.observeAsState()

    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        if (enter){
            userResponse.value?.let { list ->
                UserList(modifier = Modifier, userList = list)
            }
        }else{
            Button(onClick ={enter = true},modifier = Modifier
                .fillMaxWidth(),
                shape = RoundedCornerShape(50.dp) ) {
                Text(text = "Enter app")

            }
        }
    }
    }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkiFlareTaskTheme {

    }
}