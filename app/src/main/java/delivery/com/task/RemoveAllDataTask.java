package delivery.com.task;

import android.content.Context;
import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import delivery.com.db.DespatchDB;
import delivery.com.event.DownloadDespatchEvent;
import delivery.com.event.RemoveAllDataEvent;
import delivery.com.proxy.DownloadDespatchProxy;
import delivery.com.vo.DownloadDespatchResponseVo;

public class RemoveAllDataTask extends AsyncTask<String, Void, Boolean> {

    private Context context;

    public RemoveAllDataTask(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            DespatchDB db = new DespatchDB(context);
            db.removeAllDatas();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        EventBus.getDefault().post(new RemoveAllDataEvent(result));
    }
}