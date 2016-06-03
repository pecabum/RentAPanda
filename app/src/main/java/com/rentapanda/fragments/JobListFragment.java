package com.rentapanda.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rentapanda.adapters.JobsAdapter;
import com.rentapanda.interfaces.OnListFragmentInteractionListener;
import com.rentapanda.R;
import com.rentapanda.models.Job;

import java.util.ArrayList;

public class JobListFragment extends Fragment {

    private static final String ARG_JOB_LIST = "job_list";
    private OnListFragmentInteractionListener mListener;
    private ArrayList<Job> jobsList;

    public JobListFragment() {
    }

    public static JobListFragment newInstance(ArrayList<Job> jobsList) {
        JobListFragment fragment = new JobListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_JOB_LIST, jobsList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            jobsList = getArguments().getParcelableArrayList(ARG_JOB_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new JobsAdapter(getActivity(),jobsList, mListener));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    /**
     * For older versions
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
