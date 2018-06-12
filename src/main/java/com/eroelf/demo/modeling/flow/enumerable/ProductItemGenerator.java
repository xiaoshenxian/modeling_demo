package com.eroelf.demo.modeling.flow.enumerable;

import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.flow.ProductItemGroupStatistics;
import com.eroelf.javaxsx.util.ml.flow.controller.EnumerableFlowHandler;
import com.eroelf.javaxsx.util.ml.flow.controller.filter.ItemFilterHandler;
import com.eroelf.javaxsx.util.ml.flow.estimate.EnumerableItemGenerator;
import com.eroelf.javaxsx.util.ml.flow.estimate.statistics.ItemGroupStatistics;

public class ProductItemGenerator extends EnumerableItemGenerator<ProductItem>
{
	private EnumerableFlowHandler<ProductItem> flowHandler;
	private ItemFilterHandler<ProductItem> filterHandler;
	private boolean isDebug;

	public ProductItemGenerator(EnumerableFlowHandler<ProductItem> flowHandler, ItemFilterHandler<ProductItem> filterHandler, boolean isDebug)
	{
		this.flowHandler=flowHandler;
		this.filterHandler=filterHandler;
		this.isDebug=isDebug;
	}

	@Override
	protected EnumerableFlowHandler<ProductItem> getFlowHandler()
	{
		// Get the flow handler.
		// One can either pass it from outside just like this example to decouple this class and the business, or construct it in this method.
		return flowHandler;
	}

	@Override
	protected ItemFilterHandler<ProductItem> getFilterHandler()
	{
		// If there needs a filter handler, return it here.
		return filterHandler!=null ? filterHandler : super.getFilterHandler();
	}

	@Override
	protected void verbose(ProductItem item)
	{
		// Retain some redundant information related to the item.
		// In this example, saving the feature string if under a debug status.
		// Here, the redundant information is retained by the item object, of cause other places would be OK.
		if(isDebug)
			item.getFeatureString();
	}

	@Override
	protected ItemGroupStatistics<ProductItem> getItemGroupStatistics()
	{
		// What kind of statistics the working flow needs to calculate and use?
		// These statistics will be used by the filter returned by ItemFilterHandler::getAfterFilter, see the filter example class for detail.
		// One may like to pass the filter handler from outside of the class to gain more flexible.
		return filterHandler!=null ? new ProductItemGroupStatistics() : super.getItemGroupStatistics();
	}
}
