package tech.redltd.lmsCustomer.network

import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original  = chain.request()
        val request = original.newBuilder()
            .header("Module","JW9tc0ByZWRsdGQl")
            .addHeader("Authorization","")
            .build()
        return chain.proceed(request)
    }


}