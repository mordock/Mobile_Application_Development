package com.example.maxwe.level4gamebacklog;

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
    private List<Game> gamesList;

    final private GameClickListener gameClickListener;

    public RecycleAdapter(List<Game> gamesList, GameClickListener gameClickListener) {
        this.gamesList = gamesList;
        this.gameClickListener = gameClickListener;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_main, viewGroup, false);

        return new ViewHolder(view);
    }

    public interface GameClickListener{
        void gamesOnClick(int i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder viewHolder, int i) {
        Game game = gamesList.get(i);
        viewHolder.titleView.setText(game.getTitle());
        viewHolder.platformView.setText(game.getPlatform());
        viewHolder.statusView.setText(game.getStatus());
        viewHolder.dateView.setText(game.getDate());
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleView;
        public TextView platformView;
        public TextView statusView;
        public TextView dateView;

        public ViewHolder(View itemView){
            super(itemView);
            titleView = itemView.findViewById(R.id.TitleView);
            platformView = itemView.findViewById(R.id.PlatformView);
            statusView = itemView.findViewById(R.id.Status);
            dateView = itemView.findViewById(R.id.DateView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPos = getAdapterPosition();
            gameClickListener.gamesOnClick(clickPos);
        }
    }

    public void setData(List<Game> gamesList){
        this.gamesList = gamesList;
    }
}
