package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FelineTest {

    @Mock
    private Animal animal;

    @Test
    public void testEatMeatReturnsCarnivoreFood() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        
        assertNotNull(food);
        assertEquals(3, food.size());
        assertTrue(food.contains("Животные"));
        assertTrue(food.contains("Птицы"));
        assertTrue(food.contains("Рыба"));
    }

    @Test
    public void testGetFamilyReturnsFelineFamily() {
        Feline feline = new Feline();
        String family = feline.getFamily();
        
        assertEquals("Кошачьи", family);
    }

    @Test
    public void testGetKittensDefaultReturnsOne() {
        Feline feline = new Feline();
        int kittens = feline.getKittens();
        
        assertEquals(1, kittens);
    }

    @Test
    public void testGetKittensWithParameterReturnsCount() {
        Feline feline = new Feline();
        int expectedKittens = 5;
        int actualKittens = feline.getKittens(expectedKittens);
        
        assertEquals(expectedKittens, actualKittens);
    }

    @Test
    public void testGetKittensWithZeroReturnsZero() {
        Feline feline = new Feline();
        int kittens = feline.getKittens(0);
        
        assertEquals(0, kittens);
    }
}
