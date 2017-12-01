package com.example.elamoreslomasgrande.volley1.Ofertas;

import android.widget.CheckBox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by elamoreslomasgrande on 13/11/2017.
 */

public class Oferta implements Serializable {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("activa")
    @Expose
    int activa;
    @SerializedName("fotoportada")
    @Expose
    String fotoportada;
    @SerializedName("titulo")
    @Expose
    String titulo;
    @SerializedName("empresa")
    @Expose
    String empresa;
    @SerializedName("descripcion")
    @Expose
    String descripcion;
    @SerializedName("tags")
    @Expose
    String tags;
    @SerializedName("jornada")
    @Expose
    String jornada;
    @SerializedName("renumerado")
    @Expose
    String renumerado;
    @SerializedName("salario")
    @Expose
    String salario;
    @SerializedName("horario")
    @Expose
    String horario;
    @SerializedName("fecha")
    @Expose
    String fecha;
    @SerializedName("fechalimite")
    @Expose
    String fechalimite;
    @SerializedName("id_estilo")
    @Expose
    String id_estilo;
    @SerializedName("direccion")
    @Expose
    String direccion;
    @SerializedName("latitud")
    @Expose
    String latitud;
    @SerializedName("longitud")
    @Expose
    String longitud;
  /*  @SerializedName("checkbox")
    @Expose
    CheckBox guardado; */


    public Oferta(int id, int activa, String fotoportada, String titulo, String empresa, String descripcion, String tags, String jornada, String renumerado, String salario, String horario, String fecha, String fechalimite, String id_estilo, String direccion, String latitud, String longitud) {
        this.id = id;
        this.activa = activa;
        this.fotoportada = fotoportada;
        this.titulo = titulo;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.tags = tags;
        this.jornada = jornada;
        this.renumerado = renumerado;
        this.salario = salario;
        this.horario = horario;
        this.fecha = fecha;
        this.fechalimite = fechalimite;
        this.id_estilo = id_estilo;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
      //  this.guardado = guardado;
    }

    public Oferta() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public String getFotoportada() {
        return fotoportada;
    }

    public void setFotoportada(String fotoportada) {
        this.fotoportada = fotoportada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getRenumerado() {
        return renumerado;
    }

    public void setRenumerado(String renumerado) {
        this.renumerado = renumerado;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(String fechalimite) {
        this.fechalimite = fechalimite;
    }

    public String getId_estilo() {
        return id_estilo;
    }

    public void setId_estilo(String id_estilo) {
        this.id_estilo = id_estilo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

  //  public CheckBox getGuardado() {
  //      return guardado;
 //   }

   // public void setGuardado(CheckBox guardado) {this.guardado = guardado;}

    @Override
    public String toString() {
        return "Oferta{" +
                "id=" + id +
                ", activa=" + activa +
                ", fotoportada='" + fotoportada + '\'' +
                ", titulo='" + titulo + '\'' +
                ", empresa='" + empresa + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tags='" + tags + '\'' +
                ", jornada='" + jornada + '\'' +
                ", renumerado='" + renumerado + '\'' +
                ", salario='" + salario + '\'' +
                ", horario='" + horario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", fechalimite='" + fechalimite + '\'' +
                ", id_estilo='" + id_estilo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
              //  ", guardado='" + guardado + '\'' +
                '}';
    }
}
