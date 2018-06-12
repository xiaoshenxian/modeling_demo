package com.eroelf.demo.modeling.flow.enumerable.modeler;

import com.eroelf.demo.modeling.feature.item.TheItem1;
import com.eroelf.javaxsx.util.ml.feature.model.Modelable;
import com.eroelf.javaxsx.util.ml.feature.model.Modeler;

// An example for a Modeler related only to the product.
public class Item1BasicModeler implements Modeler
{
	@Override
	public Modelable model(Modelable modelable)
	{
		// If this object is an instance of TheItem1
		if(modelable instanceof TheItem1)
		{
			TheItem1 theItem1=(TheItem1)modelable;
			// feature with ID 1 is the grade score.
			theItem1.setFeature(1, theItem1.gradeScore);
			// feature with ID 10 is the CTR.
			theItem1.setFeature(10, theItem1.ctr);
		}
		return modelable;
	}
}
