package org.techtown.Shin;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.view.animation.BounceInterpolator;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<MovieList> movies=new ArrayList<>();
    MovieAdapter movieAdapter;
    ObjectAnimator anim;
    ViewPager pager;
    private MenuItem BottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anim= (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.translation);

        final BottomNavigationView navView = findViewById(R.id.nav_view);
        anim.setTarget(navView);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        pager=findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);



        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        Fragment1 fragment1=new Fragment1();
        adapter.addItem(fragment1);
        FragmentChats fragment2=new FragmentChats();
        adapter.addItem(fragment2);
        Fragment3 fragment3=new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

        //Swipe 처리
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
            }

            @Override
            public void onPageSelected(int position) {
                                if(BottomNavigation !=null){
                    BottomNavigation.setChecked(false);
                }
                BottomNavigation=navView.getMenu().getItem(position);
                BottomNavigation.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {
            }
        });
    }


    class MyPagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items=new ArrayList<Fragment>();
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }
        public void addItem(Fragment item){
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Fragment1 fragment1=new Fragment1();
                    return fragment1;
                case 1:
                    FragmentChats fragment2=new FragmentChats();
                    return fragment2;
                case 2:
                    Fragment3 fragment3=new Fragment3();
                    return fragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }
        public CharSequence getPageTitle(int position){
            if(position==0){

            }
            return  "페이지"+position;

        }
    }

    //탭 선택 처리
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_call:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_chats:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_contacts:
                    pager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };




}
