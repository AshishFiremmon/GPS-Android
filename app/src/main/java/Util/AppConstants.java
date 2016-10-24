package Util;

/**
 * Created by firemoonpc_11 on 03-06-2016.
 */
public class AppConstants {

    public static final String GOOGLE_API_KEY = "AIzaSyC0d9OxhCCBaf8AmG932mNx_iwRDLUrdww";

    public static double lat=0;
    public static double longitute=0;
    public static String type;
    public static boolean change=false;


    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (double) (earthRadius * c);

        return dist;
    }

}
