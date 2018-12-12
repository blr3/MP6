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
        return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        models = new ArrayList<>();
        models.add(new Model(R.drawable.sunny, "12 Dec 2018", "Today, I feel better. The day didn't go as planned but was still a day to appreciate"));
        models.add(new Model(R.drawable.cloudsun, "13 Dec 2018", "Today, I don't feel so well, mentally and physically. I wasn't able to accomplish as much as I had set out to for work"));
        models.add(new Model(R.drawable.cloud, "14 Dec 2018", "Today is a bit gloomy but overall I feel good. I Spent today finishing up a good book I started a long time ago "));
        models.add(new Model(R.drawable.nightcloud, "15 Dec 2018", "I am so happy that my mom is doing better today. I was worried about her all week "));

//        adapter = new Adapter(models, getContext(), bundle.getStringArrayList("selected"));
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



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
