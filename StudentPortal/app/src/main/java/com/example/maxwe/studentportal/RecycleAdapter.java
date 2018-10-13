package com.example.maxwe.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private List<Sites> sitesList;

    final private SitesClickListener sitesClickListener;

    public RecycleAdapter(List<Sites> sitesList, SitesClickListener sitesClickListener) {
        this.sitesList = sitesList;
        this.sitesClickListener = sitesClickListener;
    }

    public interface SitesClickListener{
        void sitesOnClick(int i);
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        RecycleAdapter.ViewHolder viewHolder = new RecycleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        Sites sites = sitesList.get(position);
        holder.textView.setText(sites.getSiteTitle());
    }

    @Override
    public int getItemCount() {
        return sitesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int clickPos = getAdapterPosition();
            sitesClickListener.sitesOnClick(clickPos);
        }
    }
}
