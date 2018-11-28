package com.erikligai.doctorplzsaveme;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.Log;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by romansky on 10/20/16.
 */
public class ElasticsearchProblemController {
    private static JestDroidClient client;

    public static class SetPatientTask extends AsyncTask<Patient, Void, Void> {
        @Override
        protected Void doInBackground(Patient... patient) {
            verifySettings();
            Index index = new Index.Builder(patient[0])
                    .index("cmput301f18t21test")
                    .type("Patient")
                    .id(patient[0].getID())
                    .build();
            try {
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {

                } else {
                    Log.i("Error", "SetPatientTask: The search query failed to find any problems that matched");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return null;
        }
    }

    public static class GetPatientTask extends AsyncTask<String, Void, Patient> {
        @Override
        protected Patient doInBackground(String... userid) {
            verifySettings();
            Patient patient = null;
            String query = "{\n  \"query\": {\n    \"terms\": {\n      \"_id\": \""+userid[0]+ "\" \n    }\n  }\n}";
            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t21test")
                    .addType("Patient")
                    .build();

            try {
                SearchResult result = client.execute(search);
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

    public static class CheckIfPatientIDExistsTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... userid) {
            verifySettings();
            Patient patient = null;
            String query = "{\n  \"query\": {\n    \"terms\": {\n      \"_id\": \""+userid[0]+ "\" \n    }\n  }\n}";
            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t21test")
                    .addType("Patient")
                    .build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    return true;
                } else {
                    Log.i("Error", "CheckIfPatientIDExistsTask: The search query failed to find a patient that matched");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return false;
        }
    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}