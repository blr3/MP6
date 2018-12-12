package com.example.brianna.mp6;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BubbleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BubbleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BubbleFragment extends Fragment {

    public ArrayList<String> selected = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    BubblePicker bubblePicker;

    String[] names = {
            "Spiritual",
            "Reflection",
            "Motivation",
            "Feelings",
            "Environment",
            "Social",
            "Empathy",
            "Regulation"
    };

    int[] images = {
            R.drawable.gradient1,
            R.drawable.palm_tree,
            R.drawable.images,
            R.drawable.images_2,
            R.drawable.gradient,
            R.drawable.skyline,
            R.drawable.sunset,
            R.drawable.wave
    };

    int[] colors = {
            Color.parseColor("#e87aad"),
            Color.parseColor("#7edaea"),
            Color.parseColor("#f2f07b"),
            Color.parseColor("#70f496"),
            Color.parseColor("#f9ad72"),
            Color.parseColor("#f45642"),
            Color.parseColor("#8886e0"),
            Color.parseColor("#8da85a")
    };



    public BubbleFragment() {
        // Required empty public constructor
    }

    public static BubbleFragment newInstance(String param1, String param2) {
        BubbleFragment fragment = new BubbleFragment();
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
        View view = inflater.inflate(R.layout.fragment_bubble, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bubblePicker = (BubblePicker) view.findViewById(R.id.picker);
        bubblePicker.setCenterImmediately(true);
//        bubblePicker.setClipBounds(Rect);
        ArrayList<PickerItem> listItems = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            //names[i], colors[i], Color.WHITE, getDrawable((images[i])
            PickerItem item = new PickerItem();
            item.setTitle(names[i]);
            listItems.add(item);
        }
        bubblePicker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return names.length;
            }

            @NotNull
            @Override
            public PickerItem getItem(int i) {
                PickerItem item = new PickerItem();
                item.setTitle(names[i]);
                item.setColor(colors[i]);
                item.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                item.setBackgroundImage(ContextCompat.getDrawable(getContext(),images[i]));
                return item;
            }
        });
        bubblePicker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem pickerItem) {
//                Toast.makeText(getActivity(), ""+pickerItem.getTitle()+" selected", Toast.LENGTH_SHORT).show();
                selected.add(pickerItem.getTitle());
            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem pickerItem) {
//                Toast.makeText(getActivity(), ""+pickerItem.getTitle()+" Deselected", Toast.LENGTH_SHORT).show();
                selected.remove(pickerItem.getTitle());
            }


        });
    }

    public void add() {
        DiaryFragment secondFrag = new DiaryFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("selected", selected);
        secondFrag.setArguments(bundle); //data being send to SecondFragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.commit();
    }

//    public void sendData(ArrayList<String> datas) {
//        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        DiaryFragment second = (DiaryFragment) .getSupportFragmentManager().findFragmentByTag(tag);
//        second.get(datas);
//    }

    @Override
    public void onResume() {
        super.onResume();
        bubblePicker.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        bubblePicker.onPause();
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
