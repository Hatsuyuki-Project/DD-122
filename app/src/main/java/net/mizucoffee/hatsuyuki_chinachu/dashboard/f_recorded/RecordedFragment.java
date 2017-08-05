package net.mizucoffee.hatsuyuki_chinachu.dashboard.f_recorded;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import net.mizucoffee.hatsuyuki_chinachu.R;
import net.mizucoffee.hatsuyuki_chinachu.databinding.FragmentRecordedBinding;

import static butterknife.ButterKnife.findById;

public class RecordedFragment extends Fragment{

    private RecordedViewModel mRecordedVM;
    private SearchView mSearchView;
    private FragmentRecordedBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_recorded, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(findById(getActivity(),R.id.toolbar));

        mRecordedVM = new RecordedViewModel(this);
        mBinding.setRecordedVM(mRecordedVM);
        mBinding.recycler.setHasFixedSize(true);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.program_list, menu);

        MenuItem menuItem = menu.findItem(R.id.search_menu_search_view);
        mSearchView = (SearchView)menuItem.getActionView();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setSubmitButtonEnabled(false);
        mSearchView.setOnQueryTextListener(mRecordedVM.getOnSearch());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort:
                PopupMenu popup = new PopupMenu(getActivity(), findById(getActivity(), R.id.menu_sort));
                popup.getMenuInflater().inflate(R.menu.program_sorttype_popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(mRecordedVM.getSortMenuListener());
                popup.show();
                break;
            case R.id.menu_list:
                PopupMenu popup2 = new PopupMenu(getActivity(), findById(getActivity(), R.id.menu_list));
                popup2.getMenuInflater().inflate(R.menu.program_listtype_popup_menu, popup2.getMenu());
                popup2.setOnMenuItemClickListener(mRecordedVM.getListMenuListener());
                popup2.show();
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecordedVM.reload();
    }

    public void onBackButton(){
        if (!mSearchView.isIconified()) mSearchView.setIconified(true);
    }
}
