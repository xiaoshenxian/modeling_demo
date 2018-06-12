package com.eroelf.demo.modeling.flow.converter;

import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.feature.item.TheItem1;
import com.eroelf.demo.modeling.feature.item.TheItem2;
import com.eroelf.demo.modeling.flow.info.TheInfo2;
import com.eroelf.demo.modeling.flow.log.TheLog;
import com.eroelf.demo.modeling.flow.log.TheLogFactory;
import com.eroelf.javaxsx.util.ml.flow.convert.Converter;
import com.eroelf.javaxsx.util.ml.flow.info.InfoFactory;
import com.eroelf.javaxsx.util.ml.flow.log.InfoLogFactory;

//A converter converts an Item instance to an Info instance and an InfoLog instance.
public class TheConverter2 extends Converter<ProductItem, TheInfo2, TheLog>
{
	@Override
	public InfoFactory<ProductItem, TheInfo2> getInfoFactory()
	{
		// A factory for creating object with a format the requester need.
		// Do some initialization if it is necessary.
		return new InfoFactory<ProductItem, TheInfo2>() {
			@Override
			public TheInfo2 create(ProductItem item, boolean verbose)
			{
				// new an object with the format the requester want.
				TheInfo2 info=new TheInfo2();

				// Here is how to convert the back-end data to the object the requester want.
				// Design for decoupling the back-end data structure and the front-end data structure.
				// One can choose to put the converting code here or in Info::convertFrom method.
				if(item instanceof TheItem1)
				{
					TheItem1 theItem=(TheItem1)item;
					info.title=theItem.name;
					info.label="type1";
					info.url="abcd";
					return info;
				}
				else if(item instanceof TheItem2)
				{
					TheItem2 theItem=(TheItem2)item;
					info.title=theItem.title;
					info.label="type2";
					info.url="efgh";
					return info;
				}

				// return null if you do not need this product or an exception thrown.
				return null;
			}
		};
	}

	@Override
	public InfoLogFactory<ProductItem, TheLog> getInfoLogFactory()
	{
		// new a object for the logger data structure.
		return new TheLogFactory();

		// Do some logging staff here or in the InfoLog::logFrom method.
		// ...
	}
}
