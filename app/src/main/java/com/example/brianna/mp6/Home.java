package com.example.brianna.mp6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Home extends AppCompatActivity {

    private FrameLayout frames;
    private MenuItem menuItem;
    private BubbleFragment bubbleFragment;
    private DiaryFragment diaryFragment;
    private QuoteFragment quoteFragment;
    private Boolean selected = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        frames = (FrameLayout) findViewById(R.id.frame);
        menuItem = findViewById(R.id.diary);
        bubbleFragment = new BubbleFragment();
        diaryFragment = new DiaryFragment();
        setFragment(bubbleFragment);

        final Button btnPop = (Button) findViewById(R.id.btnPop);
        btnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected) {
                    quoteFragment = new QuoteFragment();
                    setFragment(quoteFragment);
                    btnPop.setText(R.string.BACK);
                    selected = !selected;
                } else {
                    setFragment(bubbleFragment);
                    btnPop.setText(R.string.POP);
                    selected = !selected;
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.diary) {
            setFragment(diaryFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
