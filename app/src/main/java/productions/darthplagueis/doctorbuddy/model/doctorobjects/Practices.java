package productions.darthplagueis.doctorbuddy.model.doctorobjects;


import java.util.List;

import productions.darthplagueis.doctorbuddy.model.doctorobjects.objects.ContactNumber;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.objects.Languages;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.objects.PracticeMedia;
import productions.darthplagueis.doctorbuddy.model.doctorobjects.objects.VisitAddress;

public class Practices {

    private String location_slug;
    private boolean within_search_area;
    private float distance;
    private double lat;
    private double lon;
    private String uid;
    private String website;
    private boolean accepts_new_patients;
    private List<String> insurance_uids;
    private VisitAddress visit_address;
    private List<String> office_hours;
    private List<ContactNumber> phones;
    private List<Languages> languages;
    private List<PracticeMedia> media;

    public String getLocation_slug() {
        return location_slug;
    }

    public boolean isWithin_search_area() {
        return within_search_area;
    }

    public float getDistance() {
        return distance;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getUid() {
        return uid;
    }

    public String getWebsite() {
        return website;
    }

    public boolean isAccepts_new_patients() {
        return accepts_new_patients;
    }

    public List<String> getInsurance_uids() {
        return insurance_uids;
    }

    public VisitAddress getVisit_address() {
        return visit_address;
    }

    public List<String> getOffice_hours() {
        return office_hours;
    }

    public List<ContactNumber> getPhones() {
        return phones;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public List<PracticeMedia> getMedia() {
        return media;
    }
}
