package com.eroelf.demo.modeling.feature.item;

import com.eroelf.javaxsx.util.ml.feature.MapFeatureItem;

// The MapFeatureItem implementation uses HashMap to store feature ID and feature value. Other implementations can be found in the package. 
// The super class of MapFeatureItem, Item, also implemented the UpdatableByItem interface which means an Item object can be "updated" (combine feature) by another object of the Item class.
public class ProductItem extends MapFeatureItem
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
