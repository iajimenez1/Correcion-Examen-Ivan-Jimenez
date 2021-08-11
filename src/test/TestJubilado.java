package test;

import dominio.JubiladoDiscapacidad;
import dominio.JubiladoVejez;
import dominio.Jubilado;
import dominio.JubiladoPatron;
import java.util.ArrayList;
import java.util.Scanner;

public class TestJubilado {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Jubilado> jubilados = new ArrayList<>();

    public static void main(String[] args) {
        int op = 0;
        String cedula, nombres;
        int anios;
        do {
            op = menu(op);
            if (op != 5 && op != 4) {
                entrada.nextLine();
                System.out.print("Cedula: ");
                cedula = entrada.nextLine();
                System.out.print("Nombres: ");
                nombres = entrada.nextLine();
                System.out.print("Anios Aporte:  ");
                anios = entrada.nextInt();
                datos(op, cedula, nombres, anios);
            }
            if (op == 4) {
                System.out.println("------------------------------------------------");
                System.out.println("||       REPORTE DE JUBILADOS POR VEJEZ       ||");
                System.out.println("------------------------------------------------");
                for (Jubilado jub : jubilados) {
                    jub.calculaPensioni();
                    jub.calcularPension();
                    if (jub instanceof JubiladoVejez) {
                        System.out.println(jub);
                    }
                }
                System.out.println("--------------------------------------------------");
                System.out.println("||      REPORTE DE JUBILADOS POR INVALIDEZ      ||");
                System.out.println("--------------------------------------------------");
                for (Jubilado jub : jubilados) {
                    jub.calculaPensioni();
                    jub.calcularPension();
                    if (jub instanceof JubiladoDiscapacidad) {
                        System.out.println(jub);
                    }
                }
                System.out.println("-----------------------------------------------------");
                System.out.println("||        REPORTE DE JUBILADOS POR PATRONAL        ||");
                System.out.println("-----------------------------------------------------");
                for (Jubilado jub : jubilados) {
                    jub.calculaPensioni();
                    jub.calcularPension();
                    if (jub instanceof JubiladoPatron) {
                        ((JubiladoPatron) jub).bonoSueldo();
                        System.out.println(jub);
                    }
                }
            }
        } while (op != 5);
    }

    public static int menu(int op) {
        System.out.println("---------------------------------");
        System.out.println("||        Menu Jubilado        ||");
        System.out.println("---------------------------------");
        System.out.println("|| 1. Vejez                    ||");
        System.out.println("|| 2. Invalidez                ||");
        System.out.println("|| 3. Patronal                 ||");
        System.out.println("|| 4. Reporte                  ||");
        System.out.println("|| 5. Salir                    ||");
        System.out.println("---------------------------------");
        System.out.print("* Escoja una Opcion.");
        op = entrada.nextInt();
        return op;
    }

    public static void datos(int op, String cedula, String nombres, int anios) {
        double porcentaje;
        int tipo;
        switch (op) {
            case 1:
                jubilados.add(new JubiladoVejez(cedula, nombres, anios));
                break;
            case 2:
                System.out.print("Porcentaje de discapacidad: ");
                porcentaje = entrada.nextDouble();
                jubilados.add(new JubiladoDiscapacidad(cedula, nombres, anios, porcentaje));
                break;
            case 3:
                System.out.print("Porcentaje de Inflacion: ");
                porcentaje = entrada.nextDouble();
                System.out.print("Tipo Empresa <<1>> publica <<2>> privada ");
                tipo = entrada.nextInt();
                jubilados.add(new JubiladoPatron(cedula, nombres, anios, porcentaje, tipo));
        }
    }
}
