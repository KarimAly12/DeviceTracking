package com.example.devicetracking.common


import android.app.Application
import com.example.devicetracking.DeviceTrackingApp
import com.example.devicetracking.data.repository.ChildRepositoryImpl
import com.example.devicetracking.data.repository.ParentRepositoryImpl
import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.Usecases.Childusecases.CreateChild
import com.example.devicetracking.domain.Usecases.Childusecases.GetChild
import com.example.devicetracking.domain.Usecases.Childusecases.UpdateChild
import com.example.devicetracking.domain.Usecases.Childusecases.UpdateChildLocation
import com.example.devicetracking.domain.Usecases.ParentUsecases.AddChildToParent
import com.example.devicetracking.domain.Usecases.ParentUsecases.CreateParent
import com.example.devicetracking.domain.Usecases.ParentUsecases.GetChildren
import com.example.devicetracking.domain.Usecases.ParentUsecases.GetParent
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import com.example.devicetracking.domain.repository.ChildRepository
import com.example.devicetracking.domain.repository.ParentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provoideApplication(): Application {
        return DeviceTrackingApp()
    }

//
//
//    @Provides
//    fun provideLocationProvider(application: Application): LocationProvider {
//        return LocationProvider(application)
//    }
    @Provides
    fun provideParentRepository():ParentRepository{
        return ParentRepositoryImpl()

    }

    @Provides
    fun provideParentUsecases(parentRepository: ParentRepository):ParentUsecases{
        return ParentUsecases(
            createParent = CreateParent(parentRepository),
            addChildToParent = AddChildToParent(parentRepository),
            getParent = GetParent(parentRepository),
            getChildren = GetChildren(parentRepository)
        )
    }

    @Provides
    fun provideChildRepository():ChildRepository{
        return ChildRepositoryImpl()
    }

    @Provides
    fun childUseCases(childRepository: ChildRepository):ChildUsecases{
        return ChildUsecases(
            createChild = CreateChild(childRepository),
            updatChildLocation = UpdateChildLocation(childRepository),
            getChild = GetChild(childRepository),
            updateChild = UpdateChild(childRepository)
        )
    }


}