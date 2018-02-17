package productions.darthplagueis.doctorbuddy.recyclerview.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractViewHolder;
import productions.darthplagueis.doctorbuddy.model.Doctor;

public class DoctorViewHolder extends AbstractViewHolder<Doctor> {

    private ImageView doctorImage;
    private TextView doctorName;

    public DoctorViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setViews() {
        doctorImage = itemView.findViewById(R.id.doctor_image);
        doctorName = itemView.findViewById(R.id.doctor_name);
    }

    @Override
    public void onBind(@NonNull Doctor doctor) {
        Glide.with(itemView)
                .load(doctor.getProfile().getImage_url())
                .into(doctorImage);

        String fullName = doctor.getProfile().getTitle() + " " + doctor.getProfile().getFirst_name()
                + " " + doctor.getProfile().getMiddle_name() + " " +
                doctor.getProfile().getLast_name();
        doctorName.setText(fullName);
    }
}

