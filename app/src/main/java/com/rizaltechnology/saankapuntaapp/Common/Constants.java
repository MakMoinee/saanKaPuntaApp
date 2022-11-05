package com.rizaltechnology.saankapuntaapp.Common;

import com.rizaltechnology.saankapuntaapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final String storageBucket = "gs://saankapuntaapp.appspot.com";
    public static final String firebaseStore = "https://firebasestorage.googleapis.com/v0/b/saankapuntaapp.appspot.com/o/";
    public static final String buildingFileFolder = "Dr. Josefina Estolas Building,Dr. Lydia M. Profeta Building,Main Academic Building,Old Building,Research And Development Building,Sen. Nepatali A. Gonzales Academic Hall,Wellness And Health Building";
    public static final String buildingPath = "Buildings/";
    public static final String postersPath = "Posters/";
    public static final String virtualGuidePath = "Virtual Guides/";

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

        buildingMaps.put("Main Library", "Sen. Nepatali A. Gonzales Academic Hall");
        buildingMaps.put("Information Technology Library", "Sen. Nepatali A. Gonzales Academic Hall");
        buildingMaps.put("Information Technology Department", "Sen. Nepatali A. Gonzales Academic Hall");
        buildingMaps.put("Laboratory High School Faculty", "Sen. Nepatali A. Gonzales Academic Hall");
        buildingMaps.put("College of Education Department Head Office", "Sen. Nepatali A. Gonzales Academic Hall");
        buildingMaps.put("College of Education Faculty Office", "Sen. Nepatali A. Gonzales Academic Hall");
        buildingMaps.put("Senior High School Faculty Office", "Sen. Nepatali A. Gonzales Academic Hall");

//        buildingMaps.put(BUILDING_CODES_WAH, "Wellness And Health Building"); College of Education Office

        return buildingMaps;
    }

    public static Map<String, Integer> getLocationsMap() {
        Map<String, Integer> buildingMaps = new HashMap<>();
        buildingMaps.put("Office of the Vice-President of Academic Affairs", R.string.locations_office_of_the_vice_president_of_academic_affairs);
        buildingMaps.put("College of Education Faculty Office", R.string.locations_college_of_education_faculty_office);
        return buildingMaps;
    }

    public static Map<String, Integer> getDirectionsMap() {
        Map<String, Integer> buildingMaps = new HashMap<>();
        buildingMaps.put("Office of the Vice-President of Academic Affairs", R.string.directions_office_of_the_vice_president_of_academic_affairs);
        buildingMaps.put("College of Education Faculty Office", R.string.directions_college_of_education_faculty_office);
        return buildingMaps;
    }

    public static Map<String, String> getFloorMap() {
        Map<String, String> floorMaps = new HashMap<>();
        floorMaps.put("Office of the Vice-President of Academic Affairs", "1st floor - Profeta.jpg");
        floorMaps.put("College of Education Faculty Office", "8th Floor - SNAGAH.jpg");

        return floorMaps;
    }

    public static Map<String,String> getDirectoryMap(){
        Map<String, String> directoryMap = new HashMap<>();
        directoryMap.put("Wellness And Health Building", "https://github.com/MakMoinee/saanKaPuntaApp/blob/main/directories/Dr.%20Lydia%20M.%20Profeta%20Bldg%20Directory.png?raw=true");
        directoryMap.put("Dr. Lydia M. Profeta Building", "https://github.com/MakMoinee/saanKaPuntaApp/blob/main/directories/Dr.%20Lydia%20M.%20Profeta%20Bldg%20Directory.png?raw=true");
        directoryMap.put("Sen. Nepatali A. Gonzales Academic Hall","https://github.com/MakMoinee/saanKaPuntaApp/blob/main/directories/SNAGAH%20directory.png?raw=true");
        return directoryMap;
    }

}
