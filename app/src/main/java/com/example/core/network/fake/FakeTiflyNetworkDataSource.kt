package com.example.core.network.fake

import com.example.core.common.network.Dispatcher
import com.example.core.common.network.TiflyDispatchers
import com.example.core.network.JvmUnitTestFakeAssetManager
import com.example.core.network.TiflyNetworkDataSource
import com.example.core.network.model.NetworkCategory
import com.example.core.network.model.NetworkChangeList
import com.example.core.network.model.NetworkSign
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Inject


class FakeTiflyNetworkDataSource @Inject constructor(
    @Dispatcher(TiflyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : TiflyNetworkDataSource {

    companion object {
        private const val SIGNS_ASSET = "signs.json"
        private const val CATEGORIES_ASSET = "categories.json"
    }

    override suspend fun getSigns(ids: List<String>?): List<NetworkSign> {
        return emptyList()
    }

    override suspend fun getCategories(ids: List<String>?): List<NetworkCategory> {
        return emptyList()
    }

}

private fun <T> List<T>.mapToChangeList(
    idGetter: (T) -> String,
) = mapIndexed { index, item ->
    NetworkChangeList(
        id = idGetter(item),
        changeListVersion = index,
        isDelete = false,
    )
}
