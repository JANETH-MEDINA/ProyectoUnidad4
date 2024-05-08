import java.util.Calendar;
public class Fecha {
    static Calendar Hora=Calendar.getInstance();
    static Calendar Fecha=Calendar.getInstance();
    private int dia;
    private int mes;
    private int anualidad;
    private String prestamos;

    public Fecha(int dia, int mes, int anualidad) {
        this.dia = dia;
        this.mes = mes;
        this.anualidad = anualidad;
    }
    public Fecha (){

    }


    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnualidad() {
        return anualidad;
    }

    public void setFechaPrestamos() {
        int hora, minuto, segundo, dia, mes, anualidad;
        String fecha, horaA;

        hora = Hora.get(Calendar.HOUR_OF_DAY);
        minuto = Hora.get(Calendar.MINUTE);
        segundo = Hora.get(Calendar.SECOND);

        dia = Fecha.get(Calendar.DATE);
        mes = Fecha.get(Calendar.MONTH);
        anualidad = Fecha.get(Calendar.YEAR);

        fecha = "Fecha: " + dia + "/" + mes + "/" + anualidad;
        horaA = "\tHora: " + hora + ":" + minuto + ":" + segundo;

        this.prestamos = fecha+horaA;
    }

    public String getPrestamos() {
        return prestamos;
    }

}

