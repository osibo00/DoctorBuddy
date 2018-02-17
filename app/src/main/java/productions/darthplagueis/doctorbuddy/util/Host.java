package productions.darthplagueis.doctorbuddy.util;


public enum Host {
    BetterDoctorAPI("https://api.betterdoctor.com/");

    private final String url;

    Host(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
