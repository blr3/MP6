package com.example.brianna.mp6;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.haerul.swipeviewpager.Model;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Model> models;
    public ArrayList<String> selectedValues;
    private LayoutInflater layoutInflater;
    private Context context;
    public Dialog dialog;

    String[] vals = {
            "Spiritual",
            "Reflection",
            "Motivation",
            "Feelings",
            "Environment",
            "Social",
            "Empathy",
            "Regulation"};

//    public Adapter(List<Model> models, Context context, ArrayList<String> selctedVlaues) {
//        this.models = models;
//        this.context = context;
//        this.selectedValues = selctedVlaues;
//    }
    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.item, container,false);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        ImageView imageView;
        TextView title;
        EditText desc;
        ImageView info;
        final PieChart chart;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        info = view.findViewById(R.id.info);
        chart = dialog.findViewById(R.id.chart);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                setUpPieChart();
            }

            public void setUpPieChart() {
                List<PieEntry> pieEntries = new ArrayList<>();
//                List<String> rand = new ArrayList<String>(Arrays.asList(vals));

//        float average = (float) 1 / selectedValues.size();
//        for (int i = 0; i < selectedValues.size(); i++) {
//            pieEntries.add(new PieEntry(average, selectedValues.get(i)));
//        }
                float average = (float) 1 / vals.length;
                for (int i = 0; i < vals.length; i++) {
                    pieEntries.add(new PieEntry(average, vals[i]));
                }

                PieDataSet dataSet = new PieDataSet(pieEntries, "Today's trends");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData data = new PieData(dataSet);

                PieChart chart = (PieChart) dialog.findViewById(R.id.chart);
                chart.setData(data);
                chart.invalidate();
            }
        });

        container.addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
