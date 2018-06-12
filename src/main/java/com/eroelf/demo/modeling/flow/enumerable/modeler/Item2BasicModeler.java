package com.eroelf.demo.modeling.flow.enumerable.modeler;

import com.eroelf.demo.modeling.feature.item.TheItem2;
import com.eroelf.javaxsx.util.ml.feature.model.Modelable;
import com.eroelf.javaxsx.util.ml.feature.model.Modeler;

//An example for a Modeler related only to the product.
public class Item2BasicModeler implements Modeler
{
	@Override
	public Modelable model(Modelable modelable)
	{
		// If this object is an instance of TheItem2
		if(modelable instanceof TheItem2)
		{
			TheItem2 theItem2=(TheItem2)modelable;
			// feature with ID 500 is the weight.
			theItem2.setFeature(500, theItem2.weight);
		}
		return modelable;
	}
}
