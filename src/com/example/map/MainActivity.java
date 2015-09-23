package com.example.map;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private GoogleMap googleMap;
	private Marker mMarker = null;
	private LatLng position = null;
	private double myLatitude = 0;
	private double myLongitude = 0;
	private Button mbutton = null;
	private EditText number = null;
	private int number1 = 0;
	private LatLng latlng = null;
	private GoogleMap googleMap1;	
	private GoogleMap googleMap2;	
	private GoogleMap googleMap3;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 latlng = new LatLng(35.710065, 139.8107);
		//mbutton = (Button) findViewById(R.id.button1);
		//mbutton.setOnClickListener(MainActivity.this);

		SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		googleMap = supportMapFragment.getMap();
		if (googleMap != null) {
			googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("���݈ʒu"));
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
			googleMap.addMarker(new MarkerOptions().position(latlng).title("���݈ʒu"));

			googleMap.setMyLocationEnabled(true);
			UiSettings settings = googleMap.getUiSettings();
			settings.setMyLocationButtonEnabled(true);
			googleMap.addCircle(
					new CircleOptions().center(new LatLng(latlng.latitude, latlng.longitude))
							.radius(500).strokeColor(Color.GREEN).fillColor(Color.BLUE));

			googleMap1 = ( (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map1) ).getMap();
		if (googleMap1 != null) {
		CameraPosition cameraPos = new CameraPosition.Builder()
		.target(latlng)
		.zoom(10.0f)
		.bearing(0).build();
		googleMap1.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
		}
			mMarker = googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("���ݒn"));

			googleMap2 = ( (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map2) ).getMap();
			if (googleMap2 != null) {
			CameraPosition cameraPos = new CameraPosition.Builder()
			.target(latlng)
			.zoom(10.0f)
			.bearing(0).build();
			googleMap2.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
			}
				mMarker = googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("���ݒn"));

				googleMap3 = ( (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map3) ).getMap();
				if (googleMap3 != null) {
				CameraPosition cameraPos = new CameraPosition.Builder()
				.target(latlng)
				.zoom(10.0f)
				.bearing(0).build();
				googleMap3.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
				}
					
			
			/*googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
				public void onMyLocationChange(Location location) {
					googleMap.addCircle(
							new CircleOptions().center(new LatLng(location.getLatitude(), location.getLongitude()))
									.radius(500).strokeColor(Color.RED).fillColor(Color.BLUE));

					myLatitude = location.getLatitude();
					// �o�x���擾
					myLongitude = location.getLongitude();
					position = new LatLng(myLatitude, myLongitude);
					
					
				}

			});*/

			googleMap.setOnMapClickListener(new OnMapClickListener() {
				@Override
				public void onMapClick(LatLng point) {
					// TODO Auto-generated method stub
					// �^�b�`�n�_�ƖړI�n�Ƃ̍ŒZ�����̌v�Z
					float[] results = new float[1];
					/*
					 * double myLatitude = location.getLatitude(); //�o�x���擾
					 * double myLongitude = location.getLongitude(); LatLng
					 * position = new LatLng(myLatitude,myLongitude);
					 */
					Location.distanceBetween(point.latitude, point.longitude, latlng.latitude, latlng.longitude,
							results);
/*					Location.distanceBetween(point.latitude, point.longitude, position.latitude, position.longitude,
							results);
*/					
					Toast.makeText(getApplicationContext(), "��������̋����F" + results[0] + "m", Toast.LENGTH_LONG).show();
					mMarker.setPosition(point);

					// ���n��
					PolylineOptions geodesics = new PolylineOptions().add(point, latlng) // 2�n�_�ݒ�
							.geodesic(true) // ���n��
							.color(Color.RED).width(3);
					googleMap.addPolyline(geodesics);
				}
			});

		}
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == mbutton) {
			// number = (EditText)findViewById(R.id.editText1);
			number1 = Integer.parseInt(number.getText().toString());
			googleMap.addCircle(new CircleOptions().center(new LatLng(latlng.latitude, latlng.longitude))
					.radius(number1).strokeColor(Color.RED));
			
		}
	}

}
