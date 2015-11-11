package com.codepath.apps.HW4TwitterFragments.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.HW4TwitterFragments.Fragments.TweetsListFragment;
import com.codepath.apps.HW4TwitterFragments.Helper.EndlessScrollListener;
import com.codepath.apps.HW4TwitterFragments.R;
import com.codepath.apps.HW4TwitterFragments.Adapter.TweetsArrayAdapter;
import com.codepath.apps.HW4TwitterFragments.REST.TwitterClient;
import com.codepath.apps.HW4TwitterFragments.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;


public class TimelineActivity extends ActionBarActivity {

    //private TwitterClient client;
    //private TweetsListFragment fragmentTweetsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //display action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_tweet);
        getSupportActionBar().setTitle(R.string.home);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

/*        //get the client
        client = TwitterApplication.getRestClient();


        //if savedInstanceState is not null, that means this activity is already exist, saved in memory, then no need to recreate this activity
        if(savedInstanceState == null)
        {
            //access the fragment
            fragmentTweetsList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
        }

        populateTimeLine();
*/

    }

/*
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
                fragmentTweetsList.addAll(Tweet.fromJSONArray(response));

                Log.d("DEBUG", "Test");

            }

            // FAILURE

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        }, offset);

    }
*/

    /*// send an API request to get the timeline json
    // fill the listview by createing the tweet objects from the json
    private void populateTimeLine()
    {

        client.getHomeTimeLine(new JsonHttpResponseHandler() {
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
                fragmentTweetsList.addAll(Tweet.fromJSONArray(response));

                Log.d("DEBUG", "Test");

            }

            // FAILURE

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        }, 1);
    }
*/

    public void onComposeAction(MenuItem mi)
    {
        // handle click here
        Intent intent = new Intent(TimelineActivity.this, ComposeTweetActivity.class);
        //Toast.makeText(TimelineActivity.this, "Compose",Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, RESULT_OK);
    }

    /*
    public void onRefreshAction(MenuItem mi)
    {
        populateTimeLine();
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_compose) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
