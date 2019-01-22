
//java NameThatTune < elise.txt

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

    public static double[] chordnote(int pitch, double duration) {
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double[] a  = tone(hz, duration);
        double[] hi = tone(2*hz, duration);
        double[] lo = tone(hz/2, duration);
        double[] h  = sum(hi, lo, 0.5, 0.5);
        return sum(h, lo, 0.5, 0.5);
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

//a = {0,1,2,3,4,5}
//interval = 2
//delay- repeats a tone based on an echo volume and time interval
public static double[] delay(double[] a, int interval, int volume) {
double[] delayed = new double[a.length+a.length*(interval+1)];
//[1,2,3,4,5]
//interval = 3, volume = 2
//[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]
//[1,2,3,4,5,0,0,0,0,0,0.5,1,1.5,2,2.5,0,0,0,0,0,0.25,0.5,0.75,1,1.25]
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


    // read in notes from standard input and play them on standard audio
    public static void main(String[] args) {

        // read in pitch-duration pairs from standard input
        while (!StdIn.isEmpty()) {
            int pitch = StdIn.readInt();
            double duration = StdIn.readDouble();
            double[] a = harmonicnote(pitch, duration);
            StdAudio.play(delay(a,3,4));
            StdAudio.save("harmonic.wav", delay(a,3,4));

        }
    }
}
