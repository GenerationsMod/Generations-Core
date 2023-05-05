package generations.gg.generations.core.generationscore.util;

public final class MathUtils {

   public static boolean between(double value, int min, int max) {
      return value >= min && value <= max;
   }

   public static int clamp(int value, int min, int max) {
      return value < min ? min : (Math.min(value, max));
   }

   public static short clamp(short value, short min, short max) {
      return value < min ? min : (value > max ? max : value);
   }

   public static long clamp(long value, long min, long max) {
      return value < min ? min : (Math.min(value, max));
   }

   public static byte clamp(byte $this$clamp, byte min, byte max) {
      return $this$clamp < min ? min : ($this$clamp > max ? max : $this$clamp);
   }

   public static char clamp(char value, char min, char max) {
      return value < min ? min : (value > max ? max : value);
   }

   public static float clamp(float $this$clamp, float min, float max) {
      return $this$clamp < min ? min : (Math.min($this$clamp, max));
   }

   public static double clamp(double $this$clamp, double min, double max) {
      return $this$clamp < min ? min : (Math.min($this$clamp, max));
   }

   public static <T extends Comparable<T>> T clamp(T value, T min, T max) {
      return value.compareTo(min) < 0 ? min : (value.compareTo(max) > 0 ? max : value);
   }
}
