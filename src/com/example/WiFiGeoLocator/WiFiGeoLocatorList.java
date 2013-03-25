package com.example.WiFiGeoLocator;

import java.util.Date;
import java.util.List;

import com.example.WiFiGeoLocation.BroadcastReceiver.WiFiScanReceiver;
import com.example.WiFiGeoLocator.adapter.WiFiItemsAdapter;
import com.example.WiFiGeoLocator.model.WiFiItem;

import com.example.*;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class WiFiGeoLocatorList extends Activity {

	static final String ACTION_TODO_DETAILS = "com.example.WiFiGeoLocator.ACTION_TODO_DETAILS";

	private ListView mWiFiItemsList;

	private WiFiItemsAdapter mAdapter;
	
	public WifiManager wifi;
	BroadcastReceiver receiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi_list);
		
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifi.getConnectionInfo();


		List<WifiConfiguration> configs = wifi.getConfiguredNetworks();


		
		loadViews();
		initList();

		

		if (receiver == null)
			receiver = new WiFiScanReceiver(this);

		registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

		 List<ScanResult> results = wifi.getScanResults();
		    ScanResult bestSignal = null;
		    for (ScanResult result : results) {
		    	WiFiItem wItem = new WiFiItem(result.SSID);
		    	wItem.setFrequency(result.frequency);
		    	wItem.setSignalLevel(result.level);
		    	
		    	mAdapter.add(wItem);
		    }
	}
	@Override
	public void onStop() {
		unregisterReceiver(receiver);
	}

	private void loadViews() {
		//mItemName = (EditText) findViewById(R.id.networkName);
		mWiFiItemsList = (ListView) findViewById(R.id.wifiItems);
	}

	private void initList() {
		mAdapter = new WiFiItemsAdapter(this);
		mWiFiItemsList.setAdapter(mAdapter);
		mWiFiItemsList.setOnItemClickListener(mAdapter);

		mWiFiItemsList.setOnLongClickListener(new OnLongClickListener() {
		
			
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(WiFiGeoLocatorList.this, "Item long click",
						Toast.LENGTH_LONG).show();
				return true;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.todo_list, menu);
		return true;
	}


	public void refreshWifiItems(View view) {

				mAdapter.clear();
				wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);


				
				 List<ScanResult> results = wifi.getScanResults();
				    ScanResult bestSignal = null;
				    for (ScanResult result : results) {
				    	WiFiItem wItem = new WiFiItem(result.SSID);
				    	wItem.setFrequency(result.frequency);
				    	wItem.setSignalLevel(result.level);
				    	
				    	mAdapter.add(wItem);
				    }
				

	}
}
