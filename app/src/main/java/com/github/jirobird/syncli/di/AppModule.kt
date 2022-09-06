package com.github.jirobird.syncli.di

import com.github.jirobird.syncli.common.Constant
import com.github.jirobird.syncli.data.remote.dto.partner.IPartnerApi
import com.github.jirobird.syncli.data.remote.dto.partner.IPartnerRepository
import com.github.jirobird.syncli.data.repository.PartnerRepositoryImpl
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
}