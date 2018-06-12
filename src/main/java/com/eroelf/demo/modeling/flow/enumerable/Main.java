package com.eroelf.demo.modeling.flow.enumerable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eroelf.demo.modeling.data.RequestInfo;
import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.flow.converter.TheConverter1;
import com.eroelf.demo.modeling.flow.converter.TheConverter2;
import com.eroelf.demo.modeling.flow.filter.ProductItemFilterHandler;
import com.eroelf.demo.modeling.flow.log.TheLog;
import com.eroelf.javaxsx.util.ml.flow.controller.EnumerableFlowHandler;
import com.eroelf.javaxsx.util.ml.flow.controller.filter.ItemFilterHandler;
import com.eroelf.javaxsx.util.ml.flow.convert.Converter;
import com.eroelf.javaxsx.util.ml.flow.estimate.ItemGenerator;
import com.eroelf.javaxsx.util.ml.flow.info.Info;
import com.eroelf.javaxsx.util.ml.flow.log.InfoLog;
import com.google.gson.Gson;

// Here is an example for a whole ENUMERABLE working flow.
public class Main
{
	public static RequestInfo parseRequest(String[] args)
	{
		// Interpret request parameters here for different kind of request.
		// ...
		return new RequestInfo();
	}

	// A very simple way to construct a Converter related to requester types.
	public static Converter<ProductItem, ? extends Info, TheLog> getConverter(RequestInfo requestInfo)
	{
		switch(requestInfo.requestType)
		{
		case TYPE1:
			// Return a TheConverter1 instance if there comes a TYPE1 requester.
			return new TheConverter1();
		case TYPE2:
			// Return a TheConverter2 instance if there comes a TYPE2 requester.
			return new TheConverter2();
		}
		return null;
	}

	// Entrance to an enumerable working flow.
	// Each calling simulates a single request.
	public static void main(String[] args)
	{
		// Interpret request parameters.
		RequestInfo requestInfo=parseRequest(args);

		// For converting a back-end product data structure (Item) list to a front-end product data structure (Info) list.
		// As well as logging necessary information for each Info object to a logger data structure (InfoLog).
		Converter<ProductItem, ? extends Info, TheLog> converter=getConverter(requestInfo);

		// Construct the working flow handler.
		EnumerableFlowHandler<ProductItem> flowHandler=new ProductItemFlowHandler(requestInfo);
		// Construct the candidate filter.
		// Filters generally represent strong rules highly related to the business or task, but have little relationship with model strategies and features, thus are not suitable to configure in Modelers.
		ItemFilterHandler<ProductItem> filterHandler=new ProductItemFilterHandler();
		// Construct the sample generator.
		ItemGenerator<ProductItem> itemGenerator=new ProductItemGenerator(flowHandler, filterHandler, requestInfo.verboseInfo || requestInfo.verboseLog);
		// Generate the sample list.
		// The Modeler and Scorer provided by the flowHandler will be used to generate features and calculate scores for every Items in the candidate set, respectively.
		// Any Items which are rejected by the filterHandler will be dropped.
		// This method will NOT do the sort.
		List<ProductItem> sampleList=itemGenerator.generate();
		// Sort by the final scores of each Items, in descendant order.
		Collections.sort(sampleList);

		List<Info> whatShouldBeReturned=new ArrayList<>();
		List<InfoLog> whatShouldBeLogged=new ArrayList<>();
		// Use the pre-constructed converter and the generated sampleList to construct returning data and their logs.
		// There is a one-to-one correspondence between the final whatShouldBeReturned list and the whatShouldBeLogged list.
		// Check the Javadoc for detail.
		converter.convert(sampleList, whatShouldBeReturned, whatShouldBeLogged, requestInfo.start, requestInfo.length, requestInfo.verboseInfo, requestInfo.verboseLog);

		// logging whatShouldBeLogged list.
		System.err.println(new Gson().toJson(whatShouldBeLogged));

		// Return what the requester need.
		System.out.println(new Gson().toJson(whatShouldBeReturned));
	}
}
