package net.mizucoffee.hatsuyuki_chinachu.dashboard.f_guide;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.mizucoffee.hatsuyuki_chinachu.R;
import net.mizucoffee.hatsuyuki_chinachu.databinding.FragmentGuideBinding;

import java.util.ArrayList;

import static butterknife.ButterKnife.findById;

public class GuideFragment extends Fragment{

    private GuideViewModel mRecordedVM;
    private SearchView mSearchView;
    private FragmentGuideBinding mBinding;
    private ArrayList<RecyclerView> recyclerViews = new ArrayList<>();

    private int column = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_guide, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(findById(getActivity(),R.id.toolbar));

        for (int i = 0; i < column; i++) { //TODO: チャンネル数をAPI
            recyclerViews.add(i,new RecyclerView(getActivity()));
            mBinding.rootLl.addView(recyclerViews.get(i));
        }

        mRecordedVM = new GuideViewModel(this,recyclerViews);
        mBinding.setGuideVM(mRecordedVM);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecordedVM.reload();
    }
}
