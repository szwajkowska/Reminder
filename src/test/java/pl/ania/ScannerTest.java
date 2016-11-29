package pl.ania;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class ScannerTest {

    @Test
    public void shouldTakeEverythingUntil() throws Exception {
        //given
        String string = "test\nbody\n2011.11.11\n";
        //when
        Scanner scanner = new Scanner(string);

        //then
        Assert.assertEquals(scanner.next(), "test");
        Assert.assertEquals(scanner.nextLine(), "");
        Assert.assertEquals(scanner.next(), "body");
        Assert.assertEquals(scanner.next(), "2011.11.11");
        Assert.assertEquals(scanner.nextLine(), "");
    }
}
