

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
  public static double[] fadenote(int pitch, double duration, double fadepoint, int fadeloc) {
    double hz = 440.0 * Math.pow(2, pitch / 12.0);
    double hz2 = 440.0 * Math.pow(2, pitch+3 / 12.0);
    double[] a  = tone(hz, duration-fadepoint);
    double[] a2 = tone(hz2, fadepoint);
    return MusicTools.concatArray(a, a2);
  }



  // read in notes from standard input and play them on standard audio
  public static void main(String[] args) {
    // read in pitch-duration pairs from standard input
    while (!StdIn.isEmpty()) {
      int pitch = StdIn.readInt();
      double duration = StdIn.readDouble();
      double[] a = minorchordnote(pitch, duration);
      //StdAudio.play(a);
      StdAudio.save("minorchordnote.wav", a);
      double[] b = harmonicnote(pitch, duration);
      //StdAudio.play(b);
      StdAudio.save("harmonicnote.wav", b);
      double[] c = majorchordnote(pitch, duration);
      //StdAudio.play(c);
      StdAudio.save("majorchordnote.wav", c);
      double fadepoint = .1;
      int fadeloc = 1;
      double[] d = fadenote(pitch, duration, fadepoint, fadeloc);
      StdAudio.play(d);
      StdAudio.save("fadenote.wav", d);
    }
    while (!StdIn.isEmpty()) {

    }
  }
}
