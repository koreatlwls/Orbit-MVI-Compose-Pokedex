package di

import database.PokemonDataBase
import database.PokemonDataBase.Companion.DB_NAME
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataBaseModule {

    @Provides
    @Singleton
    fun provideApplicationDataBase(
        @ApplicationContext context : Context,
    ) : PokemonDataBase =
        Room.databaseBuilder(
            context,
            PokemonDataBase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()

}