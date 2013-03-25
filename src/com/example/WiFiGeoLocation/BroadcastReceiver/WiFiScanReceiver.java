package com.example.WiFiGeoLocation.BroadcastReceiver;

import java.util.List;

import com.example.WiFiGeoLocator.WiFiGeoLocatorList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class WiFiScanReceiver extends BroadcastReceiver {

  WiFiGeoLocatorList wifiLocator;

  public WiFiScanReceiver(WiFiGeoLocatorList wifiLocator) {
    super();
    this.wifiLocator = wifiLocator;
  }

  @Override
  public void onReceive(Context c, Intent intent) {
    List<ScanResult> results = wifiLocator.wifi.getScanResults();
    ScanResult bestSignal = null;
    for (ScanResult result : results) {
      if (bestSignal == null || (bestSignal.level - result.level)<0)
        bestSignal = result;
    }

    String message = String.format("%s networks are found in your nearby. %s is the strongest with signal level %d dBm.",
        results.size(), bestSignal.SSID, bestSignal.level);
    Toast.makeText(wifiLocator, message, Toast.LENGTH_LONG).show();

  }

}
