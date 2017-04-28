package delivery.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import delivery.com.R;
import delivery.com.adapter.DespatchAdapter;
import delivery.com.adapter.StockAdapter;
import delivery.com.db.StockDB;
import delivery.com.model.OutletItem;
import delivery.com.model.StockItem;
import delivery.com.ui.DividerItemDecoration;
import delivery.com.ui.StockActivity;

/**
 * Created by Caesar on 4/25/2017.
 */

public class StockFragment extends Fragment {

    @Bind(R.id.tv_tier)
    TextView tvTier;
    @Bind(R.id.stock_list)
    RecyclerView stockList;

    private int tier;
    private OutletItem outletItem;

    private LinearLayoutManager mLinearLayoutManager;
    private StockAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stock, container, false);

        ButterKnife.bind(StockFragment.this, v);

        tvTier.setText("ROW " + String.valueOf(tier + 1));

        stockList.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stockList.setLayoutManager(mLinearLayoutManager);
        stockList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        adapter = new StockAdapter(StockFragment.this);
        stockList.setAdapter(adapter);

        getStocks();

        return v;
    }

    public static StockFragment newInstance(OutletItem item, int pos) {
        StockFragment f = new StockFragment();
        f.tier = pos;
        f.outletItem = item;
        return f;
    }

    private void getStocks() {
        StockDB db = new StockDB(getActivity());
        ArrayList<StockItem> items = db.fetchStocksByOutletID(outletItem.getOutletId(), tier);

        adapter.addItems(items);
        adapter.notifyDataSetChanged();
    }

    public void updateStockQty(StockItem item) {
        ((StockActivity) getActivity()).addOperation(item);
    }
}