package com.example.emergency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.IBinder;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.emergency.databinding.ActivityHospitalMapBinding;

import java.util.List;
import java.util.Set;

public class HospitalMapActivity extends FragmentActivity implements OnMapReadyCallback, Api.Client, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {


    private GoogleMap mMap;
    private ActivityHospitalMapBinding binding;
    Api.Client mClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHospitalMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(100)


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isConnecting() {
        return false;
    }

    @Override
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {

    }

    @Override
    public boolean requiresSignIn() {
        return false;
    }

    @Override
    public void onUserSignOut(BaseGmsClient.SignOutCallbacks signOutCallbacks) {

    }

    @Override
    public boolean requiresAccount() {
        return false;
    }

    @Override
    public boolean requiresGooglePlayServices() {
        return false;
    }

    @Override
    public boolean providesSignIn() {
        return false;
    }

    @Override
    public Intent getSignInIntent() {
        return null;
    }

    @Nullable
    @Override
    public IBinder getServiceBrokerBinder() {
        return null;
    }

    @Override
    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override
    public String getEndpointPackageName() {
        return null;
    }

    @Override
    public int getMinApkVersion() {
        return 0;
    }

    @Override
    public Feature[] getAvailableFeatures() {
        return new Feature[0];
    }

    @NonNull
    @Override
    public Set<Scope> getScopesForConnectionlessNonSignIn() {
        return null;
    }
}