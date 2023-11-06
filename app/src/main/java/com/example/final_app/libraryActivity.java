package com.example.final_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class libraryActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private String[] tabs= new String[]{"Study set","Folder", "Group"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_library);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerFragmentSateAdapter(this));

    }
}

public class ViewPagerFragmentSateAdapter extends FragmentStateAdapter{
    public ViewPagerFragmentSateAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new studySet();
            case 1:
                return new libraryFolder();
            case 2:
                return new libraryGroup();
        }
        return  new studySet();

    }

    @Override
    public int getItemCount() {
        return tabs.length;
    }
}
