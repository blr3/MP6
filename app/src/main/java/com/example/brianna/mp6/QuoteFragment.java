package com.example.brianna.mp6;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuoteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuoteFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    //////////////////////////////////////////////////


    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;
    public final static String API_BASE_URL = "https://favqs.com/api/";
    public final static String API_KEY_PARAM = "quotes";
    public final static String API_KEY = "3a133d43c09fea61fde4f6b6a4437344";
    public List<String> allQuotes = new ArrayList<String>();
    public String username = "cs125";
    public String password = "cs125";

    AsyncHttpClient client;
    String quoteBody = "";
    String author;
    TextView tvQuote;
    TextView tvAuthor;


    public QuoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuoteFragment newInstance(String param1, String param2) {
        QuoteFragment fragment = new QuoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up a queue for our Volley requests
        requestQueue = Volley.newRequestQueue(getContext());
//        client = new AsyncHttpClient();
        getQuotes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvQuote = view.findViewById(R.id.tvQuote);
        tvAuthor = view.findViewById(R.id.tvAuthor);
        //tvQuote.setText(quoteBody);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//    private void getQuotes() {
//        String url = API_BASE_URL + "quotes";
//        RequestParams params = new RequestParams();
//        //params.put(API_KEY_PARAM,API_KEY);
//        client.addHeader(
//                "Authorization",
//                "Basic " + Base64.encodeToString(
//                        (username+":"+password).getBytes(),Base64.NO_WRAP)
//        );
//        client.get(url,new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    // TODO create an object to get quotes with a certain tag
//                    JSONArray quotes = response.getJSONArray("quotes");
//                    for (int i = 0; i < quotes.length(); i++) {
//                        JSONObject quote = quotes.getJSONObject(i);
//                        quoteBody = quote.getString("body");
//                        allQuotes.add(quoteBody);
//                    }
////                    author = quotes.getString("author");
//                } catch (JSONException e) {
//                    logError("failed parsing quote", e, true);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                logError("Failed getting qoute", throwable, true);
//            }
//        });
//    }

//    private void getQuotes() {
//        try {
//            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                    Request.Method.GET, API_BASE_URL + "qotd",
//                    null,
//                    new Response.Listener<JSONArray>() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            Log.d(TAG, response.toString());
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject quote = null;
//                                try {
//                                    quote = response.getJSONObject(i);
//                                    quoteBody = quote.getString("body");
//                                    allQuotes.add(quoteBody);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                    logError("failed parsing quote", e, true);
//                                }
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(final VolleyError error) {
//                    Log.w(TAG, error.toString());
//                    logError("failed parsing quote", error, true);
//                }
//            }) {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
////                    params.put("Content-Type", "application/json; charset=UTF-8");
//                    params.put("token", API_KEY);
//                    return params;
//                }
//            };
//            requestQueue.add(jsonArrayRequest);
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//
//    }

    private void getQuotes() {
        try {
            JsonObjectRequest jsonObject = new JsonObjectRequest(
                    Request.Method.GET, API_BASE_URL + "qotd",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());
                            try {
                                JSONObject quote = response.getJSONObject("quote");
                                String body =  quote.getString("body");
                                tvQuote.setText(body);
                                String author = quote.getString("author");
                                tvAuthor.setText("- " + author);
                                quoteBody = body;
                                //quoteBody = quote.getString("body");
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                    logError("failed parsing quote", error, true);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("Content-Type", "application/json; charset=UTF-8");
                    params.put("token", API_KEY);
                    return params;
                }
            };
            requestQueue.add(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void vollyRequestGetAuthToken()
    {
        RequestQueue queue  = Volley.newRequestQueue(getContext());
        StringRequest request =  new StringRequest(Request.Method.POST  , API_BASE_URL + "quotes", new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {
                Toast.makeText(getContext(), "RESPONSE: " + response, Toast.LENGTH_SHORT ).show();
                System.out.println("RESPONSE:          >>> " + response + "<<<");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "ERROR: " + error, Toast.LENGTH_SHORT ).show();
            }
        }) {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username","cs125");
                params.put("password","cs125");
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "<username>", "<password>").getBytes(), Base64.DEFAULT)));
                params.put("username" , "cs125" );
                params.put("password" , "cs125" );
                return params;
            }
        };
        queue.add(request);
    }

    private void logError(String message, Throwable error, boolean alertUser) {
        Log.e(TAG, message, error);
        if (alertUser) {
            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
        }
    }
}
