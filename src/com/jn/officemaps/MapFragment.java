package com.jn.officemaps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MapFragment extends Fragment {
	final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	static String[] maps = {
		"Floor 1",
		"Floor 2",
		"Floor 3",
		"Floor 4",
		"Floor 5",
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 if (savedInstanceState != null)
				mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		 return inflater.inflate(R.layout.map_view, container, false);
	}
	
	public void updateMapView(int position) {
		TextView map = (TextView) getActivity().findViewById(R.id.map);
		map.setText(MapFragment.maps[position]);
		mCurrentPosition = position;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Bundle args = getArguments();
		if (args != null) {
			// Set view based on argument passed in
			updateMapView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			// Set view based on saved instance state defined during onCreateView
			updateMapView(mCurrentPosition);
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ARG_POSITION, mCurrentPosition);
	}
	
	
}
