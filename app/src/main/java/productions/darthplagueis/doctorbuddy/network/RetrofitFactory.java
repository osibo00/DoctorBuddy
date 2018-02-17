package productions.darthplagueis.doctorbuddy.network;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import productions.darthplagueis.doctorbuddy.abstractclasses.AbstractRetrofitFactory;
import productions.darthplagueis.doctorbuddy.model.Doctor;
import productions.darthplagueis.doctorbuddy.model.ModelResponse;
import productions.darthplagueis.doctorbuddy.util.Host;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static productions.darthplagueis.doctorbuddy.BuildConfig.API_KEY;


public class RetrofitFactory extends AbstractRetrofitFactory {

    public static final String TAG = "RetrofitFactory";
    private static RetrofitFactory retrofitFactory;

    private DoctorNetworkListener doctorNetworkListener = null;

    @NonNull
    public static RetrofitFactory getInstance() {
        if (retrofitFactory == null) {
            retrofitFactory = new RetrofitFactory();
        }
        return retrofitFactory;
    }

    private RetrofitFactory(){}

    public void setDoctorNetworkListener (@NonNull DoctorNetworkListener doctorNetworkListener) {
        this.doctorNetworkListener = doctorNetworkListener;
    }

    public void getDoctorsUsingLocation(String location) {
        BetterDoctorService service = buildRetrofit().create(BetterDoctorService.class);
        Call<ModelResponse> responseCall =
                service.getModelResponse("", location + ",25", location,"0","20", API_KEY);
        responseCall.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.isSuccessful());
                    List<Doctor> doctorList = response.body().getData();
                    doctorNetworkListener.doctorCallBack(doctorList);
                    Log.d(TAG, "onResponse: listSize=" + doctorList.size());
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                doctorNetworkListener.onErrorCallBack(t);
            }
        });

    }

    @Override
    public String getHostUrl() {
        return Host.BetterDoctorAPI.getUrl();
    }

    public interface DoctorNetworkListener {
        void doctorCallBack(List<Doctor> responseList);
        void onErrorCallBack(Throwable t);
    }
}
