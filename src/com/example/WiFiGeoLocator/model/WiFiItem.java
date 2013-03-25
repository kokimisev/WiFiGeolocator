package com.example.WiFiGeoLocator.model;

import java.util.Date;

public class WiFiItem {

	private String networkName;
	private String channel;
	private Integer signalLevel;
	private Integer frequency;


	public WiFiItem() {
		super();
		this.signalLevel = 0;
		this.frequency = 0;
	}

	public WiFiItem(String netName) {
		super();
		this.networkName = netName;
		this.signalLevel = 0;
		this.frequency = 0;
		
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getSignalLevel() {
		return signalLevel;
	}

	public void setSignalLevel(Integer signalLevel) {
		this.signalLevel = signalLevel;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	
}
