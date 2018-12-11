package com.example.brianna.mp6;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.haerul.swipeviewpager.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryFragment extends Fragment {

    Button pop;
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Map<String, String> diaryEntry;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    ImageView imageView;
    Dialog dialog;

    Integer[] colors = {
            Color.parseColor("#e87aad"),
            Color.parseColor("#7edaea"),
            Color.parseColor("#f2f07b"),
            Color.parseColor("#70f496"),
            Color.parseColor("#f9ad72"),
            Color.parseColor("#f45642"),
            Color.parseColor("#8886e0"),
            Color.parseColor("#8da85a")
    };

    private OnFragmentInteractionListener mListener;

    public DiaryFragment() {
        // Required empty public constructor
    }

    public static DiaryFragment newInstance(String param1, String param2) {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.sunny, "12 Dec 2018", ""));
        models.add(new Model(R.drawable.cloudsun, "13 Dec 2018", ""));
        models.add(new Model(R.drawable.cloud, "14 Dec 2018", ""));
        models.add(new Model(R.drawable.nightcloud, "15 Dec 2018", ""));

        adapter = new Adapter(models, getContext());

        viewPager = view.findViewById(R.id.viewPager);
        imageView = view.findViewById(R.id.info);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i < (adapter.getCount() - 1) && i < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    v,
                                    colors[i],
                                    colors[i + 1]
                            )
                    );
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int i) {
                int currentItem = viewPager.getCurrentItem();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
