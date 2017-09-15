package xyz.tracestudios.currencyconverter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class ListviewConverter extends Activity
{

    ListView listview;
    ConversionListviewAdapter adaptr_listview ;
    String temp = null;
    public static String url_currency_id_name = "http://free.currencyconverterapi.com/api/v3/currencies";
    public JSONObject jsonObj_name_id = null;
    public static ArrayList<CurrencyNames> currencyNames;
    String s_rtes, s_names, s_ids_names;
    private ProgressDialog pDialog;

    String split[];
    StringTokenizer stok;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_listview);

        listview = (ListView)findViewById(R.id.listView);
        pDialog = new ProgressDialog(this);
        currencyNames = new ArrayList<>();



        new GetExchangeRates().execute();

    }

    private class GetExchangeRates extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            ServiceHandler sh = new ServiceHandler();

            String json_curncy_id_name = sh.makeServiceCall(url_currency_id_name, ServiceHandler.GET);

            try
            {

                jsonObj_name_id = new JSONObject(json_curncy_id_name);
                s_ids_names = jsonObj_name_id.getJSONObject("results").toString();

            }catch (JSONException e)
            {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            add_country_ids_names();


        }

    }

    public void add_country_ids_names()  {

        s_ids_names=s_ids_names.replace("{","");
        s_ids_names=s_ids_names.replace("}","");
        s_ids_names=s_ids_names.replace("\"","");

        stok= new StringTokenizer(s_ids_names,",");


        while(stok.hasMoreElements())
        {
            temp= stok.nextElement().toString();

            if(temp.indexOf("currencySymbol") != -1){
                temp= stok.nextElement().toString();
            }

            String split[]= temp.split(":");

            temp= stok.nextElement().toString();

            if(temp.indexOf("currencySymbol") != -1){
                temp= stok.nextElement().toString();


            }

            String split2[]= temp.split(":");

            temp = null;

            currencyNames.add(new CurrencyNames(split[2], split2[1]));


        }

        Collections.sort(currencyNames, new Comparator<CurrencyNames>() {
            @Override
            public int compare(CurrencyNames n1, CurrencyNames n2) {

                return n1.short_name.compareTo(n2.short_name);
            }
        });

        adaptr_listview = new ConversionListviewAdapter(ListviewConverter.this, currencyNames);
        listview.setAdapter(adaptr_listview);
        pDialog.dismiss();
    }
}
