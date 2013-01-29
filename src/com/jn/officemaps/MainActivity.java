package com.jn.officemaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity 
	implements FloorPlansFragment.OnItemSelectedListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (findViewById(R.id.maps_container) != null) {
			if (savedInstanceState != null)
				return;
			
			FloorPlansFragment fpf = new FloorPlansFragment();
			fpf.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction()
				.add(R.id.maps_container, fpf).commit();
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onItemSelected(int position) {
		MapFragment mapFragment = (MapFragment)
				getSupportFragmentManager().findFragmentById(R.id.map);
		if (mapFragment != null) {
			mapFragment.updateMapView(position);
		} else {
			MapFragment newFragment = new MapFragment();
			Bundle args = new Bundle();
			args.putInt(MapFragment.ARG_POSITION, position);
			newFragment.setArguments(args);

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack so the user can navigate back
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.maps_container, newFragment);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}
}
