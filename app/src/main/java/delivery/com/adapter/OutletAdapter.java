package delivery.com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import delivery.com.R;
import delivery.com.consts.StateConsts;
import delivery.com.fragment.DespatchFragment;
import delivery.com.model.DespatchItem;
import delivery.com.model.OutletItem;
import delivery.com.ui.OutletActivity;

public class OutletAdapter extends RecyclerView.Adapter<OutletAdapter.OutletViewHolder> {

    private OutletActivity parent;
    private List<OutletItem> items = new ArrayList<>();

    public OutletAdapter(OutletActivity parent) {
        this.parent = parent;
    }

    @Override
    public OutletViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_outlet, parent, false);
        return new OutletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OutletViewHolder holder, int position) {
        final OutletItem item = items.get(position);

        holder.tvOutletID.setText(item.getOutletId());
        holder.tvService.setText(item.getServiceType());
        holder.tvAddress.setText(item.getAddress());

        holder.tvAddress.setSelected(true);

        holder.setIcon(item.getCompleted());
        holder.setTVColor(item.getCompleted());
    }

    public OutletItem getItem(int pos) {
        return items.get(pos);
    }

    public void clearItems() {
        items.clear();
    }

    public void addItem(OutletItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<OutletItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class OutletViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @Bind(R.id.outlet_layout)
        LinearLayout outletLayout;
        @Bind(R.id.tv_outlet_id)
        TextView tvOutletID;
        @Bind(R.id.tv_service)
        TextView tvService;
        @Bind(R.id.tv_address)
        TextView tvAddress;
        @Bind(R.id.iv_mark)
        ImageView ivMark;

        public OutletViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        public void setTVColor(int completed) {
            if(completed == StateConsts.OUTLET_COMPLETED || completed == StateConsts.OUTLET_REMOVED) {
                tvOutletID.setTextColor(parent.getResources().getColor(R.color.colorWhite));
                tvService.setTextColor(parent.getResources().getColor(R.color.colorWhite));
                tvAddress.setTextColor(parent.getResources().getColor(R.color.colorWhite));
            } else {
                tvOutletID.setTextColor(parent.getResources().getColor(R.color.colorBlack));
                tvService.setTextColor(parent.getResources().getColor(R.color.colorBlack));
                tvAddress.setTextColor(parent.getResources().getColor(R.color.colorBlack));
            }
        }

        public void setIcon(int completed) {
            if(completed == StateConsts.OUTLET_COMPLETED) {
                outletLayout.setBackgroundDrawable(parent.getResources().getDrawable(R.drawable.outlet_bg_complete));
                ivMark.setBackground(parent.getResources().getDrawable(R.drawable.ic_complete));
                ivMark.setVisibility(View.VISIBLE);
            } else if (completed == StateConsts.OUTLET_REMOVED){
                outletLayout.setBackgroundDrawable(parent.getResources().getDrawable(R.drawable.outlet_bg_remove));
                ivMark.setBackground(parent.getResources().getDrawable(R.drawable.ic_outlet_delete));
                ivMark.setVisibility(View.VISIBLE);
            } else if (completed == StateConsts.OUTLET_DEFAULT) {
                outletLayout.setBackgroundDrawable(parent.getResources().getDrawable(R.drawable.outlet_bg_default));
                ivMark.setVisibility(View.GONE);
            }
        }
    }
}
