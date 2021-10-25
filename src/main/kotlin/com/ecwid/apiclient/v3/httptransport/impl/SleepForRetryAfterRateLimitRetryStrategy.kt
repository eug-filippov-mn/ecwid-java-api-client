package com.ecwid.apiclient.v3.httptransport.impl

import com.ecwid.apiclient.v3.httptransport.HttpRequest
import com.ecwid.apiclient.v3.httptransport.HttpResponse
import com.ecwid.apiclient.v3.httptransport.TransportHttpBody
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpUriRequest
import java.io.IOException

class SleepForRetryAfterRateLimitRetryStrategy(
	private val defaultRateLimitRetryInterval: Long,
	private val maxRateLimitRetryInterval: Long,
	private val defaultRateLimitAttempts: Int,
	private val onEverySecondOfWaiting: (Long) -> Unit,
	private val beforeEachRequestAttempt: () -> Unit,
) : RateLimitRetryStrategy {

	override fun makeHttpRequest(httpClient: HttpClient, httpRequest: HttpRequest): HttpResponse {
		val request = httpRequest.toHttpUriRequest()
		return if (httpRequest.transportHttpBody is TransportHttpBody.InputStreamBody) {
			executeWithoutRetry(httpClient, request)
		} else {
			executeWithRetryOnRateLimited(httpClient, request)
		}
	}

	private fun executeWithoutRetry(httpClient: HttpClient, request: HttpUriRequest) =
		httpClient.execute(request).toApiResponse()

	private fun executeWithRetryOnRateLimited(httpClient: HttpClient, request: HttpUriRequest): HttpResponse {
		return try {
			RateLimitedHttpClientWrapper(
                httpClient,
                defaultRateLimitRetryInterval,
                maxRateLimitRetryInterval,
                defaultRateLimitAttempts,
                onEverySecondOfWaiting,
				beforeEachRequestAttempt
            ).execute(request) { response ->
				response.toApiResponse()
			}
		} catch (e: IOException) {
            HttpResponse.TransportError(e)
		}
	}

}
