package com.eroelf.demo.modeling.feature.scorer;

import java.util.ArrayList;
import java.util.List;

import com.eroelf.javaxsx.util.ml.feature.BatchScoreableRestrictedBatchSample;
import com.eroelf.javaxsx.util.ml.feature.score.BatchScorer;

// An example of a scorer which will request another service.
public class RequestScorer extends BatchScorer
{
	public List<Double> getAllScores(BatchScoreableRestrictedBatchSample<?> batchSample)
	{
		// Go to request a specified service, e.g., a Tensorflow server, get a score list with an order corresponding to the product order in the batchSample.
		// Since this is a demo, this function does nothing.
		// Type checking for batchSample is required.
		return new ArrayList<>(batchSample.getValidSize());
	}
}
