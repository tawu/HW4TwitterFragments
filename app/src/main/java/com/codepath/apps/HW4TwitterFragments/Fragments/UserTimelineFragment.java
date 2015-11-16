package com.codepath.apps.HW4TwitterFragments.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.HW4TwitterFragments.Activities.TwitterApplication;
import com.codepath.apps.HW4TwitterFragments.REST.TwitterClient;
import com.codepath.apps.HW4TwitterFragments.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

/**
 * Created by tawu on 11/12/15.
 */
public class UserTimelineFragment extends TweetsListFragment
{
    private TwitterClient client;
    private static String screenNameArg = "screen_name";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        //get the client
        client = TwitterApplication.getRestClient();
        populateTimeLine();
    }


    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset)
    {
    }

    // Creates a new fragment given the screen name
    //UserTimelineFragment.newInstance("someScreenName");
    public static UserTimelineFragment newInstance(String screenName)
    {
        UserTimelineFragment userFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        //args.putInt("someInt", someInt);
        args.putString(screenNameArg, screenName);
        userFragment.setArguments(args);
        return userFragment;
    }

    // send an API request to get the timeline json
    // fill the listview by createing the tweet objects from the json
    private void populateTimeLine() {

        String screenName = getArguments().getString(screenNameArg);

        client.getUserTimeline(screenName, new JsonHttpResponseHandler() {
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //Log.d("DEBUG", response.toString());

                //get JSON
                //deserilize JSON
                //create models and add to adapter
                //load the model data into listview
                //tweets.clear();
                //aTweets.clear();
                //aTweets.addAll(Tweet.fromJSONArray(response));
                addAll(Tweet.fromJSONArray(response));

                Log.d("DEBUG", "Test");

            }

            // FAILURE

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }
}
