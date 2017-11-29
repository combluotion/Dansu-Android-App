package com.example.elamoreslomasgrande.volley1.Navigation.Guardados.Guardadas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.elamoreslomasgrande.volley1.Ofertas.Oferta;
import com.example.elamoreslomasgrande.volley1.Ofertas.OfertasAdapter;
import com.example.elamoreslomasgrande.volley1.PabloAPI;
import com.example.elamoreslomasgrande.volley1.R;
import com.example.elamoreslomasgrande.volley1.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Guardadas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Guardadas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Guardadas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private OfertasAdapter adapter;
    List<Oferta> ofertas;
    private SwipeRefreshLayout swipeContainer;
    public static final String LOG_TAG = OfertasAdapter.class.getName();

    private OnFragmentInteractionListener mListener;

    public Guardadas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Guardadas.
     */
    // TODO: Rename and change types and number of parameters
    public static Guardadas newInstance(String param1, String param2) {
        Guardadas fragment = new Guardadas();
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
        // Inflate the layout for this fragment
        FrameLayout Lay =(FrameLayout) inflater.inflate(R.layout.fragment_guardadas, container, false);
        swipeContainer = (SwipeRefreshLayout) Lay.findViewById(R.id.main_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        loadJSON();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(getContext(), "¡Baila!", Toast.LENGTH_SHORT).show();
            }
        });
        return Lay;}


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    } */

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

        public void loadJSON(){
            RetrofitService retrofitService = RetrofitService.getInstance();
            PabloAPI api = retrofitService.getApiProxyServer();
            Call<ArrayList<Oferta>> call = api.getGuardadas(1);
            call.enqueue(new Callback<ArrayList<Oferta>>() {
                @Override
                public void onResponse(Call<ArrayList<Oferta>> call, Response<ArrayList<Oferta>> response) {
                    Log.d("traza", "por aqui");
                    Log.d("traza", response.body().toString());
                    ofertas = response.body();
                    recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
                    recyclerView.setAdapter(new OfertasAdapter(getActivity().getApplicationContext(), ofertas));
                    recyclerView.smoothScrollToPosition(0);

                }

                @Override
                public void onFailure(Call<ArrayList<Oferta>> call, Throwable t) {
                    Log.d("traza","por alla");
                    Log.d("traza", t.toString());
                }
            });
            if (swipeContainer.isRefreshing()){
                swipeContainer.setRefreshing(false);
            }
        };




}
