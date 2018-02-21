package productions.darthplagueis.doctorbuddy.abstractclasses;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.fragments.LoadingFragment;

public abstract class FragmentAbstractActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    public final FragmentManager fragmentManager = getSupportFragmentManager();
    private final LoadingFragment loadingFragment = new LoadingFragment();

    public View container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        setContainer();
        setLoadingTransition();
    }

    protected abstract int getLayoutId();

    private void setContainer() {
        this.container = findViewById(R.id.container);
    }

    public int getContainerId() {
        return container.getId();
    }

    private void setLoadingTransition() {
        Fade fade = new Fade();
        fade.setDuration(250);
        loadingFragment.setEnterTransition(fade);
        loadingFragment.setExitTransition(fade);
    }

    public void replaceFragmentView(@NonNull AbstractFragment abstractFragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(0, R.anim.fade_out)
                .replace(getContainerId(), abstractFragment)
                .addToBackStack(null)
                .commit();
    }

    public void addFragmentToView(@NonNull AbstractFragment abstractFragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(0, R.anim.fade_out)
                .add(getContainerId(), abstractFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showLoadingFragment() {
        if (!loadingFragment.isAdded() && !isFinishing()) {
            fragmentManager.beginTransaction()
                    .add(container.getId(), loadingFragment)
                    .commit();
        }
    }

    public void hideLoadingFragment() {
        fragmentManager.beginTransaction()
                .remove(loadingFragment)
                .commit();
    }

    public void showSnackbar(@Nullable String message) {
        Snackbar.make(container, "" + message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideLoadingFragment();
    }
}
