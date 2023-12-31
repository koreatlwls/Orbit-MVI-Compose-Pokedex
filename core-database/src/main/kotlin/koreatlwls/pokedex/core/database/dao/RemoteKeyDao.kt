package koreatlwls.pokedex.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import koreatlwls.pokedex.core.model.RemoteKey

@Dao
interface RemoteKeyDao {

    @Upsert
    suspend fun upsert(remoteKey: RemoteKey)

    @Query("SELECT * FROM RemoteKey WHERE `key` = :key")
    suspend fun remoteKeyByQuery(key: String): RemoteKey

    @Query("DELETE FROM RemoteKey WHERE `key` = :key")
    suspend fun deleteByKey(key: String)

}