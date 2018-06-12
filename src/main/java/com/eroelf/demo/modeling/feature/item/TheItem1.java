package com.eroelf.demo.modeling.feature.item;

public class TheItem1 extends ProductItem
{
	// Put your real product information here.
	// Such as the name, a user grade score, or a CTR or something else.
	public String name;
	public double gradeScore;
	public double ctr;

	// As well as some other necessary information related to the project.
	// Like a distance from user.
	public double userDistance;

	public TheItem1(String productId/*, Other constructing information. */)
	{
		super(productId);
		// ...
	}
}
