package com.ifreedating.freedatingrssreader.fragment;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifreedating.freedatingrssreader.adapter.ProfileSummaryListItemArrayAdapter;
import com.ifreedating.freedatingrssreader.app.DataServiceFactory;
import com.ifreedating.freedatingrssreader.app.IDataService;
import com.ifreedating.freedatingrssreader.app.R;
import com.ifreedating.freedatingrssreader.rss.Feed;
import com.ifreedating.freedatingrssreader.rss.FeedItem;

import java.util.List;

public class LastOnlineFragment extends ListFragment {

    public static final String TAG = "LastOnlineFragment";
    private View theView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate");

        DownloadFeedTask task = new DownloadFeedTask();
        task.execute("http://ifreedating.com/new-profiles.rss");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        theView = inflater.inflate(R.layout.fragment_profiles_list, container, false);
        theView.setBackgroundColor(Color.parseColor("#FFFFEE"));
        return theView;
    }

    private class DownloadFeedTask extends AsyncTask<String, Void, Feed> {
        @Override
        protected Feed doInBackground(String... source) {
            IDataService dataService = DataServiceFactory.getRSSDataService();
            Feed theFeed = dataService.getData(source[0]);
            return theFeed;
        }

        @Override
        protected void onPostExecute(Feed theFeed) {
            Log.i(TAG, "We got " + theFeed.getItems().size() + " items");

            List<FeedItem> profiles = theFeed.getItems();
            int numberOfProfiles = profiles.size();
            String[] titles = new String[numberOfProfiles];
            String[] descs = new String[numberOfProfiles];
            String[] guids = new String[numberOfProfiles];
            int idx = 0;
            for (FeedItem profile : profiles) {
                titles[idx] = profile.getTitle();
                descs[idx] = profile.getDescription();
                guids[idx] = profile.getGuid();
                idx++;
            }

            ProfileSummaryListItemArrayAdapter adapter =
                    new ProfileSummaryListItemArrayAdapter(getActivity(), titles, descs, guids);

            setListAdapter(adapter);

        }
    }
}
