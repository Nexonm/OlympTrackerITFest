package neoxs.olymptracker.presentation.olympians;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import neoxs.olymptracker.R;
import neoxs.olymptracker.app.AppStart;
import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.presentation.olympiancard.OlympianCard;

public class OlympiansList extends Fragment {

    private ListOfPeopleViewModel viewModel;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    LinearLayout ll_add_olympian;

    private ItemTouchHelper peopleTouchHelper;

    private Adapter adapter;

    public static OlympiansList newInstance() {
        return new OlympiansList();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //parseParams();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_olympians_main_list,
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
    }

    private void initAdapter() {
        adapter = new Adapter(new DiffCallback());
        adapter.cardClickListener = this::startCardFragment;
        initSwipe();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(
                this,
                new ListOfPeopleViewModelFactory(
                        AppStart.getInstance().getDelete(),
                        AppStart.getInstance().getAddNew(),
                        AppStart.getInstance().getGetAll()
                ))
                .get(ListOfPeopleViewModel.class);
        viewModel
                .getPeopleList()
                .observe(
                        getViewLifecycleOwner(),
                        peoples -> adapter.submitList(peoples)
                );
    }

    private void initSwipe() {
        peopleTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            @NonNull RecyclerView.ViewHolder target
                    ) {
                        return false;
                    }

                    @Override
                    public void onSwiped(
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            int direction
                    ) {
                        Olympian people = adapter.getCurrentList().get(viewHolder.getAdapterPosition());
                        viewModel.deletePeople(people);
                    }

                }
        );
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rv_main_card_list);
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_CARD_VISITOR, Adapter.MAX_POOL_SIZE);

        ll_add_olympian = view.findViewById(R.id.ll_main_add_olympian);

        fab = view.findViewById(R.id.fb_main_list_fab);
        fab.setOnClickListener(view1 -> {
            new AddNewOlympian().saveNewOlympian(view);
        });
    }

    //big card
    private void startCardFragment(Olympian card) {
        Fragment fragment = new OlympianCard();
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager
                .beginTransaction()
                .addToBackStack("null")
                .replace(R.id.nav_host_fragment_content_main, fragment, null)
                .commit();


    }

    private void setRVInvisible(){
        recyclerView.setClickable(false);
        recyclerView.setVisibility(View.INVISIBLE);
        ll_add_olympian.setVisibility(View.VISIBLE);
        ll_add_olympian.setClickable(true);
    }

    private void setLLInvisible(){
        ll_add_olympian.setVisibility(View.INVISIBLE);
        ll_add_olympian.setClickable(false);
        recyclerView.setClickable(true);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private class AddNewOlympian{

        Button bt_save, bt_escape;


        void saveNewOlympian(View view){
            setRVInvisible();

            bt_save = view.findViewById(R.id.bt_main_save);
            bt_save.setOnClickListener(view1 -> {
                save(view);
            });
            bt_escape = view.findViewById(R.id.bt_main_escape);
            bt_escape.setOnClickListener(view1 -> {
                escape();
            });

        }


        private void save(View view){
            EditText et_name = view.findViewById(R.id.et_main_name);
            EditText et_sport_name = view.findViewById(R.id.et_main_sport_name);
            EditText et_medals = view.findViewById(R.id.et_main_medals);
            EditText et_year_of_birth = view.findViewById(R.id.et_main_year_of_birth);
            EditText et_description = view.findViewById(R.id.et_main_description);

            TextView tv_error = view.findViewById(R.id.tv_main_error);

            boolean isOk = true;
            if (et_name.getText().toString().length()<1){
                et_name.setText("Введите корректный текст");
                isOk = false;
            }
            if (et_sport_name.getText().toString().length()<1){
                et_sport_name.setText("Введите корректный текст");
                isOk = false;
            }
            if (!isNum(et_year_of_birth.getText().toString())){
                et_year_of_birth.setText("Введите число - год");
                isOk = false;
            }
            if (!et_medals.getText().toString().matches("\\d+\\s\\d+\\s\\d")){
                et_medals.setText("введите верный формат");
                isOk = false;
            }

            if (isOk){
                viewModel.addNewPeople(new Olympian(
                        et_name.getText().toString(),
                        et_sport_name.getText().toString(),
                        "no path yet",
                        getNum(et_year_of_birth.getText().toString()),
                        et_medals.getText().toString(),
                        et_description.getText().toString()
                ));
                tv_error.setVisibility(View.INVISIBLE);
                setLLInvisible();
            }else{
                tv_error.setVisibility(View.VISIBLE);
            }
            //set invisible add part

        }

        private void escape(){
            setLLInvisible();
        }

        private boolean isNum(String s){
            try{
                int a = Integer.parseInt(s);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }

        private int getNum(String s){
            return Integer.parseInt(s);
        }

    }
}
