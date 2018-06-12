package com.eroelf.demo.modeling.flow.info;

import com.eroelf.javaxsx.util.ml.feature.Item;
import com.eroelf.javaxsx.util.ml.flow.info.Info;

public class TheInfo2 implements Info
{
	// Define what the front-end want.
	public String title;
	public String label;
	public String url;

	@Override
	public boolean isValid()
	{
		// Here to define whether the final generated object valid.
		// Just follow the demand.
		return url!=null;
	}

	public void convertFrom(Item item, boolean verbose)
	{
		// One can also configure the data here rather than in the InfoFactory::create.
		// This method will be called after InfoFactory::create.
	}
}
