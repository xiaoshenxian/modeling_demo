package com.eroelf.demo.modeling.flow.filter;

import java.util.HashSet;
import java.util.Set;

import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.feature.item.TheItem1;
import com.eroelf.demo.modeling.feature.item.TheItem2;
import com.eroelf.demo.modeling.flow.ProductItemGroupStatistics;
import com.eroelf.javaxsx.util.ml.flow.controller.filter.ItemFilter;
import com.eroelf.javaxsx.util.ml.flow.controller.filter.ItemFilterHandler;
import com.eroelf.javaxsx.util.ml.flow.estimate.statistics.ItemGroupStatistics;

// Configure all the three kind of filters here: preFilter, innerFilter, and afterFilter.
// Filters generally represent strong rules highly related to the business or task, but have little relationship with model strategies and features, thus are not suitable to configure in Modelers.
public class ProductItemFilterHandler implements ItemFilterHandler<ProductItem>
{
	private static final Set<String> BLACK_SET;

	static
	{
		// A very simple black list.
		BLACK_SET=new HashSet<>();
		//...
	}

	@Override
	public ItemFilter<ProductItem> getPreFilter()
	{
		// preFilter, knows to retain or not at the time it receives the candidate.
		// For example, in the black list.
		return productItem -> !BLACK_SET.contains(productItem.productId);
	}

	@Override
	public ItemFilter<ProductItem> getInnerFilter()
	{
		// innerFilter, knows to retain or not only after be modeled and scored.
		// For example, drop those TheItem1 object with score less than 0.1 and #1 feature less than 2, and those TheItem2 object with #500 feature greater than 0.99.
		return new ItemFilter<ProductItem>() {
			@Override
			public boolean test(ProductItem item)
			{
				return !((item instanceof TheItem1 && item.score<0.1 && item.getFeature(1)<2) || (item instanceof TheItem2 && item.getFeature(500)>0.99));
			}
		};
	}

	@Override
	public ItemFilter<ProductItem> getAfterFilter(ItemGroupStatistics<ProductItem> itemGroupStatistics)
	{
		// afterFilter, filters candidates refers to all candidates statistics (itemGroupStatistics), after all candidates are modeled and scored.
		// For example, drop all TheItem1 objects whose distance from the requester are larger than 10 times the minimum value of the TheItem1-requester distances.
		return new ItemFilter<ProductItem>() {
			@Override
			public boolean test(ProductItem item)
			{
				return !(item instanceof TheItem1 && ((TheItem1)item).userDistance>10*((ProductItemGroupStatistics)itemGroupStatistics).minUserDistance);
			}
		};
	}
}
