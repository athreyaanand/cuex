package xyz.tracestudios.currencyconverter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{

	public LayoutInflater l_Inflater;
	public ArrayList<CurrencyRates> currencyRates;
	public ArrayList<CurrencyNames> currencyNames;
	public Activity activity;

	int resId;
	int pos;
	
	public ListViewAdapter(Activity a , ArrayList<CurrencyNames> list , ArrayList<CurrencyRates> list1 ) {
	
	 	this.activity = a;

		this.currencyNames = list;
		this.currencyRates = list1;

	 	this.l_Inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return currencyRates.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return currencyRates.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		
		final View_Holder holder;
		
		if (convertView == null) {

			convertView = l_Inflater.inflate(R.layout.row_custom, null);
			holder = new View_Holder();

			holder.short_title= (TextView)convertView.findViewById(R.id.title);
			holder.curency_rate= (TextView)convertView.findViewById(R.id.currency_title);
			holder.long_title= (TextView)convertView.findViewById(R.id.description);
			holder.imageView= (ImageView) convertView.findViewById(R.id.imageView);

			convertView.setTag(holder);
		} else {

			holder = (View_Holder) convertView.getTag();

		}

		holder.short_title.setText(currencyNames.get(position).short_name);
		holder.long_title.setText(currencyNames.get(position).abrivation);
		holder.curency_rate.setText(currencyRates.get(position).price);

		resId = activity.getResources().getIdentifier(currencyNames.get(position).short_name.toLowerCase(), "drawable",activity.getPackageName());


		if(currencyNames.get(position).short_name.contains("TRY"))
		{
			resId = activity.getResources().getIdentifier("tnd", "drawable",activity.getPackageName());
			holder.imageView.setImageResource(resId);
		}else if(resId==0)
		{
			resId = activity.getResources().getIdentifier("xdr", "drawable",activity.getPackageName());

			holder.imageView.setImageResource(resId);
		}

		holder.imageView.setImageResource(resId);
	
		return convertView;
		 
	}

	static class View_Holder
	{

		public TextView short_title;
		public TextView long_title;
		public ImageView imageView;
		public TextView curency_rate;
		
	}
	
}
