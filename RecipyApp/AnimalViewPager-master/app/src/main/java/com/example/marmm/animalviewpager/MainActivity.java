package com.example.marmm.animalviewpager;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    final int highestNumber = 101;
    public static ImageView imageView;

    private static final  List<Animal> recipes = new ArrayList<>();
    private static final List<Drawable> images = new ArrayList<>();

    public static Drawable image1;
    public static Drawable image2;
    public static Drawable image3;

    public static String imageUrl;

    private static Animal recipe;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //requestData(47024);
        requestData(35382);
        //image1 = LoadImageFromWebOperations(imageUrl);

        //recipes.add(new Animal(1, image1));

        //recipes.add(new Animal(2, R.drawable.image2));
        //requestData(47319);
        //recipes.add(new Animal(3, R.drawable.image3));






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            imageView = rootView.findViewById(R.id.imageView);
            //imageView.setImageResource(recipes.get(getArguments().getInt(ARG_SECTION_NUMBER)).getImageRes());
            //imageView.setImageDrawable(images.get(getArguments().getInt(ARG_SECTION_NUMBER)).getCurrent());
            //imageView.setImageDrawable(image1);

            Glide.with(this).load(imageUrl).into(imageView);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return recipes.size();
        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        Integer name = 1;
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable drawable = Drawable.createFromStream(is, name.toString());
            name++;
            return drawable;
        } catch (Exception e) {
            return null;
        }
    }

    public void setRecipes(String imageUrl, String title){
        //Glide.with(this).load(imageUrl).into(imageView);
        Glide.with(this).load(imageUrl).into(imageView);
        //LoadImageFromWebOperations(imageUrl);
        //recipes.add(new Animal(1, imageUrl));

        //Snackbar.make(findViewById(R.id.container),imageUrl, Snackbar.LENGTH_LONG).setAction("Action", null).show();

    }

    private void requestData(int recipeID){
        Food2ForkApiService service = Food2ForkApiService.retrofit.create(Food2ForkApiService.class);
        String search = "http://food2fork.com/api/get?key=bc7ac4ec5a11c70eac7b54f5bf980788&rId=";
        //int recipeID1 = 35382, recipeID2 = 47024, recipeID3 = 47319;
        //Call<Recipy> call = service.getRecipy(recipeID1);
        Call<Recipe_Response> call = service.getsearchList(search + recipeID);

        Log.d("url:", call.request().url() + " url");

        call.enqueue(new Callback<Recipe_Response>() {
            @Override
            public void onResponse(Call<Recipe_Response> call, Response<Recipe_Response> response) {
                Recipy recipy = response.body().getResults();
                //setRecipes(recipy.getImage_url(), recipy.getTitle());
                imageUrl = recipy.getImage_url();
                //image1 = LoadImageFromWebOperations(recipy.getImage_url());
                //recipes.add(new Animal(1, Integer.valueOf(image1)));
                Snackbar.make(findViewById(R.id.container),"succes", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

            @Override
            public void onFailure(Call<Recipe_Response> call, Throwable t) {
                Snackbar.make(findViewById(R.id.container),"failure", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public void SetQuoteTextView(String text, int random){
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }

    private void requestData2(){
        NumberApiService service = NumberApiService.retrofit.create(NumberApiService.class);
        final int random = new Random().nextInt(highestNumber);
        Call<TriviaItem> call = service.getRandomTrivia(random);
        call.enqueue(new Callback<TriviaItem>() {
            @Override
            public void onResponse(Call<TriviaItem> call, Response<TriviaItem> response) {
                TriviaItem triviaItem = response.body();
                SetQuoteTextView(triviaItem.getText(), random);
            }

            @Override
            public void onFailure(Call<TriviaItem> call, Throwable t) {
                Snackbar.make(findViewById(R.id.container),"failure", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}
