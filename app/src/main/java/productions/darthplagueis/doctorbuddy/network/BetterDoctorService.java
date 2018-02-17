package productions.darthplagueis.doctorbuddy.network;


import productions.darthplagueis.doctorbuddy.model.ModelResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BetterDoctorService {

    String doctorEndpoint = "2016-03-01/doctors";

    @GET(doctorEndpoint)
    Call<ModelResponse> getModelResponse(@Query("query") String query,
                                         @Query("location") String location,
                                         @Query("user_location") String userLocation,
                                         @Query("skip") String skip,
                                         @Query("limit") String limit,
                                         @Query("user_key") String apiKey);
}
