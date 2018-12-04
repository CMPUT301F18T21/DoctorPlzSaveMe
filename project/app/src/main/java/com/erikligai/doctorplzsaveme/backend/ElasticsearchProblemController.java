package com.erikligai.doctorplzsaveme.backend;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;
import android.util.Log;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;

import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * This class is responsible for tasks that communicate with the DB using ES
 */
public class ElasticsearchProblemController {

    private static JestDroidClient client;

    private static String server = "http://cmput301.softwareprocess.es:8080/";

    /**
     * upload patient to DB Task
     */
    public static class SetPatientTask extends AsyncTask<Patient, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Patient... patient) {

            Index index = new Index.Builder(patient[0])
                    .index("cmput301f18t21test")
                    .type("Patient")
                    .id(patient[0].getID())
                    .build();
            try {
                verifySettings();
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    return true;
                } else {
                    Log.i("Error", "SetPatientTask: The search query failed to upload patient");
                    return false;
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                return false;
            }
        }
    }

    /**
     *  get patient from db with a given patient ID Task
     */
    public static class GetPatientTask extends AsyncTask<String, Void, Patient> {
        @Override
        protected Patient doInBackground(String... userid) {
            Patient patient = null;
            Get get = new Get.Builder("cmput301f18t21test", userid[0]).type("Patient").build();

            try {
                verifySettings();
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    patient = result.getSourceAsObject(Patient.class); // TODO: FIX
                } else {
                    Log.i("Error", "GetPatientTask: The search query failed to find a patient that matched");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return patient;
        }
    }

    /**
     * check if patient id exists on the DB Task
     */
    public static class CheckIfPatientIDExistsTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... userid) {
            Patient patient = null;
            Get get = new Get.Builder("cmput301f18t21test", userid[0]).type("Patient").build();

            try {
                verifySettings();
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    return 0;
                } else {
                    Log.i("Error", "CheckIfPatientIDExistsTask: The search query failed to find a patient that matched");
                    return 1;
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                return -1;
            }
        }
    }

    /**
     * add patient id to list of assigned patients to a given cp id
     */
    public static class AssignPatientToCPTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                verifySettings();
                PatientsWrapper p = null;
                Get get = new Get.Builder("cmput301f18t21test", params[0]).type("PatientsWrapper").build();
                try {
                    JestResult result = client.execute(get);
                    if (result.isSucceeded()){
                        p = result.getSourceAsObject(PatientsWrapper.class);
                        p.getPatients().add(params[1]);
                    }
                    else {
                        Log.i("Error", "The search query failed to find any patients that matched");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }
               if (p == null) {Log.d("p is null: ",Boolean.toString(p == null)); return null;}


                Index index = new Index.Builder(p)
                        .index("cmput301f18t21test")
                        .type("PatientsWrapper")
                        .id(params[0])
                        .build();
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {

                } else {
                    Log.i("Error", "AssignPatientToCPTask: fail");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return null;
        }
    }

    /**
     * get all ids of patients assigned to given cp id
     */
    public static class GetCPPatientsTask extends AsyncTask<String, Void, PatientsWrapper> {
        @Override
        protected PatientsWrapper doInBackground(String... cp_id) {
            PatientsWrapper p = null;
            Get get = new Get.Builder("cmput301f18t21test", cp_id[0]).type("PatientsWrapper").build();
            try {
                verifySettings();
                JestResult result = client.execute(get);
                if (result.isSucceeded()){
                    p = result.getSourceAsObject(PatientsWrapper.class);
                }
                else {
                    Log.i("Error", "The search query failed to find any patients that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            Log.d("p is null: ",Boolean.toString(p == null));
            return p;
        }
    }

    /**
     * check if a cp id exists on the db
     */
    public static class CheckIfCPExistsTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... cp_id) {
            Get get = new Get.Builder("cmput301f18t21test", cp_id[0]).type("PatientsWrapper").build();
            try {
                verifySettings();
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    return 0;
                } else {
                    Log.i("Error", "CheckIfCPIDExistsTask: The search query failed to find a patient that matched");
                    return 1;
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                return -1;
            }
        }
    }

    /**
     * add a cp to the DB
     */
    public static class AddCPTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                verifySettings();
                ArrayList<String> temp = new ArrayList<String>();
                // temp.add("tiganov"); // DEBUG
                PatientsWrapper p = new PatientsWrapper(temp);
                Index index = new Index.Builder(p)
                        .index("cmput301f18t21test")
                        .type("PatientsWrapper")
                        .id(params[0])
                        .build();
                DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                } else {
                    Log.i("Error", "AssignPatientToCPTask: fail");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return null;
        }
    }

    /**
     * makes sure we have stuff we need to use ES
     * @throws NetworkErrorException if can't connect to ES
     */
    public static void verifySettings() throws NetworkErrorException{
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder(server);
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
            if (client == null) { throw new NetworkErrorException(); }
        }
    }
}