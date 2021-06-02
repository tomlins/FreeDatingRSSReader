package com.ifreedating.freedatingrssreader.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifreedating.freedatingrssreader.app.R;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jason on 14/06/14.
 */
public class ProfileSummaryListItemArrayAdapter extends ArrayAdapter<String> {

    public static final String TAG = "ProfileSummaryListItemArrayAdapter";

    private final Activity context;
    private final String[] title;
    private final String[] description;
    private final String[] guid;

    private Bitmap[] imageCache;

    static class ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descTextView;
        //Bitmap bitmap;
        int index;
    }

    public ProfileSummaryListItemArrayAdapter(Activity context, String[] title,
                                              String[] description, String[] guid) {

        super(context, R.layout.fragment_profiles_list, title);
        this.context = context;
        this.title = title;
        this.description = description;
        this.guid = guid;

        imageCache = new Bitmap[title.length];
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if (view == null) {

            Log.i(TAG, "Creating new rowView instance");

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.profile_row_item_view, null, true);

            viewHolder = new ViewHolder();
            viewHolder.index = position;
            viewHolder.titleTextView = (TextView) view.findViewById(R.id.title);
            viewHolder.descTextView = (TextView) view.findViewById(R.id.description);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.profile_pic);

            view.setTag(viewHolder);

        } else {
            Log.i(TAG, "Reusing existing rowView");
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.index = position;
        }

        viewHolder.titleTextView.setText(title[position]);
        viewHolder.descTextView.setText(description[position]);
        viewHolder.imageView.setImageResource(R.drawable.if_splash_logo_bw);

        new DownloadImageAsyncTask().execute(viewHolder);

        return view;
    }

    private class DownloadImageAsyncTask extends AsyncTask<ViewHolder, Void, ViewHolder> {

        @Override
        protected ViewHolder doInBackground(ViewHolder... params) {

            ViewHolder viewHolder = params[0];
            String fileName = guid[viewHolder.index];
            if (fileName.contains("\n\t")) {
                Log.i(TAG, "Bad image name, returning");
                imageCache[viewHolder.index] = null;
                return viewHolder;
            }


            if (imageCache[viewHolder.index] == null ) {

                StringBuilder imageName = new StringBuilder("http://ifreedating.com/ImageServer?name=");
                imageName.append(guid[viewHolder.index]);
                imageName.append("&w=90");

                try {
                    URL imageURL = new URL(imageName.toString());
                    Log.i(TAG, "Downloading Image file " + imageName);
                    Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openStream());
                    //viewHolder.bitmap = bitmap;

                    if(bitmap==null)
                        throw new IOException("");

                    Log.i(TAG, "Adding " + imageName + " to cache at index " + viewHolder.index);
                    imageCache[viewHolder.index] = bitmap;
                }
                catch (IOException e) {
                    Log.e("error", "Downloading Image Failed " + imageName);
                    //viewHolder.bitmap = null;
                    imageCache[viewHolder.index] = null;
                }
            } else {
                Log.i(TAG, "Using cached image file" );
                //viewHolder.bitmap = imageCache[viewHolder.index];
            }

            return viewHolder;
        }

        @Override
        protected void onPostExecute(ViewHolder viewHolder) {
            if (imageCache[viewHolder.index] == null) {
                viewHolder.imageView.setImageResource(R.drawable.if_splash_logo);
            } else {
                viewHolder.imageView.setImageBitmap(imageCache[viewHolder.index]);
            }
        }
    }


}
