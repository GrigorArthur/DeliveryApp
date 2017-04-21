package delivery.com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import delivery.com.R;

public class DespatchFragment extends Fragment {

    @Bind(R.id.despatch_list)
    RecyclerView despatchList;

    private LinearLayoutManager mLinearLayoutManager;

    public static DespatchFragment newInstance() {
        DespatchFragment fragment = new DespatchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_despatch, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
