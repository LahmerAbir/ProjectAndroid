package com.example.tounsia.English;

public class DemandeHelper2 {

    String Violence , Personne ,Adresse ,Histoire , Name, Date , Tel ;

    public DemandeHelper2() {

    }


    public DemandeHelper2(String violence, String personne, String histoire, String adresse , String name, String date, String tel) {
        Violence = violence;
        Personne = personne;
        Adresse = adresse ;
        Histoire= histoire;
        Date = date;
        Name = name;
        Tel = tel;
    }


    public String getViolence() {
        return Violence;
    }

    public String getPersonne() {
        return Personne;
    }
    public String getHistoire() {
        return Histoire;
    }


    public String getAdress() {
        return Adresse;
    }
    public String getDate() {
        return Date;
    }
    public String getName() { return Name; }
    public String getTel() { return Tel; }





}
