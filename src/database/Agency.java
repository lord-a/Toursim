package Database;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Agency {
    private SimpleStringProperty name;
    private SimpleStringProperty type;
    private SimpleStringProperty place;
    private SimpleIntegerProperty phonenumber;
    private SimpleStringProperty email;
    private SimpleIntegerProperty keyno;



    public Agency(){ //, String type, String place, String phonenumber) {
        this.name = new SimpleStringProperty();
    this.type = new SimpleStringProperty();
       this.place = new SimpleStringProperty();
       this.phonenumber = new SimpleIntegerProperty();
       this.email=new SimpleStringProperty();
       this.keyno=new SimpleIntegerProperty();

   }
//
        public String getEmail() {
            return email.get();
        }
            public void setEmail(String email) {
                this.email.set(email);
            }

    public int getKeyno() {
        return keyno.get();
    }
    public void setKeyno(int keyno) {
        this.keyno.set(keyno);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getPlace() {
        return place.get();
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

    public int getPhonenumber() {
        return phonenumber.get();
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber.set(phonenumber);
    }

    @Override
    public String toString() {
        return
                "Name of an Organization=" + name +
                "Type=" + type +
                "Place=" + place +
                "Phonenumber=" + phonenumber +
                "Email=" + email +
                '}';
    }
}
