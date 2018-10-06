package com.example.marmm.GeoGuessSwipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<GeoObject> geoObjects = new ArrayList<>();

        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS.length; i++) {

            geoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i],
                    GeoObject.PRE_FEFINDED_GEO_OBJECT_EUROPE_YES[i]));
        }

        final RecyclerView mGeoRecyclerView =  findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        final GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, geoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);



        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override

                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                   public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());

                      //  if(position == 0){
                        //    return;
                        //}
                        final GeoObject geoObject = geoObjects.get(position);
                        if(swipeDir == 4){
                            if(geoObject.getEuropeYes() == true){
                                Toast.makeText(MainActivity.this,"Wrong this was not Europe " + geoObject.getEuropeYes(), Toast.LENGTH_SHORT).show();
                            }
                            if(geoObject.getEuropeYes() == false){
                                Toast.makeText(MainActivity.this,"correct this was not Europe " + geoObject.getEuropeYes(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(swipeDir == 8){
                            if(geoObject.getEuropeYes() == true){
                                Toast.makeText(MainActivity.this,"correct this was Europe " + geoObject.getEuropeYes(), Toast.LENGTH_SHORT).show();
                            }
                            if(geoObject.getEuropeYes() == false){
                                Toast.makeText(MainActivity.this,"Wrong this was Europe " + geoObject.getEuropeYes(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        geoObjects.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mGeoRecyclerView);
    }



}
