package com.apparchar.apparchar.Presentador;

import com.apparchar.apparchar.Contract.ContractEmpresa;
import com.apparchar.apparchar.Modelo.UserEmpresa;

import java.util.concurrent.ExecutionException;

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
                empresa.setUbicacion(direccion);
                empresa.setTelefono(cel);
                empresa.setUsuario(user);
                empresa.setContrasenia(pass);
                int x= (int) (Math.random()*9999+1);
                empresa.setNitEmpresa(String.valueOf(x));
                empresa.setDescripcion("sasas");
                vista.cambiar();

                String resultado="s";
                String sql="insert into empresa values ('"+empresa.getNitEmpresa()+"','"+empresa.getNombre()+"','"+empresa.getUbicacion()+"','"+empresa.getTelefono()+"','"+empresa.getCorreo()+"','"+empresa.getDescripcion()+"','"+empresa.getUsuario()+"','"+empresa.getContrasenia()+"')";
                //Token token=new Token("generico","update",new String(sql));
                //MyATaskCliente myCliente=new MyATaskCliente(token);
                /*try {
                    resultado= myCliente.execute(resultado).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                vista.showResultE("Registro realizado: "+resultado);
            }

        }
    }
}
