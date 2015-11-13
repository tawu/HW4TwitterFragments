package com.codepath.apps.HW4TwitterFragments.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.HW4TwitterFragments.Fragments.UserTimelineFragment;
import com.codepath.apps.HW4TwitterFragments.R;
import com.codepath.apps.HW4TwitterFragments.REST.TwitterClient;
import com.codepath.apps.HW4TwitterFragments.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class ProfileActivity extends ActionBarActivity {

    TwitterClient client;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        client = TwitterApplication.getRestClient();
        //get account info
        client.getUserInfo(new JsonHttpResponseHandler()
            {
               @Override
               public void onSuccess(int statusCode, Header[] headers, JSONObject response)
               {
                   user = User.fromJSON(response);
                   // my current user account info
                   getSupportActionBar().setTitle("@" + user.getScreenName());
                   populateProfileHeader(user);
               }
            }

        );





        // get the screen name from the activity that launches this
        String screenName = getIntent().getStringExtra("screen_name");

        //if instance is not exist, create it once for all
        if(savedInstanceState == null)
        {
            //create the user timeline fragment
            UserTimelineFragment fragmentUserTimeline = UserTimelineFragment.newInstance(screenName);

            //display user fragment within this activity (dynamically)
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, fragmentUserTimeline);
            ft.commit(); //change the fragment
        }

    }

    private void populateProfileHeader(User user)
    {
        TextView tvProfileName = (TextView) findViewById(R.id.tvProfileUserName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagLine);
        TextView tvFollowers = (TextView) findViewById(R.id.tvProfileFollowers);
        TextView tvFollowing = (TextView) findViewById(R.id.tvProfileFollowing);
        ImageView ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);

        tvProfileName.setText(user.getName());
        tvTagline.setText(user.getDescription());
        tvFollowers.setText(String.valueOf(user.getFollowersCount()) + " Followers");
        tvFollowing.setText(String.valueOf(user.getFriendsCount()) + " Following");
        Picasso.with(getBaseContext()).load(user.getProfileImageUrl()).into(ivProfileImage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
