package com.eroelf.demo.modeling.flow;

import com.eroelf.demo.modeling.feature.item.TheItem1;
import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.javaxsx.util.ml.flow.estimate.statistics.ItemGroupStatistics;

public class ProductItemGroupStatistics extends ItemGroupStatistics<ProductItem>
{
	// Save some statistics for generated samples.
	public double minUserDistance=Double.MAX_VALUE;

	@Override
	public void computeStatistics(Iterable<ProductItem> itemIter)
	{
		// Calculate statistics that can only be calculated after all candidates have been modeled and scored.
		// For example, mean and standard deviation along a certain feature ID.
		// This method will be invoked only once after all Items have been modeled and scored.
	}

	@Override
	public void increaseStatistics(ProductItem item)
	{
		// Calculate statistics that can be cumulatively calculated in the process of item generation.
		// For example, here to calculate the minimum distance that all TheItem1 candidates from the requester.
		// This method will be invoked after each Item has been modeled and scored.
		if(item instanceof TheItem1)
		{
			TheItem1 theItem1=(TheItem1)item;
			if(theItem1.userDistance>=0 && minUserDistance>theItem1.userDistance)
				minUserDistance=theItem1.userDistance;
		}
	}
}
