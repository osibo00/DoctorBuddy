package productions.darthplagueis.doctorbuddy.model;

import java.util.List;


public class ModelResponse {

    private MetaDataDoctor meta;
    private List<Doctor> data;

    public MetaDataDoctor getMeta() {
        return meta;
    }

    public List<Doctor> getData() {
        return data;
    }
}
