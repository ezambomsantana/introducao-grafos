/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permutation;

/**
 *
 * @author ezamb
 */
public class Permutation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int N = 13;
        int[] Value = new int[N];;
        for (int i = 0; i < N; i++) {
            Value[i] = 0;
        }
        long inicio = System.currentTimeMillis();
        visit(Value, N, 0);
        long fim = System.currentTimeMillis();
        System.out.println((fim - inicio) / 1000);
    }

    static void print(int[] v, int size) {
        if (v.length != 0) {
            for (int i = 0; i < size; i++) {
                System.out.print(v[i]);
            }
            System.out.println("");
        }
    } // print

    static int level = -1;

    static void visit(int[] Value, int N, int k) {
        level = level + 1;
        Value[k] = level;

        if (level == N) {
          //  print(Value, N);
        } else {
            for (int i = 0; i < N; i++) {
                if (Value[i] == 0) {
                    visit(Value, N, i);
                }
            }
        }

        level = level - 1;
        Value[k] = 0;
    }

}
