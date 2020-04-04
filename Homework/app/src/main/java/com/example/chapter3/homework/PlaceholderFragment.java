package com.example.chapter3.homework;



import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.airbnb.lottie.LottieAnimationView;


public class PlaceholderFragment extends Fragment {

    //private String data[] = {"Item1","Item2","Item3","Item4","Item5","Item6","Item7"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        final LottieAnimationView lottieAnimationView = view.findViewById(R.id.animation_view);
        final ListView listView = view.findViewById(R.id.listview);//在视图中找到ListView

        listView.setAdapter(new ListViewAdpter());
        getView().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator load =ObjectAnimator.ofFloat(lottieAnimationView,"alpha",1f,0f);
                load.setDuration(500);
                ObjectAnimator list =ObjectAnimator.ofFloat(listView,"alpha",0f,1f);
                list.setDuration(500);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.playTogether(load,list);
                animatorSet.start();

//                getFragmentManager().beginTransaction()
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
//                        .replace(R.id.placeholder, new List()).commit();

//                ObjectAnimator.ofFloat(lottieAnimationView,"alpha",1,0);
//                ObjectAnimator.ofFloat(listView,"alpha",0,1);


            }
        }, 5000);

    }

    public class ListViewAdpter extends BaseAdapter {

        public class item{
            TextView content;
        }
        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            item myitem=null;
            if (view == null){
                myitem=new item();
                view=View.inflate(viewGroup.getContext(), R.layout.list_item, null);
                myitem.content=view.findViewById(R.id.text);
                view.setTag(myitem);
            }
            else{
                myitem=(item)view.getTag();
            }
            myitem.content.setText(String.format("Item %d",i+1));
            return view;
        }
    }
}

