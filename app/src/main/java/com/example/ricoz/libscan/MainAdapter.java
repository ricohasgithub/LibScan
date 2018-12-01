package com.example.ricoz.libscan;

import 	android.support.v7.widget.*;
import 	android.widget.TextView;
import 	android.view.*;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private BookList history;

    public MainAdapter (BookList history) {
        this.history = history;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView bTitle;

        public ViewHolder(TextView t) {
            super(t);
            bTitle = t;
        }
    }

    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bTitle.setText(history.getBookTitle(position));
    }

    public int getItemCount() {
        return history.getSize();
    }


}
