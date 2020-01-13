package Forms;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Map;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javazoom.jl.decoder.JavaLayerException;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javazoom.jl.player.Player;
import java.util.ArrayList;

class Music
  implements Serializable
{
  String SongName;
  String AuthorName;
  String Album;
  Long tempo;
  String path;
  
  public String getPath()
  {
    return path;
  }
  
  public void setPath(String path) {
    this.path = path;
  }
  
  public Music(String SongName, String AuthorName, String Album, String data, Long tempo, String path) {
    this.SongName = SongName;
    this.AuthorName = AuthorName;
    this.Album = Album;
    this.tempo = tempo;
  }
  
  public Music() {}
  
  public String getSongName()
  {
    return SongName;
  }
  
  public void setSongName(String SongName) {
    this.SongName = SongName;
  }
  
  public String getAuthorName() {
    return AuthorName;
  }
  
  public void setAuthorName(String AuthorName) {
    this.AuthorName = AuthorName;
  }
  
  public String getAlbum() {
    return Album;
  }
  
  public void setAlbum(String Album) {
    this.Album = Album;
  }
  
  public Long getTempo() {
    return tempo;
  }
  
  public void setTempo(Long tempo) {
    this.tempo = tempo;
  }
  
  @Override
  public String toString()
  {
    return "Musica{SongName=" + SongName + ", AuthorName=" + AuthorName + ", Album=" + Album + ", tempo=" + tempo + '}';
  }
  


  public void serializaListaMusicas(ArrayList<Music> listaMusicas, String arquivo)
  {
    FileOutputStream arq = null;
    ObjectOutputStream out = null;
    try
    {
      arq = new FileOutputStream(arquivo);
      out = new ObjectOutputStream(arq);
      out.writeObject(listaMusicas);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    } finally {
      try {
        arq.close();
        out.close();
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
  
  public ArrayList<Music> deserializaListaMusicas(String arquivo)
  {
    FileInputStream arqLeitura = null;
    ObjectInputStream in = null;
    try
    {
      arqLeitura = new FileInputStream(arquivo);
      in = new ObjectInputStream(arqLeitura);
      return (ArrayList)in.readObject();
    } catch (ClassNotFoundException|IOException ex) {
      System.out.println(ex.getMessage());
    } finally {
      try {
        arqLeitura.close();
        in.close();
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }
    return null;
  }
}

class Time
  extends Thread
{
  JLabel lbtempo;
  Player player;
  Long duration;
  JSlider slider;
  Boolean stopFlag;
  
  public Boolean getStopFlag()
  {
    return stopFlag;
  }
  
  public void setStopFlag(Boolean stopFlag) {
    this.stopFlag = stopFlag;
  }
  
  public Time(Boolean stopFlag) {
    this.stopFlag = stopFlag;
  }
  
  public Time()
  {
    stopFlag = true;
  }
  
  public Time(JLabel tempo, Player player, Long duration, JSlider slider)
  {
    lbtempo = tempo;
    this.player = player;
    this.duration = duration;
    this.slider = slider;
    stopFlag = false;
  }
  

  @Override
  public void run()
  {
    SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
    Calendar cad = Calendar.getInstance();
    cad.clear();
    Date data = new Date(cad.getTime().getTime());
    

    Integer index = 2;
    slider.setMaximum(duration.intValue());
    while (!stopFlag)
    {
      cad.clear();
      Integer value = player.getPosition() / 1000;
      cad.add(13, value);
      slider.setValue(value);
      data.setTime(cad.getTime().getTime());
      lbtempo.setText(dt.format(data));
      

      Integer localInteger1 = index;Integer localInteger2 = index = index + 1;
      if (index > 5) {
        index = 2;
      }
      
      try
      {
        Thread.sleep(200L);
      } catch (InterruptedException ex) {} }
    slider.setValue(0);
    lbtempo.setText("00:00:00");
  }
}

class MusicModel
        extends AbstractTableModel {

    private final String[] colunas = {"Song Name", "Author", "Album"};

    private ArrayList<Music> arraylistMusic;

    public MusicModel() {
        arraylistMusic = new ArrayList();
    }

    public MusicModel(ArrayList<Music> arraylistMusic) {
        this.arraylistMusic = arraylistMusic;
    }

    @Override
    public int getRowCount() {
        return arraylistMusic.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Music m = (Music) arraylistMusic.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getSongName();
            case 1:
                return m.getAuthorName();
            case 2:
                return m.getAlbum();
        }

        return null;
    }

    @Override
    public void setValueAt(Object objetct, int rowIndex, int columnIndex) {
        Music m = (Music) arraylistMusic.get(rowIndex);
        String value = (String) objetct;
        switch (columnIndex) {
            case 0:
                m.setSongName(value);
                break;
            case 1:
                m.setAuthorName(value);
                break;
            case 2:
                m.setAlbum(value);
                break;
        }

        fireTableRowsInserted(rowIndex, rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
        }

        return null;
    }

    public void limpar() {
        arraylistMusic.clear();

        fireTableDataChanged();
    }

    public void addListaDeMusicas(List<Music> musicas) {
        int indice = getRowCount();

        arraylistMusic.addAll(musicas);

        fireTableRowsInserted(indice, indice + musicas.size());
    }

    public Music getMusica(int rowIndex) {
        return (Music) arraylistMusic.get(rowIndex);
    }

    public void addMusica(Music musica) {
        arraylistMusic.add(musica);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeMusica(int indiceLinha) {
        arraylistMusic.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public ArrayList<Music> getAsArrayList() {
        return arraylistMusic;
    }

    public void mistura() {
        Collections.shuffle(arraylistMusic);

        fireTableRowsUpdated(0, arraylistMusic.size() - 1);
    }
}

public final class frmMain extends javax.swing.JFrame {

    public frmMain() {
        initComponents();
        setLocationRelativeTo(null);
        songListList.setModel(musicModel);
        setLayoutTabela();
        lblTitleCurrentSong.setText("Current Song:");
        lblTitleCurrentSongName.setText("Please add songs");
        lblAuthorName.setText("Please add songs");
        slider1.setValue(0);
        btstop.setEnabled(false);
        fc.setMultiSelectionEnabled(true);
        if (songListList.getRowCount() > 0) {
            btnSuffle.setEnabled(true);
        } else {
            btnSuffle.setEnabled(false);
        }
    }

    public void StopMusica() {
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        btnAddMusic = new JButton();
        btnRemoveMusic = new JButton();
        btnSuffle = new JButton();
        jPanel2 = new JPanel();
        btplay = new JButton();
        btstop = new JButton();
        jPanel5 = new JPanel();
        lblTitleCurrentSong = new JLabel();
        lblTitleCurrentSongName = new JLabel();
        slider1 = new JSlider();
        lblTimeForSong = new JLabel();
        lbAnime = new JLabel();
        lbtituloAutor = new JLabel();
        lblAuthorName = new JLabel();
        pnPrograma = new JPanel();
        btnPreviousSong = new JButton();
        btnForwardSong = new JButton();
        jPanel4 = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        songListList = new JTable();
        jPanel3 = new JPanel();

        setDefaultCloseOperation(3);
        setTitle("Music Player");

        jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Music Add/Remove/Suffle", 0, 0, new Font("Agency FB", 1, 14)));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnAddMusic.setFont(new Font("Agency FB", 1, 14));
        btnAddMusic.setIcon(new ImageIcon(getClass().getResource("/Icons/MusicIcon.png")));
        btnAddMusic.setText("Add Songs");
        btnAddMusic.addActionListener((ActionEvent evt) -> {
            frmMain.this.btnAddMusicActionPerformed(evt);
        });
        jPanel1.add(btnAddMusic);

        btnRemoveMusic.setFont(new Font("Agency FB", 1, 14));
        btnRemoveMusic.setIcon(new ImageIcon(getClass().getResource("/Icons/removeMusicIcon.png")));
        btnRemoveMusic.setText("Remove Selected");
        btnRemoveMusic.addActionListener((ActionEvent evt) -> {
            frmMain.this.btnRemoveMusicActionPerformed(evt);
        });
        jPanel1.add(btnRemoveMusic);

        btnSuffle.setFont(new Font("Agency FB", 1, 14));
        btnSuffle.setIcon(new ImageIcon(getClass().getResource("/Icons/suffleIcon.png")));
        btnSuffle.setText("Suffle");
        btnSuffle.addActionListener((ActionEvent evt) -> {
            frmMain.this.btnSuffleActionPerformed(evt);
        });
        jPanel1.add(btnSuffle);

        jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Player", 0, 0, new Font("Agency FB", 1, 14)));

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

        lblTitleCurrentSong.setFont(new Font("Agency FB", 1, 14));
        lblTitleCurrentSong.setText("Current Song:");

        lblTitleCurrentSongName.setFont(new Font("Agency FB", 1, 14));
        lblTitleCurrentSongName.setText("jLabel2");

        slider1.setBackground(new java.awt.Color(255, 255, 255));

        lblTimeForSong.setText("00:00:00");

        lbtituloAutor.setFont(new Font("Agency FB", 1, 14));
        lbtituloAutor.setText("Author:");

        lblAuthorName.setFont(new Font("Agency FB", 1, 14));
        lblAuthorName.setText("jLabel2");

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addComponent(slider1, -2, 0, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lblTimeForSong, -2, 54, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbAnime, -2, 40, -2).addGap(25, 25, 25)).addGroup(jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup().addComponent(lbtituloAutor).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lblAuthorName, -1, -1, 32767)).addGroup(GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup().addComponent(lblTitleCurrentSong).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lblTitleCurrentSongName, -2, 182, -2))).addContainerGap(-1, 32767)))));

        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleCurrentSongName).addComponent(lblTitleCurrentSong)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblAuthorName).addComponent(lbtituloAutor)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, 32767).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(slider1, GroupLayout.Alignment.TRAILING, -2, 34, -2).addComponent(lbAnime, GroupLayout.Alignment.TRAILING, -2, 43, -2).addComponent(lblTimeForSong, GroupLayout.Alignment.TRAILING, -2, 34, -2))));

        pnPrograma.setBorder(BorderFactory.createTitledBorder(null, "Sobre", 0, 0, new Font("Agency FB", 1, 14)));

        GroupLayout pnProgramaLayout = new GroupLayout(pnPrograma);
        pnPrograma.setLayout(pnProgramaLayout);
        btnPreviousSong.setIcon(new ImageIcon(getClass().getResource("/Icons/playPreviousIcon.png")));
        btnPreviousSong.addActionListener((ActionEvent evt) -> {
            frmMain.this.btnPreviousSongActionPerformed(evt);
        });
        btnForwardSong.setIcon(new ImageIcon(getClass().getResource("/Icons/playForwardIcon.png")));
        btnForwardSong.addActionListener((ActionEvent evt) -> {
            frmMain.this.btnForwardSongActionPerformed(evt);
        });
        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addComponent(btnPreviousSong, -2, 0, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(btnForwardSong, -2, 43, -2)).addComponent(btplay, -1, 100, 32767).addComponent(btstop, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel5, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(pnPrograma, -2, -1, -2)));

        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(pnPrograma, -2, 117, -2).addGroup(jPanel2Layout.createSequentialGroup().addComponent(btplay, -2, 36, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btstop, -2, 34, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(btnPreviousSong, -2, 24, 32767).addComponent(btnForwardSong, -2, 0, 32767)))).addGap(0, 0, 32767)).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jPanel5, -2, -1, -2).addContainerGap(-1, 32767)));

        jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Music Library", 0, 0, new Font("Agency FB", 1, 14)));

        songListList.setAutoCreateRowSorter(true);
        songListList.setBorder(BorderFactory.createEtchedBorder());
        songListList.setFont(new Font("Century Gothic", 1, 10));
        songListList.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{{null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}}, new String[]{"Title 1", "Title 2", "Title 3", "Title 4"}));

        songListList.setAutoResizeMode(4);
        songListList.setSelectionMode(0);
        songListList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                frmMain.this.songListListMouseClicked(evt);
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                frmMain.this.songListListMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(songListList);

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
        songListList.getColumnModel().getColumn(0).setPreferredWidth(150);
        songListList.getColumnModel().getColumn(1).setPreferredWidth(100);
        songListList.getColumnModel().getColumn(2).setPreferredWidth(70);
        songListList.setRowSorter(null);
    }

    private void btnAddMusicActionPerformed(ActionEvent evt) {
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
                        musica.setSongName(val);
                        key = "author";
                        val = (String) properties.get(key);
                        musica.setAuthorName(val);
                        key = "album";
                        val = (String) properties.get(key);
                        musica.setAlbum(val);
                        key = "duration";
                        Long valLong = (Long) properties.get(key);
                        musica.setTempo(valLong / 1000000L);
                        musica.setPath(file.getAbsolutePath());
                        musicModel.addMusica(musica);
                    }
                }

            } catch (IOException | UnsupportedAudioFileException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (songListList.getRowCount() > 0) {
            btnSuffle.setEnabled(true);

        } else {
            btnSuffle.setEnabled(false);
        }
    }

    private void btnRemoveMusicActionPerformed(ActionEvent evt) {
        Integer linha = songListList.getSelectedRow();
        if (linha > -1) {
            if ((lblTitleCurrentSongName.getText().equals(musicModel.getMusica(linha).getSongName())) && (lblTitleCurrentSong.getText().equals("Current Song:"))) {
                stop = true;
                tempo.setStopFlag(true);
                btstop.setEnabled(false);
                player.close();
                btplay.setEnabled(true);
                btnSuffle.setEnabled(true);
                lblTitleCurrentSong.setText("Current Song:");
                lblAuthorName.setText("Please add songs");
                lblTitleCurrentSongName.setText("Please add songs");
            }
            musicModel.removeMusica(linha);
            if (songListList.getRowCount() > 0) {
                btnSuffle.setEnabled(true);
            } else {
                btnSuffle.setEnabled(false);
            }
        }
    }

    private void btnSuffleActionPerformed(ActionEvent evt) {
        musicModel.mistura();
        if (songListList.getSelectedRow() > -1) {
            lblTitleCurrentSong.setText("Current Song:");
            lblAuthorName.setText(musicModel.getMusica(songListList.getSelectedRow()).getAuthorName());
            lblTitleCurrentSongName.setText(musicModel.getMusica(songListList.getSelectedRow()).getSongName());
        }
    }

    private void songListListMouseEntered(MouseEvent evt) {
    }

    private void songListListMouseClicked(MouseEvent evt) {
        Integer linha = songListList.getSelectedRow();
        if (linha > -1) {
            if (tempo.getStopFlag() == true) {
                lblTitleCurrentSongName.setText(musicModel.getMusica(linha).getSongName());
                lblAuthorName.setText(musicModel.getMusica(linha).getAuthorName());
            }
        }
        if (evt.getClickCount() == 2) {
            if (!lblTitleCurrentSong.getText().equals("Current Song:")) {
                btplayActionPerformed(null);
            } else {
                stop = true;
                tempo.setStopFlag(stop);
                player.close();
                btplayActionPerformed(null);
            }
        }
    }

    private void btnForwardSongActionPerformed(ActionEvent evt) {
        if (songListList.getRowCount() > 0) {
            if (lblTitleCurrentSong.getText().equals("Current Song:")) {
                if (line < songListList.getRowCount() - 1) {
                    player.close();
                }
                btplay.setEnabled(false);
                btstop.setEnabled(true);
            }
        }
    }

    private void btnPreviousSongActionPerformed(ActionEvent evt) {
        if (songListList.getRowCount() > 0) {
            if (lblTitleCurrentSong.getText().equals("Current Song:")) {
                if (line > 0) {
                    volta = true;
                    player.close();
                    btplay.setEnabled(false);
                    btstop.setEnabled(true);
                }
            }
        }
    }

    private void btstopActionPerformed(ActionEvent evt) {
        if (songListList.getSelectedRow() == -1) {
            lblTitleCurrentSongName.setText("Please add songs");
            lblAuthorName.setText("Please add songs");
        } else {
            lblAuthorName.setText(musicModel.getValueAt(songListList.getSelectedRow(), 1).toString());
            lblTitleCurrentSongName.setText(musicModel.getValueAt(songListList.getSelectedRow(), 0).toString());
        }
        stop = true;
        tempo.setStopFlag(stop);
        player.close();
        btplay.setEnabled(true);
        btstop.setEnabled(false);
        btnSuffle.setEnabled(true);
        lblTitleCurrentSong.setText("Current Song:");
    }

    private void btplayActionPerformed(ActionEvent evt) {
        final Integer linha = songListList.getSelectedRow();
        if (linha > -1) {

            new Thread() {
                @Override
                public void run() {
                    line = linha;
                    stop = false;
                    while ((!stop) && (line < musicModel.getRowCount())) {
                        try {
                            player = new Player(new java.io.FileInputStream(new File(musicModel.getMusica(line).getPath())));

                            lblTimeForSong.setText(String.valueOf(player.getPosition()));
                            tempo = new Time(lblTimeForSong, player, musicModel.getMusica(line).getTempo(), slider1);

                            tempo.start();
                            songListList.setRowSelectionInterval(line, line);
                            lblTitleCurrentSong.setText("Current Song:");
                            lblAuthorName.setText(musicModel.getMusica(line).getAuthorName());
                            lblTitleCurrentSongName.setText(musicModel.getMusica(line).getSongName());

                            player.play();
                            player.close();
                            tempo.setStopFlag(true);
                            if (volta) {
                                volta = false;
                            }
                            if (line == musicModel.getRowCount()) {
                                Integer arraylistMusicelecionada = songListList.getSelectedRow();
                                if (arraylistMusicelecionada > -1) {
                                    lblTitleCurrentSong.setText("Current Song:");
                                    lblAuthorName.setText(musicModel.getMusica(songListList.getSelectedRow()).getAuthorName());
                                    lblTitleCurrentSongName.setText(musicModel.getMusica(songListList.getSelectedRow()).getSongName());
                                } else {
                                    lblTitleCurrentSong.setText("Current Song:");
                                    lblAuthorName.setText("Please add songs");
                                    lblTitleCurrentSongName.setText("Please add songs");
                                }

                            }
                        } catch (FileNotFoundException | JavaLayerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }.start();
            btnSuffle.setEnabled(false);
            btplay.setEnabled(false);
            btstop.setEnabled(true);
        }
    }
    MusicModel musicModel = new MusicModel();
    JFileChooser fc = new JFileChooser();
    Player player;
    Boolean volta = false;
    Boolean stop = false;
    Time tempo = new Time();
    Integer line;
    javax.sound.sampled.FloatControl volCtrl;
    private JButton btnAddMusic;
    private JButton btnPreviousSong;
    private JButton btnSuffle;

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new frmMain().setVisible(true);
        });
    }

    private JButton btnForwardSong;
    private JButton btnRemoveMusic;
    private JButton btplay;
    private JButton btstop;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private JLabel lbAnime;
    private JLabel lblAuthorName;
    private JLabel lblTitleCurrentSong;
    private JLabel lblTitleCurrentSongName;
    private JLabel lblTimeForSong;
    private JLabel lbtituloAutor;
    private JPanel pnPrograma;
    private JSlider slider1;
    private JTable songListList;
}
