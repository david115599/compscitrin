/*
NameThatTune
By David Bershadsky and Sira Nassoko
Run the program by runing "java NameThatTune.java integer" integer represents how long the generated music will play
This program proceduraly generates music based on a random number generator
The Program makes the generated music the same length as the number of seconds that is
inputed when the program is called.
It generates music in diffrent keys which are determined by a random number at the begining
of the program.
It then chooses the length of each note randomly.
It uses 2d arrays in order to add multiple instruments which play in different octaves simultaniously with the melody
At the end the program saves the music in a wav file.
*/

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.PrintWriter;
import java.io.File;

public class NameThatTune {

  // return weighted sum of two arrays
  public static double[] sum(double[] a, double[] b, double awt, double bwt) {

    /*  // precondition: arrays have the same length
    assert a.length == b.length;

    // compute the weighted sum
    double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
    c[i] = a[i]*awt + b[i]*bwt;
  }*/
  int mn = (a.length>b.length) ? a.length : b.length;
  double[] c = new double[mn];
  if (a.length>=b.length) {
    for (int i=0;i<b.length ;i++ ) {
      c[i]=a[i]*awt+b[i]*bwt;
    }
    for (int i=b.length;i<a.length ;i++ ) {
      c[i]=a[i]*awt;
    }
  }
  else{
    for (int i=0;i<a.length ;i++ ) {
      c[i]=a[i]*awt+b[i]*bwt;
    }
    for (int i=a.length;i<b.length ;i++ ) {
      c[i]=b[i]*bwt;
    }
  }
  return c;
}
//this function utilizes wieghted addtion to change the volume of a wave
public static double[] changeVolume(double[] a, double volume) {
  return sum(a, a,volume,volume);
}
// create a pure tone of the given frequency for the given duration
public static double[] tone(double hz, double duration) {
  int n = (int) (StdAudio.SAMPLE_RATE * duration);
  double[] a = new double[n+1];
  for (int i = 0; i <= n; i++) {
    a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
  }
  return a;
}

//establishes a max/min for the amplitude of a sound wave. Values that exceed the boundaries of the range are replaced with the max/min
public static double[] clip(double[] a, double max, double min) {
  double[] clip = new double[a.length];
  for (int i = 0; i < a.length; i++) {
    if ((a[i] < max) && (a[i] > min)) {
      clip[i] = a[i];
    }
    else if (a[i] > max){
      clip[i] = max;
    }
    else {
      clip[i] = min;
    }
  }
  return clip;
}

//removes leading and trailing 0’s from your array
public static double[] trim(double[] a) {
  int size = 0;
  int size2 = 0;
  for (int i = 0; i < a.length; i++) {
    if (a[i] == 0) {
      size += 1;
    }
    else {
      break;
    }
  }

  for (int l = a.length - 1; l > 0; l--) {
    if (a[l] == 0) {
      size2 += 1;
    }
    else {
      break;
    }
  }

  double[] trimmed = new double[a.length - size - size2];
  for (int j = 0; j < trimmed.length; j++) {
    trimmed[j] = a[j + size];
  }
  return trimmed;
}

//harmonic – adds both a high and a low octave to a root note
public static double[] harmonicnote(int pitch, double duration) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  double[] a  = tone(hz, duration);
  double[] hi = tone(2*hz, duration);
  double[] lo = tone(hz/2, duration);
  double[] h  = sum(hi, lo, 0.5, 0.5);
  return sum(h, lo, 0.5, 0.5);
}
//majorChord- forms a major chord around a root note
public static double[] majorchordnote(int pitch, double duration) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  double hz4 = 440.0 * Math.pow(2, pitch+4 / 12.0);
  double hz7 = 440.0 * Math.pow(2, pitch+7 / 12.0);
  double[] a  = tone(hz, duration);
  double[] a4 = tone(hz4, duration);
  double[] a7 = tone(hz7, duration);
  double[] h  = sum(a, a4, 0.5, 0.5);
  return sum(h, a7, 0.5, 0.5);
}
//minorChord- forms a minor chord around a root note
public static double[] minorchordnote(int pitch, double duration) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  double hz3 = 440.0 * Math.pow(2, pitch+3 / 12.0);
  double hz7 = 440.0 * Math.pow(2, pitch+7 / 12.0);
  double[] a  = tone(hz, duration);
  double[] a3 = tone(hz3, duration);
  double[] a7 = tone(hz7, duration);
  double[] h  = sum(a, a3, 0.5, 0.5);
  return sum(h, a7, 0.5, 0.5);
}
//fadeinout is our main music generator, it fades each note in and out as well as
//it integrates harmonics and sums all of the waves of the diffent instrument together.
public static double[] fadeinoutnote(int pitch, int pitch1,int pitch2,int pitch3, double duration,double duration1,double duration2,double duration3, double fadeloc) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  double hzhi = 440.0 * Math.pow(2, (pitch+12) / 12.0);
  double hzlo = 440.0 * Math.pow(2, (pitch-12) / 12.0);
  double hz1 = 440.0 * Math.pow(2, pitch1 / 12.0);
  double hzhi1 = 440.0 * Math.pow(2, (pitch1+12) / 12.0);
  double hzlo1 = 440.0 * Math.pow(2, (pitch1-12) / 12.0);
  double hz2 = 440.0 * Math.pow(2, pitch2 / 12.0);
  double hzhi2 = 440.0 * Math.pow(2, (pitch2+12) / 12.0);
  double hzlo2 = 440.0 * Math.pow(2, (pitch2-12) / 12.0);
  double hz3 = 440.0 * Math.pow(2, pitch3 / 12.0);
  double hzhi3 = 440.0 * Math.pow(2, (pitch3+12) / 12.0);
  double hzlo3 = 440.0 * Math.pow(2, (pitch3-12) / 12.0);
  double[] a  = fadeinouttone(hz, duration,fadeloc);
  double[] hi = fadeinouttone(hzhi, duration,fadeloc);
  double[] lo = fadeinouttone(hzlo, duration,fadeloc);
  double[] h  = sum(hi, lo, 0.5, 0.5);
  double[] hh = sum(h, a, 0.1, 0.4);//a
  double[] a1  = fadeinouttone(hz1, duration1,fadeloc);
  double[] hi1 = fadeinouttone(hzhi1, duration1,fadeloc);
  double[] lo1 = fadeinouttone(hzlo1, duration1,fadeloc);
  double[] h1  = sum(hi1, lo1, 0.5, 0.5);
  double[] hh1  = sum(h1, a1, 0.5, 0.5);
  double[] a2  = fadeinouttone(hz2, duration2,fadeloc);
  double[] hi2 = fadeinouttone(hzhi2, duration2,fadeloc);
  double[] lo2 = fadeinouttone(hzlo2, duration2,fadeloc);
  double[] h2  = sum(hi2, lo2, 0.5, 0.5);
  double[] hh2  = sum(h2, a2, 0.5, 0.5);
  double[] a3  = fadeinouttone(hz3, duration3,fadeloc);
  double[] hi3 = fadeinouttone(hzhi3, duration3,fadeloc);
  double[] lo3 = fadeinouttone(hzlo3, duration3,fadeloc);
  double[] h3  = sum(hi3, lo3, 0.5, 0.5);
  double[] hh3  = sum(h3, a3, 0.5, 0.5);


  double[] is23  = sum(hh3, hh2, 0.5, 0.5);
  double[] is01  = sum(hh, hh1, 0.5, 0.5);

  //paramzone Choose one to uncomment
  //  return hh;/*one instrument*/
  //  return sum(is01, is01, 0.5, 0.5);/*two instruments*/
  return sum(is01, is23, 0.5, 0.5);/*four instruments*/
}
public static double[] fadeinouttone(double hz, double duration, double fadeloc) {
  int n = (int) (StdAudio.SAMPLE_RATE * duration);
  int f = (int) (StdAudio.SAMPLE_RATE * fadeloc);
  double[] a = new double[n+1];
  for (int i = 0; i <= f; i++) {
    double q = ((Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE))/(f))*i;
    //  a[i] = (.1*q)*Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
    a[i] = q;

  }
  for (int i = f; i <= n; i++) {
    a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
  }
  for (int i = 0; i <= f; i++) {
    a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
  }
  for (int i = f; i <= n; i++) {
    double q = ((Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE))/(n-f))*((n-f)-(i-f));
    a[i] = q;
  }
  return a;
}
//fade- scales the amplitude of the first n seconds from 0 to usual value
public static double[] fadeinnote(int pitch, double duration, double fadeloc) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  double[] a  = fadeintone(hz, duration, fadeloc);
  //MusicTools.printArray(a);
  return (a);
}
public static double[] fadeintone(double hz, double duration, double fadeloc) {
  int n = (int) (StdAudio.SAMPLE_RATE * duration);
  int f = (int) (StdAudio.SAMPLE_RATE * fadeloc);
  double[] a = new double[n+1];
  for (int i = 0; i <= f; i++) {
    double q = ((Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE))/(f))*i;
    //  a[i] = (.1*q)*Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
    a[i] = q;

  }
  for (int i = f; i <= n; i++) {
    a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
  }
  return a;
}
//fade- scales the amplitude of the last n seconds from usual value to 0
public static double[] fadeoutnote(int pitch, double duration, double fadeloc) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  double[] a  = fadeouttone(hz, duration, fadeloc);
  //MusicTools.printArray(a);
  return (a);
}
public static double[] fadeouttone(double hz, double duration, double fadeloc) {
  int n = (int) (StdAudio.SAMPLE_RATE * duration);
  int f = (int) (StdAudio.SAMPLE_RATE * fadeloc);
  double[] a = new double[n+1];
  for (int i = 0; i <= f; i++) {
    a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
  }
  for (int i = f; i <= n; i++) {
    double q = ((Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE))/(n-f))*((n-f)-(i-f));
    a[i] = q;
  }
  return a;
}

//establishes a max/min for the amplitude of a sound wave. Values that exceed the boundaries of the range are replaced with the max/min
public static double[] clipnote(int pitch, double duration, double cliploc) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  int n = (int) (StdAudio.SAMPLE_RATE * duration);
  double[] a = new double[n+1];
  for (int i = 0; i <= n; i++) {
    a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
    if (a[i] > cliploc) {
      a[i] = cliploc;
    }
    if (a[i] < -cliploc) {
      a[i] = -cliploc;
    }
  }
  //MusicTools.printArray(a);
  return (a);
}

//repeats a tone based on an echo volume and time interval
public static double[] delay(double[] array1, double volume, double seconds){
  double n = StdAudio.SAMPLE_RATE*seconds;
  double[] array2 = new double[array1.length+((int)n)];
  for(int i = 0; i < ((int)n); i++)
  array2[i] = 0;
  for(int i = ((int)n); i < array1.length+((int)n); i++)
  array2[i] = array1[i-((int)n)];
  return MusicTools.weightedAddArray(array1, MusicTools.scaleArray(array2, (int)volume), .5, .5);
}
//repeats a tone based on an echo volume for the two following notes
public static double[] eco2(int pitch, double duration, int ecoloc, double[][]sheetmusic, int i) {
  double hz = 440.0 * Math.pow(2, pitch / 12.0);
  double hz4;
  double hz7;
  if (i>=6) {
    hz4 = 440.0 * Math.pow(2, sheetmusic[0][i-ecoloc*3] / 12.0);
    hz7 = 440.0 * Math.pow(2, sheetmusic[0][i-2*ecoloc*3]/ 12.0);
  }
  else {
    hz4 = 440.0 * Math.pow(2, sheetmusic[0][i] / 12.0);
    hz7 = 440.0 * Math.pow(2, sheetmusic[0][i]/ 12.0);
  }
  double[] a  = tone(hz, duration);
  double[] a4 = tone(hz4, duration);
  double[] a7 = tone(hz7, duration);
  double[] h  = sum(a, a4, 0.8, 0.2);
  return sum(h, a7, 0.8, 0.2);
}

//______________________________________________________________________________________
public static double[][] generatemusic(int length) {
  //defines variables for each note
  double note_a = 0;
  double note_as_bf = 1;
  double note_b = 2;
  double note_c = 3;
  double note_cs_df = 4;
  double note_d = 5;
  double note_ds_ef = 6;
  double note_e = 7;
  double note_f = 8;
  double note_fs_gf = 9;
  double note_g = 10;
  double note_gs_af = 11;
  double note_a2 = 12;
  //__________________________________________________________________
  //random music gen  START:
  double currentlength = 0;
  //defines the sheetmusic array
  double[][] sheetmusic = new double[4][length*40];
  //initializes begining of sheetmusic array
  sheetmusic[0][0]=0;
  sheetmusic[0][1] = .25;
  sheetmusic[0][2] = 1;
  sheetmusic[1][0]=0;
  sheetmusic[1][1] = .25;
  sheetmusic[1][2] = 1;
  sheetmusic[2][0]=0;
  sheetmusic[2][1] = .25;
  sheetmusic[2][2] = 1;
  sheetmusic[3][0]=0;
  sheetmusic[3][1] = .25;
  sheetmusic[3][2] = 1;
  {  int i = 3;
    while (currentlength<=length) {
      double the_chosen_one = ((Math.random()));//used to determine the note
      double the_second_chosen_one = (int)((Math.random()*5+1));// used to determine the note length
      double the_third_chosen_one = (double) ((int) (Math.random()*4)); //used to determine what key
      //sheetmusic[0][i] = (double)(int)(the_chosen_one*12);
      double the_questioned_one = sheetmusic[0][i-3];
      //__________________________________________________________________
      //C Major
      if (the_third_chosen_one==0) {
        if (the_questioned_one == note_a) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_fs_gf ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }

        if (the_questioned_one == note_b) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_fs_gf ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_a ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }

        if (the_questioned_one == note_d) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_a ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_fs_gf ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }
        if (the_questioned_one == note_e) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_fs_gf ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_a ;
          }
        }
        if (the_questioned_one == note_fs_gf) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8&& the_chosen_one >.5) {
            sheetmusic[0][i]=note_e ;
          }
          if (the_chosen_one <=1  && the_chosen_one >.8) {
            sheetmusic[0][i]=note_a2 ;
          }
        }


        if (the_questioned_one == note_a2) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_fs_gf ;
          }
          if (the_chosen_one <=1&& the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }
      }
      //__________________________________________________________________
      //G Major
      if (the_third_chosen_one==1) {
        if (the_questioned_one == note_a) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }

        if (the_questioned_one == note_b) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_a ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }

        if (the_questioned_one == note_d) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_a ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }
        if (the_questioned_one == note_e) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_a ;
          }
        }
        if (the_questioned_one == note_g) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8&& the_chosen_one >.5) {
            sheetmusic[0][i]=note_e ;
          }
          if (the_chosen_one <=1  && the_chosen_one >.8) {
            sheetmusic[0][i]=note_a2 ;
          }
        }


        if (the_questioned_one == note_a2) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_b ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1&& the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }
      }
      //__________________________________________________________________
      //D Major
      if (the_third_chosen_one==2) {
        if (the_questioned_one == note_a) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_c ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }

        if (the_questioned_one == note_c) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_a ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }

        if (the_questioned_one == note_d) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_c ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_a ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }
        if (the_questioned_one == note_e) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_c ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1 && the_chosen_one >.8) {
            sheetmusic[0][i]=note_a ;
          }
        }
        if (the_questioned_one == note_g) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_c ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8&& the_chosen_one >.5) {
            sheetmusic[0][i]=note_e ;
          }
          if (the_chosen_one <=1  && the_chosen_one >.8) {
            sheetmusic[0][i]=note_a2 ;
          }
        }


        if (the_questioned_one == note_a2) {
          if (the_chosen_one <=.3) {
            sheetmusic[0][i]=note_c ;
          }
          if (the_chosen_one <=.5 && the_chosen_one >.3) {
            sheetmusic[0][i]=note_d ;
          }
          if (the_chosen_one <=.8 && the_chosen_one >.5) {
            sheetmusic[0][i]=note_g ;
          }
          if (the_chosen_one <=1&& the_chosen_one >.8) {
            sheetmusic[0][i]=note_e ;
          }
        }
      }
      //__________________________________________________________________
      // note selection based on data from tabs statistics website
      if (the_third_chosen_one>=3){
        if (i>=3) {
          if (the_questioned_one == note_a) {
            if (the_chosen_one <=.11) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.11+.24 && the_chosen_one >.11) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.11+.24+.20 && the_chosen_one >.11+.24) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.11+.24+.20+.06 && the_chosen_one >.11+.24+.20) {
              sheetmusic[0][i]=note_e ;
            }
            if (the_chosen_one <=.11+.24+.20+.06+.06 && the_chosen_one >.11+.24+.20+.06) {
              sheetmusic[0][i]=note_d ;
            }
          }

          if (the_questioned_one == note_as_bf) {
            if (the_chosen_one <=.20) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.20+.23 && the_chosen_one >.20) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.20+.23+.06 && the_chosen_one >.20+.23) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.20+.23+.06+.11+.05 && the_chosen_one >.20+.23+.060) {
              sheetmusic[0][i]=note_a ;
            }
          }

          if (the_questioned_one == note_b) {
            if (the_chosen_one <=.18+.16+.05) {
              sheetmusic[0][i]=note_e ;
            }
            if (the_chosen_one <=.18+.16+.14+.05 && the_chosen_one >.18+.16+.05) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.18+.16+.14+.05+.05 && the_chosen_one >.18+.16+.14+.05) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.18+.16+.14+.05+.05+.05 && the_chosen_one >.18+.16+.14+.05+.05) {
              sheetmusic[0][i]=note_a ;
            }
            if (the_chosen_one <=.18+.16+.14+.05+.05+.05+.09 && the_chosen_one >.18+.16+.14+.05+.05) {
              sheetmusic[0][i]=note_as_bf ;
            }
          }

          if (the_questioned_one == note_c) {
            if (the_chosen_one <=.19) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.19+.25 && the_chosen_one >.19) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.19+.25+.10 && the_chosen_one >.19+.25) {
              sheetmusic[0][i]=note_a ;
            }
            if (the_chosen_one <=.19+.25+.10+.06 && the_chosen_one >.19+.25+.10) {
              sheetmusic[0][i]=note_d ;
            }
            if (the_chosen_one <=.19+.25+.10+.06+.05 && the_chosen_one >.19+.25+.10+.06) {
              sheetmusic[0][i]=note_b ;
            }
          }

          if (the_questioned_one == note_cs_df) {
            if (the_chosen_one <=.19) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.19+.25 && the_chosen_one >.19) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.19+.25+.10 && the_chosen_one >.19+.25) {
              sheetmusic[0][i]=note_a ;
            }
            if (the_chosen_one <=.19+.25+.10+.06 && the_chosen_one >.19+.25+.10) {
              sheetmusic[0][i]=note_d ;
            }
            if (the_chosen_one <=.19+.25+.10+.06+.05 && the_chosen_one >.19+.25+.10+.06) {
              sheetmusic[0][i]=note_b ;
            }
          }

          if (the_questioned_one == note_d) {
            if (the_chosen_one <=.13) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.13+.16 && the_chosen_one >.13) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.13+.16+.16 && the_chosen_one >.13+.16) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.13+.16+.16+.09 && the_chosen_one >.13+.16+.16) {
              sheetmusic[0][i]=note_e ;
            }
            if (the_chosen_one <=.13+.16+.16+.09+.18 && the_chosen_one >.13+.16+.16+.09) {
              sheetmusic[0][i]=note_a ;
            }
          }

          if (the_questioned_one == note_ds_ef) {
            if (the_chosen_one <=.13) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.13+.16 && the_chosen_one >.13) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.13+.16+.16 && the_chosen_one >.13+.16) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.13+.16+.16+.09 && the_chosen_one >.13+.16+.16) {
              sheetmusic[0][i]=note_e ;
            }
            if (the_chosen_one <=.13+.16+.16+.09+.18 && the_chosen_one >.13+.16+.16+.09) {
              sheetmusic[0][i]=note_a ;
            }
          }

          if (the_questioned_one == note_e) {
            if (the_chosen_one <=.05) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.05+.08 && the_chosen_one >.05) {
              sheetmusic[0][i]=note_d ;
            }
            if (the_chosen_one <=.05+.08+.08 && the_chosen_one >.05+.08) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.05+.08+.08+.33 && the_chosen_one >.05+.08+.08) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.05+.08+.08+.33+.26 && the_chosen_one >.05+.08+.08+.33) {
              sheetmusic[0][i]=note_a ;
            }
          }

          if (the_questioned_one == note_f) {
            if (the_chosen_one <=.29) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.29+.29 && the_chosen_one >.29) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.29+.29+.05 && the_chosen_one >.29+.29) {
              sheetmusic[0][i]=note_d ;
            }
            if (the_chosen_one <=.29+.29+.05+.10 && the_chosen_one >.29+.29+.05) {
              sheetmusic[0][i]=note_a ;
            }
          }

          if (the_questioned_one == note_fs_gf) {
            if (the_chosen_one <=.29) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.29+.29 && the_chosen_one >.29) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.29+.29+.05 && the_chosen_one >.29+.29) {
              sheetmusic[0][i]=note_d ;
            }
            if (the_chosen_one <=.29+.29+.05+.10 && the_chosen_one >.29+.29+.05) {
              sheetmusic[0][i]=note_a ;
            }
          }

          if (the_questioned_one == note_g) {
            if (the_chosen_one <=.21) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.21+.06 && the_chosen_one >.21) {
              sheetmusic[0][i]=note_d ;
            }
            if (the_chosen_one <=.21+.06+.21 && the_chosen_one >.21+.06) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.21+.06+.21+.26  && the_chosen_one >.21+.06+.21) {
              sheetmusic[0][i]=note_a2 ;
            }
          }

          if (the_questioned_one == note_gs_af) {
            if (the_chosen_one <=.21) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.21+.06 && the_chosen_one >.21) {
              sheetmusic[0][i]=note_d ;
            }
            if (the_chosen_one <=.21+.06+.21 && the_chosen_one >.21+.06) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.21+.06+.21+.26  && the_chosen_one >.21+.06+.21) {
              sheetmusic[0][i]=note_a2 ;
            }
          }

          if (the_questioned_one == note_a2) {
            if (the_chosen_one <=.11) {
              sheetmusic[0][i]=note_c ;
            }
            if (the_chosen_one <=.11+.24 && the_chosen_one >.11) {
              sheetmusic[0][i]=note_f ;
            }
            if (the_chosen_one <=.11+.24+.20 && the_chosen_one >.11+.24) {
              sheetmusic[0][i]=note_g ;
            }
            if (the_chosen_one <=.11+.24+.20+.06 && the_chosen_one >.11+.24+.20) {
              sheetmusic[0][i]=note_e ;
            }
            if (the_chosen_one <=.11+.24+.20+.06+.06 && the_chosen_one >.11+.24+.20+.06) {
              sheetmusic[0][i]=note_d ;
            }
          }
        }
      }
      //__________________________________________________________________
      //sets the off sets of the notes for the other instrument
      // format == sheetmusic[0][i]+|-x
      sheetmusic[1][i] = sheetmusic[0][i]-4;//instrument 2
      sheetmusic[2][i] = sheetmusic[0][i]-7;//instrument 3
      sheetmusic[3][i] = sheetmusic[0][i]+12;//intrument 4
      //__________________________________________________________________
      //determines note length based of the_second_chosen_one
      double notetype = 1.5;//length of a whole note
      if (the_second_chosen_one<=1) {//if whole note
        sheetmusic[0][i+1] =notetype/1;
        sheetmusic[1][i+1] =notetype/1;
        sheetmusic[2][i+1] =notetype/1;
        sheetmusic[3][i+1] =notetype/1;
        currentlength+=(notetype/1);//increases the time counter by the length of the note
      }
      if (the_second_chosen_one==2) {//if half note
        sheetmusic[0][i+1] =notetype/2;
        sheetmusic[1][i+1] =notetype/2;
        sheetmusic[2][i+1] =notetype/2;
        sheetmusic[3][i+1] =notetype/2;
        currentlength+=(notetype/2);//increases the time counter by the length of the note
      }
      if (the_second_chosen_one>=3) {//if quater note
        sheetmusic[0][i+1] =notetype/4;
        sheetmusic[1][i+1] =notetype/4;
        sheetmusic[2][i+1] =notetype/4;
        sheetmusic[3][i+1] =notetype/4;
        currentlength+=(notetype/3);//increases the time counter by the length of the note
      }
      //__________________________________________________________________
      sheetmusic[0][i+2] = the_third_chosen_one;//stores the key selector in the sheetmusic
      //__________________________________________________________________
      i +=3; //increases while loop counter by the number of spots in the array that have been filled during this iteration
      //random non uniform music gen  END:
      //__________________________________________________________________
    }
  }
  return (sheetmusic);
}
//______________________________________________________________________________________
// read in notes from standard input and play them on standard audio
public static void main(String[] args) {
  //defines variables for each note
  double note_a = 0;
  double note_as_bf = 1;
  double note_b = 2;
  double note_c = 3;
  double note_cs_df = 4;
  double note_d = 5;
  double note_ds_ef = 6;
  double note_e = 7;
  double note_f = 8;
  double note_fs_gf = 9;
  double note_g = 10;
  double note_gs_af = 11;
  double note_a2 = 12;
  //__________________________________________________________________
  //sets the canvas size for the visualizer
  StdDraw.setCanvasSize(1500, 850);
  //__________________________________________________________________
  //parses the inputed request length value
  int length = 0;
  if (args.length > 0) {
    try {
      length = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.err.println("Argument" + args[0] + " must be an integer.");
      System.exit(1);
    }
  }
  //__________________________________________________________________
  //defines calls the sheet music generation function
  double[][]sheetmusicuncut=generatemusic(length);
  //__________________________________________________________________
  //defines variable that contains the whole song for saving to a file
  double[] output = new double[1];
  output[0] = 0;
  //__________________________________________________________________
  //trims excess zeros from the ends of the 2d array
  int l=sheetmusicuncut[0].length;
  int itrim = sheetmusicuncut[0].length-1;
  while (sheetmusicuncut[0][itrim]==0) {
    l=itrim;
    itrim--;
  }
  double[][]sheetmusic= new double[4][l];
  for (int i =0;i<l;i++ ) {
    sheetmusic[0][i]=sheetmusicuncut[0][i];
    sheetmusic[1][i]=sheetmusicuncut[1][i];
    sheetmusic[2][i]=sheetmusicuncut[2][i];
    sheetmusic[3][i]=sheetmusicuncut[3][i];
  }
  for (int i = 0; i< (sheetmusic[0].length)/3; i+=3) {
    double duration = sheetmusic[0][i+1];
    double duration1 = sheetmusic[1][i+1];
    double duration2 = sheetmusic[2][i+1];
    double duration3 = sheetmusic[3][i+1];
    //__________________________________________________________________
    //accesses the sheetmusic array and extracts the notes and durations and puts them into local variables
    double temp =  sheetmusic[0][i];
    int pitch = (int) temp;
    double temp1 =  sheetmusic[1][i];
    int pitch1 = (int) temp1;
    double temp2 =  sheetmusic[2][i];
    int pitch2 = (int) temp2;
    double temp3 =  sheetmusic[3][i];
    int pitch3 = (int) temp3;
    //__________________________________________________________________
    //______________________________________________________________________________________
    //__________________________________________________________________
    //Processes the sheetmusic into waves
    //__________________________________________________________________
    double fadeinloc = duration/2;
    double[] d = fadeinnote(pitch, duration, fadeinloc);//processes sheetmusic using fadein modifier
    //__________________________________________________________________
    double fadeinoutloc = duration/2;
    double[] q = fadeinoutnote(pitch,pitch1,pitch2,pitch3, duration,duration1,duration2,duration3, fadeinoutloc);//processes using fadeinout,harmonic, and multi intrument modifier
    //__________________________________________________________________
    double[] a = minorchordnote(pitch, duration);//processes with minor chord modifier
    //__________________________________________________________________
    double[] b = harmonicnote(pitch, duration);//processes with harmonic modifier
    //__________________________________________________________________
    double[] c = majorchordnote(pitch, duration);//processes with major chord modifier
    //__________________________________________________________________
    double fadeoutloc = duration/2;
    double[] e = fadeoutnote(pitch, duration, fadeoutloc);//processes sheetmusic using fadeout modifier
    //__________________________________________________________________
    double cliploc = .1 ;
    double[] f = clipnote(pitch, duration, cliploc);//processes sheetmusic using clip modifier
    //__________________________________________________________________
    int ecoloc = 1 ;
    double[] g = eco2(pitch, duration, ecoloc, sheetmusic, i);//processes sheetmusic using eco for 2 note modifier
    int delaytime = 1 ;
    int delayvolume = 1 ;
    //__________________________________________________________________
    double[] z = delay(q, delayvolume, delaytime);//processes sheetmusic using delay modifier
    //__________________________________________________________________


    //makes an array for the currently playing note
    double[] currentnote = new double[1+ (int) (StdAudio.SAMPLE_RATE * duration)];
    //__________________________________________________________________
    //paramzone
    double volume=.5;//.5= normal, quieter<.5, louder>.5
    //---------------------------------------------------------------------------------------------------------------------------------------
    // currentnote = changeVolume("", volume) set "" equal to one of the leters of the above functions to play the
    //random music through that function
    currentnote = changeVolume(q, volume);
    //---------------------------------------------------------------------------------------------------------------------------------------
    //__________________________________________________________________
    //redefines variables
    double[] currentnotefinal =  currentnote;
    int ii = i;
    //__________________________________________________________________
    //Multithreading
    final CountDownLatch latch = new CountDownLatch(2);
    final long start = System.nanoTime();
    ExecutorService es = Executors.newCachedThreadPool();
    //defines first runnable for playing the music
    Runnable runnable = new Runnable() {
      public void run() {
        StdAudio.play(currentnotefinal);
        latch.countDown();
      }
    };
    //defines second runnable for the visualizer
    Runnable runnable1 = new Runnable() {
      public void run() {
        StdDraw.clear();
        //System.out.println(sheetmusic[0][ii]);
        //draws the illuminated letters to represent notes visualizer
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(.55, .9, "Note="+Double.toString(sheetmusic[0][ii]));
        StdDraw.text(.55, .8, "Duration="+Double.toString(duration));
        if (sheetmusic[0][ii] == 0) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.6, .9, "A");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.6, .9, "A");
        }
        if (sheetmusic[0][ii] == 1) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.625, .9, "#A/B-");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.625, .9, "#A/B-");
        }
        if (sheetmusic[0][ii] == 2) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.5);
          StdDraw.text(.65, .9, "B");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.65, .9, "B");
        }
        if (sheetmusic[0][ii] == 3) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.66, .9, "C");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.66, .9, "C");
        }
        if (sheetmusic[0][ii] == 4) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.685, .9, "#C/D-");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.685, .9, "#C/D-");
        }
        if (sheetmusic[0][ii] == 5) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.71, .9, "D");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.71, .9, "D");
        }
        if (sheetmusic[0][ii] == 6) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.735, .9, "#D/E-");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.735, .9, "#D/E-");
        }
        if (sheetmusic[0][ii] == 7) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.76, .9, "E");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.76, .9, "E");
        }
        if (sheetmusic[0][ii] == 8) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.77, .9, "F");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.77, .9, "F");
        }
        if (sheetmusic[0][ii] == 9) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.795, .9, "#F/G-");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.795, .9, "#F/G-");
        }
        if (sheetmusic[0][ii] == 10) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.82, .9, "G");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.82, .9, "G");
        }
        if (sheetmusic[0][ii] == 11) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.845, .9, "#G/A-");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.845, .9, "#G/A-");
        }
        if (sheetmusic[0][ii] == 12) {
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.87, .9, "A");
        }
        else {
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.1);
          StdDraw.text(.87, .9, "A");
        }
        //__________________________________________________________________

        //Draws circle pattern in visualizer
        StdDraw.setPenRadius(Math.abs(pitch*.05+.01));
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(.3, 1-.2);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(.1, 1-.2);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(.2, 1-.3);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.point(.2, 1-.1);
        //__________________________________________________________________
        //draws the wavelike representation of the sheetmusic in the visualizer
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);

        if (40>=((sheetmusic[0].length/3)-ii)) {
          for (int i = 0;i<(sheetmusic[0].length/3)-ii ;i++ ) {
            StdDraw.line(.1+.01*i, sheetmusic[0][ii+3*i]*.05, .1+.01*i, .2);
          }
        }
        else{
          for (int i = 0;i<40 ;i++ ) {
            StdDraw.line(.1+.01*i, sheetmusic[0][ii+3*i]*.05, .1+.01*i, .2);
          }}
        //__________________________________________________________________
          //delay
          /*  try
          {
          Thread.sleep(1000*(int)duration +100);
        }
        catch(InterruptedException ex)
        {
        //  Thread.currentThread().interrupt();
      }*/
      //__________________________________________________________________
      latch.countDown();//thread alingment
    }
  };
  es.submit(runnable);//runs thread 1
  es.submit(runnable1);//runs thread 2
  //__________________________________________________________________
  //ensures that threads remain aligned
  try
  {
    latch.await();
  }
  catch(InterruptedException ex)
  {
    Thread.currentThread().interrupt();
  }
  //__________________________________________________________________
  // 1 nanoseconds is equal to 1/1000000000 of a second.
  long total = (System.nanoTime() - start) / 1000000;
  //System.out.println("total time: " + total);
  //shuts down Multithreading
  es.shutdown();
  //adds the latest note to the output array
  output = MusicTools.concatArray(output, q);//the second param must be set to the same variable as you have selected under the modifier area
  //saves the note as a wav file
  StdAudio.save("current note.wav", currentnote);
  //__________________________________________________________________
}
//prints the sheetmusic to the console
MusicTools.printArray(sheetmusic[0]);
//saves the whole song as a wav
StdAudio.save("full_song.wav", output);
//quits the program
System.exit(1);
}

//function for a short delay
public static void sleep(int i) {
  try {
    Thread.sleep(i * 10);
  } catch (InterruptedException ie) {}
  }
}
