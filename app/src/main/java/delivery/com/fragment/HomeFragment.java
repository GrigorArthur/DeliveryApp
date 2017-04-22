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
import delivery.com.event.DownloadDespatchEvent;
import delivery.com.model.DespatchItem;
import delivery.com.task.DownloadDespatchTask;
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

    @OnClick(R.id.btn_download_dispatch)
    void onClickBtnDownDispatch() {
        progressDialog.setMessage(getResources().getString(R.string.downloading));
        progressDialog.show();
        startDownloadDespatch();
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
            DespatchDB db = new DespatchDB(getActivity());
            for(int i = 0; i < jsonDespatchArray.length(); i++) {
                JSONObject jsonDespatchItem = jsonDespatchArray.getJSONObject(i);
                DespatchItem item = new DespatchItem();

                item.setDespatchId(jsonDespatchItem.getString("despatchID"));
                item.setRunId(jsonDespatchItem.getString("run"));
                item.setDriverName(jsonDespatchItem.getString("driver"));
                item.setCreationDate(jsonDespatchItem.getString("date"));
                item.setCompleted(StateConsts.DESPATCH_DEFAULT);

                if(!db.isExist(item))
                    db.addDespatch(item);
            }
            hideProgressDialog();

            ((MainActivity) getActivity()).showDespatchFragment();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}
