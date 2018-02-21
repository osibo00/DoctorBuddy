package productions.darthplagueis.doctorbuddy.recyclerview.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractFragment;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractViewHolder;
import productions.darthplagueis.doctorbuddy.recyclerview.viewholder.DoctorViewHolder;
import productions.darthplagueis.doctorbuddy.recyclerview.viewholder.MapViewHolder;


public class LocationRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> list;
    private LatLng location;
    private float mapZoom;
    private AbstractFragment abstractFragment;

    public LocationRecyclerAdapter(double latitude, double longitude, float mapZoom,
                                   AbstractFragment abstractFragment) {
        list = new ArrayList<>();
        this.mapZoom = mapZoom;
        this.abstractFragment = abstractFragment;
        location = new LatLng(latitude, longitude);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View childView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_maps, parent, false);
            return new MapViewHolder(childView, abstractFragment);
        } else if (viewType == 2) {
            View childView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.doctor_item_view, parent, false);
            return new DoctorViewHolder(childView);
        } else {
            View childView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.doctor_item_view, parent, false);
            return new DoctorViewHolder(childView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof MapViewHolder) {
                ((MapViewHolder) holder).locationTextView.setText(locationAsString(location));
            } else if (holder instanceof AbstractViewHolder) {
                if (!list.isEmpty()) {
                    ((AbstractViewHolder) holder).onBind(list.get(position));
                }
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof MapViewHolder) {
            ((MapViewHolder) holder).getMapFragmentAndCallBack(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMap.addMarker(new MarkerOptions().position(location));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                    googleMap.moveCamera(CameraUpdateFactory.zoomTo(mapZoom));
                }
            });
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof MapViewHolder) {
            // If IllegalArgumentException occurs then mapFragment can be removed from view here.
            // ((MapViewHolder) holder).removeMapFragment();
        }
    }

    @Override
    public int getItemCount() {
        if (!list.isEmpty()) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 2;
        } else return 3;
    }

    public void updateList(@NonNull List<T> newList) {
        list.addAll(newList);
        notifyItemRangeInserted(getItemCount(), list.size() - 1);
    }

    private String locationAsString(LatLng location) {
        String s = "";
        NumberFormat nf = new DecimalFormat("0.000");
        String latitude = nf.format(location.latitude);
        String longitude = nf.format(location.longitude);
        if (latitude.startsWith("-")) {
            s += latitude.substring(1) + "°S /";
        } else {
            s += latitude + "°N / ";
        }
        if (longitude.startsWith("-")) {
            s += longitude.substring(1) + "°W";
        } else {
            s += longitude + "°E";
        }
        return s;
    }
}
