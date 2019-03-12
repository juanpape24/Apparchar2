package com.apparchar.apparchar.Presentador;


import android.content.Context;
import android.widget.Toast;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractClient;

import com.apparchar.apparchar.Modelo.UserClient;
import com.apparchar.apparchar.VO.ClienteVO;
import com.apparchar.apparchar.Vista.Cliente;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class RegistrarPresenter implements ContractClient.Presenter, OnLoopjCompleted {
    UserClient cliente;
    Context context;
    RequestParams params;

    private ContractClient.View vista;


    /**
     * HOST
     */

    public RegistrarPresenter(ContractClient.View vista) {
        this.vista = vista;
        cliente = new UserClient();
        this.context=context;
    }

    @Override
    public void enviar(String nombre, String apellido, String edad, String correo, String cel, String user, String pass, String pass2) throws JSONException {
        if (nombre.equals("") || apellido.equals("") || edad.equals("") || correo.equals("") || cel.equals("") || user.equals("") || pass.equals("") || pass2.equals("")) {
            vista.showResult("Llene todos los campos");
        } else {
            if (!pass.equals(pass2)) {
                vista.showResult("Las contraseñas no coinciden");
            } else {
                cliente.setNombre(nombre);
                cliente.setEdad(Integer.parseInt(edad));
                cliente.setCorreo(correo);
                cliente.setTelefono(cel);
                cliente.setUsuario(user);
                cliente.setContrasenia(pass);
                cliente.setId((int) (Math.random()*99999+1));
                vista.swap();
                ClienteVO client=new ClienteVO(cliente.getId(),cliente.getNombre(),cliente.getEdad(),cliente.getCorreo(),cliente.getTelefono(),cliente.getUsuario(),cliente.getContrasenia());
                params=new RequestParams();
                JSONObject x=new JSONObject();

                x.putOpt("cliente",client);
                String envio=x.toString();
                params.put("insertar","pailas");
                params.put("k1",envio);
                String nameServlet = "SERVRegister";


                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet,(Context)vista,this);
                loopjTask.executeLoopjCall();
                vista.showResult("Registro op: ");
            }

        }
    }


    @Override
    public void taskCompleted(String results) {
        vista.showResult("Registro op: "+results);
    }
}

