package cz.maderajan.mml


// TODO REMOVE
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import cz.maderajan.mml.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    val REQUEST_CODE = 1337
//    val REDIRECT_URI = "mml://callback"
//    val CLIENT_ID = "f1c93f2c3bd64faf9c410970d69a1fac"
//    var token: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        binding.spotifyLoginButton.setOnClickListener {
//            val builder: AuthenticationRequest.Builder =
//                AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
//
//            builder.setScopes(arrayOf("user-library-read"))
//            val request: AuthenticationRequest = builder.build()
//
//            AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
//        }
//
//        binding.getMyAlbumsButton.setOnClickListener {
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
//                .addInterceptor { chain ->
//                    val request = chain
//                        .request()
//                        .newBuilder()
//                        .addHeader("Authorization", "Bearer " + token + "")
//                        .build()
//
//                    chain.proceed(request)
//                }.build()
//
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .baseUrl("https://api.spotify.com")
//                .client(okHttpClient)
//                .build()
//
//            val api = retrofit.create(SpotifyAlbumWebService::class.java)
//
//            GlobalScope.launch {
//                val albums = api.getAlbums().await()
//                albums.items.forEach {
//                    Log.d("SPOT_SPOT", it.toString())
//                }
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intent)

//        // Check if result comes from the correct activity
//        if (requestCode == REQUEST_CODE) {
//            val response = AuthenticationClient.getResponse(resultCode, intent)
//            when (response.type) {
//                AuthenticationResponse.Type.TOKEN -> {
//                    token = response.accessToken
//                }
//                AuthenticationResponse.Type.ERROR -> {
//                    Log.d("SPOTIFY_LOGIN", response.error)
//                }
//                else -> {
//                    Log.d("SPOTIFY_LOGIN", response.state)
//                }
//            }
//        }
//    }
//}

//interface SpotifyAlbumWebService {
//
//    @GET("/v1/me/albums")
//    fun getAlbums(): Deferred<AlbumResponse>
//}
//
//data class AlbumResponse(
//    val href: String,
//    val items: List<AlbumItemResponse>
//)
//
//data class AlbumItemResponse(
//    val album_type: String,
//    val id: String,
//    val name: String
//) {
//    override fun toString(): String {
//        return "AlbumItemResponse(album_type='$album_type', id='$id', name='$name')"
//    }
//}