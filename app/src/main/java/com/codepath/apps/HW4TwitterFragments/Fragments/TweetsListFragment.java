package com.codepath.apps.HW4TwitterFragments.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.HW4TwitterFragments.Adapter.TweetsArrayAdapter;
import com.codepath.apps.HW4TwitterFragments.Helper.EndlessScrollListener;
import com.codepath.apps.HW4TwitterFragments.R;
import com.codepath.apps.HW4TwitterFragments.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * extend from android.support.v4.app.Fragment
 */
public class TweetsListFragment extends Fragment
{
    //inflation logic
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;

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

/*        //set endless scroll event handler
        lvTweets.setOnScrollListener(new EndlessScrollListener()
                                     {
                                         @Override
                                         public boolean onLoadMore(int page, int totalItemsCount)
                                         {
                                             // Triggered only when new data needs to be appended to the list
                                             // Add whatever code is needed to append new items to your AdapterView
                                             customLoadMoreDataFromApi(page);
                                             // or customLoadMoreDataFromApi(totalItemsCount);
                                             return true; // ONLY if more data is actually being loaded; false otherwise.
                                         }
                                     }

        );
*/
        return v;
    }

    public void addAll(List<Tweet> tweets)
    {
        aTweets.addAll(tweets);
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
