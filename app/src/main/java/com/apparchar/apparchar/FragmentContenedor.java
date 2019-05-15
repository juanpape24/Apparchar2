package com.apparchar.apparchar;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentContenedor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentContenedor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentContenedor extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private OnFragmentInteractionListener mListener;
    private AppBarLayout appBar;
    private View vista;
    private TabLayout pestanas;
    private ViewPager viewPager;

    public FragmentContenedor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentContenedor.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentContenedor newInstance(String param1, String param2) {
        FragmentContenedor fragment = new FragmentContenedor();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_fragment_contenedor, container, false);
        if (Utilidades.rotacion == 0) {
            View parent = (View) container.getParent();
            if (appBar == null) {
                appBar = parent.findViewById(R.id.appbar);
                pestanas = new TabLayout(getActivity());
                pestanas.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
                appBar.addView(pestanas);
                viewPager = vista.findViewById(R.id.viewpagerInformacion);
                llenarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestanas.setupWithViewPager(viewPager);
            }
            pestanas.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            Utilidades.rotacion=1;
        }
        return vista;
    }

    private void llenarViewPager(ViewPager viewPager) {
        AdapterSecciones adapterSecciones = new AdapterSecciones(getFragmentManager());
        adapterSecciones.addFragment(new FragmentCategoria(), "Categorias");
        adapterSecciones.addFragment(new FragmentEventos(), "Eventos");
        adapterSecciones.addFragment(new FragmentRealTime(), "Mapa");
        viewPager.setAdapter(adapterSecciones);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Utilidades.rotacion == 0){
            appBar.removeView(pestanas);
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
