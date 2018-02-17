package productions.darthplagueis.doctorbuddy.abstractclasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import productions.darthplagueis.doctorbuddy.R;

public abstract class AbstractSplashActivity extends AppCompatActivity {

    private View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        setContainer();
    }

    private void setContainer() {
        container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setAnimation();
    }

    private void toNextActivity() {
        startActivity(new Intent(AbstractSplashActivity.this, getNextActivity()));
    }

    protected abstract int getLayoutId();

    protected abstract Class<?> getNextActivity();

    protected abstract void setAnimation();
}
