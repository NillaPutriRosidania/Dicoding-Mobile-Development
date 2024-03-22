package com.example.githubusernilla.retrofit
import com.example.githubusernilla.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "token " + BuildConfig.GITHUB_API_TOKEN)
            .build()
        return chain.proceed(newRequest)
    }
}
