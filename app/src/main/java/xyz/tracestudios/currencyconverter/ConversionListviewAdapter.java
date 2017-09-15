package xyz.tracestudios.currencyconverter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ConversionListviewAdapter extends BaseAdapter{


	public LayoutInflater l_Inflater;

	ArrayList<CurrencyNames> list_curency_names;
	public Activity activity;




//	Context context;

	int resId;
	int pos;

	public ConversionListviewAdapter(Activity a , ArrayList<CurrencyNames> list ) {
	
	 	this.activity	 =	a;

		this.list_curency_names	 =	list;


	 	this.l_Inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	 
       Log.d("my list size",""+getCount());
	 
	}
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return list_curency_names.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list_curency_names.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		
		final View_Holder holder;
		
		if (convertView == null) {

			convertView = l_Inflater.inflate(R.layout.row_curency_converter, null);
			holder      = new View_Holder();

			holder.short_title= (TextView)convertView.findViewById(R.id.title);

			holder.imageView= (ImageView) convertView.findViewById(R.id.imageView);

			convertView.setTag(holder);
		} else {

			holder = (View_Holder) convertView.getTag();

		}


		if(GlobalData.android_version>=5.0) {
			holder.short_title.setText(list_curency_names.get(position).short_name);
		}
		else{
			holder.short_title.setText(list_curency_names.get(position).abrivation);
		}


		if(GlobalData.android_version>=5.0) {
		resId = activity.getResources().getIdentifier(list_curency_names.get(position).abrivation.toLowerCase(), "drawable",activity.getPackageName());
		}
		else
		{
			resId = activity.getResources().getIdentifier(list_curency_names.get(position).short_name.toLowerCase(), "drawable",activity.getPackageName());

		}
		Log.d("Resouce resouce id",""+list_curency_names.get(position).short_name);


		if(resId==0)
		{
			resId = activity.getResources().getIdentifier("tryk", "drawable",activity.getPackageName());

			holder.imageView.setImageResource(resId);
		}
			holder.imageView.setImageResource(resId);


		convertView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

				if(GlobalData.android_version>=5.0) {
					GlobalData.global_image_id = activity.getResources().getIdentifier(list_curency_names.get(position).abrivation.toLowerCase(), "drawable", activity.getPackageName());
					GlobalData.global_country_name = list_curency_names.get(position).short_name;
					GlobalData.country_id = list_curency_names.get(position).abrivation;

					if(GlobalData.country_id.contains("TRY"))
					{
						GlobalData.global_image_id = activity.getResources().getIdentifier("tryk", "drawable", activity.getPackageName());

					}
					if(GlobalData.country_id.contains("TMM"))
					{
						GlobalData.global_image_id = activity.getResources().getIdentifier("tryk", "drawable", activity.getPackageName());

					}


				}
				else{
					GlobalData.global_image_id = activity.getResources().getIdentifier(list_curency_names.get(position).short_name.toLowerCase(), "drawable", activity.getPackageName());
					GlobalData.global_country_name = list_curency_names.get(position).abrivation;
					GlobalData.country_id = list_curency_names.get(position).short_name;

					if(GlobalData.country_id.contains("TRY"))
					{
						GlobalData.global_image_id = activity.getResources().getIdentifier("tryk", "drawable", activity.getPackageName());

					}
					if(GlobalData.country_id.contains("TMM"))
					{
						GlobalData.global_image_id = activity.getResources().getIdentifier("tryk", "drawable", activity.getPackageName());

					}

				}

				((Activity)activity).finish();



			}
		});


		return convertView;
		
		 
	}

	static class View_Holder
	{

		public TextView short_title;
		public ImageView imageView;

		
	}
	
}