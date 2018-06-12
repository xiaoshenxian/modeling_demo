package com.eroelf.demo.modeling.feature.item;

import com.eroelf.javaxsx.util.ml.feature.MapFeatureItem;
import com.eroelf.javaxsx.util.ml.feature.UpdatableByItem;

// Extends <1> implements <2>:
// 1. This implementation uses HashMap to store feature ID and feature value. Other implementations can be found in the package. 
// 2. Means this object can be "updated" (combine feature) by another object of the same class.
public class ProductItem extends MapFeatureItem implements UpdatableByItem<ProductItem>
{
	// Some common information for all products in the project, such as a product ID.
	public String productId;

	private String featureString;

	public ProductItem(String productId)
	{
		this.productId=productId;
	}

	@Override
	public String getFeatureString()
	{
		// One may like to use the feature string many times but to generate it only once for saving computing resource.
		if(featureString==null)
			featureString=super.getFeatureString();
		return featureString;
	}

	// Define the feature combination strategy.
	// This method will be invoked during an innumerable working flow.
	@Override
	public void update(ProductItem item)
	{
		featuresMap.putAll(item.featuresMap);
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof ProductItem && this.productId.equals(((ProductItem)obj).productId);
	}

	@Override
	public int hashCode()
	{
		return productId.hashCode();
	}
}
