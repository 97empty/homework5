package homew5;

import java.util.Arrays;

    public class Main {

        static final int size = 4;
        static final int h = size / 2;

        public static void main(String[] args) {
            method1();
            method2();
        }

        private static void fillArray(float[] a) {
            Arrays.fill(a, 1);
        }

        private static void method1() {
            long time = System.currentTimeMillis();
            float[] a = new float[size];
            fillArray(a);
            System.out.println("method1 before " + Arrays.toString(a));
            for (int i = 0; i < a.length; i++)
                a[i] = (float) (a[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            System.out.println("execution time " + (System.currentTimeMillis() - time));
            System.out.println("method1 after " + Arrays.toString(a));
        }

        private static void method2a(float[] a, int h) {

            for (int i = 0; i < a.length; i++) {
                a[i] = (float) (a[i] * Math.sin(0.2f + (i + h) / 5.0) * Math.cos(0.2f + (i + h) / 5.0)
                        * Math.cos(0.4f + (i + h) / 2.0));
            }
        }

        private static void method2() {
            long time = System.currentTimeMillis();
            float[] a = new float[size];
            fillArray(a);
            System.out.println("method2 before " + Arrays.toString(a));

            float[] a1 = new float[h];
            float[] a2 = new float[h];
            System.arraycopy(a, 0, a1, 0, h);
            System.arraycopy(a, h, a2, 0, h);

            System.out.println("a1 before " + Arrays.toString(a1));
            System.out.println("a2 before " + Arrays.toString(a2));

            new Thread(() -> homew5.Main.method2a(a1, 0)).start();
            new Thread(() -> homew5.Main.method2a(a2, h)).start();


            System.out.println("a1 after " + Arrays.toString(a1));
            System.out.println("a2 after " + Arrays.toString(a2));

            System.arraycopy(a1, 0, a, 0, h);
            System.arraycopy(a2, 0, a, h, h);
            System.out.println("execution time " + (System.currentTimeMillis() - time));
            System.out.println("method2 after " + Arrays.toString(a));
        }
    }