package com.github.jirobird.syncli.domain.use_cases.partner

import com.github.jirobird.syncli.common.RequestResponse
import com.github.jirobird.syncli.data.repository.IPartnerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHasPartnerWithSameNameUseCase @Inject constructor(
    private val repository: IPartnerRepository
) {
    operator fun invoke(nameToCheck:String): Flow<RequestResponse<Boolean>> = flow {
        println()
        try {
            emit(RequestResponse.Loading())
            val hasSame = repository.hasPartnerWithSameName(nameToCheck)
            emit(RequestResponse.Success(hasSame))
        } catch (e: HttpException) {
            emit(RequestResponse.Error(null, e.localizedMessage?: "An unexpected error"))
        } catch (e: IOException) {
            emit(RequestResponse.Error(null, "Couldn't reach server. Check internet connection"))
        }
    }
}