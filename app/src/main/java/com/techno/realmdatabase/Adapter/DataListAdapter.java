package com.techno.realmdatabase.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techno.realmdatabase.Model.Book;
import com.techno.realmdatabase.R;

import java.util.ArrayList;

/**
 * Created by Arbaz.
 * Date: 23/4/18
 * Time: 3:27 PM
 */
public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {

    ArrayList<Book> bookArrayList;
    Book book;
    Context context;

    public DataListAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @Override
    public DataListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_list_row, parent, false);
        return new DataListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            book = bookArrayList.get(position);
            if (book != null) {
                holder.tvName.setText(book.getName());
                holder.tvMobile.setText(book.getMobile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMobile;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvName = view.findViewById(R.id.tvName);
            tvMobile = view.findViewById(R.id.tvMobile);

        }
    }
}
