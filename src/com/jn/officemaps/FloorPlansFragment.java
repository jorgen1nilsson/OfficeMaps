package com.jn.officemaps;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FloorPlansFragment extends ListFragment {
	
	OnItemSelectedListener mSelectListener;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnItemSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onItemSelected(int position);
    }
	
	public void onCreate(Bundle savedInstanceState) {
		int layout;
		super.onCreate(savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			layout = android.R.layout.simple_list_item_activated_1;
		else
			layout = android.R.layout.simple_list_item_1;
		
		//setListAdapter(new ArrayAdapter<String>(getActivity(), layout, FloorPlansFragment.Headlines));
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout,
				getResources().getStringArray(R.array.FloorNames)));
		
	}
	
	public void onStart() {
		super.onStart();
		// Check if we need to set a default floor for the maps view
		if (getFragmentManager().findFragmentById(R.id.map) != null)
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		mSelectListener.onItemSelected(position);
        getListView().setItemChecked(position, true);
	}
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
        	mSelectListener = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItemSelectedListener");
        }
    }
	
}
