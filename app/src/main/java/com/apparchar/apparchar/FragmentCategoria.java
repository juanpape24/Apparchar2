package com.apparchar.apparchar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractListaCategoria;
import com.apparchar.apparchar.Presentador.CategoriaPresenter;
import com.apparchar.apparchar.Vista.LoginActivity;

import org.checkerframework.framework.qual.Bottom;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCategoria.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCategoria#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategoria extends Fragment implements ContractListaCategoria.viewCategoria {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv;
    private AdapterRecycler adapter;
    private ContractListaCategoria.presenterCategoria presenter;
    //private String idUser = "";
    private ArrayList<String> categoria;
    private Bottom boton1,boton2;
    View vista;

    private OnFragmentInteractionListener mListener;

    public FragmentCategoria() {
        // Required empty public constructor
    }
   /* public void setIdUser(String idUser){
        this.idUser=idUser;
    }
    public String getIdUser(){
        return idUser;
    }
*/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCategoria.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCategoria newInstance(String param1, String param2) {
        FragmentCategoria fragment = new FragmentCategoria();
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
        vista=inflater.inflate(R.layout.fragment_fragment_categoria, container, false);
    // Inflate the layout for this fragment
        return vista;
    }
    @Override
    public void onResume(){
        super.onResume();
        presenter = new CategoriaPresenter(this,getActivity());
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
    public void datos(ArrayList<String> categoria) {
        rv = vista.findViewById(R.id.recycler2);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new AdapterRecycler(getActivity().getApplicationContext(), categoria, LoginActivity.idUser);
        showResult(LoginActivity.idUser);
        rv.setAdapter(adapter);
        this.categoria = categoria;

    }

    @Override
    public void showResult(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
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
    public void refreshC(View view){
        presenter=new CategoriaPresenter(this,getActivity());
    }
    public void finishedC(View view){
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }
}