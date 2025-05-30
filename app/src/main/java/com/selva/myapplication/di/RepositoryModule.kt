package com.selva.myapplication.di

import com.selva.myapplication.data.remote.repository.MealRepositoryImpl
import com.selva.myapplication.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repository: MealRepositoryImpl): MealRepository
}