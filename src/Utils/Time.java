package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javazoom.jl.player.Player;



public class Time
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
