package pe.edu.idat.appfragmentsavanzado1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.idat.appfragmentsavanzado1.Adapters.FloresAdapter;
import pe.edu.idat.appfragmentsavanzado1.Modelo.Flores;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentGaleria.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentGaleria#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGaleria extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private RecyclerView rvDatos;
    private FloresAdapter adapter;
    ArrayList<Flores> vDatos;
    //Declaramos la petición al servicio.
    private RequestQueue mQueue;

    public FragmentGaleria() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentGaleria newInstance(String param1, String param2) {
        FragmentGaleria fragment = new FragmentGaleria();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_galeria, container, false);

        rvDatos = view.findViewById(R.id.rvDatos);
        rvDatos.setLayoutManager(new LinearLayoutManager(
                getActivity()
        ));
        adapter = new FloresAdapter(getActivity());
        rvDatos.setAdapter(adapter);
        vDatos = new ArrayList<Flores>();
        //Instanciamos la cola de peticiones.
        mQueue = Volley.newRequestQueue(getActivity());
        //Llamar al método ConsumirWS
        ConsumirWS();

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
     /*   if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/

    }

    private void ConsumirWS() {
        //Inicializar el URL del servicio web.
        String url = "https://pixabay.com/api/?key=12544769-ce772d6f6df4078b74b23c3cf&q=yellow+flowers&image_type=photo";
        //Instanciar el objeto request para que sea agregado
        // a la cola de requests.
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray =
                                    response.getJSONArray("hits");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject objImagen
                                        = jsonArray.getJSONObject(i);
                                vDatos.add(new Flores(objImagen.getString("user"),
                                        objImagen.getString("webformatURL")));

                            }
                            adapter.agregarElemento(vDatos);

                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                }
        );
        mQueue.add(request);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
