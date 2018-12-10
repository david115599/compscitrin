
public class NameThatTune {

    public static void main(String[] args) {
      while (!StdIn.isEmpty()) {
          int pitch = StdIn.readInt();
          double duration = StdIn.readDouble();
          double[] a = note(pitch, duration);
          StdAudio.play(a);
          StdAudio.save("harmonic.wav", a);
      }

    }
    
    public static void Harmonic(int note, double duration) {
      PlayThatTuneDeluxe.note(note,duration);
    }
}
