package delivery.com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import delivery.com.R;
import delivery.com.fragment.DespatchFragment;
import delivery.com.model.DespatchItem;

public class DespatchAdapter extends RecyclerView.Adapter<DespatchAdapter.DespatchViewHolder> {

    private DespatchFragment parent;
    private List<DespatchItem> items = new ArrayList<>();

    public DespatchAdapter(DespatchFragment parent) {
        this.parent = parent;
    }

    @Override
    public DespatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_despatch, parent, false);
        return new DespatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DespatchViewHolder holder, int position) {
        DespatchItem item = items.get(position);

        holder.tvDespatchId.setText(item.getDespatchId());
        holder.tvRun.setText(item.getRunId());
        holder.tvDriverName.setText(item.getDriverName());
    }

    public DespatchItem getItem(int pos) {
        return items.get(pos);
    }

    public void clearItems() {
        items.clear();
    }

    public void addItem(DespatchItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<DespatchItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class DespatchViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @Bind(R.id.tv_despatch_id)
        TextView tvDespatchId;
        @Bind(R.id.tv_run)
        TextView tvRun;
        @Bind(R.id.tv_driver_name)
        TextView tvDriverName;

        public DespatchViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
