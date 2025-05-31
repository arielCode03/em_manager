package com.example.helloworldcompose.di



import android.content.Context
import androidx.room.Room
import com.example.helloworldcompose.data.local.AppDatabase
import com.example.helloworldcompose.data.local.dao.*
import com.example.helloworldcompose.data.repository.DashboardRepository
import com.example.helloworldcompose.data.repository.TrabajadorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Context): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "mi_bd.db").build()

    @Provides
    fun provideTrabajadorDao(db: AppDatabase): TrabajadorDao = db.trabajadorDao()

    @Provides
    fun provideVentaDao(db: AppDatabase): VentaDao = db.ventaDao()

    @Provides
    fun provideDiaTrabajadoDao(db: AppDatabase): DiaTrabajadoDao = db.diaTrabajadoDao()

    @Provides
    @Singleton
    fun provideDashboardRepository(
        trabajadorDao: TrabajadorDao,
        ventaDao: VentaDao,
        diaTrabajadoDao: DiaTrabajadoDao
    ): DashboardRepository = DashboardRepository(trabajadorDao, ventaDao, diaTrabajadoDao)

    @Provides
    @Singleton
    fun provideTrabajadorRepository(trabajadorDao: TrabajadorDao): TrabajadorRepository =
        TrabajadorRepository(trabajadorDao)
}
