package com.codepath.apps.HW4TwitterFragments.REST;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 *
 * This is the object responsible for communicating with a REST API.
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes:
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 *
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 *
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "hYSq9afJrBpSGcuTmjGFEuXJe";       // Change this
	public static final String REST_CONSUMER_SECRET = "CvK9YVEZR9I8eBGPVlidLkAh2NyEO7nRDjXGt7TZupy5n0w7Os"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here
	public void getInterestingnessList(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}

	// METHOD = ENDPOINT

	// HomeTimeLine - GETs us the home timeline
	//Get the home timeline for the user
	//		GET https://api.twitter.com/1.1/statuses/home_timeline.json
	//		count=25
	//		since_id=1

	public void getHomeTimeLine(AsyncHttpResponseHandler handler, int offset)
	{
		String apiUrl = getApiUrl("statuses/home_timeline.json");

		// specify the params
		RequestParams params = new RequestParams();
		params.put("count", 25);
		params.put("since_id", offset);

		// execute the request
		getClient().get(apiUrl, params, handler);
	}

	public void getMentionsTimeline(AsyncHttpResponseHandler handler)
	{
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");

		// specify the params
		RequestParams params = new RequestParams();
		params.put("count", 25);
		//params.put("since_id", offset);

		// execute the request
		getClient().get(apiUrl, params, handler);
	}


	public void getUserTimeline(String screen_name, AsyncHttpResponseHandler handler)
	{
		String apiUrl = getApiUrl("statuses/user_timeline.json");

		// specify the params
		RequestParams params = new RequestParams();
		params.put("count", 25);
		params.put("screen_name", screen_name);

		// execute the request
		getClient().get(apiUrl, params, handler);
	}

	public void getUserInfo(AsyncHttpResponseHandler handler)
	{
		String apiUrl = getApiUrl("account/verify_credentials.json");

		// execute the request
		getClient().get(apiUrl, null, handler);
	}


	// compose tweet


	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/update.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */

	public void postTweet(String status, Long parentTweet, AsyncHttpResponseHandler handler)
	{
		String apiUrl = getApiUrl("statuses/update.json");

		// specify the params
		RequestParams params = new RequestParams();
		params.put("status", status);

		if (parentTweet != null)
		{
			params.put("in_reply_to_status_id", String.valueOf(parentTweet));
		}

		//client.post(apiUrl, params, handler);
		getClient().post(apiUrl, params, handler);
	}


}