package pl.ania;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lukasz on 2016-11-22.
 */
public class AniaTest {
    @Test
    public void shouldBeTheSame() throws Exception {
        //given
        Ania ania = new Ania("Ania", 26);
        //when

        //then
        assertTrue(ania == ania);
        assertTrue(ania.equals(ania));

    }

    @Test
    public void shouldBeDifferent() throws Exception {
        //given
        Ania ania = new Ania("Ania", 26);
        Ania bania = new Ania("Ania", 26);
        //when

        //then
        assertTrue(ania != bania);
        assertTrue(!ania.equals(bania));
    }

    @Test
    public void shouldEqualWhenNameIsTheSame() throws Exception {
        //given
        Ania ania = new Ania("Ania", 26);
        Ania bania = new Ania("Ania", 26);
        //when

        //then
        assertTrue(ania != bania);
        assertTrue(ania.equals(bania));
    }

    @Test
    public void shouldBePresentOnTheList() throws Exception {
        //given
        Ania ania = new Ania("Ania", 26);
        Ania bania = new Ania("Ania", 26);
        List<Ania> aniaList = new ArrayList<>();
        aniaList.add(ania);
        //when

        //then
        assertTrue(aniaList.contains(bania));

        //and
        //when
        aniaList.add(bania);

        //then
        assertEquals(aniaList.size(), 2);

        aniaList.remove(ania);
        assertEquals(aniaList.size(), 1);

    }
}