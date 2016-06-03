package com.rentapanda.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rentapanda.R;
import com.rentapanda.interfaces.OnListFragmentInteractionListener;
import com.rentapanda.models.Job;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private final List<Job> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public JobsAdapter(Context ctx, List<Job> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        context = ctx;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvName.setText(holder.mItem.getCustomer_name());
        holder.tvCity.setText(holder.mItem.getJobCity());

        if (!holder.mItem.getDistance().equals("")) {
            holder.tvDistance.setText(String.format(context.getString(R.string.km), holder.mItem.getDistance()));
        } else {
            holder.tvDistance.setText(context.getString(R.string.unknown));
        }

        holder.tvRecurrence.setText(holder.mItem.getRecurrency(context));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onJobItemPressed(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvName;
        public final TextView tvCity;
        public final TextView tvRecurrence;
        public final TextView tvDistance;
        public Job mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvCity = (TextView) view.findViewById(R.id.tvCity);
            tvRecurrence = (TextView) view.findViewById(R.id.tvRecurrence);
            tvDistance = (TextView) view.findViewById(R.id.tvDistance);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvName.getText() + "'";
        }
    }
}
