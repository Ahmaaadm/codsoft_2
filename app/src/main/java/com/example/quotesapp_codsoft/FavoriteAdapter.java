package com.example.quotesapp_codsoft;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final List<String> favoriteQuotes;

    public FavoriteAdapter(List<String> favoriteQuotes) {
        this.favoriteQuotes = favoriteQuotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_quote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String quote = favoriteQuotes.get(position);
        holder.quoteTextView.setText(quote);
    }

    @Override
    public int getItemCount() {
        return favoriteQuotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quoteTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            quoteTextView = itemView.findViewById(R.id.quoteTextView);
        }
    }
}
