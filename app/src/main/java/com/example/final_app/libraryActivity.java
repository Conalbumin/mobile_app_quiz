package com.example.final_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class libraryActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private String[] titles = new String[]{"Study set", "Folder", "Group"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_library);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerFragmentSateAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(titles[position])).attach();
    }
}

class ViewPagerFragmentSateAdapter extends FragmentStateAdapter {
    private String[] titles = new String[]{"Study set", "Folder", "Group"};
    public ViewPagerFragmentSateAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new LibraryStudySet();
        } else if (position == 1) {
            return new LibraryFolder();
        } else if (position == 2) {
            return new LibraryGroup();
        }
        return new LibraryStudySet();

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
