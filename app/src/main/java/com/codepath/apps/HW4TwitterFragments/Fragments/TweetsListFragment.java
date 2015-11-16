package com.codepath.apps.HW4TwitterFragments.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.HW4TwitterFragments.Activities.TwitterApplication;
import com.codepath.apps.HW4TwitterFragments.Adapter.TweetsArrayAdapter;
import com.codepath.apps.HW4TwitterFragments.Helper.EndlessScrollListener;
import com.codepath.apps.HW4TwitterFragments.R;
import com.codepath.apps.HW4TwitterFragments.REST.TwitterClient;
import com.codepath.apps.HW4TwitterFragments.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * extend from android.support.v4.app.Fragment
 */
public abstract class TweetsListFragment extends Fragment
{
    //inflation logic
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;


    protected ListView lvTweets;
    protected TwitterClient client;

    //protected String tweetType;

    //move original timeline logic part to here
    //if refrences are view, then they must be in onCreateView method
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);

        //find listview
        lvTweets = (ListView)v.findViewById(R.id.lvTweets);

        //connect adapter to listview
        lvTweets.setAdapter(aTweets);


        //set endless scroll event handler
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
                                         @Override
                                         public boolean onLoadMore(int page, int totalItemsCount) {
                                             // Triggered only when new data needs to be appended to the list
                                             // Add whatever code is needed to append new items to your AdapterView
                                             customLoadMoreDataFromApi(page);
                                             // or customLoadMoreDataFromApi(totalItemsCount);
                                             return true; // ONLY if more data is actually being loaded; false otherwise.
                                         }
                                     }

        );

        //get the client
        client = TwitterApplication.getRestClient();

        return v;
    }
/*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); // ask exactly what is going on here

        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
        lvTweets.setAdapter(aTweets);
    }
*/

    abstract protected void customLoadMoreDataFromApi(int page);


    public void addAll(List<Tweet> tweets)
    {
        aTweets.addAll(tweets);
    }

    public void clearAll()
    {
        aTweets.clear();
    }

    public TweetsArrayAdapter getAdapter()
    {
        return aTweets;
    }

    //creation lifecycle event

    //if it doesn't reference a view, then it must be in onCreate method.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //all components that don't need view are listed here
        //create arraylist (data source)
        tweets = new ArrayList<>();
        //construct the adapter from data source
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);


    }
}
