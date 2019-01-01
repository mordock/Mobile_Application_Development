package com.example.maxwe.numbertrivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<Numbers> numbersList;

    final private NumberClickListener numberClickListener;

    public RecycleAdapter (List<Numbers> numbersList, NumberClickListener numberClickListener){
        this.numbersList = numbersList;
        this.numberClickListener = numberClickListener;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder viewHolder, int i) {
        Numbers numbers = numbersList.get(i);
        viewHolder.valueView.setText(String.valueOf(numbers.getNumberValue()));
        viewHolder.quoteView.setText(numbers.getNumberText());
    }

    public interface NumberClickListener{
        void numberOnClick(int i);
    }

    @Override
    public int getItemCount() {
        return numbersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView valueView;
        public TextView quoteView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            valueView = itemView.findViewById(R.id.number);
            quoteView = itemView.findViewById(R.id.quote);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPos = getAdapterPosition();
            numberClickListener.numberOnClick(clickPos);
        }
    }

    public void setData(List<Numbers> numbersList){
        this.numbersList = numbersList;
    }
}
