package com.github.jirobird.syncli.data.remote.dto.partner


interface IPartnerRepository {

    suspend fun hasPartnerWithSameName(name: String):Boolean

    suspend fun findPartnerInformationByName(name: String): PartnerDto
}