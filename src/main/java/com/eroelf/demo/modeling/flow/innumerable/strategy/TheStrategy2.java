package com.eroelf.demo.modeling.flow.innumerable.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.feature.item.TheItem1;
import com.eroelf.demo.modeling.feature.item.TheItem2;
import com.eroelf.javaxsx.util.ml.feature.model.Modelable;
import com.eroelf.javaxsx.util.ml.feature.strategy.Strategy;
import com.eroelf.javaxsx.util.ml.flow.controller.filter.ItemFilter;

//If the strategy candidates are innumerable.
public class TheStrategy2 extends Strategy<ProductItem>
{
	@Override
	public Modelable model(Modelable modelable)
	{
		// Throw an exception when misuse.
		throw new UnsupportedOperationException();
	}

	@Override
	protected Set<ProductItem> candicates()
	{
		// Throw an exception when misuse.
		throw new UnsupportedOperationException();
	}

	// How to deal with the situation that even the Strategy candidates are innumerable?
	// A simple solution is to override the generate method, and ignore the two methods above.
	// See the ProductItemFilterHandler class to learn what is a preFilter.
	@Override
	public List<ProductItem> generate(ItemFilter<ProductItem> preFilter)
	{
		List<ProductItem> res=new ArrayList<>();
		TheItem1 theItem1=new TheItem1("The theItem1 product ID");
		if(preFilter.test(theItem1))
		{
			theItem1.setFeature(1, theItem1.gradeScore);
			res.add(theItem1);
		}
		TheItem2 theItem2=new TheItem2("The theItem2 product ID");
		if(preFilter.test(theItem2))
		{
			theItem2.setFeature(500, theItem2.weight);
			res.add(theItem2);
		}
		return res;
	}
}
