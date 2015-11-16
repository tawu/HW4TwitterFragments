package com.codepath.apps.HW4TwitterFragments.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.HW4TwitterFragments.Activities.TwitterApplication;
import com.codepath.apps.HW4TwitterFragments.Helper.EndlessScrollListener;
import com.codepath.apps.HW4TwitterFragments.REST.TwitterClient;
import com.codepath.apps.HW4TwitterFragments.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

/**
 * Created by tawu on 11/10/15.
 */
public class HomeTimelineFragment extends TweetsListFragment
{
    private TwitterClient client;


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
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter

        client.getHomeTimeLine(new JsonHttpResponseHandler() {
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //Log.d("DEBUG", response.toString());

                //get JSON
                //deserilize JSON
                //create models and add to adapter
                //load the model data into listview
                //clearAll();
                addAll(Tweet.fromJSONArray(response));

                Log.d("DEBUG", "Test");

            }

            // FAILURE

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        }, offset);

    }


    // send an API request to get the timeline json
    // fill the listview by createing the tweet objects from the json
    private void populateTimeLine()
    {

        client.getHomeTimeLine(new JsonHttpResponseHandler() {
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response)
            {
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
        }, 1);
    }

}
