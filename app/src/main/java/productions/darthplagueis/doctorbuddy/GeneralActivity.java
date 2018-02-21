package productions.darthplagueis.doctorbuddy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.math.BigDecimal;
import java.math.RoundingMode;

import productions.darthplagueis.doctorbuddy.abstractclasses.FragmentAbstractActivity;
import productions.darthplagueis.doctorbuddy.fragments.DocDetailFragment;
import productions.darthplagueis.doctorbuddy.fragments.DoctorFragment;
import productions.darthplagueis.doctorbuddy.fragments.SearchFragment;
import productions.darthplagueis.doctorbuddy.model.Doctor;
import productions.darthplagueis.doctorbuddy.util.NetworkConnectivity;

public class GeneralActivity extends FragmentAbstractActivity implements
        GoogleApiClient.ConnectionCallbacks {

    public static final String TAG = "GeneralActivity";
    public static final String ANONYMOUS = "anonymous";
    private static final int RC_LOCATION = 4020;
    private String username;
    private String photoUrl;
    private long UPDATE_INTERVAL = 2 * 5000;
    private long FASTEST_INTERVAL = 2000;

    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient locationProviderClient;
    private LocationRequest locationRequest;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = ANONYMOUS;

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

//        locationRequest = new LocationRequest();
//        locationRequest.setInterval(UPDATE_INTERVAL);
//        locationRequest.setFastestInterval(FASTEST_INTERVAL);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_general;
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            startActivity(new Intent(this, SignUpActivity.class));
            finish();
        } else {
            googleApiClient.connect();
            username = firebaseUser.getDisplayName();
            if (firebaseUser.getPhotoUrl() != null) {
                photoUrl = firebaseUser.getPhotoUrl().toString();
            }
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.invite_menu:
                return true;
            case R.id.sign_out_menu:
                firebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient);
                username = ANONYMOUS;
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GeneralActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, RC_LOCATION);
        } else {
            Log.d(TAG, "GoogleApiClient is connected.");
            passLocationToFragment();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: requestCode=" + requestCode);
        if (requestCode == RC_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                passLocationToFragment();
            }
        }
    }

    public void showDocDetailFragment(Doctor doctor) {
        DocDetailFragment docDetailFragment = new DocDetailFragment();
        addFragmentToView(docDetailFragment);
    }

    private void showDoctorFragment(double lat, double lng) {
        if (NetworkConnectivity.isConnected(this)) {
            SearchFragment searchFragment = new SearchFragment();
            searchFragment.setLat(lat);
            searchFragment.setLng(lng);
            showFragment(searchFragment);
        } else {
            showSnackbar("No Network Connectivity.");
        }
    }

    private void passLocationToFragment() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationProviderClient.getLastLocation().addOnSuccessListener(this,
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Log.d(TAG, "LastLocation: " + location);
                                double lat = location.getLatitude();
                                double lng = location.getLongitude();
                                showDoctorFragment(lat, lng);
                            }
                        }
                    });
        }
    }
}
