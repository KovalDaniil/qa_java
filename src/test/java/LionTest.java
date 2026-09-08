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

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    public void testLionSexAndManeRelationship(boolean expectedHasMane) {
        Lion lion = new Lion(feline);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test
    public void testGetKittensDelegatesToFeline() {
        when(feline.getKittens()).thenReturn(3);

        Lion lion = new Lion(feline);
        int kittens = lion.getKittens();

        verify(feline, times(1)).getKittens();
        assertEquals(3, kittens);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testGetKittensWithVariousCounts(int expectedKittens) {
        when(feline.getKittens()).thenReturn(expectedKittens);

        Lion lion = new Lion(feline);
        int actualKittens = lion.getKittens();

        verify(feline, times(1)).getKittens();
        assertEquals(expectedKittens, actualKittens);
    }

    @Test
    public void testGetFoodDelegatesToFelineEatMeat() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion(feline);
        List<String> actualFood = lion.getFood();

        verify(feline, times(1)).eatMeat();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testLionConstructorWithoutSexDoesNotSetMane() {
        Lion lion = new Lion(feline);

        assertFalse(lion.doesHaveMane());
    }
}
