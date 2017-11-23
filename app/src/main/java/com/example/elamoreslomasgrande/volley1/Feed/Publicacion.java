package com.example.elamoreslomasgrande.volley1.Feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by elamoreslomasgrande on 23/11/2017.
 */

public class Publicacion implements Serializable {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("id_usuario")
    @Expose
    int id_usuario;

    @SerializedName("archivo")
    @Expose
    String archivo;

    @SerializedName("descripcion")
    @Expose
    String descripcion;

    @SerializedName("tags")
    @Expose
    String tags;

    public Publicacion(int id, int id_usuario, String archivo, String descripcion, String tags) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.archivo = archivo;
        this.descripcion = descripcion;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
}
