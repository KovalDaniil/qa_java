import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LionTest {

    @Mock
    private Feline feline;

//    @Test
//    public void testLionWithMaleHasMane() throws Exception {
//        Lion lion = new Lion("Самец", feline);
//
//        assertTrue(lion.doesHaveMane());
//    }
//
//    @Test
//    public void testLionWithFemaleNoMane() throws Exception {
//        Lion lion = new Lion("Самка", feline);
//
//        assertFalse(lion.doesHaveMane());
//    }

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    public void testLionSexAndManeRelationship(String sex, boolean expectedHasMane) throws Exception {
        Lion lion = new Lion(sex, feline);

        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test
    public void testLionInvalidSexThrowsException() {
        assertThrows(Exception.class, () -> new Lion("Неизвестный", feline));
    }

    @Test
    public void testGetKittensDelegatesToFeline() throws Exception {
        when(feline.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", feline);
        int kittens = lion.getKittens();

        verify(feline, times(1)).getKittens();
        assertEquals(3, kittens);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testGetKittensWithVariousCounts(int expectedKittens) throws Exception {
        when(feline.getKittens()).thenReturn(expectedKittens);

        Lion lion = new Lion("Самец", feline);
        int actualKittens = lion.getKittens();

        verify(feline, times(1)).getKittens();
        assertEquals(expectedKittens, actualKittens);
    }

    @Test
    public void testGetFoodDelegatesToFelineEatMeat() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", feline);
        List<String> actualFood = lion.getFood();

        verify(feline, times(1)).eatMeat();
        assertEquals(expectedFood, actualFood);
    }
}
