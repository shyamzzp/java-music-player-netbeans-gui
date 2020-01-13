package Forms;

import Entities.Music;
import TableModel.MusicModel;
import Utils.Time;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public final class frmMain extends javax.swing.JFrame {

    public frmMain() {
        initComponents();
        setLocationRelativeTo(null);
        tbPlayListAtual.setModel(modeloMusicas);
        setLayoutTabela();
        lbTituloTocando.setText("Current Song:");
        lbTocandoAgora.setText("Please add songs");
        lbAutor.setText("Please add songs");
        slider1.setValue(0);
        btstop.setEnabled(false);
        fc.setMultiSelectionEnabled(true);
        jcSalvaLista.setMultiSelectionEnabled(false);
        jcSalvaLista.setApproveButtonText("Salvar");
        if (tbPlayListAtual.getRowCount() > 0) {
           
            btMistura.setEnabled(true);
        } else {
            
            btMistura.setEnabled(false);
        }
    }

    public void StopMusica() {
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        btAddmusica = new JButton();
        btRemoveMusica = new JButton();
        btMistura = new JButton();
        jPanel2 = new JPanel();
        btplay = new JButton();
        btstop = new JButton();
        jPanel5 = new JPanel();
        lbTituloTocando = new JLabel();
        lbTocandoAgora = new JLabel();
        slider1 = new JSlider();
        lbtempoDecorrido = new JLabel();
        lbAnime = new JLabel();
        lbtituloAutor = new JLabel();
        lbAutor = new JLabel();
        pnPrograma = new JPanel();
        jButton3 = new JButton();
        btAnteriorMusica = new JButton();
        btProximaMusica = new JButton();
        jPanel4 = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlayListAtual = new JTable();
        jPanel3 = new JPanel();
   

        setDefaultCloseOperation(3);
        setTitle("Music Player");

        jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Music Add/Remove/Suffle", 0, 0, new Font("Agency FB", 1, 14)));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btAddmusica.setFont(new Font("Agency FB", 1, 14));
        btAddmusica.setIcon(new ImageIcon(getClass().getResource("/Icons/AddMusica.png")));
        btAddmusica.setText("Add Songs");
        btAddmusica.addActionListener((ActionEvent evt) -> {
            frmMain.this.btAddmusicaActionPerformed(evt);
        });
        jPanel1.add(btAddmusica);

        btRemoveMusica.setFont(new Font("Agency FB", 1, 14));
        btRemoveMusica.setIcon(new ImageIcon(getClass().getResource("/Icons/removeMusica.png")));
        btRemoveMusica.setText("Remove Selected");
        btRemoveMusica.addActionListener((ActionEvent evt) -> {
            frmMain.this.btRemoveMusicaActionPerformed(evt);
        });
        jPanel1.add(btRemoveMusica);

        btMistura.setFont(new Font("Agency FB", 1, 14));
        btMistura.setIcon(new ImageIcon(getClass().getResource("/Icons/misturar.png")));
        btMistura.setText("Suffle");
        btMistura.addActionListener((ActionEvent evt) -> {
            frmMain.this.btMisturaActionPerformed(evt);
        });
        jPanel1.add(btMistura);

        jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Player", 0, 0, new Font("Agency FB", 1, 14)));
        jPanel2.setToolTipText("Tocando agora");

        btplay.setFont(new Font("Agency FB", 1, 14));
        btplay.setIcon(new ImageIcon(getClass().getResource("/Icons/play.png")));
        btplay.setText("Play");
        btplay.addActionListener((ActionEvent evt) -> {
            frmMain.this.btplayActionPerformed(evt);
        });
        btstop.setFont(new Font("Agency FB", 1, 14));
        btstop.setIcon(new ImageIcon(getClass().getResource("/Icons/stop.png")));
        btstop.setText("Stop");
        btstop.addActionListener((ActionEvent evt) -> {
            frmMain.this.btstopActionPerformed(evt);
        });
        jPanel5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jPanel5.setBorder(BorderFactory.createEtchedBorder());

        lbTituloTocando.setFont(new Font("Agency FB", 1, 14));
        lbTituloTocando.setText("Tocando:");

        lbTocandoAgora.setFont(new Font("Agency FB", 1, 14));
        lbTocandoAgora.setText("jLabel2");

        slider1.setBackground(new java.awt.Color(255, 255, 255));

        lbtempoDecorrido.setText("00:00:00");

        lbtituloAutor.setFont(new Font("Agency FB", 1, 14));
        lbtituloAutor.setText("Autor:");

        lbAutor.setFont(new Font("Agency FB", 1, 14));
        lbAutor.setText("jLabel2");

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addComponent(slider1, -2, 0, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbtempoDecorrido, -2, 54, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbAnime, -2, 40, -2).addGap(25, 25, 25)).addGroup(jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup().addComponent(lbtituloAutor).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lbAutor, -1, -1, 32767)).addGroup(GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup().addComponent(lbTituloTocando).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lbTocandoAgora, -2, 182, -2))).addContainerGap(-1, 32767)))));

        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lbTocandoAgora).addComponent(lbTituloTocando)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lbAutor).addComponent(lbtituloAutor)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, 32767).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(slider1, GroupLayout.Alignment.TRAILING, -2, 34, -2).addComponent(lbAnime, GroupLayout.Alignment.TRAILING, -2, 43, -2).addComponent(lbtempoDecorrido, GroupLayout.Alignment.TRAILING, -2, 34, -2))));

        pnPrograma.setBorder(BorderFactory.createTitledBorder(null, "Sobre", 0, 0, new Font("Agency FB", 1, 14)));
        
        jButton3.setIcon(new ImageIcon(getClass().getResource("/Icons/bug.png")));
        jButton3.setToolTipText("Informe um bug");
        jButton3.addActionListener((ActionEvent evt) -> {
            frmMain.this.jButton3ActionPerformed(evt);
        });
        GroupLayout pnProgramaLayout = new GroupLayout(pnPrograma);
        pnPrograma.setLayout(pnProgramaLayout);
        btAnteriorMusica.setIcon(new ImageIcon(getClass().getResource("/Icons/anterior.png")));
        btAnteriorMusica.addActionListener((ActionEvent evt) -> {
            frmMain.this.btAnteriorMusicaActionPerformed(evt);
        });
        btProximaMusica.setIcon(new ImageIcon(getClass().getResource("/Icons/proxima.png")));
        btProximaMusica.addActionListener((ActionEvent evt) -> {
            frmMain.this.btProximaMusicaActionPerformed(evt);
        });
        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addComponent(btAnteriorMusica, -2, 0, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(btProximaMusica, -2, 43, -2)).addComponent(btplay, -1, 100, 32767).addComponent(btstop, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel5, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(pnPrograma, -2, -1, -2)));

        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(pnPrograma, -2, 117, -2).addGroup(jPanel2Layout.createSequentialGroup().addComponent(btplay, -2, 36, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btstop, -2, 34, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(btAnteriorMusica, -2, 24, 32767).addComponent(btProximaMusica, -2, 0, 32767)))).addGap(0, 0, 32767)).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jPanel5, -2, -1, -2).addContainerGap(-1, 32767)));

        jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Music Library", 0, 0, new Font("Agency FB", 1, 14)));

        tbPlayListAtual.setAutoCreateRowSorter(true);
        tbPlayListAtual.setBorder(BorderFactory.createEtchedBorder());
        tbPlayListAtual.setFont(new Font("Century Gothic", 1, 10));
        tbPlayListAtual.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{{null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}}, new String[]{"Title 1", "Title 2", "Title 3", "Title 4"}));

        tbPlayListAtual.setAutoResizeMode(4);
        tbPlayListAtual.setSelectionMode(0);
        tbPlayListAtual.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                frmMain.this.tbPlayListAtualMouseClicked(evt);
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                frmMain.this.tbPlayListAtualMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbPlayListAtual);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, -2, 0, 32767).addComponent(jPanel3, -1, 425, 32767));

        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(jScrollPane1, -1, 172, 32767).addGap(18, 18, 18).addComponent(jPanel3, -2, 64, -2).addGap(7, 7, 7)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(jPanel4, -1, -1, 32767).addComponent(jPanel2, -1, -1, 32767)).addContainerGap()));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel4, -1, -1, 32767).addGap(16, 16, 16)));

        pack();
    }

    public void setLayoutTabela() {
        tbPlayListAtual.getColumnModel().getColumn(0).setPreferredWidth(150);
        tbPlayListAtual.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbPlayListAtual.getColumnModel().getColumn(2).setPreferredWidth(70);
        tbPlayListAtual.setRowSorter(null);
    }

    private void btAddmusicaActionPerformed(ActionEvent evt) {
        if (fc.showOpenDialog(jPanel1) == 0) {
            try {
                File[] files = fc.getSelectedFiles();

                for (File file : files) {
                    javax.sound.sampled.AudioFileFormat baseFileFormat = javax.sound.sampled.AudioSystem.getAudioFileFormat(file);
                    if (baseFileFormat instanceof org.tritonus.share.sampled.file.TAudioFileFormat) {
                        Music musica = new Music();
                        Map properties = ((org.tritonus.share.sampled.file.TAudioFileFormat) baseFileFormat).properties();
                        String key = "title";
                        String val = (String) properties.get(key);
                        musica.setNome(val);
                        key = "author";
                        val = (String) properties.get(key);
                        musica.setAutor(val);
                        key = "album";
                        val = (String) properties.get(key);
                        musica.setAlbum(val);
                        key = "duration";
                        Long valLong = (Long) properties.get(key);
                        musica.setTempo(valLong / 1000000L);
                        musica.setPath(file.getAbsolutePath());
                        modeloMusicas.addMusica(musica);
                    }
                }

            } catch (IOException | UnsupportedAudioFileException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (tbPlayListAtual.getRowCount() > 0) {
            btMistura.setEnabled(true);

        } else {
            btMistura.setEnabled(false);
        }
    }

    private void btRemoveMusicaActionPerformed(ActionEvent evt) {
        Integer linha = tbPlayListAtual.getSelectedRow();
        if (linha > -1) {

            if ((lbTocandoAgora.getText().equals(modeloMusicas.getMusica(linha).getNome())) && (lbTituloTocando.getText().equals("Tocando:"))) {

                stop = true;
                tempo.setStopFlag(true);
                btstop.setEnabled(false);
                player.close();
                btplay.setEnabled(true);
                btMistura.setEnabled(true);

                lbTituloTocando.setText("Current Song:");
                lbAutor.setText("Please add songs");
                lbTocandoAgora.setText("Please add songs");
            }

            modeloMusicas.removeMusica(linha);

            if (tbPlayListAtual.getRowCount() > 0) {
                btMistura.setEnabled(true);
            } else {
                btMistura.setEnabled(false);
            }
        }
    }

    private void btMisturaActionPerformed(ActionEvent evt) {
        modeloMusicas.mistura();
        if (tbPlayListAtual.getSelectedRow() > -1) {
            lbTituloTocando.setText("Current Song:");
            lbAutor.setText(modeloMusicas.getMusica(tbPlayListAtual.getSelectedRow()).getAutor());
            lbTocandoAgora.setText(modeloMusicas.getMusica(tbPlayListAtual.getSelectedRow()).getNome());
        }
    }

    private void tbPlayListAtualMouseEntered(MouseEvent evt) {
    }

    private void tbPlayListAtualMouseClicked(MouseEvent evt) {
        Integer linha = tbPlayListAtual.getSelectedRow();
        if (linha > -1) {
            if (tempo.getStopFlag() == true) {
                lbTocandoAgora.setText(modeloMusicas.getMusica(linha).getNome());
                lbAutor.setText(modeloMusicas.getMusica(linha).getAutor());
            }
        }
        if (evt.getClickCount() == 2) {
            if (!lbTituloTocando.getText().equals("Tocando:")) {
                btplayActionPerformed(null);
            } else {
                stop = true;
                tempo.setStopFlag(stop);
                player.close();
                btplayActionPerformed(null);
            }
        }
    }

    private void btProximaMusicaActionPerformed(ActionEvent evt) {
        if (tbPlayListAtual.getRowCount() > 0) {
            if (lbTituloTocando.getText().equals("Tocando:")) {
                if (line < tbPlayListAtual.getRowCount() - 1) {
                    player.close();
                }
                btplay.setEnabled(false);
                btstop.setEnabled(true);
            }
        }
    }

    private void btAnteriorMusicaActionPerformed(ActionEvent evt) {
        if (tbPlayListAtual.getRowCount() > 0) {
            if (lbTituloTocando.getText().equals("Tocando:")) {
                if (line > 0) {
                    volta = true;
                    player.close();
                    btplay.setEnabled(false);
                    btstop.setEnabled(true);
                }
            }
        }
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        new frmBugReport(this, rootPaneCheckingEnabled).setVisible(true);
    }

    private void btstopActionPerformed(ActionEvent evt) {
        if (tbPlayListAtual.getSelectedRow() == -1) {
            lbTocandoAgora.setText("Please add songs");
            lbAutor.setText("Please add songs");
        } else {
            lbAutor.setText(modeloMusicas.getValueAt(tbPlayListAtual.getSelectedRow(), 1).toString());
            lbTocandoAgora.setText(modeloMusicas.getValueAt(tbPlayListAtual.getSelectedRow(), 0).toString());
        }
        stop = true;
        tempo.setStopFlag(stop);
        player.close();
        btplay.setEnabled(true);
        btstop.setEnabled(false);
        btMistura.setEnabled(true);
        lbTituloTocando.setText("Current Song:");
    }

    private void btplayActionPerformed(ActionEvent evt) {
        final Integer linha = tbPlayListAtual.getSelectedRow();
        if (linha > -1) {

            new Thread() {
                @Override
                public void run() {
                    line = linha;
                    stop = false;
                    while ((!stop) && (line < modeloMusicas.getRowCount())) {
                        try {
                            player = new Player(new java.io.FileInputStream(new File(modeloMusicas.getMusica(line).getPath())));

                            lbtempoDecorrido.setText(String.valueOf(player.getPosition()));
                            tempo = new Time(lbtempoDecorrido, player, modeloMusicas.getMusica(line).getTempo(), slider1);

                            tempo.start();
                            tbPlayListAtual.setRowSelectionInterval(line, line);
                            lbTituloTocando.setText("Tocando:");
                            lbAutor.setText(modeloMusicas.getMusica(line).getAutor());
                            lbTocandoAgora.setText(modeloMusicas.getMusica(line).getNome());

                            player.play();
                            player.close();
                            tempo.setStopFlag(true);
                            Integer localInteger1;
                            if (volta) {
                                volta = false;
                            } else {
                                localInteger1 = line;
                                Integer localInteger2 = frmMain.this.line = line + 1;
                            }
                            if (line == modeloMusicas.getRowCount()) {
                                Integer linhaSelecionada = tbPlayListAtual.getSelectedRow();
                                if (linhaSelecionada > -1) {
                                    lbTituloTocando.setText("Current Song:");
                                    lbAutor.setText(modeloMusicas.getMusica(tbPlayListAtual.getSelectedRow()).getAutor());
                                    lbTocandoAgora.setText(modeloMusicas.getMusica(tbPlayListAtual.getSelectedRow()).getNome());
                                } else {
                                    lbTituloTocando.setText("Current Song:");
                                    lbAutor.setText("Please add songs");
                                    lbTocandoAgora.setText("Please add songs");
                                }

                            }
                        } catch (FileNotFoundException | JavaLayerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }.start();
            btMistura.setEnabled(false);
            btplay.setEnabled(false);
            btstop.setEnabled(true);
        }
    }

    MusicModel modeloMusicas = new MusicModel();
    JFileChooser fc = new JFileChooser();
    JFileChooser jcSalvaLista = new JFileChooser();
    JFileChooser jcImportaLista = new JFileChooser();
    Player player;
    Boolean volta = false;
    Boolean stop = false;
    Time tempo = new Time();

    Integer line;

    javax.sound.sampled.FloatControl volCtrl;
    private JButton btAddmusica;
    private JButton btAnteriorMusica;
    private JButton btMistura;

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new frmMain().setVisible(true);
        });
    }

    private JButton btProximaMusica;
    private JButton btRemoveMusica;
    private JButton btplay;
    private JButton btstop;
    private JButton jButton3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private JLabel lbAnime;
    private JLabel lbAutor;
    private JLabel lbTituloTocando;
    private JLabel lbTocandoAgora;
    private JLabel lbtempoDecorrido;
    private JLabel lbtituloAutor;
    private JPanel pnPrograma;
    private JSlider slider1;
    private JTable tbPlayListAtual;
}
