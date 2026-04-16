package com.jilesh.class9thhindi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    // ─── Data Model ───────────────────────────────────────────────
    public static class SlideItem {
        public final int imageRes;
        public final String label;
        public final String title;
        public final String badge;

        public SlideItem(int imageRes, String label, String title, String badge) {
            this.imageRes = imageRes;
            this.label = label;
            this.title = title;
            this.badge = badge;
        }
    }

    // ─── Click Listener Interface ─────────────────────────────────
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // ─── Fields ───────────────────────────────────────────────────
    private final Context context;
    private final List<SlideItem> items;
    private final OnItemClickListener listener;

    // ─── Constructor ──────────────────────────────────────────────
    public SliderAdapter(Context context, List<SlideItem> items, OnItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    // ─── Adapter Methods ──────────────────────────────────────────
    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.slider_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SlideItem item = items.get(position);

        holder.image.setImageResource(item.imageRes);
        holder.label.setText(item.label);
        holder.title.setText(item.title);
        holder.badge.setText(item.badge);

        // ✅ CLICK HANDLING
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ─── ViewHolder ───────────────────────────────────────────────
    static class SliderViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView label, title, badge;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.sliderImage);
            label = itemView.findViewById(R.id.sliderLabel);
            title = itemView.findViewById(R.id.sliderTitle);
            badge = itemView.findViewById(R.id.sliderBadge);
        }
    }
}