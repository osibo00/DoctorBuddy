//package productions.darthplagueis.doctorbuddy.recyclerview.viewholder;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.TextView;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import productions.darthplagueis.doctorbuddy.R;
//import productions.darthplagueis.doctorbuddy.abstractclasses.FragmentAbstractActivity;
//
//
//public class MapViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
//
//    private SupportMapFragment mapFragment;
//    public TextView locationName;
//    private GoogleMap map;
//    private Context context;
//    private LatLng location;
//    private float zoom;
//
//    public MapViewHolder(View itemView, Context context, LatLng location, float zoom) {
//        super(itemView);
//        this.context = context;
//        this.location = location;
//        this.zoom = zoom;
//    }
//
//    public void initMap() {
//        if (mapFragment == null) {
//            mapFragment = SupportMapFragment.newInstance();
//            ((FragmentAbstractActivity) context).getSupportFragmentManager().beginTransaction()
//                    .add(R.id.map, mapFragment, "MapFragment").commit();
//            mapFragment.getMapAsync(this);
//        }
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        map.addMarker(new MarkerOptions().position(location));
//        map.moveCamera(CameraUpdateFactory.newLatLng(location));
//        map.moveCamera(CameraUpdateFactory.zoomTo(zoom));
//    }
//
//    public void removeMapFragment() {
//        if (mapFragment != null) {
//            ((FragmentAbstractActivity) context).getSupportFragmentManager().beginTransaction()
//                    .remove(mapFragment).commitAllowingStateLoss();
//            mapFragment = null;
//        }
//    }
//}
