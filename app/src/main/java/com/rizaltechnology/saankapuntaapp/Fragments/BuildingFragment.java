package com.rizaltechnology.saankapuntaapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.rizaltechnology.saankapuntaapp.Common.Constants;
import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.Models.Offices;
import com.rizaltechnology.saankapuntaapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BuildingFragment extends Fragment {

    Context mContext;
    Buildings buildings;
    ImageView imgBuildingPoster;
    ProgressBar pbPlayer;
    PlayerControlView player;
    SurfaceView surfaceView;
    ExoPlayer exoPlayer;
    RelativeLayout relativeVideo;
    ImageButton btnMusic;
    Button btnPlay;
    String videoURL = "";
    String videoKey = "";
    Boolean musicON = true;

    Offices offices;

    List<String> officeStrList;

    public BuildingFragment(Context mContext, Buildings buildings, Offices o, List<String> l) {
        this.mContext = mContext;
        this.buildings = buildings;
        this.offices = o;
        this.officeStrList = l;
    }

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
        ImageView imgNavGuide = mView.findViewById(R.id.imgNavGuide);
        TextView lblTitle = mView.findViewById(R.id.lblTitle);
        TextView lblLocation = mView.findViewById(R.id.lblLocation);
        TextView txtLocation = mView.findViewById(R.id.txtLocation);
        TextView lblDirections = mView.findViewById(R.id.lblDirections);
        TextView txtDirections = mView.findViewById(R.id.txtDirections);
        TextView lblNavGuide = mView.findViewById(R.id.lblNavGuide);
        TextView lblVirtualGuide = mView.findViewById(R.id.lblVirtualGuide);
        btnMusic = mView.findViewById(R.id.btnMusic);
        btnMusic.setVisibility(View.INVISIBLE);
        btnPlay = mView.findViewById(R.id.btnPlay);
        relativeVideo = mView.findViewById(R.id.relativeVideo);
        pbPlayer = mView.findViewById(R.id.pbPlayer);
        player = mView.findViewById(R.id.player);
        exoPlayer = new ExoPlayer.Builder(mContext).build();
        surfaceView = mView.findViewById(R.id.surfaceView);
        player.hide();
        initListeners();

        String url = Constants.webAminHost + buildings.getPosterPath();
        Uri uri = Uri.parse(url);
        Picasso.get().invalidate(uri);
        Picasso.get().load(uri)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(imgBuildingPoster, new Callback() {
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                        String locationKey = "";
                        if (buildings.getDescription().equals("")) {
                            lblTitle.setText(buildings.getBuildingName());
                            videoKey = "";
                            String data = "List of Offices Within This Building:\n\n";
                            for (String str : officeStrList) data += "- " + str + "\n";
                            lblLocation.setVisibility(View.GONE);
                            txtLocation.setVisibility(View.VISIBLE);
                            txtLocation.setText(data);
                        } else {
                            lblTitle.setText(buildings.getDescription());
                            if (offices != null) {
                                if (offices.getFloor() != "") {
                                    String locationRaw = String.format("%s - %s", offices.getFloor(), buildings.getBuildingName());
                                    lblLocation.setVisibility(View.VISIBLE);
                                    txtLocation.setVisibility(View.VISIBLE);
                                    txtLocation.setText(locationRaw);
                                }

                                if (offices.getDirections() != "") {
                                    lblDirections.setVisibility(View.VISIBLE);
                                    txtDirections.setVisibility(View.VISIBLE);
                                    txtDirections.setText(offices.getDirections());
                                }

                                loadNavGuide(imgNavGuide, offices.getFloorMapPath(), lblNavGuide);
                                videoKey = offices.getVideoURL();
                            }

                        }
//                        boolean hasThatLocation = locationMap.containsKey(locationKey);
//
//                        if (hasThatLocation) {
//                            int locationStringId = locationMap.get(locationKey);
//                            lblLocation.setVisibility(View.VISIBLE);
//                            txtLocation.setVisibility(View.VISIBLE);
//                            txtLocation.setText(locationStringId);
//                        }
//
//                        boolean hasThatDirection = directionsMap.containsKey(locationKey);
//
//                        if (hasThatDirection) {
//                            int directionStringId = directionsMap.get(locationKey);
//                            lblDirections.setVisibility(View.VISIBLE);
//                            txtDirections.setVisibility(View.VISIBLE);
//                            txtDirections.setText(directionStringId);
//                        }
//
//                        if (!hasThatDirection && !hasThatLocation) {
//                            Map<String, String> buildingMaps = Constants.getBuildingMaps();
//                            lblLocation.setVisibility(View.GONE);
//                            txtLocation.setVisibility(View.VISIBLE);
//                            String data = "List of Offices Within This Building:\n\n";
//                            for (Map.Entry<String, String> entry : buildingMaps.entrySet()) {
//                                Log.e("entry key", entry.getKey());
//                                Log.e("entry val", entry.getValue());
//                                if (entry.getValue().equals(locationKey)) {
//                                    data += "- " + entry.getKey() + "\n";
//
//                                }
//                            }
//                            txtLocation.setText(data);
//                        } else {
//                            lblVirtualGuide.setVisibility(View.VISIBLE);
//                            relativeVideo.setVisibility(View.VISIBLE);
//                        }
                        if (videoKey != "") {
                            lblVirtualGuide.setVisibility(View.VISIBLE);
                            relativeVideo.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("BUILDING_FRAGMENT", e.getMessage());
                        pd.dismiss();
                    }
                });
    }

    private void initListeners() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlay.setVisibility(View.GONE);
                pbPlayer.setVisibility(View.VISIBLE);
                processVideo();
            }
        });
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicON) {
                    exoPlayer.setVolume(0f);
                    btnMusic.setImageResource(R.drawable.ic_music_off);
                    musicON = false;
                } else {
                    btnMusic.setImageResource(R.drawable.ic_music_on);
                    exoPlayer.setVolume(1f);
                    musicON = true;
                }
            }
        });
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!player.isVisible()) {
                    player.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            player.hide();
                        }
                    }, 1500);
                }
            }
        });
    }

    private void processVideo() {
//        Map<String, String> videoMaps = Constants.getVirtualGuideMap();
//
//        Log.e("VIDEOKEY", videoKey);
//        if (videoKey == "") {
//            return;
//        }
//        if (!videoMaps.containsKey(videoKey)) {
//            Log.e("VIDEOKEY3", videoKey);
//            return;
//        }

        String videoURL = Constants.webAminHost + videoKey;
        Log.e("videoURL", videoURL);
        buildings.setVideoPath(videoURL);
        playVideo(buildings);

    }

    private void playVideo(Buildings buildings) {
        player.setPlayer(exoPlayer);
        String videoURL = buildings.getVideoPath();
        Uri uri = Uri.parse(videoURL);
        MediaItem item = MediaItem.fromUri(uri);
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(item);
        exoPlayer.setMediaSource(videoSource);
        player.setShowTimeoutMs(6500);
        player.setShowNextButton(false);
        player.setShowPreviousButton(false);
        exoPlayer.setVideoSurfaceView(surfaceView);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                switch (playbackState) {
                    case Player.STATE_READY:
                        pbPlayer.setVisibility(View.GONE);
                        btnMusic.setVisibility(View.VISIBLE);
                        player.show();
                        break;
                }
                Player.Listener.super.onPlaybackStateChanged(playbackState);
            }

            @Override
            public void onPlayerError(PlaybackException error) {
                pbPlayer.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                btnMusic.setVisibility(View.INVISIBLE);
                Player.Listener.super.onPlayerError(error);
            }
        });


    }

    private void loadNavGuide(ImageView img, String key, TextView lblNavGuide) {

        String fileName = Constants.webAminHost + key;

        Uri uri = Uri.parse(fileName);
        Picasso.get().invalidate(uri);
        Picasso.get().load(uri)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                        lblNavGuide.setVisibility(View.VISIBLE);
                        img.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    /* access modifiers changed from: private */
    public void releasePlayer() {
        if (this.exoPlayer != null) {
            Log.e("release player", "release player");
            try {
                this.exoPlayer.stop();
                this.exoPlayer.release();
                this.exoPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
