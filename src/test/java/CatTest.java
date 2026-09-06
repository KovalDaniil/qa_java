import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatTest {

    @Mock
    private Feline feline;

    @Test
    public void testGetSoundReturnsMeow() {
        Cat cat = new Cat(feline);
        String sound = cat.getSound();

        assertEquals("Мяу", sound);
    }

    @Test
    public void testGetFoodCallsPredatorEatMeat() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(feline);
        List<String> actualFood = cat.getFood();

        verify(feline, times(1)).eatMeat();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFoodWithEmptyList() throws Exception {
        List<String> emptyFood = List.of();
        when(feline.eatMeat()).thenReturn(emptyFood);

        Cat cat = new Cat(feline);
        List<String> actualFood = cat.getFood();

        verify(feline, times(1)).eatMeat();
        assertTrue(actualFood.isEmpty());
    }
}
