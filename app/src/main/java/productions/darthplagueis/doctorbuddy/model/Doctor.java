package productions.darthplagueis.doctorbuddy.model;


import java.util.List;

import productions.darthplagueis.doctorbuddy.model.doctorobjects.DoctorProfile;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.Education;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.Insurances;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.Licenses;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.Practices;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.Ratings;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.Specialties;

public class Doctor {

    private List<Practices> practices;
    private List<Education> educations;
    private DoctorProfile profile;
    private List<Ratings> ratings;
    private List<Insurances> insurances;
    private List<Specialties> specialties;
    private List<Licenses> licenses;
    private String uid;
    private String npi;

    public List<Practices> getPractices() {
        return practices;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public DoctorProfile getProfile() {
        return profile;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public List<Insurances> getInsurances() {
        return insurances;
    }

    public List<Specialties> getSpecialties() {
        return specialties;
    }

    public List<Licenses> getLicenses() {
        return licenses;
    }

    public String getUid() {
        return uid;
    }

    public String getNpi() {
        return npi;
    }
}
