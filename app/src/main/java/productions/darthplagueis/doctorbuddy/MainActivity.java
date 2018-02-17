//package productions.darthplagueis.doctorbuddy;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class MainActivity extends AppCompatActivity implements
//        GoogleApiClient.OnConnectionFailedListener {
//
//    private static final String TAG = "MainActivity";
//    public static final String ANONYMOUS = "anonymous";
//    private String username;
//    private String photoUrl;
//    private GoogleApiClient googleApiClient;
//
//    private TextView usernameText;
//
//    private FirebaseAuth firebaseAuth;
//    private FirebaseUser firebaseUser;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        username = ANONYMOUS;
//
//        usernameText = (TextView) findViewById(R.id.user_name);
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API)
//                .build();
//
//        findViewById(R.id.temp_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MapsActivity.class));
//            }
//        });
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
//        if (firebaseUser == null) {
//            startActivity(new Intent(this, SignInActivity.class));
//            finish();
//        } else {
//            username = firebaseUser.getDisplayName();
//            usernameText.setText(username);
//            if (firebaseUser.getPhotoUrl() != null) {
//                photoUrl = firebaseUser.getPhotoUrl().toString();
//            }
//        }
//        haveLocationPermission();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.invite_menu:
//                return true;
//            case R.id.sign_out_menu:
//                firebaseAuth.signOut();
//                Auth.GoogleSignInApi.signOut(googleApiClient);
//                username = ANONYMOUS;
//                startActivity(new Intent(this, SignInActivity.class));
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
//        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.w(TAG, "Permission: " + "Granted. " + "requestCode=" + requestCode);
//            // something is done here
//        }
//    }
//
//    private boolean haveLocationPermission() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED ||
//                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                Log.w(TAG, "Permission: " + "You have permission.");
//                return true;
//            } else {
//                Log.w(TAG, "Permission: " + "You have asked for permission.");
//                ActivityCompat.requestPermissions(this, new
//                        String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1010);
//                return false;
//            }
//        } else {
//            Log.w(TAG, "Permission: " + "Does not require requestPermissions.");
//            return true;
//        }
//    }
//}
