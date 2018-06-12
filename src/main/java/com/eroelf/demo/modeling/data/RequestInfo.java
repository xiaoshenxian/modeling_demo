package com.eroelf.demo.modeling.data;

public class RequestInfo
{
	// A very simple way to determine which kind of requester is requesting the service.
	// Different types are corresponded to different Info classes.
	public static enum RequestType
	{
		TYPE1, TYPE2;
	}

	// Some request information
	public String userId;
	public String query;
	// ...

	// The sub-list in the range of [start, start+length) of the ranking list will be returned.
	public int start;
	public int length;

	public RequestType requestType;

	// Determine whether or not to return some redundant information, like debug information, to the requester.
	public boolean verboseInfo;
	// Determine whether or not to log redundant information.
	public boolean verboseLog;
}
