package productions.darthplagueis.doctorbuddy.fragments;


import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractFragment;
import productions.darthplagueis.doctorbuddy.model.Doctor;
import productions.darthplagueis.doctorbuddy.network.RetrofitFactory;
import productions.darthplagueis.doctorbuddy.recyclerview.controller.DoctorAdapter;

public class DoctorFragment extends AbstractFragment {

    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;

    private String location;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_doctor_recycler;
    }

    @Override
    public void onCreateView() {
        getParentActivity().showLoadingFragment();
        doctorAdapter = new DoctorAdapter();
        setViews();
        getListOfDoctors();
    }

    private void setViews() {
        recyclerView = parentView.findViewById(R.id.doctor_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(doctorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void getListOfDoctors() {
        RetrofitFactory.DoctorNetworkListener listener = new RetrofitFactory.DoctorNetworkListener() {
            @Override
            public void doctorCallBack(List<Doctor> responseList) {
                doctorAdapter.updateList(responseList);
                getParentActivity().hideLoadingFragment();
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                Snackbar.make(parentView, "Network error.", Snackbar.LENGTH_LONG).show();
                getParentActivity().hideLoadingFragment();
            }
        };
        RetrofitFactory.getInstance().setDoctorNetworkListener(listener);
        RetrofitFactory.getInstance().getDoctorsUsingLocation(location);
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
