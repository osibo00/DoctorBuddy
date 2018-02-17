package productions.darthplagueis.doctorbuddy;


import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractSplashActivity;

public class SplashActivity extends AbstractSplashActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected Class<?> getNextActivity() {
        return GeneralActivity.class;
    }

    @Override
    protected void setAnimation() {

    }
}
