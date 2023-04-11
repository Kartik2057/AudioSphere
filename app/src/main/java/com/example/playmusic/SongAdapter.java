package com.example.playmusic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private ArrayList<File> songs;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView songName;

        public ViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
        }
    }

    public SongAdapter(Context context, ArrayList<File> songs) {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_song_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        position = holder.getBindingAdapterPosition();
        holder.songName.setText(songs.get(position).getName().replace(".mp3", ""));
        int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaySong.class);
                String currentSong = songs.get(finalPosition).getName().replace(".mp3", "");
                intent.putExtra("songList", songs);
                intent.putExtra("currentSong", currentSong);
                intent.putExtra("position", finalPosition);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}

