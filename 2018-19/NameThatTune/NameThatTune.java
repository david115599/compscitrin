import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    // read in pitch-duration pairs from standard input
    double[] output = new double[1];
    output[0] = 0;
    while (!StdIn.isEmpty()) {
      int pitch = StdIn.readInt();
      double duration = StdIn.readDouble();
      double[] a = minorchordnote(pitch, duration);
      //StdAudio.play(a);
      //output = MusicTools.concatArray(output, a);
      StdAudio.save("minorchordnote.wav", a);

      double[] b = harmonicnote(pitch, duration);
      //StdAudio.play(b);
      //output = MusicTools.concatArray(output, b);
      StdAudio.save("harmonicnote.wav", b);

      double[] c = majorchordnote(pitch, duration);
      //StdAudio.play(c);
      //output = MusicTools.concatArray(output, c);
      StdAudio.save("majorchordnote.wav", c);

      double fadeinloc = duration/2;
      double[] d = fadeinnote(pitch, duration, fadeinloc);
      final CountDownLatch latch = new CountDownLatch(2);
      final long start = System.nanoTime();
      ExecutorService es = Executors.newCachedThreadPool();
      Runnable runnable = new Runnable() {
        public void run() {
          StdAudio.play(d);
          latch.countDown();
        }
      };
      Runnable runnable1 = new Runnable() {
        public void run() {
          StdDraw.setPenRadius(Math.abs(pitch*.1));
          StdDraw.setPenColor(StdDraw.GREEN);
          StdDraw.point(.75, .5);
          StdDraw.setPenColor(StdDraw.BLUE);
          StdDraw.point(.25, .5);
          StdDraw.setPenColor(StdDraw.RED);
          StdDraw.point(.5, .8);
          StdDraw.setPenColor(StdDraw.YELLOW);
          StdDraw.point(.5, .2);

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
      StdAudio.save("fadeinnote.wav", d);

      double fadeoutloc = duration/2;
      double[] e = fadeoutnote(pitch, duration, fadeoutloc);
      //StdAudio.play(e);
      //output = MusicTools.concatArray(output, e);
      StdAudio.save("fadeoutnote.wav", e);

      double cliploc = .1 ;
      double[] f = clipnote(pitch, duration, cliploc);
      //StdAudio.play(f);
      //output = MusicTools.concatArray(output, f);
      StdAudio.save("clip.wav", f);
    }
    //MusicTools.printArray(output);
    StdAudio.save("full_song.wav", output);
  }
  public static void sleep(int i) {
    try {
      Thread.sleep(i * 1000);
    } catch (InterruptedException ie) {}
    }
  }
