package tech.redltd.lmsCustomer.base

import org.koin.dsl.module
import tech.redltd.lmsCustomer.network.ApiService
import tech.redltd.lmsCustomer.utils.AppUtils

val appModule = module {
    single { ApiService() }
    single { AppUtils(get()) }
}