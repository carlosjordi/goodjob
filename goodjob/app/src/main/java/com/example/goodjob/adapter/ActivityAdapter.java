package com.example.goodjob.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goodjob.R;
import com.example.goodjob.classes.Actividad;
import com.example.goodjob.viewholder.ActivityViewHolder;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    private List<Actividad> activities;
    private Context context;
    private OnActivityListener onActivityListener;

    public ActivityAdapter(List<Actividad> activities, Context context, OnActivityListener onActivityListener) {
        this.activities = activities;
        this.context = context;
        this.onActivityListener = onActivityListener;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item,
                viewGroup, false);

        return new ActivityViewHolder(view, onActivityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder avh, int index) {
        avh.title.setText(activities.get(index).getTitle());
        avh.authorName.setText(activities.get(index).getAuthor());
        if (activities.get(index).getAuthor().length() > 20)
            avh.authorName.setText(activities.get(index).getAuthor().substring(0, 20));
        avh.description.setText(activities.get(index).getDescription());
        if (activities.get(index).getDescription().length() > 50)
            avh.description.setText(activities.get(index).getDescription().substring(0, 50).concat("..."));
        avh.participantsCounter.setText(activities.get(index).getCurrentParticipants()
                + " / " + activities.get(index).getRequiredParticipants());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    // creando interface para hacer click en las activities
    public interface OnActivityListener {
        void onActivityClick(int position);
    }
}