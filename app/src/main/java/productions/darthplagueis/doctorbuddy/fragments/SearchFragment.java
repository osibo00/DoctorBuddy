package productions.darthplagueis.doctorbuddy.fragments;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.model.LatLng;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractFragment;
import productions.darthplagueis.doctorbuddy.model.Doctor;
import productions.darthplagueis.doctorbuddy.network.RetrofitFactory;
import productions.darthplagueis.doctorbuddy.recyclerview.controller.LocationRecyclerAdapter;


public class SearchFragment extends AbstractFragment {

    private LocationRecyclerAdapter doctorAdapter;

    private double lat;
    private double lng;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_doc_search;
    }

    @Override
    public void onCreateView() {
        setViews();
        getListOfDoctors();
    }

    private void setViews() {
        getParentActivity().showLoadingFragment();
        Toolbar searchToolbar = parentView.findViewById(R.id.toolbar);
        getParentActivity().setSupportActionBar(searchToolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) parentView.findViewById(R.id.collapsing_search_toolbar);
        collapsingToolbar.setTitle("Peace is a lie.");
        ImageView imageView = parentView.findViewById(R.id.search_image);
        Glide.with(parentView)
                .load(R.drawable.doctor_patient)
                .apply(new RequestOptions().override(imageView.getWidth(), imageView.getHeight()))
                .into(imageView);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = parentView.findViewById(R.id.doctor_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        doctorAdapter = new LocationRecyclerAdapter(lat, lng, 10,
                SearchFragment.this);
        recyclerView.setAdapter(doctorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void getListOfDoctors() {
        RetrofitFactory.DoctorNetworkListener listener = new RetrofitFactory.DoctorNetworkListener() {
            @Override
            public void doctorCallBack(List<Doctor> responseList) {
                List<LatLng> latLngList = new ArrayList<>();
                for (int i = 0; i < responseList.size(); i++) {
                    LatLng latLng = new LatLng(responseList.get(i).getPractices().get(0).getLat(),
                            responseList.get(i).getPractices().get(0).getLon());
                    latLngList.add(latLng);
                }
                doctorAdapter.updateList(responseList, latLngList);
                getParentActivity().hideLoadingFragment();
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                Snackbar.make(parentView, "Network error.", Snackbar.LENGTH_LONG).show();
                getParentActivity().hideLoadingFragment();
            }
        };
        RetrofitFactory.getInstance().setDoctorNetworkListener(listener);
        RetrofitFactory.getInstance().getDoctorsUsingLocation(String.valueOf(roundThreePlaces(lat))
                + "," + String.valueOf(roundThreePlaces(lng)));
    }

    private double roundThreePlaces(double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
