package com.ecwid.apiclient.v3.dto.order.request

import com.ecwid.apiclient.v3.dto.ApiRequest
import com.ecwid.apiclient.v3.impl.RequestInfo

data class OrderItemOptionFileDeleteRequest(
		var orderNumber: Int = 0,
		var orderIdentity: String = "",
		var orderItemId: Int = 0,
		var optionName: String = "",
		var fileId: Int = 0
) : ApiRequest {
	constructor(orderNumber: Int = 0,
				orderItemId: Int = 0,
				optionName: String = "",
				fileId: Int = 0
	) : this(orderNumber, orderNumber.toString(), orderItemId, optionName, fileId)

	override fun toRequestInfo() = RequestInfo.createDeleteRequest(
			endpoint = "orders/$orderIdentity/items/$orderItemId/options/$optionName/files/$fileId"
	)
}
