package productions.darthplagueis.doctorbuddy.abstractclasses;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public abstract class AbstractRetrofitFactory {

    private Retrofit retrofit;

    public abstract String getHostUrl();

    @NonNull
    protected Retrofit buildRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getHostUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    @NonNull
    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .writeTimeout(getTimeoutLength(), TimeUnit.SECONDS)
                .readTimeout(getTimeoutLength(), TimeUnit.SECONDS)
                .connectTimeout(getTimeoutLength(), TimeUnit.SECONDS)
                .build();
    }

    public int getTimeoutLength() {
        return 10;
    }
}
