import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class GUIContactos {

    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton modificarButton;
    private JTextField nombreTexto;
    private JPanel main;
    private JTextField textField1;
    private JTable table1;
    private DefaultTableModel tableModel;

    // Atributos

   String nombres_arreglos[] = new String[100];
   int telefono_arreglo[] = new int[100];
   int contador = 0;



   public  GUIContactos(){ // siempre en el constructor va los botones y dentro de ellos los metodos

       // para que este pueda funcionar a la hora de darle click

       tableModel = new DefaultTableModel(new String[]{"nombre" , "telefono"}, 0);
       table1.setModel(tableModel);

       agregarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               agregar();
               JOptionPane.showMessageDialog(null, " agregado exitosamente");

           }
       });

       modificarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               modficar();
               JOptionPane.showMessageDialog(null, " modificado exitosamente");


           }
       });

       buscarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               buscar();
           }
       });


       eliminarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              eliminar();
           }
       });




   }

   public void agregar(){
      if(nombreTexto.getText().length() <100 && textField1.getText().length() <10){
          nombres_arreglos[0] = nombreTexto.getText();
          telefono_arreglo[0] = Integer.parseInt(textField1.getText());

          tableModel.addRow(new Object[]{nombres_arreglos[contador] , telefono_arreglo[contador]});
          contador++;
          textField1.setText("");
          nombreTexto.setText("");

      }
      else {
          JOptionPane.showMessageDialog(null, "demasiados digitos, intenta de nuevo");
      }







   }

   public void  modficar(){
       int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, " ingrese la posicion del anterior"));
       String guardar_nombre =JOptionPane.showInputDialog(null, "ingrese el nombre nuevo");

       nombres_arreglos[posicion] = guardar_nombre;
      tableModel.setValueAt(guardar_nombre, posicion,0);


   }

   public void buscar(){

       int op = Integer.parseInt(JOptionPane.showInputDialog(" 1. Nombre \n 2. Telefono"));

       switch (op){

           case 1:
               boolean encontrar = false;
               JOptionPane.showMessageDialog(null, "escogiste por nombre");
               String nombreBuscado = (JOptionPane.showInputDialog(null, "ingrese el nombre de la persona"));

               for (int i = 0; i < nombres_arreglos.length ; i++) {
                   if (nombres_arreglos[i] != null && nombres_arreglos[i].equalsIgnoreCase(nombreBuscado)) { // equalsIgnoreCase() solo funciona
                       // con String, no se puede usar para comparar valores numéricos.
                       JOptionPane.showMessageDialog(null, "Nombre: " + nombres_arreglos[i] + ", Teléfono: " + telefono_arreglo[i]);
                        encontrar = true;
                       break;




                   }
               }
               if (!encontrar) {
                   JOptionPane.showMessageDialog(null, "no esta el nombre");
               }
               break;


           case 2:
               boolean encontrar1 = false;
               JOptionPane.showMessageDialog(null, "escogiste por Telefono");
               int nombreBuscadotelefono = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la persona"));

               for (int i = 0; i < telefono_arreglo.length ; i++) {
                   if (telefono_arreglo[i] ==(nombreBuscadotelefono)) { // == para comparación de enteros
                       JOptionPane.showMessageDialog(null, "Nombre: " + nombres_arreglos[i] + ", Teléfono: " + telefono_arreglo[i]);
                       encontrar1 = true;
                       break;




                   }
               }
               if (!encontrar1) {
                   JOptionPane.showMessageDialog(null, "no está el numero");
               }
               break;







       }
   }

   public void eliminar(){

       int position = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the position of the contact to delete (starting from 0)"));

       if (position < 0 || position >= contador) {
           JOptionPane.showMessageDialog(null, "Invalid position!"); // Validar posición
           return;
       }

       int confirmar = JOptionPane.showConfirmDialog(null, "Seguro que quieres eliminar", "Confirme para eliminar", JOptionPane.YES_NO_OPTION);
       if (confirmar == JOptionPane.YES_OPTION) {
           // Eliminar de los arrays
           for (int i = position; i < contador - 1; i++) {
               nombres_arreglos[i] = nombres_arreglos[i + 1];
               telefono_arreglo[i] = telefono_arreglo[i + 1];
           }

           nombres_arreglos[contador - 1] = null;
           telefono_arreglo[contador - 1] = 0;

           tableModel.removeRow(position);

           contador--;
           JOptionPane.showMessageDialog(null, "Contact deleted successfully!");
       }





   }






    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de contactos");

        frame.setContentPane(new GUIContactos().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
