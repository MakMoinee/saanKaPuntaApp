package com.rizaltechnology.saankapuntaapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BuildingFragment extends Fragment {

    Context mContext;
    Buildings buildings;
    ImageView imgBuildingPoster;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_building, container, false);
        initViews(mView);
        return mView;
    }

    private void initViews(View mView) {
        ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage("Loading info ...");
        pd.setCancelable(false);
        pd.show();
        imgBuildingPoster = mView.findViewById(R.id.imgBuildingPoster);
        Uri uri = Uri.parse(buildings.getPicturePath());
        Picasso.get().invalidate(uri);
        Picasso.get().load(uri)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(imgBuildingPoster, new Callback() {
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("BUILDING_FRAGMENT", e.getMessage());
                        pd.dismiss();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
