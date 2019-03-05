package com.apparchar.apparchar.Presentador;


import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.Modelo.MyATaskCliente;
import com.apparchar.apparchar.Modelo.Token;
import com.apparchar.apparchar.Modelo.UserClient;

import java.util.concurrent.ExecutionException;


public class RegistrarPresenter implements ContractClient.Presenter {
    UserClient cliente;
    private ContractClient.View vista;


    /**
     * HOST
     */

    public RegistrarPresenter(ContractClient.View vista) {
        this.vista = vista;
        cliente = new UserClient();
    }

    @Override
    public void enviar(String nombre, String apellido, String edad, String correo, String cel, String user, String pass, String pass2) {
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
                String resultado="s";
                String sql="insert into cliente values ("+cliente.getId()+",'"+cliente.getNombre()+"',"+cliente.getEdad()+",'"+cliente.getCorreo()+"','"+cliente.getTelefono()+"','"+cliente.getUsuario()+"','"+cliente.getContrasenia()+"')";
                Token token=new Token("generico","update",new String(sql));
                MyATaskCliente myCliente=new MyATaskCliente(token);
                try {
                    resultado= myCliente.execute(resultado).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                vista.showResult("Registro op: "+resultado);
            }

        }
    }



}

