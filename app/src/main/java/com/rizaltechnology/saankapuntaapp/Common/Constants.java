package com.rizaltechnology.saankapuntaapp.Common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final String storageBucket = "gs://saankapuntaapp.appspot.com";
    public static final String firebaseStore = "https://firebasestorage.googleapis.com/v0/b/saankapuntaapp.appspot.com/o/";
    public static final String buildingFileFolder = "Dr. Josefina Estolas Building,Dr. Lydia M. Profeta Building,Main Academic Building,Old Building,Research And Development Building,Sen. Nepatali A. Gonzales Academic Hall,Wellness And Health Building";
    public static final String buildingPath = "Buildings/";
    public static final String postersPath = "Posters/";

    public static Map<String, String> getBuildingMaps() {
        Map<String, String> buildingMaps = new HashMap<>();

        buildingMaps.put("Faculty Office of the Department of Electrical Engineering", "Dr. Josefina Estolas Building");
        buildingMaps.put("Faculty Office of the Department of Engineering", "Dr. Josefina Estolas Building");
        buildingMaps.put("Dean’s Office – College of Engineering, Architecture and Technology", "Dr. Josefina Estolas Building");
        buildingMaps.put("Alumni Office", "Dr. Josefina Estolas Building");
        buildingMaps.put("ECE Department Office", "Dr. Josefina Estolas Building");

        buildingMaps.put("Management Information Center", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Office of the Vice-President of Academic Affairs", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Office of the Director of the Finance Services", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Cashier Window", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Cashier Staff’s Office", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Office of the VPSS", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Office of the VPDA", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Office the President", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("COA", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Accounting Office", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Office of the Department for Administration Services", "Dr. Lydia M. Profeta Building");
        buildingMaps.put("Department of Instrumentation and Control Engineering", "Dr. Lydia M. Profeta Building");

        buildingMaps.put("Scholarship &amp; Grant Office", "Main Academic Building");
        buildingMaps.put("Center for Students Affairs", "Main Academic Building");
        buildingMaps.put("Registrar", "Main Academic Building");
        buildingMaps.put("DOST Office", "Main Academic Building");
        buildingMaps.put("Records Management Office", "Main Academic Building");
        buildingMaps.put("College of Arts and Science Faculty Office", "Main Academic Building");
        buildingMaps.put("College of Arts and Sciences Reading Center", "Main Academic Building");

//        buildingMaps.put(BUILDING_CODES_O, "Old Building");
//        buildingMaps.put(BUILDING_CODES_RAD, "Research And Development Building");
//        buildingMaps.put(BUILDING_CODES_SBAGAH, "Sen. Nepatali A. Gonzales Academic Hall");
//        buildingMaps.put(BUILDING_CODES_WAH, "Wellness And Health Building");

        return buildingMaps;
    }

}
