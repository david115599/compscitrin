import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.PrintWriter;
import java.io.File;

public class NameThatTune {

  // return weighted sum of two arrays
  public static double[] sum(double[] a, double[] b, double awt, double bwt) {

    // precondition: arrays have the same length
    assert a.length == b.length;

    // compute the weighted sum
    double[] c = new double[a.length];
    for (int i = 0; i < a.length; i++) {
      c[i] = a[i]*awt + b[i]*bwt;
    }
    return c;
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

  // create a note with harmonics of the given pitch and duration
  // (where 0 = concert A)
  public static double[] harmonicnote(int pitch, double duration) {
    double hz = 440.0 * Math.pow(2, pitch / 12.0);
    double[] a  = tone(hz, duration);
    double[] hi = tone(2*hz, duration);
    double[] lo = tone(hz/2, duration);
    double[] h  = sum(hi, lo, 0.5, 0.5);
    return sum(h, lo, 0.5, 0.5);
  }

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

  public static double[] fadeinoutnote(int pitch, double duration, double fadeloc) {
    double hz = 440.0 * Math.pow(2, pitch / 12.0);
      double hzhi = 440.0 * Math.pow(2, (pitch+12) / 12.0);
        double hzlo = 440.0 * Math.pow(2, (pitch-12) / 12.0);
    double[] a  = fadeinouttone(hz, duration,fadeloc);
    double[] hi = fadeinouttone(hzhi, duration,fadeloc);
    double[] lo = fadeinouttone(hzlo, duration,fadeloc);
    double[] h  = sum(hi, lo, 0.5, 0.5);
    return sum(h, a, 0.1, 0.4);//a
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

//______________________________________________________________________________________
  public static double[] generatemusic(int length) {
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
    double[] sheetmusic = new double[length*40];
    sheetmusic[0]=0;
    sheetmusic[1] = .25;
    sheetmusic[2] = 1;
    {  int i = 3;
    while (currentlength<=length) {
        double the_chosen_one = ((Math.random()));
        double the_second_chosen_one = (int)((Math.random()*5+1));
        double the_third_chosen_one = (double) ((int) (Math.random()*5));
        //sheetmusic[i] = (double)(int)(the_chosen_one*12);
        double the_questioned_one = sheetmusic[i-3];
          if (the_questioned_one == note_a) {
            if (the_chosen_one <=.25) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.5 && the_chosen_one >.25) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.75 && the_chosen_one >.5) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=1 && the_chosen_one >.75) {
              sheetmusic[i]=note_e ;
            }
          }

          if (the_questioned_one == note_c) {
            if (the_chosen_one <=.25) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.5 && the_chosen_one >.25) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.75 && the_chosen_one >.5) {
              sheetmusic[i]=note_a ;
            }
            if (the_chosen_one <=1 && the_chosen_one >.75) {
              sheetmusic[i]=note_e ;
            }
          }

          if (the_questioned_one == note_d) {
            if (the_chosen_one <=.25) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.5 && the_chosen_one >.25) {
              sheetmusic[i]=note_a ;
            }
            if (the_chosen_one <=.75 && the_chosen_one >.5) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=1 && the_chosen_one >.75) {
              sheetmusic[i]=note_e ;
            }
          }
          if (the_questioned_one == note_e) {
            if (the_chosen_one <=.25) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.5 && the_chosen_one >.25) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.75 && the_chosen_one >.5) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=1 && the_chosen_one >.75) {
              sheetmusic[i]=note_a ;
            }
          }
          if (the_questioned_one == note_g) {
            if (the_chosen_one <=.25) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.5 && the_chosen_one >.25) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.75&& the_chosen_one >.5) {
              sheetmusic[i]=note_e ;
            }
            if (the_chosen_one <=1  && the_chosen_one >.75) {
              sheetmusic[i]=note_a2 ;
            }
          }


          if (the_questioned_one == note_a2) {
            if (the_chosen_one <=.25) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.5 && the_chosen_one >.25) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.75 && the_chosen_one >.25) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=1&& the_chosen_one >.75) {
              sheetmusic[i]=note_e ;
            }
          }
      /*  if (i>=3) {
          if (the_questioned_one == note_a) {
            if (the_chosen_one <=.11) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.11+.24 && the_chosen_one >.11) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.11+.24+.20 && the_chosen_one >.11+.24) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.11+.24+.20+.06 && the_chosen_one >.11+.24+.20) {
              sheetmusic[i]=note_e ;
            }
            if (the_chosen_one <=.11+.24+.20+.06+.06 && the_chosen_one >.11+.24+.20+.06) {
              sheetmusic[i]=note_d ;
            }
          }

          if (the_questioned_one == note_as_bf) {
            if (the_chosen_one <=.20) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.20+.23 && the_chosen_one >.20) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.20+.23+.06 && the_chosen_one >.20+.23) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.20+.23+.06+.11+.05 && the_chosen_one >.20+.23+.060) {
              sheetmusic[i]=note_a ;
            }
          }

          if (the_questioned_one == note_b) {
            if (the_chosen_one <=.18+.16+.05) {
              sheetmusic[i]=note_e ;
            }
            if (the_chosen_one <=.18+.16+.14+.05 && the_chosen_one >.18+.16+.05) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.18+.16+.14+.05+.05 && the_chosen_one >.18+.16+.14+.05) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.18+.16+.14+.05+.05+.05 && the_chosen_one >.18+.16+.14+.05+.05) {
              sheetmusic[i]=note_a ;
            }
            if (the_chosen_one <=.18+.16+.14+.05+.05+.05+.09 && the_chosen_one >.18+.16+.14+.05+.05) {
              sheetmusic[i]=note_as_bf ;
            }
          }

          if (the_questioned_one == note_c) {
            if (the_chosen_one <=.19) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.19+.25 && the_chosen_one >.19) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.19+.25+.10 && the_chosen_one >.19+.25) {
              sheetmusic[i]=note_a ;
            }
            if (the_chosen_one <=.19+.25+.10+.06 && the_chosen_one >.19+.25+.10) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.19+.25+.10+.06+.05 && the_chosen_one >.19+.25+.10+.06) {
              sheetmusic[i]=note_b ;
            }
          }

          if (the_questioned_one == note_cs_df) {
            if (the_chosen_one <=.19) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.19+.25 && the_chosen_one >.19) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.19+.25+.10 && the_chosen_one >.19+.25) {
              sheetmusic[i]=note_a ;
            }
            if (the_chosen_one <=.19+.25+.10+.06 && the_chosen_one >.19+.25+.10) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.19+.25+.10+.06+.05 && the_chosen_one >.19+.25+.10+.06) {
              sheetmusic[i]=note_b ;
            }
          }

          if (the_questioned_one == note_d) {
            if (the_chosen_one <=.13) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.13+.16 && the_chosen_one >.13) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.13+.16+.16 && the_chosen_one >.13+.16) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.13+.16+.16+.09 && the_chosen_one >.13+.16+.16) {
              sheetmusic[i]=note_e ;
            }
            if (the_chosen_one <=.13+.16+.16+.09+.18 && the_chosen_one >.13+.16+.16+.09) {
              sheetmusic[i]=note_a ;
            }
          }

          if (the_questioned_one == note_ds_ef) {
            if (the_chosen_one <=.13) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.13+.16 && the_chosen_one >.13) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.13+.16+.16 && the_chosen_one >.13+.16) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.13+.16+.16+.09 && the_chosen_one >.13+.16+.16) {
              sheetmusic[i]=note_e ;
            }
            if (the_chosen_one <=.13+.16+.16+.09+.18 && the_chosen_one >.13+.16+.16+.09) {
              sheetmusic[i]=note_a ;
            }
          }

          if (the_questioned_one == note_e) {
            if (the_chosen_one <=.05) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.05+.08 && the_chosen_one >.05) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.05+.08+.08 && the_chosen_one >.05+.08) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.05+.08+.08+.33 && the_chosen_one >.05+.08+.08) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.05+.08+.08+.33+.26 && the_chosen_one >.05+.08+.08+.33) {
              sheetmusic[i]=note_a ;
            }
          }

          if (the_questioned_one == note_f) {
            if (the_chosen_one <=.29) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.29+.29 && the_chosen_one >.29) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.29+.29+.05 && the_chosen_one >.29+.29) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.29+.29+.05+.10 && the_chosen_one >.29+.29+.05) {
              sheetmusic[i]=note_a ;
            }
          }

          if (the_questioned_one == note_fs_gf) {
            if (the_chosen_one <=.29) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.29+.29 && the_chosen_one >.29) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.29+.29+.05 && the_chosen_one >.29+.29) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.29+.29+.05+.10 && the_chosen_one >.29+.29+.05) {
              sheetmusic[i]=note_a ;
            }
          }

          if (the_questioned_one == note_g) {
            if (the_chosen_one <=.21) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.21+.06 && the_chosen_one >.21) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.21+.06+.21 && the_chosen_one >.21+.06) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.21+.06+.21+.26  && the_chosen_one >.21+.06+.21) {
              sheetmusic[i]=note_a2 ;
            }
          }

          if (the_questioned_one == note_gs_af) {
            if (the_chosen_one <=.21) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.21+.06 && the_chosen_one >.21) {
              sheetmusic[i]=note_d ;
            }
            if (the_chosen_one <=.21+.06+.21 && the_chosen_one >.21+.06) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.21+.06+.21+.26  && the_chosen_one >.21+.06+.21) {
              sheetmusic[i]=note_a2 ;
            }
          }

          if (the_questioned_one == note_a2) {
            if (the_chosen_one <=.11) {
              sheetmusic[i]=note_c ;
            }
            if (the_chosen_one <=.11+.24 && the_chosen_one >.11) {
              sheetmusic[i]=note_f ;
            }
            if (the_chosen_one <=.11+.24+.20 && the_chosen_one >.11+.24) {
              sheetmusic[i]=note_g ;
            }
            if (the_chosen_one <=.11+.24+.20+.06 && the_chosen_one >.11+.24+.20) {
              sheetmusic[i]=note_e ;
            }
            if (the_chosen_one <=.11+.24+.20+.06+.06 && the_chosen_one >.11+.24+.20+.06) {
              sheetmusic[i]=note_d ;
            }
          }
        }*/
        //sheetmusic[i] = the_chosen_one;
        double notetype = 2;
        if (the_second_chosen_one<=1) {
          sheetmusic[i+1] =notetype/1;
                  currentlength+=notetype/1;
        }
        if (the_second_chosen_one==2) {
          sheetmusic[i+1] =notetype/2;
                  currentlength+=notetype/2;
        }
        if (the_second_chosen_one>=3) {
          sheetmusic[i+1] =notetype/4;
                  currentlength+=notetype/4;
        }
        /*if (the_second_chosen_one>=4) {
          sheetmusic[i+1] =notetype/the_second_chosen_one;
                  currentlength+=notetype/the_second_chosen_one;
        }*/
        sheetmusic[i+2] = the_third_chosen_one;
        i +=3;

      //random music gen  END:
      //__________________________________________________________________
    }
  }
    return (sheetmusic);
  }
  //______________________________________________________________________________________





  // read in notes from standard input and play them on standard audio
  public static void main(String[] args) {
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
    StdDraw.setCanvasSize(1500, 850);
    int length = 0;
    if (args.length > 0) {
      try {
        length = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[0] + " must be an integer.");
        System.exit(1);
      }
    }

double[]sheetmusic=generatemusic(length);
    // read in pitch-duration pairs from standard input
    double[] output = new double[1];
    output[0] = 0;

    for (int i = 0; i<= (sheetmusic.length-3)/3; i+=3) {
      double duration = sheetmusic[i+1];
      //__________________________________________________________________
      double temp =  sheetmusic[i];
      int pitch = (int) temp;
      //__________________________________________________________________
      double fadeinloc = duration/2;
      double[] d = fadeinnote(pitch, duration, fadeinloc);
      //__________________________________________________________________
      double fadeinoutloc = duration/2;
      double[] q = fadeinoutnote(pitch, duration, fadeinoutloc);
      //__________________________________________________________________
      double[] a = minorchordnote(pitch, duration);
      //__________________________________________________________________
      double[] b = harmonicnote(pitch, duration);
      //__________________________________________________________________
      double[] c = majorchordnote(pitch, duration);
      //__________________________________________________________________
      double fadeoutloc = duration/2;
      double[] e = fadeoutnote(pitch, duration, fadeoutloc);
      //__________________________________________________________________
      double cliploc = .1 ;
      double[] f = clipnote(pitch, duration, cliploc);
      //__________________________________________________________________

      double[] currentnote = new double[1+ (int) (StdAudio.SAMPLE_RATE * duration)];
      /*
      if (sheetmusic[i+2]== 1) {
      currentnote = a;
    }
    if (sheetmusic[i+2]== 2) {
    currentnote = b;
  }
  if (sheetmusic[i+2]== 3) {
  currentnote = c;
}
if (sheetmusic[i+2]== 4) {
currentnote = d;
}
if (sheetmusic[i+2]== 5) {
currentnote = e;
}
if (sheetmusic[i+2]== 0) {
currentnote = f;
}
*/



//---------------------------------------------------------------------------------------------------------------------------------------
currentnote = q;//remove, it disables ^^
//---------------------------------------------------------------------------------------------------------------------------------------




double[] currentnotefinal =  currentnote;
int ii = i;

final CountDownLatch latch = new CountDownLatch(2);
final long start = System.nanoTime();
ExecutorService es = Executors.newCachedThreadPool();
Runnable runnable = new Runnable() {
  public void run() {
    StdAudio.play(currentnotefinal);
    latch.countDown();
  }
};
Runnable runnable1 = new Runnable() {
  public void run() {
    StdDraw.clear();
    //System.out.println(sheetmusic[ii]);
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.text(.55, .9, Double.toString(sheetmusic[ii]));
    if (sheetmusic[ii] == 0) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.6, .9, "A");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.6, .9, "A");
    }
    if (sheetmusic[ii] == 1) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.625, .9, "#A/B-");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.625, .9, "#A/B-");
    }
    if (sheetmusic[ii] == 2) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.5);
      StdDraw.text(.65, .9, "B");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.65, .9, "B");
    }
    if (sheetmusic[ii] == 3) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.66, .9, "C");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.66, .9, "C");
    }
    if (sheetmusic[ii] == 4) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.685, .9, "#C/D-");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.685, .9, "#C/D-");
    }
    if (sheetmusic[ii] == 5) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.71, .9, "D");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.71, .9, "D");
    }
    if (sheetmusic[ii] == 6) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.735, .9, "#D/E-");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.735, .9, "#D/E-");
    }
    if (sheetmusic[ii] == 7) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.76, .9, "E");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.76, .9, "E");
    }
    if (sheetmusic[ii] == 8) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.77, .9, "F");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.77, .9, "F");
    }
    if (sheetmusic[ii] == 9) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.795, .9, "#F/G-");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.795, .9, "#F/G-");
    }
    if (sheetmusic[ii] == 10) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.82, .9, "G");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.82, .9, "G");
    }
    if (sheetmusic[ii] == 11) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.845, .9, "#G/A-");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.845, .9, "#G/A-");
    }
    if (sheetmusic[ii] == 12) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.87, .9, "A");
    }
    else {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(.1);
      StdDraw.text(.87, .9, "A");
    }


    StdDraw.setPenRadius(Math.abs(pitch*.05+.01));
    StdDraw.setPenColor(StdDraw.GREEN);
    StdDraw.point(.3, 1-.2);
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.point(.1, 1-.2);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.point(.2, 1-.3);
    StdDraw.setPenColor(StdDraw.YELLOW);
    StdDraw.point(.2, 1-.1);

    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(.01);

if (40>=((sheetmusic.length/3)-ii)) {
  for (int i = 0;i<(sheetmusic.length/3)-ii ;i++ ) {
    StdDraw.line(.1+.01*i, sheetmusic[ii+3*i]*.05, .1+.01*i, .2);
  }
}
else{
    for (int i = 0;i<40 ;i++ ) {
      StdDraw.line(.1+.01*i, sheetmusic[ii+3*i]*.05, .1+.01*i, .2);
    }}
  /*  try
    {
      Thread.sleep(1000*(int)duration +100);
    }
    catch(InterruptedException ex)
    {
      //  Thread.currentThread().interrupt();
    }*/
    latch.countDown();
  }
};
es.submit(runnable);
es.submit(runnable1);
try
{
  latch.await();
}
catch(InterruptedException ex)
{
  Thread.currentThread().interrupt();
}
// 1 nanoseconds is equal to 1/1000000000 of a second.
long total = (System.nanoTime() - start) / 1000000;
//System.out.println("total time: " + total);
es.shutdown();
output = MusicTools.concatArray(output, d);
StdAudio.save("current note.wav", currentnote);
//__________________________________________________________________
}
MusicTools.printArray(sheetmusic);
StdAudio.save("full_song.wav", output);
System.exit(1);
}


public static void sleep(int i) {
  try {
    Thread.sleep(i * 10);
  } catch (InterruptedException ie) {}
  }
}
