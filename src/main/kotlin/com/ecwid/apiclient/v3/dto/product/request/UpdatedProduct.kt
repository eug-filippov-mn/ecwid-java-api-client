package com.ecwid.apiclient.v3.dto.product.request

import com.ecwid.apiclient.v3.dto.common.ApiUpdatedDTO
import com.ecwid.apiclient.v3.dto.common.ApiUpdatedDTO.ModifyKind
import com.ecwid.apiclient.v3.dto.common.LocalizedValueMap
import com.ecwid.apiclient.v3.dto.common.ProductCondition
import com.ecwid.apiclient.v3.dto.product.enums.*
import com.ecwid.apiclient.v3.dto.product.result.FetchedProduct

data class UpdatedProduct(
	val name: String? = null,
	val nameTranslated: LocalizedValueMap? = null,
	val description: String? = null,
	val descriptionTranslated: LocalizedValueMap? = null,
	val sku: String? = null,
	val isSampleProduct: Boolean? = null,

	val enabled: Boolean? = null,
	val quantity: Int? = null,
	val unlimited: Boolean? = null,
	val warningLimit: Int? = null,

	val categoryIds: List<Int>? = null,
	val defaultCategoryId: Int? = null,
	val showOnFrontpage: Int? = null,

	val price: Double? = null,
	val wholesalePrices: List<WholesalePrice>? = null,
	val compareToPrice: Double? = null,

	val weight: Double? = null,
	val dimensions: ProductDimensions? = null,
	val shipping: ShippingSettings? = null,
	val isShippingRequired: Boolean? = null,

	val productClassId: Int? = null,
	val attributes: List<AttributeValue>? = null,

	val seoTitle: String? = null,
	val seoDescription: String? = null,

	val options: List<ProductOption>? = null,
	val tax: TaxInfo? = null,
	val relatedProducts: RelatedProducts? = null,

	val media: ProductMedia? = null,

	val subtitle: String? = null,
	val ribbon: Ribbon? = null,
	val ribbonTranslated: LocalizedValueMap? = null,
	val subtitleTranslated: LocalizedValueMap? = null,
	val nameYourPriceEnabled: Boolean? = null,
	val subscriptionSettings: SubscriptionSettings? = null,
	val googleProductCategory: Int? = null,
	val productCondition: ProductCondition? = null,
	val externalReferenceId: String? = null
) : ApiUpdatedDTO {

	data class Ribbon(
		val text: String? = null,
		val color: String? = null
	)

	data class SubscriptionSettings(
		val subscriptionAllowed: Boolean = false,
		val oneTimePurchaseAllowed: Boolean = false,
		val recurringChargeSettings: List<RecurringChargeSettings> = emptyList()
	)

	data class RecurringChargeSettings(
		val recurringInterval: RecurringSubscriptionInterval = RecurringSubscriptionInterval.MONTH,
		val recurringIntervalCount: Int = 1
	)

	data class WholesalePrice(
		val quantity: Int = 0,
		val price: Double = 0.0
	)

	data class TaxInfo(
		val taxable: Boolean = true,
		val enabledManualTaxes: List<Int>? = null
	)

	sealed class ProductOption constructor(
		val type: ProductOptionType? = null
	) {
		data class SelectOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val choices: List<ProductOptionChoice> = listOf(),
			val defaultChoice: Int = 0,
			val required: Boolean = false
		) : ProductOption(ProductOptionType.SELECT)

		data class SizeOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val choices: List<ProductOptionChoice> = listOf(),
			val defaultChoice: Int = 0,
			val required: Boolean = false
		) : ProductOption(ProductOptionType.SIZE)

		data class RadioOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val choices: List<ProductOptionChoice> = listOf(),
			val defaultChoice: Int = 0,
			val required: Boolean = false
		) : ProductOption(ProductOptionType.RADIO)

		data class CheckboxOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val choices: List<ProductOptionChoice> = listOf(),
			val required: Boolean = false
		) : ProductOption(ProductOptionType.CHECKBOX)

		data class TextFieldOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val required: Boolean = false
		) : ProductOption(ProductOptionType.TEXTFIELD)

		data class TextAreaOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val required: Boolean = false
		) : ProductOption(ProductOptionType.TEXTAREA)

		data class DateOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val required: Boolean = false
		) : ProductOption(ProductOptionType.DATE)

		data class FilesOption(
			val name: String = "",
			val nameTranslated: LocalizedValueMap? = null,
			val required: Boolean = false
		) : ProductOption(ProductOptionType.FILES)

		companion object {

			fun createSelectOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				choices: List<ProductOptionChoice> = listOf(),
				defaultChoice: Int = 0,
				required: Boolean = false
			) = SelectOption(
				name = name,
				nameTranslated = nameTranslated,
				choices = choices,
				defaultChoice = defaultChoice,
				required = required
			)

			fun createSizeOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				choices: List<ProductOptionChoice> = listOf(),
				defaultChoice: Int = 0,
				required: Boolean = false
			) = SizeOption(
				name = name,
				nameTranslated = nameTranslated,
				choices = choices,
				defaultChoice = defaultChoice,
				required = required
			)

			fun createRadioOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				choices: List<ProductOptionChoice> = listOf(),
				defaultChoice: Int = 0,
				required: Boolean = false
			) = RadioOption(
				name = name,
				nameTranslated = nameTranslated,
				choices = choices,
				defaultChoice = defaultChoice,
				required = required
			)

			fun createCheckboxOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				choices: List<ProductOptionChoice> = listOf()
			) = CheckboxOption(
				name = name,
				nameTranslated = nameTranslated,
				choices = choices
			)

			fun createTextFieldOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				required: Boolean = false
			) = TextFieldOption(
				name = name,
				nameTranslated = nameTranslated,
				required = required
			)

			fun createTextAreaOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				required: Boolean = false
			) = TextAreaOption(
				name = name,
				nameTranslated = nameTranslated,
				required = required
			)

			fun createDateOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				required: Boolean = false
			) = DateOption(
				name = name,
				nameTranslated = nameTranslated,
				required = required
			)

			fun createFilesOption(
				name: String = "",
				nameTranslated: LocalizedValueMap? = null,
				required: Boolean = false
			) = FilesOption(
				name = name,
				nameTranslated = nameTranslated,
				required = required
			)
		}
	}

	data class ProductOptionChoice(
		val text: String = "",
		val textTranslated: LocalizedValueMap? = null,
		val priceModifier: Double = 0.0,
		val priceModifierType: PriceModifierType = PriceModifierType.ABSOLUTE
	)

	data class ShippingSettings(
		val type: ShippingSettingsType? = null,
		val methodMarkup: Double? = null,
		val flatRate: Double? = null,
		val disabledMethods: List<String>? = null,
		val enabledMethods: List<String>? = null
	)

	data class AttributeValue internal constructor(
		val id: Int? = null,
		val alias: AttributeValueAlias? = null,
		val name: String? = null,
		val value: String? = null
	) {

		companion object {

			fun createBrandAttributeValue(value: String) = AttributeValue(
				id = null,
				alias = AttributeValueAlias.BRAND,
				value = value
			)

			fun createUpcAttributeValue(value: String) = AttributeValue(
				id = null,
				alias = AttributeValueAlias.UPC,
				value = value
			)

			fun createPricePerUnitAttributeValue(value: String) = AttributeValue(
				id = null,
				alias = AttributeValueAlias.PRICE_PER_UNIT,
				value = value
			)

			fun createUnitsInProductAttributeValue(value: String) = AttributeValue(
				id = null,
				alias = AttributeValueAlias.UNITS_IN_PRODUCT,
				value = value
			)

			@Suppress("unused")
			fun createAttributeValue(productAttributeId: Int, value: String) = AttributeValue(
				id = productAttributeId,
				value = value
			)

			@Suppress("unused")
			fun createAttributeValue(name: String, value: String) = AttributeValue(
				name = name,
				value = value
			)
		}
	}

	data class RelatedProducts(
		val productIds: List<Int>? = null,
		val relatedCategory: RelatedCategory? = null
	)

	data class RelatedCategory(
		val enabled: Boolean? = null,
		val categoryId: Int? = null,
		val productCount: Int? = null
	)

	data class ProductDimensions(
		val length: Double? = null,
		val width: Double? = null,
		val height: Double? = null
	)

	data class ProductMedia(
		val images: List<ProductImage>? = null
	)

	data class ProductImage(
		val id: String = "0",
		val orderBy: Int = 0
	)

	override fun getModifyKind() = ModifyKind.ReadWrite(FetchedProduct::class)
}
