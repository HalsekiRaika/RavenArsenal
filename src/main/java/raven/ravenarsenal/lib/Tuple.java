package raven.ravenarsenal.lib;

public class Tuple {
    public static class Double<T1, T2> {
        private final T1 first;
        private final T2 second;

        private Double(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        public T1 getFirst() {
            return first;
        }

        public T2 getSecond() {
            return second;
        }

        public boolean isFirstEmpty() {
            return first == null;
        }

        public boolean isSecondEmpty() {
            return second == null;
        }
    }

    public static <T1, T2> Double<T1, T2> of(T1 t1, T2 t2) {
        return new Double<>(t1, t2);
    }
}
