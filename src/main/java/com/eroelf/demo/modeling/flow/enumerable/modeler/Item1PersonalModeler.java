package com.eroelf.demo.modeling.flow.enumerable.modeler;

import com.eroelf.demo.modeling.feature.UserStatisticsInfo;
import com.eroelf.demo.modeling.feature.item.TheItem1;
import com.eroelf.javaxsx.util.ml.feature.model.Modelable;
import com.eroelf.javaxsx.util.ml.feature.model.Modeler;

// An example for a Modeler related to both the product and the requester.
public class Item1PersonalModeler implements Modeler
{
	private int featureStart;
	private UserStatisticsInfo userStatisticsInfo;

	public Item1PersonalModeler(int featureStart, UserStatisticsInfo userStatisticsInfo)
	{
		// The featureStart is used to adjust feature ID range, for flexible.
		this.featureStart=featureStart;
		// This is the request information, statistics information, or any necessary information calculated in advance.
		this.userStatisticsInfo=userStatisticsInfo;
	}

	@Override
	public Modelable model(Modelable modelable)
	{
		if(modelable instanceof TheItem1)
		{
			if(userStatisticsInfo!=null)
			{
				TheItem1 theItem1=(TheItem1)modelable;
				// Now you have both the product (theItem1) and request information (userStatisticsInfo). Just do your featuring task.
				// ...
				// feature with ID (featureStart+5) is the 1.
				theItem1.setFeature(featureStart+5, 1);
			}
		}
		return modelable;
	}
}
