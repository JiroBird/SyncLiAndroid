package com.github.jirobird.syncli.di

import android.app.Application
import androidx.room.Room
import com.github.jirobird.syncli.common.Constant
import com.github.jirobird.syncli.data.data_source.SyncedListApplicationDatabase
import com.github.jirobird.syncli.data.remote.dto.partner.IPartnerApi
import com.github.jirobird.syncli.data.repository.IPartnerRepository
import com.github.jirobird.syncli.data.repository.ISyncedListRepository
import com.github.jirobird.syncli.domain.repository.PartnerRepositoryImpl
import com.github.jirobird.syncli.domain.repository.SyncedListLocalRepositoryImpl
import com.github.jirobird.syncli.domain.use_cases.sync_li.GetLocalSyncedListCount
import com.github.jirobird.syncli.domain.use_cases.sync_li.GetLocalSyncedLists
import com.github.jirobird.syncli.domain.use_cases.sync_li.SyncedListUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerPartnerApi():IPartnerApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IPartnerApi::class.java)
    }

    @Provides
    @Singleton
    fun providePartnerRepository(api:IPartnerApi): IPartnerRepository {
        return PartnerRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app:Application):SyncedListApplicationDatabase {
        return Room.databaseBuilder(
            app, SyncedListApplicationDatabase::class.java,
            SyncedListApplicationDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideSyncedListLocalRepository(syncedListApplicationDatabase: SyncedListApplicationDatabase): ISyncedListRepository{
        return SyncedListLocalRepositoryImpl(syncedListApplicationDatabase.syncedListDao)
    }

    @Provides
    @Singleton
    fun provideSyncedListUseCases(syncedListLocalRepository:ISyncedListRepository):SyncedListUseCases {
        return SyncedListUseCases(
            getLocalSyncedListCount = GetLocalSyncedListCount(syncedListLocalRepository),
            getLocalSyncedLists = GetLocalSyncedLists(syncedListLocalRepository)
        )
    }
}