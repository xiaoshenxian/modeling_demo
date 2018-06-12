package com.eroelf.demo.modeling.flow.enumerable;

import java.util.HashSet;

import com.eroelf.demo.modeling.data.RequestInfo;
import com.eroelf.demo.modeling.feature.UserStatisticsInfo;
import com.eroelf.demo.modeling.feature.item.ProductItem;
import com.eroelf.demo.modeling.feature.scorer.Scorers;
import com.eroelf.demo.modeling.flow.enumerable.modeler.Item1BasicModeler;
import com.eroelf.demo.modeling.flow.enumerable.modeler.Item1PersonalModeler;
import com.eroelf.demo.modeling.flow.enumerable.modeler.Item2BasicModeler;
import com.eroelf.javaxsx.util.ml.feature.model.Modeler;
import com.eroelf.javaxsx.util.ml.feature.model.impl.CombinedModeler;
import com.eroelf.javaxsx.util.ml.feature.score.Scorer;
import com.eroelf.javaxsx.util.ml.flow.controller.EnumerableFlowHandler;
import com.eroelf.javaxsx.util.ml.flow.controller.StatisticsInfo;

public class ProductItemFlowHandler implements EnumerableFlowHandler<ProductItem>
{
	private RequestInfo requestInfo;
	private UserStatisticsInfo userStatisticsInfo;

	public ProductItemFlowHandler(RequestInfo requestInfo)
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
	public Iterable<ProductItem> getCandidates()
	{
		// An Iterator object iterates all the candidates should be returned, since it is an enumerable-candidate task.
		// If there is a different data structure of those candidates at hand, one can encapsulate it into a sub-class of ProductItem. 
		return new HashSet<>();
	}

	@Override
	public Modeler getModeler()
	{
		// Combine all the Modelers needed by current working flow.
		CombinedModeler combinedModeler=new CombinedModeler();
		combinedModeler.addModeler(new Item1BasicModeler());
		combinedModeler.addModeler(new Item1PersonalModeler(100, userStatisticsInfo));
		combinedModeler.addModeler(new Item2BasicModeler());
		return combinedModeler;
	}

	@Override
	public Scorer getScorer()
	{
		// Which Scorer is needed?
		// In general, a Scorer for a specified model is globally unique.
		// Here is the logistic regression Scorer.
		return Scorers.LR_SCORER;
	}

	@Override
	public int getBatchSize()
	{
		// The batch size corresponding to the Scorer.
		// Here is 1 for calling the Scorer for each candidate.
		return 1;
	}
}
