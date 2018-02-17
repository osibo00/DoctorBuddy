package productions.darthplagueis.doctorbuddy.recyclerview.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import productions.darthplagueis.doctorbuddy.GeneralActivity;
import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractViewHolder;
import productions.darthplagueis.doctorbuddy.model.Doctor;

public class DoctorViewHolder extends AbstractViewHolder<Doctor> {

    private ImageView doctorImage;
    private TextView doctorName, doctorSpeciality, specializesIn;

    private Doctor doctor;

    public DoctorViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setViews() {
        doctorImage = itemView.findViewById(R.id.doctor_image);
        doctorName = itemView.findViewById(R.id.doctor_name);
        doctorSpeciality = itemView.findViewById(R.id.doctor_speciality);
        specializesIn = itemView.findViewById(R.id.specializes_in);
    }

    @Override
    public void onBind(@NonNull final Doctor doctor) {
        this.doctor = doctor;

        Glide.with(itemView)
                .load(doctor.getProfile().getImage_url())
                .apply(new RequestOptions().override(doctorImage.getWidth(), doctorImage.getHeight()))
                .into(doctorImage);

        String fullName;
        if (doctor.getProfile().getMiddle_name() != null) {
            fullName = doctor.getProfile().getFirst_name()
                    + " " + doctor.getProfile().getMiddle_name() + " " +
                    doctor.getProfile().getLast_name() + " " + doctor.getProfile().getTitle();
        } else {
            fullName = doctor.getProfile().getFirst_name() + " " +
                    doctor.getProfile().getLast_name() + " " + doctor.getProfile().getTitle();
        }
        doctorName.setText(fullName);
        doctorSpeciality.setText(doctor.getSpecialties().get(0).getActor());
        specializesIn.setText(doctor.getSpecialties().get(0).getDescription());
    }

    @Override
    public void onClick(View v) {
        ((GeneralActivity) getContext()).showDocDetailFragment(doctor);
    }
}

