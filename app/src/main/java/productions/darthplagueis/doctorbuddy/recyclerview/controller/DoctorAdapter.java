package productions.darthplagueis.doctorbuddy.recyclerview.controller;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractAdapter;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractViewHolder;
import productions.darthplagueis.doctorbuddy.model.Doctor;
import productions.darthplagueis.doctorbuddy.recyclerview.viewholder.DoctorViewHolder;


public class DoctorAdapter extends AbstractAdapter<Doctor> {

    @Override
    protected AbstractViewHolder getViewHolder() {
        return new DoctorViewHolder(view);
    }

    @Override
    public int getLayout() {
        return R.layout.doctor_item_view;
    }
}
