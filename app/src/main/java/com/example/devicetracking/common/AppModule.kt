package com.example.devicetracking.common


import android.app.Application
import com.example.devicetracking.DeviceTrackingApp
import com.example.devicetracking.data.repository.ChildRepositoryImpl
import com.example.devicetracking.data.repository.ParentRepositoryImpl
import com.example.devicetracking.data.repository.UserRepositoryImpl
import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.Usecases.Childusecases.CreateChild
import com.example.devicetracking.domain.Usecases.CreateParent.CreateParent
import com.example.devicetracking.domain.Usecases.CreateParent.ParentUsecases
import com.example.devicetracking.domain.Usecases.Signup
import com.example.devicetracking.domain.Usecases.UserUsecases
import com.example.devicetracking.domain.repository.ChildRepository
import com.example.devicetracking.domain.repository.ParentRepository
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
    fun provideParentRepository():ParentRepository{
        return ParentRepositoryImpl()

    }

    @Provides
    fun provideParentUsecases(parentRepository: ParentRepository):ParentUsecases{
        return ParentUsecases(
            createParent = CreateParent(parentRepository)
        )
    }

    @Provides
    fun provideChildRepository():ChildRepository{
        return ChildRepositoryImpl()
    }

    @Provides
    fun childUseCases(childRepository: ChildRepository):ChildUsecases{
        return ChildUsecases(
            createChild = CreateChild(childRepository)
        )
    }


}