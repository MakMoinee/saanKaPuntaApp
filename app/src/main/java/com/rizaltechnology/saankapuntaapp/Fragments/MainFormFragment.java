package com.rizaltechnology.saankapuntaapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rizaltechnology.saankapuntaapp.Adapters.BuildingAdapter;
import com.rizaltechnology.saankapuntaapp.Common.Constants;
import com.rizaltechnology.saankapuntaapp.Interfaces.BuildingListener;
import com.rizaltechnology.saankapuntaapp.Interfaces.FireStoreListener;
import com.rizaltechnology.saankapuntaapp.Interfaces.FragmentFinish;
import com.rizaltechnology.saankapuntaapp.Interfaces.MainButtonsListener;
import com.rizaltechnology.saankapuntaapp.Interfaces.StorageListener;
import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.Models.Offices;
import com.rizaltechnology.saankapuntaapp.R;
import com.rizaltechnology.saankapuntaapp.Services.LocalFirestore2;
import com.rizaltechnology.saankapuntaapp.Services.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainFormFragment extends Fragment implements StorageListener {


    private Context context;
    private RecyclerView recyclerView;
    private ImageButton btnSettings, btnSearch;
    private List<Buildings> buildingsList = new ArrayList<>();
    private List<Buildings> origList = new ArrayList<>();
    private BuildingAdapter adapter;
    private Storage storage;
    private AutoCompleteTextView txtSearch;
    private String[] buildingFilePaths;
    private int currentFileIndex = 0;
    private FragmentFinish fn;
    private String[] countries;
    private ArrayAdapter<String> adapterStr;
    private String lastSearchVal = "";
    private ImageButton btnProfile;
    private MainButtonsListener mainBtnListener;
    List<Offices> officeList = new ArrayList<>();

    LocalFirestore2 fs;
    Offices selectedOffice;

    private BuildingListener bListener = new BuildingListener() {
        @Override
        public void OnClickListener(View mView, int position) {
            Buildings buildings = buildingsList.get(position);
            String buildName = buildings.getBuildingName().replaceAll(".jpg", "");
            buildings.setDescription(lastSearchVal);
            buildings.setBuildingName(buildName);
            selectedOffice = new Offices();
            List<String> stringList = new ArrayList<>();
            if (officeList.size() == 0) {
                fs.getOffices(new FireStoreListener() {
                    @Override
                    public void onError() {
                        FireStoreListener.super.onError();
                    }

                    @Override
                    public void onSuccessOffice(List<Offices> o) {
                        officeList = o;
                        for (Offices offices : officeList) {
                            if (offices.getOfficeName().equals(lastSearchVal)) {
                                selectedOffice = offices;
                            }
                            if (offices.getBuilding().equals(buildings.getDocID())) {
                                stringList.add(offices.getOfficeName());
                            }
                        }
                        fn.openBuildingFragment(buildings, selectedOffice, stringList);
                    }
                });
            } else {
                for (Offices offices : officeList) {
                    if (offices.getOfficeName().equals(lastSearchVal)) {
                        selectedOffice = offices;
                    }
                    if (buildings.getDocID().equals(offices.getBuilding())) {
                        stringList.add(offices.getOfficeName());
                    }
                }
                fn.openBuildingFragment(buildings, selectedOffice, stringList);
            }


        }
    };


    public MainFormFragment(Context mContext, FragmentFinish listener, boolean refresh, MainButtonsListener btn) {
        this.context = mContext;
        this.fn = listener;
        this.mainBtnListener = btn;
        if (refresh) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fs.getBuildings(new FireStoreListener() {
                        @Override
                        public void onError() {
                            FireStoreListener.super.onError();
                        }

                        @Override
                        public void onSuccess(List<Buildings> b) {
                            origList = new ArrayList<>();
                            buildingsList = new ArrayList<>();
                            for (Buildings buildings : b) {
                                buildingsList.add(buildings);
                                origList.add(buildings);
                            }

                            adapter = new BuildingAdapter(context, buildingsList, bListener);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            recyclerView.setAdapter(adapter);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }, 50);
                        }
                    });

                    fs.getOffices(new FireStoreListener() {
                        @Override
                        public void onError() {
                            FireStoreListener.super.onError();
                        }

                        @Override
                        public void onSuccessOffice(List<Offices> o) {
                            List<String> officeArray = new ArrayList<>();
                            for (Offices offices : o) officeArray.add(offices.getOfficeName());
                            countries = officeArray.toArray(new String[officeArray.size()]);
                            adapterStr = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, countries);
                            txtSearch.setAdapter(adapterStr);
                        }
                    });
                }
            }, 50);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = LayoutInflater.from(context).inflate(R.layout.fragment_main, container, false);
        initViews(mView);
        initListeners(mView);
        return mView;
    }

    private void initListeners(View mView) {
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadChangeSearchValue();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBtnListener.onProfileClick();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBtnListener.onNavClick();
            }
        });
    }

    private void loadChangeSearchValue() {
        String searchVal = txtSearch.getText().toString();
        if (searchVal.length() == 0) {
            lastSearchVal = "";
            adapter = new BuildingAdapter(context, origList, bListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }, 50);
            return;
        }
        if (adapterStr.getPosition(searchVal) < 0) {
            lastSearchVal = "";
            return;
        }
        lastSearchVal = searchVal;
        Map<String, String> map = Constants.getBuildingMaps();
        String storageName = map.get(searchVal);


        if (buildingsList.size() == 0) {

        } else {
            Log.e("STORAGE_NAME", storageName.toString());
            List<Buildings> newBuilding = new ArrayList<>();
            for (Buildings b : buildingsList) {
                String buildName = b.getBuildingName().replaceAll(".jpg", "");
                Log.e("buildName", buildName);
                if (buildName.equals(storageName)) {
                    newBuilding.add(b);
                    break;
                }
            }
            buildingsList = newBuilding;

            adapter = new BuildingAdapter(context, newBuilding, bListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }, 50);
        }


    }

    private void initViews(View mView) {
        fs = new LocalFirestore2();
        txtSearch = mView.findViewById(R.id.editSearch);
        recyclerView = mView.findViewById(R.id.recycler);
        recyclerView.setVisibility(View.INVISIBLE);
        btnSearch = mView.findViewById(R.id.imgSearch);
        btnProfile = mView.findViewById(R.id.btnProfile);
        btnSettings = mView.findViewById(R.id.btnSettings);
        storage = new Storage(this);
        buildingFilePaths = Constants.buildingFileFolder.split(",");
        currentFileIndex = 0;
//        storage.getBuildingsFromStorage(buildingFilePaths[0]);
        fs.getBuildings(new FireStoreListener() {
            @Override
            public void onError() {
                FireStoreListener.super.onError();
            }

            @Override
            public void onSuccess(List<Buildings> b) {
                origList = new ArrayList<>();
                buildingsList = new ArrayList<>();
                for (Buildings buildings : b) {
                    buildingsList.add(buildings);
                    origList.add(buildings);
                }

                adapter = new BuildingAdapter(context, buildingsList, bListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(adapter);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }, 50);
            }
        });


        fs.getOffices(new FireStoreListener() {
            @Override
            public void onError() {
                Toast.makeText(context, "There are no offices available yet", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccessOffice(List<Offices> o) {
                officeList = o;
                List<String> officeArray = new ArrayList<>();
                for (Offices offices : o) officeArray.add(offices.getOfficeName());
                countries = officeArray.toArray(new String[officeArray.size()]);
                adapterStr = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, countries);
                txtSearch.setAdapter(adapterStr);
            }
        });
//        countries = getResources().getStringArray(R.array.search_array);
        // Create the adapter and set it to the AutoCompleteTextView

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSuccess(String data) {

    }

    @Override
    public void onSuccessBuilding(List<Buildings> b) {
        if (b != null) {

            if (buildingsList.size() > 0) {
                buildingsList.clear();
                origList.clear();
            }
            for (Buildings buildings : b) {
                buildingsList.add(buildings);
                origList.add(buildings);
            }

            adapter = new BuildingAdapter(context, buildingsList, bListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }, 50);

        }
    }

    @Override
    public void onError(Exception e) {
        Log.e("STORAGE_ERR", e.getMessage());
    }

    @Override
    public void onSuccessRetrieveNavGuide(Buildings buildings) {

    }

    @Override
    public void onSuccessRetrieveVideoURL(Buildings buildings) {

    }
}
