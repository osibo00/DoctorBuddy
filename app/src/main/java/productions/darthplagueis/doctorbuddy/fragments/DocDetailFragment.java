package productions.darthplagueis.doctorbuddy.fragments;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractFragment;


public class DocDetailFragment extends AbstractFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_doc_detail;
    }

    @Override
    public void onCreateView() {
        Toolbar toolbar = parentView.findViewById(R.id.toolbar);
        getParentActivity().setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) parentView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Peace is a lie.");
        ImageView imageView = parentView.findViewById(R.id.image);
        Glide.with(parentView)
                .load(R.drawable.doctor_patient)
                .apply(new RequestOptions().override(imageView.getWidth(), imageView.getHeight()))
                .into(imageView);
    }
}
