package com.petfolio.infinitus.fragmentpetlover.favourites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petfolio.infinitus.R;


public class DoctorFavFragment extends Fragment {


    private String TAG = "DoctorFavFragment";

    public DoctorFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.w(TAG,"onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_fav, container, false);
    }
}