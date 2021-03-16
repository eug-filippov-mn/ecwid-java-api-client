package com.ecwid.apiclient.v3.dto.profile.request

import com.ecwid.apiclient.v3.dto.ApiRequest
import com.ecwid.apiclient.v3.impl.RequestInfo

class StoreProfileRequest(
		val lang: String? = null
) : ApiRequest {

	override fun toRequestInfo() = RequestInfo.createGetRequest(
			endpoint = "profile",
			params = toParams()
	)

	private fun toParams(): Map<String, String> {
		val request = this
		return mutableMapOf<String, String>().apply {
			request.lang?.let { put("lang", it) }
		}.toMap()
	}

}
