# Ecwid java api client library ![](https://github.com/Ecwid/ecwid-java-api-client/workflows/Gradle%20Package/badge.svg)

API documentation: https://api-docs.ecwid.com/reference/overview 

#### Configure client library:
You can test this api client library on you local storage. 
For this you need to copy test/resources/test.properties.sample renamed to test.properties
and configure it

For example: `test.properties`
```
storeId=2
apiToken=secret_4T6z...
apiHost=app.local.ecwid.com
apiPort=8443
```

## Examples

#### Simple example:
```
val apiClient = ApiClient.create(
		apiServerDomain = ApiServerDomain(),
		storeCredentials = ApiStoreCredentials(
				storeId = 1003,
				apiToken = "secret_mysecuretoken"),
		httpTransport = ApacheCommonsHttpClientTransport(),
		jsonTransformerProvider = GsonTransformerProvider()

val customer = apiClient.getCustomerDetails(CustomerDetailsRequest(customerId = 1))
println("api/v3 customer: $customer")
```

#### Batch api example:
```
val apiClient = ApiClient.create(
		apiServerDomain = ApiServerDomain(),
		storeCredentials = ApiStoreCredentials(
				storeId = 1003,
				apiToken = "secret_mysecuretoken"),
		httpTransport = ApacheCommonsHttpClientTransport(),
		jsonTransformerProvider = GsonTransformerProvider()

val requestsForBatch = listOf(CustomerDetailsRequest(1), CustomerDetailsRequest(2))
val batch = apiClient.createBatch(CreateBatchRequest(requestsForBatch, stopOnFirstFailure = true))

while (true) {
	val typedBatch = apiClient.getTypedBatch(GetEscapedBatchRequest(batch.ticket))
	if (typedBatch.status != BatchStatus.COMPLETED) {
		TimeUnit.SECONDS.sleep(2)
		continue
	}
	val customers = typedBatch.responses.orEmpty()
			.map { it.toTypedResponse(FetchedCustomer::class.java) }
			.mapNotNull { if (it is TypedBatchResponse.Ok<FetchedCustomer>) it.value else null }
	val errors = typedBatch.responses.orEmpty()
			.map { it.toTypedResponse(FetchedCustomer::class.java) }
			.mapNotNull { if (it !is TypedBatchResponse.Ok<FetchedCustomer>) it.toString() else null }
	println("api/v3 customers: ${customers.joinToString { it.id.toString() }}, errors: ${errors.joinToString()}")
	break;
}
```
