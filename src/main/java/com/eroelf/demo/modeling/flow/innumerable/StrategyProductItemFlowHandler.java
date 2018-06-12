package com.eroelf.demo.modeling.flow.innumerable;

import java.util.Arrays;
import java.util.List;

import com.eroelf.demo.modeling.data.RequestInfo;
import com.eroelf.demo.modeling.feature.UserStatisticsInfo;
import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.feature.scorer.Scorers;
import com.eroelf.demo.modeling.flow.innumerable.strategy.TheStrategy1;
import com.eroelf.demo.modeling.flow.innumerable.strategy.TheStrategy2;
import com.eroelf.javaxsx.util.ml.feature.score.Scorer;
import com.eroelf.javaxsx.util.ml.feature.strategy.Strategy;
import com.eroelf.javaxsx.util.ml.flow.controller.InnumerableFlowHandler;
import com.eroelf.javaxsx.util.ml.flow.controller.StatisticsInfo;

public class StrategyProductItemFlowHandler implements InnumerableFlowHandler<ProductItem>
{
	private RequestInfo requestInfo;
	private UserStatisticsInfo userStatisticsInfo;

	public StrategyProductItemFlowHandler(RequestInfo requestInfo)
	{
		this.requestInfo=requestInfo;
		// Calculate statistics related to the requester.
		// Calculate all the necessary information which is needed by the following working flow and can be calculate in advance.
		userStatisticsInfo=(UserStatisticsInfo)calcStatisticsInfo();
	}

	@Override
	public StatisticsInfo calcStatisticsInfo()
	{
		// Calculate statistics related to the requester.
		// Calculate all the necessary information which is needed by the following working flow and can be calculate in advance.
		return new UserStatisticsInfo(requestInfo);
	}

	@Override
	public List<Strategy<ProductItem>> getStrategies()
	{
		// Generate a Strategy list for the working flow.
		// One may like passing the list from outside of the class for decoupling and flexibility.
		return Arrays.asList(new TheStrategy1(userStatisticsInfo), new TheStrategy2());
	}

	@Override
	public Scorer getScorer()
	{
		// Which Scorer is needed?
		// In general, a Scorer for a specified model is globally unique.
		// Here is a scorer to request another service to score a batch sample.
		// A scorer to batch sample should support batch size equal or less to the batch size, since the size of all candidates may not be divisible by the batch size.
		return Scorers.REQUEST_SCORER;
	}

	@Override
	public int getBatchSize()
	{
		// The batch size corresponding to the Scorer.
		// If the Scorer should be invoke for each sample, this method should return 1.
		// Here is an example for a batch-request-scorer, the number 512 is set to invoke the Scorer for every 512 samples.
		// The scorer must support batch size less than 512 for the last mini-batch.
		return 512;
	}
}
