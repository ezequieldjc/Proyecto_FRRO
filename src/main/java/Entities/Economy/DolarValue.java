package Entities.Economy;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class DolarValue {

    @SerializedName("d")
    private Timestamp fecha;
    @SerializedName("v")
    private float value;

    public DolarValue(){

    }

    public DolarValue(Timestamp fecha, float value) {
        this.fecha = fecha;
        this.value = value;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
