package com.apparchar.apparchar.Presentador;

import com.apparchar.apparchar.Contract.ContractEmpresa;
import com.apparchar.apparchar.Modelo.UserEmpresa;

public class EmpresaPresenter implements ContractEmpresa.PresenterE {

    ContractEmpresa.ViewE vista;
    UserEmpresa empresa;
    public EmpresaPresenter(ContractEmpresa.ViewE vista){
        this.vista=vista;
        empresa=new UserEmpresa();
    }
    @Override
    public void signUp(String nombre, String correo, String direccion, String cel, String user, String pass,String pass2) {
        if(nombre.equals("") || correo.equals("") || direccion.equals("") || cel.equals("") || user.equals("") || pass.equals("") || pass2.equals("")){
            vista.showResultE("Llene todos los campos");
        }
        else{
            if(!pass.equals(pass2)){
            vista.showResultE("Las contraseñas no coinciden");
            }
            else{
                empresa.setNombre(nombre);
                empresa.setCorreo(correo);
                empresa.setDireccion(direccion);
                empresa.setCelular(cel);
                empresa.setUsuario(user);
                empresa.setContraseña(pass);
                vista.cambiar();
                vista.showResultE(empresa.toString());
            }

        }
    }
}
