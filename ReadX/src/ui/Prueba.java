package ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Prueba {
    public static void main(String[] args) {
        List<Calendar> calendarList = new ArrayList<>();

        // Agregar elementos al ArrayList
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2023, Calendar.MAY, 1);
        calendarList.add(calendar1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2022, Calendar.DECEMBER, 15);
        calendarList.add(calendar2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.JANUARY, 30);
        calendarList.add(calendar3);

        // Ordenar el ArrayList utilizando el algoritmo de ordenación burbuja
        int n = calendarList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Calendar current = calendarList.get(j);
                Calendar next = calendarList.get(j + 1);
                
                if (current.after(next)) {
                    // Intercambiar los elementos
                    calendarList.set(j, next);
                    calendarList.set(j + 1, current);
                }
            }
        }
        //generame un codigo de matriz burbuja para ordenas variables calendar del menor al mayor

        // Imprimir los elementos ordenados
        for (Calendar calendar : calendarList) {
            System.out.println(calendar.getTime());

        }
        }
        
        public static void bubbleSortCalendar(List<Calendar> calendarList) {
        int n = calendarList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Calendar current = calendarList.get(j);
                Calendar next = calendarList.get(j + 1);

                if (current.after(next)) {
                    // Intercambiar los elementos
                    calendarList.set(j, next);
                    calendarList.set(j + 1, current);
                }
            }
        }
    
    
        

}



}

 