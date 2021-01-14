/**
 * Tu Cafe Java
 * @author Ing. Dick Díaz Delgado
 * https://tucafejava.blogspot.com/
 * Ejercicio 04
 **/
package tucafejava.ecuacionapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText eA;
    EditText eB;
    EditText eC;
    Button bdet;
    Button blim;
    TextView tres;
    TextView tmues;

    DecimalFormat formato = new DecimalFormat("#.0000");

    String seA;
    String seB;
    String seC;
    String smuestra;

    AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eA = (EditText) findViewById(R.id.exa);
        eB = (EditText) findViewById(R.id.exb);
        eC = (EditText) findViewById(R.id.exc);

        bdet = (Button) findViewById(R.id.bdeterminar);
        blim = (Button) findViewById(R.id.blimpiar);

        tres = (TextView) findViewById(R.id.tresultado);
        tmues = (TextView) findViewById(R.id.tmuestra);

        bdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seA = eA.getText().toString();
                seB = eB.getText().toString();
                seC = eC.getText().toString();
                smuestra = tmues.getText().toString();

                if(seA.length() == 0){
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Error");
                    dialog.setMessage("Debe ingresar el cociente 'a'");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo, int id) {
                            dialogo.cancel();
                            eA.setText("");
                            eA.requestFocus();
                        }
                    });
                    dialog.show();
                }else{
                    if(seA.equals("0")){
                        dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Error");
                        dialog.setMessage("El cociente 'a' no puede ser 0");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo, int id) {
                                dialogo.cancel();
                                eA.setText("");
                                eA.selectAll();
                                eA.requestFocus();
                            }
                        });
                        dialog.show();
                    }else {
                        if(seB.length() == 0){
                            dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("Error");
                            dialog.setMessage("Debe ingresar el cociente 'b'");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo, int id) {
                                    dialogo.cancel();
                                    eB.setText("");
                                    eB.requestFocus();
                                }
                            });
                            dialog.show();
                        }else{
                            if(seC.length() == 0){
                                dialog = new AlertDialog.Builder(MainActivity.this);
                                dialog.setTitle("Error");
                                dialog.setMessage("Debe ingresar el cociente 'c'");
                                dialog.setCancelable(false);
                                dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo, int id) {
                                        dialogo.cancel();
                                        eC.setText("");
                                        eC.requestFocus();
                                    }
                                });
                                dialog.show();
                            }else{
                                String smuestra = tmues.getText().toString();
                                double determinante = 0.0;
                                String apoyo;
                                determinante = Math.pow(Double.parseDouble(seB), 2.0) - 4 * Double.parseDouble(seA) * Double.parseDouble(seC);
                                if(determinante < 0){
                                    apoyo = "";
                                }else{
                                    if(determinante == 0){
                                        double unico = -Double.parseDouble(seB) / 2 * Double.parseDouble(seA);
                                        apoyo = String.valueOf("\n" + formato.format(unico));
                                        if(unico % 1 == 0){
                                            apoyo = String.valueOf("\nX = " + (int)unico);
                                        }
                                    }else{
                                        double uno = ((-1) * Double.parseDouble(seB) - Math.pow(determinante, 0.5)) / (2 * Double.parseDouble(seA));
                                        double dos = ((-1) * Double.parseDouble(seB) + Math.pow(determinante, 0.5)) / (2 * Double.parseDouble(seA));
                                        apoyo = String.valueOf("\nX1 = " + formato.format(uno) + "\nX2 = " + formato.format(dos));
                                        if(uno % 1 == 0){
                                            apoyo = String.valueOf("\nX1 = " + (int)uno + "\nX2 = " + formato.format(dos));
                                        }else{
                                            if(dos % 1 == 0){
                                                apoyo = String.valueOf("\nX1 = " + formato.format(uno) + "\nX2 = " + (int)dos);
                                            }else{
                                                if(uno % 1 == 0 && dos % 1 == 0){
                                                    apoyo = String.valueOf("\nX1 = " + (int)uno + "\nX2 = " + (int)dos);
                                                }
                                            }
                                        }
                                    }
                                }
                                if(Double.parseDouble(seA) < 0){
                                    smuestra = smuestra.replace("a", "- " + seA.substring(1, seA.length()));
                                    seA = "(" + seA + ")";
                                }else{
                                    smuestra = smuestra.replace("a", seA);
                                }
                                if(Double.parseDouble(seB) < 0){
                                    smuestra = smuestra.replace("+ b", "- " + seB.substring(1, seB.length()));
                                    seB = "(" + seB + ")";
                                }else{
                                    smuestra = smuestra.replace("b", seB);
                                }
                                if(Double.parseDouble(seC) < 0){
                                    smuestra = smuestra.replace("+ c", "- " + seC.substring(1, seC.length()));
                                    seC = "(" + seC + ")";
                                }else{
                                    smuestra = smuestra.replace("c", seC);
                                }
                                tres.setText("∆ = " + seB + "² - 4." + seA + "." + seC + apoyo);
                                tmues.setText(smuestra);
                                blim.setEnabled(true);
                                bdet.setEnabled(false);
                            }
                        }
                    }
                }
            }
        });

        blim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar_controles();
            }
        });
    }

    public void limpiar_controles(){
        tmues.setText("ax² + bx + c = 0");
        eA.setText("");
        eB.setText("");
        eC.setText("");
        tres.setText("");
        eA.requestFocus();
        blim.setEnabled(false);
        bdet.setEnabled(true);
    }
}