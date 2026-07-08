package com.demis.gymsharkshop.data.repository

import android.util.Log
import com.demis.gymsharkshop.data.mapper.toProduct
import com.demis.gymsharkshop.data.remote.ProductApi
import com.demis.gymsharkshop.domain.ErrorType
import com.demis.gymsharkshop.domain.Product
import com.demis.gymsharkshop.domain.ProductRepository
import com.demis.gymsharkshop.domain.ProductResult
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
) : ProductRepository {

    private val cache = ConcurrentHashMap<Long, Product>()

    override suspend fun getProducts(): ProductResult =
        try {
            val products = api.getProducts().hits.map { it.toProduct() }
            cache.clear()
            products.forEach { cache[it.id] = it }

            ProductResult.Success(products)
        } catch (e: CancellationException) {
            throw e
        } catch (e: IOException) {
            ProductResult.Error(ErrorType.NO_INTERNET)
        } catch (e: HttpException) {
            ProductResult.Error(ErrorType.SERVER)
        } catch (e: SerializationException) {
            ProductResult.Error(ErrorType.SERIALIZATION)
        } catch (e: Exception) {
            Log.e("Unknown Error", e.message.toString())
            ProductResult.Error(ErrorType.UNKNOWN)
        }

    override fun getCachedProductById(id: Long): Product? = cache[id]
}