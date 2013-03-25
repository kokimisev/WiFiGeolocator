package com.example.WiFiGeoLocator.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.WiFiGeoLocator.R;
import com.example.WiFiGeoLocator.WiFiNetworkDetails;
import com.example.WiFiGeoLocator.model.WiFiItem;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WiFiItemsAdapter extends BaseAdapter implements OnItemClickListener {

	private List<WiFiItem> items;
	private Context ctx;
	private LayoutInflater inflater;

	public WiFiItemsAdapter(Context ctx) {
		items = new ArrayList<WiFiItem>();
		this.ctx = ctx;
		inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public WiFiItemsAdapter(List<WiFiItem> items, Context ctx) {
		this.items = items;
		this.ctx = ctx;
		inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class WiFiHolder {
		
		public RelativeLayout itemLayout;
		public TextView name;
		public TextView channel;
		public TextView frequency;
		public TextView signalLevel;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		WiFiItem item = items.get(position);
		WiFiHolder holder = null;
		if (convertView == null) {
			holder = new WiFiHolder();
			holder.itemLayout = (RelativeLayout) inflater.inflate(R.layout.item_wifi, null);

			holder.name = (TextView) holder.itemLayout.findViewById(R.id.networkName);
			holder.frequency = (TextView) holder.itemLayout.findViewById(R.id.frequency);
			holder.signalLevel = (TextView) holder.itemLayout.findViewById(R.id.signalLevel);
			convertView = holder.itemLayout;
			convertView.setTag(holder);

		}

		holder = (WiFiHolder) convertView.getTag();

		holder.name.setText(item.getNetworkName());
		holder.frequency.setText(item.getFrequency().toString()+" MHz");
		holder.signalLevel.setText(item.getSignalLevel().toString()+" dBm");


		return convertView;
	}

	public void add(WiFiItem item) {
		items.add(item);
		notifyDataSetChanged();
	}
	public void clear() {
		items.clear();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		
	}

}
