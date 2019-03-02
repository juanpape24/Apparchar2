package com.apparchar.apparchar.Presentador;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.Modelo.UserClient;

public class RegistrarPresenter implements ContractClient.Presenter {
    private ContractClient.View vista;
    UserClient cliente;
    public RegistrarPresenter(ContractClient.View vista) {
        this.vista=vista;
        cliente=new UserClient();
    }

    @Override
    public void enviar(String nombre, String apellido, String edad, String correo, String cel, String user, String pass,String pass2) {
        if(nombre.equals("") && apellido.equals("") && edad.equals("") && correo.equals("") && cel.equals("") && user.equals("") && pass.equals("") && pass2.equals("")){
            vista.showResult("Llene todos los campos");
        }else{
            if(!pass.equals(pass2))
            {
                vista.showResult("Las contraseñas no coinciden");
            }else{
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEdad(Integer.parseInt(edad));
                cliente.setCorreo(correo);
                cliente.setCelular(cel);
                cliente.setUser(user);
                cliente.setPass(pass);
                vista.swap();
                vista.showResult("Registro realizado");
            }

        }
    }
}
