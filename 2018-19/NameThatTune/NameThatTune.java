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



  // read in notes from standard input and play them on standard audio
  public static void main(String[] args) {
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

    //__________________________________________________________________
    //random music gen  START:

    double[] sheetmusic = new double[length];
    for (int i = 0;i<length-1;i+=3 ) {
      double the_chosen_one = ((int) (Math.random()));
      double the_second_chosen_one = (Math.random()*.5);
      double the_third_chosen_one = (double) ((int) (Math.random()*5));
      sheetmusic[i] = the_chosen_one;
      if (i>=3) {
        if (sheetmusic[i]-3 == 0) {
          if (the_chosen_one <=.11) {
            sheetmusic[i]=3 ;
          }
          if (the_chosen_one <=.11+.24 && the_chosen_one >.11) {
            sheetmusic[i]=6 ;
          }
          if (the_chosen_one <=.11+.24+.20 && the_chosen_one >.11+.24) {
            sheetmusic[i]=7 ;
          }
          if (the_chosen_one <=.11+.24+.20+.06 && the_chosen_one >.11+.24+.20) {
            sheetmusic[i]=5 ;
          }
          if (the_chosen_one <=.11+.24+.20+.06+.06 && the_chosen_one >.11+.24+.20+.06) {
            sheetmusic[i]=4 ;
          }
        }

        if (sheetmusic[i]-3 == 1) {
          if (the_chosen_one <=.07) {
            sheetmusic[i]=5 ;
          }
          if (the_chosen_one <=.07+.24 && the_chosen_one >.07) {
            sheetmusic[i]=3 ;
          }
          if (the_chosen_one <=.07+.24+.05 && the_chosen_one >.07+.24) {
            sheetmusic[i]=7 ;
          }
          if (the_chosen_one <=.11+.24+.20+.06 && the_chosen_one >.11+.24+.20) {
            sheetmusic[i]=5 ;
          }
          if (the_chosen_one <=.11+.24+.20+.06+.06 && the_chosen_one >.11+.24+.20+.06) {
            sheetmusic[i]=4 ;
          }
        }

      }
      //sheetmusic[i] = the_chosen_one;
      sheetmusic[i+1] = the_second_chosen_one;
      sheetmusic[i+2] = the_third_chosen_one;
    }
    //random music gen  END:
    //__________________________________________________________________

    // read in pitch-duration pairs from standard input
    double[] output = new double[1];
    output[0] = 0;

    for (int i = 0; i<= sheetmusic.length-3; i+=3) {
      //__________________________________________________________________
      double temp =  sheetmusic[i];
      int pitch = (int) temp;
      double duration = sheetmusic[i+1];
      //__________________________________________________________________
      double fadeinloc = duration/2;
      double[] d = fadeinnote(pitch, duration, fadeinloc);
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
            //System.out.println(sheetmusic[ii]);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.text(.73, .9, Double.toString(sheetmusic[ii]));
          if (sheetmusic[ii] == 0 || sheetmusic[ii] == 7) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.75, .9, "A");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.75, .9, "A");
          }
          if (sheetmusic[ii] == 1 || sheetmusic[ii] == 8) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.76, .9, "B");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.76, .9, "B");
          }
          if (sheetmusic[ii] == 2 || sheetmusic[ii] == 9) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.77, .9, "C");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.77, .9, "C");
          }
          if (sheetmusic[ii] == 3 || sheetmusic[ii] == 10) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.78, .9, "D");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.78, .9, "D");
          }
          if (sheetmusic[ii] == 4 || sheetmusic[ii] == 11) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.79, .9, "E");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.79, .9, "E");
          }
          if (sheetmusic[ii] == 5 || sheetmusic[ii] == 12) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.80, .9, "F");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.80, .9, "F");
          }
          if (sheetmusic[ii] == 6 || sheetmusic[ii] == 13) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.81, .9, "G");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.81, .9, "G");
          }
          if (sheetmusic[ii] == 7 || sheetmusic[ii] == 14) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.82, .9, "A");
          }
          else {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.1);
            StdDraw.text(.82, .9, "A");
          }


          StdDraw.setPenRadius(Math.abs(pitch*.05));
          StdDraw.setPenColor(StdDraw.GREEN);
          StdDraw.point(.3, 1-.2);
          StdDraw.setPenColor(StdDraw.BLUE);
          StdDraw.point(.1, 1-.2);
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.point(.2, 1-.3);
          StdDraw.setPenColor(StdDraw.YELLOW);
          StdDraw.point(.2, 1-.1);
          try
          {
            Thread.sleep(1000*(int)duration +100);
          }
          catch(InterruptedException ex)
          {
            //  Thread.currentThread().interrupt();
          }
          StdDraw.clear();
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
      Thread.sleep(i * 1000);
    } catch (InterruptedException ie) {}
    }
  }
