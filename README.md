# Rick And Morty App with Jetpack Compose and MVVM Architecture

This android app, Rick and Morty Universe, utilized [Rick and Morty API](https://rickandmortyapi.com/) and Jetpack Compose. The API is affiliated with
the [Rick and Morty TV Series](https://www.imdb.com/title/tt2861424/), and it includes thorough information about characters, episodes and locations involved in the entire series.
I built this app as a mean of my first Jetpack Compose portfolio.

You can download apk file this app from [here](https://drive.google.com/file/d/1RFggZRFfL9zhomeeiP7nVETpF0S6lRMs/view?usp=sharing).

## Core Features
It has the functionalities described below.
* Revealing all characters, locations and episodes with **paginated API response**
* Caching fetch data for offline utilization
* Instant search for characters
* Bookmark your favorite characters
* Sort you favorite characters by name in ascending and descending orders and ID.
* Character details and developer infos

## Tech Stack
* [Jetpack Compose](https://developer.android.com/develop/ui/compose) : Jetpack Compose is Android’s recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.
* [Dagger Hilt](https://dagger.dev/hilt/) : utilized for dependency injection
* [Retrofit](https://square.github.io/retrofit/) : used for working with REST API
* [okHttp interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) : to cache API Responses and combined with Retrofit
* [Room Database](https://developer.android.com/jetpack/androidx/releases/room) : to save/remove/order favorite characters
* [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) : to pagniate data, including list of characters in home page and search results, locations and episodes in 20 items per page
* [Data Store Preferences](https://developer.android.com/topic/libraries/architecture/datastore) : to save sorting data (ie: Sort by name in ascending, descending and characters' ID)
* [Lottie Composition](https://github.com/airbnb/lottie/blob/master/android-compose.md) : to visualize Lottie animations in search prompt, empty search result and empty favorite screen.
* [Splash Screen API](https://developer.android.com/develop/ui/views/launch/splash-screen) : to show a splash screen while the app's process isn't running.
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) : Kotlin coroutines
* [Flow](https://developer.android.com/kotlin/flow) : Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
* [ViewModlel](https://developer.android.com/topic/libraries/architecture/viewmodel) : Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
* [Compose Navigation](https://developer.android.com/develop/ui/compose/navigation) : Component that allows easier implementation of navigation from composables.
* [System UI Controller](https://google.github.io/accompanist/systemuicontroller/) : System UI Controller provides easy-to-use utilities for updating the System UI bar colors within Jetpack Compose.
* [Coil](https://coil-kt.github.io/coil/compose/) : to load network images

## The Implementation
### Screeshots
![Screenshot_1](https://github.com/zawwynnmyat/Rick-And-Morty-Jetpack-Compose-MVVM/blob/master/assets/rick%20and%20morty-images-0.jpg?raw=true)
![Screenshot_2](https://github.com/zawwynnmyat/Rick-And-Morty-Jetpack-Compose-MVVM/blob/master/assets/rick%20and%20morty-images-1.jpg?raw=true)
![Screenshot_3](https://github.com/zawwynnmyat/Rick-And-Morty-Jetpack-Compose-MVVM/blob/master/assets/rick%20and%20morty-images-2.jpg?raw=true)
![Screenshot_4](https://github.com/zawwynnmyat/Rick-And-Morty-Jetpack-Compose-MVVM/blob/master/assets/rick%20and%20morty-images-3.jpg?raw=true)

## Video Demonstration

https://github.com/zawwynnmyat/Rick-And-Morty-Jetpack-Compose-MVVM/assets/94133457/a96860e1-dfa5-408d-9e8e-8cf241ef59d6

## Support
If you like this project, please do not forget to star the repo. Every single star would be highly appreciated.
