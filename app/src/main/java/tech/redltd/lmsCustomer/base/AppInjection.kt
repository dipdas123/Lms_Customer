package tech.redltd.lmsCustomer.base

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.dsl.module
import tech.redltd.lmsCustomer.local.AppDatabase
import tech.redltd.lmsCustomer.network.ApiService
import tech.redltd.lmsCustomer.network.AspService
import tech.redltd.lmsCustomer.utils.AppUtils

val appModule = module {
    single { Room.databaseBuilder(get(),AppDatabase::class.java,"aniklmsdb")
        .fallbackToDestructiveMigration()
        .build()}
    single { AppUtils(get()) }
    single { ApiService(get<AppUtils>()) }
    single { AspService() }

}