package think_in_java.enumerated;

import think_in_java.net.mindview.util.Generator;

import java.util.Random;

/**
 * Created by Yang on 2015/3/27.
 */

enum CartoonCharacter implements Generator<CartoonCharacter>{
    SLAPPY,SPNANKY,PUNCHY,SILLY,BOUNCY,NUTTY,BOB,TOM;
    private Random random = new Random(47);

    public CartoonCharacter next() {
        return values()[random.nextInt(values().length)];
    }
}
public class EnumImpl {
    public static <T> void printNext(Generator<T> rg) {
        System.out.println(rg.next());
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++) {
            printNext(cc);
        }
    }
}
