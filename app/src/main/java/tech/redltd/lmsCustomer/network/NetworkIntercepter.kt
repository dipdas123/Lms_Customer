package tech.redltd.lmsCustomer.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader("Module", "JW9tc0ByZWRsdGQl")
            .addHeader("Authorization", "Bearer "+token)
            .build()
        return chain.proceed(request)
    }
}

object AspInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original  = chain.request()
        val request = original.newBuilder()
            .addHeader("Module","JW9tc0ByZWRsdGQl")
            .addHeader("Authorization","Basic MTg1NDc4NDUxMjpGYTEyMzQ1Njc4OQ==")
            .addHeader("x-Auth-Token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZ2VudElEIejoyLCJtb2JpbGUiOiIxOTEyNjEwODk5IiwiZmlyc3RuYW1lIjoiU2hhcmlmdWwiLCJvdHAiOjMwMTk1OCwiaWF0IjoxNTcwODU5NTYwfQ.k8ICcAyzAOqlbmgW0N-kr8lUMwcMLDOnTIFEbku0PAs")
            .build()
        return chain.proceed(request)
    }

}

