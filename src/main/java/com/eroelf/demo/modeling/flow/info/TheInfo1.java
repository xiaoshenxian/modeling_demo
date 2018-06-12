package com.eroelf.demo.modeling.flow.info;

import com.eroelf.javaxsx.util.ml.feature.Item;
import com.eroelf.javaxsx.util.ml.flow.info.Info;

public class TheInfo1 implements Info
{
	// Define what the front-end want.
	public String title;
	public String label;
	public String price;
	public String score;
	public String featureString;

	@Override
	public boolean isValid()
	{
		// Here to define whether the final generated object valid.
		// Just follow the demand.
		return title!=null;
	}

	public void convertFrom(Item item, boolean verbose)
	{
		// One can also configure the data here rather than in the InfoFactory::create.
		// This method will be called after InfoFactory::create.
	}
}
