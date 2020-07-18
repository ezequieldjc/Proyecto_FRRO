package Entities.Persona;

import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;

import java.util.ArrayList;

public class PersonaPerfil {

    private int id;
    private String codigo;
    private String name;
    private String desc;
    private PersonaEmpleado owner;
    private PersonaEmpleado backup;
    private ArrayList<SistemaAccion> acciones;
    private ArrayList<SistemaBoton> botones;

    public PersonaPerfil(){

    }

    public PersonaPerfil(int id) {
        this.id = id;
    }

    public PersonaPerfil(String codigo) {
        this.codigo = codigo;
    }

    public PersonaPerfil(int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup) {
        this.id = id;
        this.codigo = codigo;
        this.name = name;
        this.desc = desc;
        this.owner = owner;
        this.backup = backup;
    }

    public PersonaPerfil(int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup, ArrayList<SistemaAccion> acciones, ArrayList<SistemaBoton> botones) {
        this.id = id;
        this.codigo = codigo;
        this.name = name;
        this.desc = desc;
        this.owner = owner;
        this.backup = backup;
        this.acciones = acciones;
        this.botones = botones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public PersonaEmpleado getOwner() {
        return owner;
    }

    public void setOwner(PersonaEmpleado owner) {
        this.owner = owner;
    }

    public PersonaEmpleado getBackup() {
        return backup;
    }

    public void setBackup(PersonaEmpleado backup) {
        this.backup = backup;
    }

    public ArrayList<SistemaAccion> getAcciones() {
        return acciones;
    }

    public void setAcciones(ArrayList<SistemaAccion> acciones) {
        this.acciones = acciones;
    }

    public ArrayList<SistemaBoton> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<SistemaBoton> botones) {
        this.botones = botones;
    }
}
