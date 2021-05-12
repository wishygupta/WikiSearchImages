package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.data.remote.WikiImageRemoteDataSource
import com.example.rickandmorty.data.remote.WikiImagesService
import com.example.rickandmorty.data.repository.WikiImageRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mine.wikisearchimages.data.local.AppDatabase
import com.mine.wikisearchimages.data.local.WikiImagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://en.wikipedia.org/w/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideWikiImagesService(retrofit: Retrofit): WikiImagesService =
        retrofit.create(WikiImagesService::class.java)

    @Singleton
    @Provides
    fun provideWikiImageRemoteDataSource(characterService: WikiImagesService) =
        WikiImageRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.imagesDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: WikiImageRemoteDataSource,
        localDataSource: WikiImagesDao
    ) = WikiImageRepository(remoteDataSource, localDataSource)
}