package productions.darthplagueis.doctorbuddy.fragments;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

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
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) parentView.findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle("Peace is a lie.");
    }
}
