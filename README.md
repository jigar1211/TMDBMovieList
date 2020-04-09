# TMDBMovieList
List of popular and top rated movie list

# Languages : Kotlin
# Architecture : MVVM with repository pattern
 USE TMDB API and API Key to fetch movie list. For more reference visit "https://www.themoviedb.org/documentation/api"
# Libraries :

    # ViewModel and LiveData
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'

    # Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.5.0"
    implementation "com.squareup.retrofit2:converter-gson:2.5.0"
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0

    # Gson
    implementation "com.google.code.gson:gson:2.8.5"

    # Glide
    implementation 'com.github.bumptech.glide:glide:4.8.0'

    # Paging
    implementation "androidx.paging:paging-runtime:2.1.2"

     # Rx
    implementation 'io.reactivex.rxjava2:rxjava:2.2.7'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'

    # Mockito test
    testImplementation 'org.mockito:mockito-core:2.7.22'
