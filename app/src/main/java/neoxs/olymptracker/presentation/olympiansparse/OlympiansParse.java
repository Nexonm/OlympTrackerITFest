package neoxs.olymptracker.presentation.olympiansparse;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import neoxs.olymptracker.R;
import neoxs.olymptracker.app.AppStart;
import neoxs.olymptracker.domain.entity.ItemParse;

public class OlympiansParse extends Fragment {

    private OlympiansParseViewModel viewModel;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private ParseAdapter adapter;

    public static OlympiansParse newInstance() {
        return new OlympiansParse();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_olympians_main_list_parse,
                container,
                false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initViewModel();
        initView(view);
        //content.execute();
    }

    //init main adaptor to see what happened
    private void initAdapter() {
        adapter = new ParseAdapter(new DiffCallbackParse());
    }

    //init view model that observes if anything changed
    private void initViewModel() {
        viewModel = new ViewModelProvider(
                this,
                new OlympiansParseViewModelFactory(
                        AppStart.getInstance().getItemsGetAll(),
                        AppStart.getInstance().getItemsAddNew()
                ))
                .get(OlympiansParseViewModel.class);
        viewModel
                .getItemsList()
                .observe(
                        getViewLifecycleOwner(),
                        items -> adapter.submitList(items)
                );
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rv_olympians_list);
        recyclerView.setAdapter(adapter);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            viewModel.addNewItems();
        });
    }
}
