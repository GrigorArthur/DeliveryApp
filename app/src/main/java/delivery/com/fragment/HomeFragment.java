package delivery.com.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import delivery.com.R;
import delivery.com.consts.StateConsts;
import delivery.com.db.DespatchDB;
import delivery.com.db.OutletDB;
import delivery.com.event.DownloadDespatchEvent;
import delivery.com.event.RemoveAllDataEvent;
import delivery.com.model.DespatchItem;
import delivery.com.model.OutletItem;
import delivery.com.task.DownloadDespatchTask;
import delivery.com.task.RemoveAllDataTask;
import delivery.com.ui.MainActivity;
import delivery.com.vo.DownloadDespatchResponseVo;

public class HomeFragment extends Fragment {

    private ProgressDialog progressDialog;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getActivity());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDownloadDespatchEvent(DownloadDespatchEvent event) {
        hideProgressDialog();
        DownloadDespatchResponseVo responseVo = event.getResponse();
        if (responseVo != null) {
            parseDespatches(responseVo.despatch);
        }
    }

    @Subscribe
    public void onRemoveDespatchesEvent(RemoveAllDataEvent event) {
        hideProgressDialog();
        boolean result = event.getRemoveResult();
        if(result) {
            Toast.makeText(getActivity(), R.string.remove_success, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.remove_failed, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_download_dispatch)
    void onClickBtnDownDispatch() {
        progressDialog.setMessage(getResources().getString(R.string.downloading));
        progressDialog.show();
        startDownloadDespatch();
    }

    @OnClick(R.id.btn_remove_datas)
    void onClickBtnRemoveData() {
        progressDialog.setMessage(getResources().getString(R.string.removing));
        progressDialog.show();
        RemoveAllDataTask task = new RemoveAllDataTask(getActivity());
        task.execute();
    }

    private void startDownloadDespatch() {
        DownloadDespatchTask task = new DownloadDespatchTask();
        task.execute();
    }

    private void hideProgressDialog() {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void parseDespatches(String despatches) {
        try {
            JSONArray jsonDespatchArray = new JSONArray(despatches);
            DespatchDB despatchDB = new DespatchDB(getActivity());
            OutletDB outletDB = new OutletDB(getActivity());
            for(int i = 0; i < jsonDespatchArray.length(); i++) {
                JSONObject jsonDespatchItem = jsonDespatchArray.getJSONObject(i);
                DespatchItem despatchItem = new DespatchItem();

                String despatchID = jsonDespatchItem.getString("despatchID");

                despatchItem.setDespatchId(despatchID);
                despatchItem.setRunId(jsonDespatchItem.getString("run"));
                despatchItem.setDriverName(jsonDespatchItem.getString("driver"));
                despatchItem.setCreationDate(jsonDespatchItem.getString("date"));
                despatchItem.setCompleted(StateConsts.DESPATCH_DEFAULT);

                if(!despatchDB.isExist(despatchItem)) {
                    despatchDB.addDespatch(despatchItem);
                    String despatchOutlet = jsonDespatchItem.getString("outlet");
                    JSONArray jsonOutletArray = new JSONArray(despatchOutlet);
                    for(int j = 0; j < jsonOutletArray.length(); j++) {
                        JSONObject jsonOutletItem = jsonOutletArray.getJSONObject(j);

                        OutletItem outletItem = new OutletItem();
                        outletItem.setDespatchId(despatchID);
                        outletItem.setOutletId(jsonOutletItem.getString("outletID"));
                        outletItem.setOutlet(jsonOutletItem.getString("outlet"));
                        outletItem.setAddress(jsonOutletItem.getString("address"));
                        outletItem.setServiceType(jsonOutletItem.getString("service"));
                        outletItem.setDelivered(jsonOutletItem.getString("delivered"));
                        outletItem.setDeliveredTime(jsonOutletItem.getString("deliveredtime"));
                        outletItem.setReason(0);
                        outletItem.setCompleted(StateConsts.OUTLET_REMOVED);

                        outletDB.addOutlet(outletItem);
                        String stock = jsonOutletItem.getString("stock");
                    }
                }
            }
            hideProgressDialog();

            ((MainActivity) getActivity()).showDespatchFragment();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}
