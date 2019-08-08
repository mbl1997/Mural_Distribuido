package Escritor;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

/**
 *
 * @author Marih Bianchini
 */
public class escritor {

    public static void main(String[] args) {
          String nome = showInputDialog(null, "Nome para o Escritor","",PLAIN_MESSAGE);

       enviarMensagem msg = new enviarMensagem("Escritor: "+nome);
        msg.setVisible(true);
    }
    
}
