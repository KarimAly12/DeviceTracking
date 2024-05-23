package com.example.devicetracking.common


import android.app.Application
import android.content.Context
import com.example.devicetracking.DeviceTrackingApp
import com.example.devicetracking.core.tracking.notification.LocationNotification
import com.example.devicetracking.core.tracking.notification.LocationNotificationHelper
import com.example.devicetracking.data.repository.ChildRepositoryImpl
import com.example.devicetracking.data.repository.ParentRepositoryImpl
import com.example.devicetracking.domain.Usecases.ParentUsecases.CreateParent
import com.example.devicetracking.domain.Usecases.ParentUsecases.GetChildren
import com.example.devicetracking.domain.Usecases.ParentUsecases.GetParent
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import com.example.devicetracking.domain.repository.ChildRepository
import com.example.devicetracking.domain.repository.ParentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {




//    @Provides
//    fun provoideApplication(): Context {
//        return DeviceTrackingApp().applicationContext
//    }


    @Singleton
    @Provides
    fun provideLocationNotification(@ApplicationContext context: Context):LocationNotification{
        return LocationNotificationHelper(context)
    }

//
//
//    @Provides
//    fun provideLocationProvider(application: Application): LocationProvider {
//        return LocationProvider(application)
//    }
    @Singleton
    @Provides
    fun provideParentRepository():ParentRepository{
        return ParentRepositoryImpl()

    }

//    @Provides
//    fun provideParentUsecases(parentRepository: ParentRepository):ParentUsecases{
//        return ParentUsecases(
//            createParent = CreateParent(parentRepository),
//            addChildToParent = AddChildToParent(parentRepository),
//            getParent = GetParent(parentRepository),
//            getChildren = GetChildren(parentRepository)
//        )
//    }

    @Singleton
    @Provides
    fun provideChildRepository():ChildRepository{
        return ChildRepositoryImpl()
    }

//    @Provides
//    fun childUseCases(childRepository: ChildRepository):ChildUsecases{
//        return ChildUsecases(
//            createChild = CreateChild(childRepository),
//            updatChildLocation = UpdateChildLocation(childRepository),
//            getChild = GetChild(childRepository),
//            updateChild = UpdateChild(childRepository),
//            getChildLocation = GetChildLocation(childRepository)
//        )
//    }



}