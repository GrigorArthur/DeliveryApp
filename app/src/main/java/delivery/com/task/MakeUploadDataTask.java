package delivery.com.task;

import android.content.Context;
import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import delivery.com.consts.StateConsts;
import delivery.com.db.DespatchDB;
import delivery.com.db.OutletDB;
import delivery.com.db.StockDB;
import delivery.com.event.DespatchStoreEvent;
import delivery.com.event.MakeUploadDataEvent;
import delivery.com.model.DespatchItem;
import delivery.com.model.OutletItem;
import delivery.com.model.StockItem;

public class MakeUploadDataTask extends AsyncTask<Void, Void, String> {

    private String despatches;
    private Context ctx;

    public MakeUploadDataTask(Context context) {
        this.ctx = context;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            JSONObject allData = new JSONObject();


            JSONArray jsonDespatchArray = new JSONArray();

            DespatchDB despatchDB = new DespatchDB(ctx);
            OutletDB outletDB = new OutletDB(ctx);
            StockDB stockDB = new StockDB(ctx);

            ArrayList<DespatchItem> despatchItems = despatchDB.fetchCompletedDespatches();
            if(despatchItems.size() == 0)
                return null;
            for(int i = 0; i < despatchItems.size(); i++) {
                DespatchItem despatchItem = despatchItems.get(i);
                JSONObject jsonDespatch = new JSONObject();
                jsonDespatch.put("despatchID", despatchItem.getDespatchId());
                jsonDespatch.put("status", StateConsts.DESPATCH_COMPLETED);
                jsonDespatch.put("run", despatchItem.getRunId());
                jsonDespatch.put("driver", despatchItem.getDriverName());
                jsonDespatch.put("date", despatchItem.getCreationDate());

                ArrayList<OutletItem> outletItems = outletDB.fetchOutletsByDespatchID(despatchItem.getDespatchId());

                JSONArray jsonOutletArray = new JSONArray();
                for(int j = 0; j < outletItems.size(); j++) {
                    OutletItem outletItem = outletItems.get(j);

                    JSONObject jsonOutlet = new JSONObject();
                    jsonOutlet.put("outletID", outletItem.getOutletId());
                    jsonOutlet.put("outlet", outletItem.getOutlet());
                    jsonOutlet.put("address", outletItem.getAddress());
                    jsonOutlet.put("service", outletItem.getServiceType());
                    jsonOutlet.put("delivered", outletItem.getDelivered());
                    jsonOutlet.put("deliveredtime", outletItem.getDeliveredTime());
                    jsonOutlet.put("reason", outletItem.getReason());
                    jsonOutlet.put("tiers", outletItem.getTiers());

                    JSONArray jsonStockArray = new JSONArray();
                    ArrayList<StockItem> stockItems = stockDB.fetchAllStocksByOutletID(outletItem.getOutletId());
                    for(int k = 0; k < stockItems.size(); k++) {
                        StockItem item = stockItems.get(k);

                        JSONObject jsonStock = new JSONObject();
                        jsonStock.put("stockID", item.getStockId());
                        jsonStock.put("titleID", item.getTitleID());
                        jsonStock.put("stock", item.getStock());
                        jsonStock.put("size", item.getSize());
                        jsonStock.put("status", item.getStatus());
                        jsonStock.put("tier", item.getTier());
                        jsonStock.put("slot", item.getSlot());
                        jsonStock.put("qty", item.getQty());
                        jsonStock.put("remove", item.getRemove());
                        jsonStock.put("removeID", item.getRemoveID());

                        jsonStockArray.put(jsonStock);
                    }
                    jsonOutlet.put("stock", jsonStockArray);

                    jsonOutletArray.put(jsonOutlet);
                }
                jsonDespatch.put("outlet", jsonOutletArray);

                jsonDespatchArray.put(jsonDespatch);
            }
            allData.put("despatch", jsonDespatchArray);

            despatches = allData.toString();

            return despatches;
        } catch (JSONException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println(result);
        EventBus.getDefault().post(new MakeUploadDataEvent(result));
    }
}