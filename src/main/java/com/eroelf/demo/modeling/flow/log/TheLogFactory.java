package com.eroelf.demo.modeling.flow.log;

import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.javaxsx.util.ml.flow.log.InfoLogFactory;

public class TheLogFactory implements InfoLogFactory<ProductItem, TheLog>
{
	@Override
	public TheLog create(ProductItem item, boolean verbose)
	{
		TheLog theLog=new TheLog();
		// Here is how to convert the back-end data to the log object.
		// Design for decoupling the back-end data structure and the logger data structure.
		// One can choose to put the converting code here or in InfoLog::logFrom method.
		if(item instanceof ProductItem)
		{
			ProductItem productItem=(ProductItem)item;
			theLog.productId=productItem.productId;
			// A redundant output example: output the feature string only when the verbose is true.
			if(verbose)
				theLog.featureString=productItem.getFeatureString();
		}
		// In principle, null is not allowed. Because the log must be legal if a valid Info has been converted, aiming to guarantee the one-to-one correspondence.
		return theLog;
	}
}
