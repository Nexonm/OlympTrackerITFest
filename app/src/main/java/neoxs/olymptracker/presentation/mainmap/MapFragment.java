package neoxs.olymptracker.presentation.mainmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.FilteringMode;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import neoxs.olymptracker.R;
import neoxs.olymptracker.api.APIConfig;
import neoxs.olymptracker.app.AppStart;

public class MapFragment extends Fragment {
    private MapView mapview;

    static Context context;

    private final static Point START_LOCATION;
    private final static Point PEKIN_LOCATION;

    static{
        START_LOCATION = new Point(56.837650, 60.594528);
        PEKIN_LOCATION = new Point(39.9075, 116.39723);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        //set and init mapkit_api before creating view with map
        context = this.getContext();
        AppStart.getInstance().initMap();
        return inflater.inflate(
                R.layout.fragment_main_map,
                container,
                false);
    }

    public static Context getContextMF(){
        return context;
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View view){

        mapview = (MapView)view.findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(PEKIN_LOCATION, 13.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 3),
                null);

        mapview.getMap().getMapObjects().addPlacemark(PEKIN_LOCATION);
        findLocation();
    }

    private void findLocation(){
        MapKit mapKit = MapKitFactory.getInstance();
        mapKit.createLocationManager().subscribeForLocationUpdates(0,0, 0, true, FilteringMode.ON, new LocationListener() {
            @Override
            public void onLocationUpdated(@NonNull Location location) {
                Log.d("TagCheck", "LocationUpdated " + location.getPosition().getLongitude());
                Log.d("TagCheck", "LocationUpdated " + location.getPosition().getLatitude());
                mapview.getMap().getMapObjects().addPlacemark(new Point(
                        location.getPosition().getLatitude(), location.getPosition().getLongitude() ));
            }

            @Override
            public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapview.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mapview.destroyDrawingCache();
    }

}
