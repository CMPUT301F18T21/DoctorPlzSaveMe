package com.erikligai.doctorplzsaveme;

import android.os.AsyncTask;
import android.util.Log;

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

    // TODO we need a function which adds problems to elastic search
    public static class AddProblemsTask extends AsyncTask<Problem, Void, Void> {

        @Override
        protected Void doInBackground(Problem... problems) {
            verifySettings();

            for (Problem problem : problems) {
                Index index = new Index.Builder(problem).index("cmput301f18t21test").type("problem").build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        problem.setId(result.getId());
                        Log.d("es",result.getId());
                    }
                    else {
                        Log.i("Error", "Elasticsearch was not able to add the problem");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the problems");
                }

            }
            return null;
        }
    }

    // TODO we need a function which gets problems from elastic search
    public static class GetProblemsTask extends AsyncTask<String, Void, ArrayList<Problem>> {
        @Override
        protected ArrayList<Problem> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Problem> problems = new ArrayList<Problem>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "";

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t21test")
                    .addType("problem")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<Problem> foundProblems = result.getSourceAsObjectList(Problem.class);
                    problems.addAll(foundProblems);
                }
                else {
                    Log.i("Error", "The search query failed to find any problems that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return problems;
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