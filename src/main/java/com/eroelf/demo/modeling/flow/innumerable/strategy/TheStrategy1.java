package com.eroelf.demo.modeling.flow.innumerable.strategy;

import java.util.HashSet;
import java.util.Set;

import com.eroelf.demo.modeling.feature.UserStatisticsInfo;
import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.feature.item.TheItem1;
import com.eroelf.demo.modeling.feature.item.TheItem2;
import com.eroelf.demo.modeling.flow.enumerable.modeler.Item1PersonalModeler;
import com.eroelf.javaxsx.util.ml.feature.model.Modelable;
import com.eroelf.javaxsx.util.ml.feature.strategy.Strategy;

// This is an example for enumerable strategy candidates.
// See TheStrategy2 for innumerable strategy candidates.
public class TheStrategy1 extends Strategy<ProductItem>
{
	private Item1PersonalModeler item1PersonalModeler;

	public TheStrategy1(UserStatisticsInfo userStatisticsInfo)
	{
		item1PersonalModeler=new Item1PersonalModeler(100, userStatisticsInfo);
	}

	@Override
	public Modelable model(Modelable modelable)
	{
		// Here to model (calculate features for) all candidates.
		if(modelable instanceof TheItem1)
		{
			TheItem1 theItem1=(TheItem1)modelable;
			// Directly set feature value.
			theItem1.setFeature(1, theItem1.gradeScore);
			theItem1.setFeature(10, theItem1.ctr);
			// Or invoke a Modeler.
			theItem1.modelBy(item1PersonalModeler);
		}
		else if(modelable instanceof TheItem2)
		{
			TheItem2 theItem2=(TheItem2)modelable;
			theItem2.setFeature(500, theItem2.weight);
		}
		return modelable;
	}

	@Override
	protected Set<ProductItem> candicates()
	{
		// Generate all candidates related to this strategy here.
		// Candidate sets from different strategies should not be mutual exclusive. See UpdatableByItem::update
		return new HashSet<>();
	}
}
