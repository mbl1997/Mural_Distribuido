package Mural;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import static javax.swing.JOptionPane.*;

/**
 *
 * @author Marih Bianchini
 */


public class Txt extends javax.swing.JFrame  {
    
 private String nome;
    private int faltante;
    private int repetida;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    private boolean rodar;

    
    public Txt(String nome) {

        initComponents();
        jLabel1.setText(nome);

        rodar = true;
        this.nome = nome;

        try {

            s = new Socket("127.0.0.1", 5000);
        
        } catch (Exception e) {
            showMessageDialog(null, "Não cinseguiu se conectar ao servidor");
            System.exit(0);
        }

        Thread();

    }

    private void Thread() {
        Thread t = new Thread(new Runnable() {

            String mensagem;

            @Override
            public void run() {
                try {
                    isr = new InputStreamReader(s.getInputStream()); 
                    
                    br = new BufferedReader(isr);
                    
                    while ((mensagem = br.readLine()) != null) {

                        mensagemRecebida.setText(mensagemRecebida.getText() + mensagem + "\n");
                        if (!rodar) {
                            break;
                        }
                    }

                } catch (IOException e) {
                    showMessageDialog(null, "Erro na conexão com o servidor", "", ERROR_MESSAGE);
                }
            }
        });
        t.start();
    }

    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        mensagemRecebida = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("DialogInput", 0, 14)); 
        setForeground(java.awt.Color.red);

        mensagemRecebida.setEditable(false);
        mensagemRecebida.setColumns(20);
        mensagemRecebida.setRows(5);
        mensagemRecebida.setBorder(new javax.swing.border.MatteBorder(null));
        jScrollPane2.setViewportView(mensagemRecebida);

        jButton2.setBackground(new java.awt.Color(102, 255, 51));
        jButton2.setText("Saida");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 0), new java.awt.Color(153, 153, 153)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Leitor do Mural");
        jLabel1.setMaximumSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            s.close();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagemRecebida;
}
