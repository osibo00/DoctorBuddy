package productions.darthplagueis.doctorbuddy.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import productions.darthplagueis.doctorbuddy.R;
import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractFragment;

public class MapViewHolder extends RecyclerView.ViewHolder {

    public TextView locationTextView;
    private SupportMapFragment mapFragment;
    private AbstractFragment abstractFragment;

    public MapViewHolder(View itemView, AbstractFragment abstractFragment) {
        super(itemView);
        locationTextView = (TextView) itemView.findViewById(R.id.locationNameTextView);
        this.abstractFragment = abstractFragment;
    }

    public SupportMapFragment getMapFragmentAndCallBack(OnMapReadyCallback callback) {
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(callback);
        }
        abstractFragment.getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment)
                .commit();
        return mapFragment;
    }

    public void removeMapFragment() {
        if (mapFragment != null) {
            abstractFragment.getChildFragmentManager().beginTransaction().remove(mapFragment)
                    .commitAllowingStateLoss();
            mapFragment = null;
        }
    }
}
