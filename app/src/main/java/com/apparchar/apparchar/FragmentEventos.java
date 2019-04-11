package com.apparchar.apparchar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Presentador.ListaEventoPresenter;
import com.apparchar.apparchar.Vista.LoginActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentEventos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentEventos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventos extends Fragment implements ContractListaEvento.ViewEvento {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "LISTA EVENTO";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    GridLayoutManager gridLayoutManager;
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;
    private ContractListaEvento.EventoPresenter presenter;
    private String idUser = "", cat = "";
    private TextView event;
    private ArrayList<EventoM> eventos;
    private View vista;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentEventos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEventos newInstance(String param1, String param2) {
        FragmentEventos fragment = new FragmentEventos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void setIdUser(String idUser){
        this.idUser=idUser;
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
        vista= inflater.inflate(R.layout.fragment_fragment_eventos, container, false);
        event=vista.findViewById(R.id.textEvento);
        return vista;
    }
    @Override
    public void onResume(){
        super.onResume();
        presenter = new ListaEventoPresenter(this,getContext());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void dato(List<EventoM> lista) {
        rv = vista.findViewById(R.id.recycler);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter = new RecyclerViewAdapter(getActivity(), lista, idUser);
        rv.setAdapter(adapter);
        eventos = (ArrayList<EventoM>) lista;

    }

    @Override
    public void showResult(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_LONG).show();
    }
    public void actualizarE(View view){
        presenter = new ListaEventoPresenter(this,getActivity());
    }
    public void cSesionE(View view) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
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
}
