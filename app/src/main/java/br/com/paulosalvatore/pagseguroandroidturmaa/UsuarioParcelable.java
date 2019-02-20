package br.com.paulosalvatore.pagseguroandroidturmaa;

import android.os.Parcel;
import android.os.Parcelable;

public class UsuarioParcelable implements Parcelable {
    public static final Creator<UsuarioParcelable> CREATOR = new Creator<UsuarioParcelable>() {
        @Override
        public UsuarioParcelable createFromParcel(Parcel in) {
            return new UsuarioParcelable(in);
        }

        @Override
        public UsuarioParcelable[] newArray(int size) {
            return new UsuarioParcelable[size];
        }
    };
    private String nome;
    private String sobrenome;

    public UsuarioParcelable(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    protected UsuarioParcelable(Parcel in) {
        nome = in.readString();
        sobrenome = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(sobrenome);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
