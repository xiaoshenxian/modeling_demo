package com.eroelf.demo.modeling.feature.scorer;

import com.eroelf.javaxsx.util.ml.feature.score.Scorer;
import com.eroelf.javaxsx.util.ml.feature.score.impl.LogisticRegressionScorer;

public class Scorers
{
	// In general, a scorer for a specified model is unique in global.
	// Thus just put it to some place, in this simple case, a static final variable scope.
	public static final Scorer LR_SCORER;
	public static final Scorer REQUEST_SCORER;

	static
	{
		// Don't forget to do the initialization.
		LR_SCORER=new LogisticRegressionScorer(Scorer.class, "/weight", "/mapping");
		REQUEST_SCORER=new RequestScorer();
	}
}
