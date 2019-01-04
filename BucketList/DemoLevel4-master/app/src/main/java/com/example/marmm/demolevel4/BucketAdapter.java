package com.example.marmm.demolevel4;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.ViewHolder> {

    private List<Bucket> buckets;
    final private ReminderClickListener bucketClickListener;

    public interface ReminderClickListener{
        void onCheckBoxChanged(int pos, Boolean isChecked);
    }

    public BucketAdapter(List<Bucket> Buckets, ReminderClickListener bucketClickListener) {
        this.buckets = Buckets;
        this.bucketClickListener = bucketClickListener;
    }

    @NonNull
    @Override
    public BucketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.checkbox, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BucketAdapter.ViewHolder holder, int position) {
        Bucket bucket =  buckets.get(position);
        holder.textView.setText(bucket.getBucketText());
        holder.titleView.setText(bucket.getBucketTitle());
        holder.checkBox.setChecked(bucket.getIsChecked());

        crossTextIfChecked(holder.checkBox, holder.titleView, holder.textView);
    }

    @Override
    public int getItemCount() {
        return buckets.size();
    }

    public void crossTextIfChecked(CheckBox checkBox, TextView text, TextView title){
        if(checkBox.isChecked()){
            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            text.setPaintFlags(text.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView titleView;
        public TextView textView;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.Title);
            textView = itemView.findViewById(R.id.Text);
            checkBox = itemView.findViewById(R.id.checkBox);
            this.view = itemView;

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPos = getAdapterPosition();
                    bucketClickListener.onCheckBoxChanged(clickedPos, checkBox.isChecked());
                    crossTextIfChecked(checkBox, textView, titleView);
                }
            });
        }
    }

    public void swapList (List<Bucket> newList) {
        buckets = newList;
        if (newList != null) {
            this.notifyDataSetChanged();
        }
    }
}
