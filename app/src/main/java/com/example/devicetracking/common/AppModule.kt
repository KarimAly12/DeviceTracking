package com.example.devicetracking.common


import android.app.Application
import com.example.devicetracking.DeviceTrackingApp
import com.example.devicetracking.data.repository.UserRepositoryImpl
import com.example.devicetracking.domain.Usecases.CreateUser
import com.example.devicetracking.domain.Usecases.Signup
import com.example.devicetracking.domain.Usecases.UserUsecases
import com.example.devicetracking.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    fun provoideApplication(): Application {
        return DeviceTrackingApp()
    }

    @Provides
    fun provideUserRepository(application: Application): UserRepository {
        return UserRepositoryImpl(application)
    }

    @Provides
    fun provideUserusercases(repository: UserRepository):UserUsecases{
        return UserUsecases(
            signup = Signup(repository),
            createUser = CreateUser(repository)
        )

    }
}