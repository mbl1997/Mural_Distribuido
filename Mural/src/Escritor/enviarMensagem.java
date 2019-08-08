package Escritor;

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

public class enviarMensagem  extends javax.swing.JFrame {

     private String nome;
    private int faltante;
    private int repetida;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    private boolean rodar;

    public enviarMensagem (String nome) {

        initComponents();
        jLabel1.setText(nome);

        rodar = true;
        this.nome = nome;

        try {

            s = new Socket("127.0.0.1", 5000);
        
        } catch (Exception e) {
            showMessageDialog(null, "Não  consegeui se conectar ao servidor");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        mensagemEnviada = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        mensagemEnviada.setColumns(20);
        mensagemEnviada.setRows(5);
        mensagemEnviada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensagemEnviadaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(mensagemEnviada);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario");
        jLabel1.setMaximumSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(39, 39, 39))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        String mensagem = nome + " disse: ";

        try {
            PrintStream ps = new PrintStream(s.getOutputStream());
            mensagem += mensagemEnviada.getText();
            
            ps.println(mensagem);
            ps.flush();
            mensagemEnviada.setText("");
        } catch (Exception e) {
            showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);
        }
    }

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            s.close();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {

    }

    private void mensagemEnviadaKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.isShiftDown() && evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String mensagem = nome + " disse: ";

            try {
                PrintStream ps = new PrintStream(s.getOutputStream());
                mensagem += mensagemEnviada.getText();
                ps.println(mensagem);
                System.out.println();
                ps.flush();
                mensagemEnviada.setText("");
            } catch (IOException e) {
                showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);
            }
        }
    }

     private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mensagemEnviada;
    
}
   