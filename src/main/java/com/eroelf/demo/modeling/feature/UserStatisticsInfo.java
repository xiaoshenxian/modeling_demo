package com.eroelf.demo.modeling.feature;

import com.eroelf.demo.modeling.data.RequestInfo;
import com.eroelf.javaxsx.util.ml.flow.controller.StatisticsInfo;

// This class calculate and recored some statistics information or something needed to be calculated in advance.
public class UserStatisticsInfo implements StatisticsInfo
{
    public UserStatisticsInfo(RequestInfo requestInfo/* The constructing information */)
    {
    	// Do the calculation and statistics here for the modeling task.
    	// An object of this class will be constructed before any feature to be calculated (before any calling of any Modeler object).
		// ...
    }
}
