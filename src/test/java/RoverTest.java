import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    private Rover rover;
    private Planet planet;

    /**
     * Runs before each test, creates rover and planet objects that we'll be using in our tests
     */
    @BeforeEach
    void setUp() {
        rover = new Rover();
        planet = new Planet(5, 8);
    }

    @Test
    public void testLandWithXOutOfBoundsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            rover.land(planet, 6, 1, Rover.Direction.N);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            rover.land(planet, -1, 1, Rover.Direction.N);
        });

    }

    @Test
    public void testLandWithYOutOfBoundsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            rover.land(planet, 1, 9, Rover.Direction.N);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            rover.land(planet, 1, -1, Rover.Direction.N);
        });
    }

    @Test
    public void testLandSuccessful() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.N);

        assertEquals(planet, rover.getPlanet());
        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.N, rover.getDirection());
    }

    @Test
    public void testScanForRockFalse() throws IllegalArgumentException {
        planet.createRock(1, 2);
        rover.land(planet, 3, 4, Rover.Direction.N);

        assertFalse(rover.scanForRock());

        planet.createRock(3, 2);

        assertFalse(rover.scanForRock());
    }

    @Test
    public void testScanForRockTrue() throws IllegalArgumentException {
        planet.createRock(3, 4);
        rover.land(planet, 3, 4, Rover.Direction.N);

        assertTrue(rover.scanForRock());
    }

    @Test
    public void testMoveForwardNorth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.N);
        rover.moveForward();

        assertEquals(3, rover.getPositionX());
        assertEquals(5, rover.getPositionY());
    }

    @Test
    public void testMoveForwardSouth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.S);
        rover.moveForward();

        assertEquals(3, rover.getPositionX());
        assertEquals(3, rover.getPositionY());
    }

    @Test
    public void testMoveForwardEast() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.E);
        rover.moveForward();

        assertEquals(4, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
    }

    @Test
    public void testMoveForwardWest() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.W);
        rover.moveForward();

        assertEquals(2, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
    }

    @Test
    public void testMoveBackwardNorth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.N);
        rover.moveBackward();

        assertEquals(3, rover.getPositionX());
        assertEquals(3, rover.getPositionY());
    }

    @Test
    public void testMoveBackwardSouth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.S);
        rover.moveBackward();

        assertEquals(3, rover.getPositionX());
        assertEquals(5, rover.getPositionY());
    }

    @Test
    public void testMoveBackwardEast() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.E);
        rover.moveBackward();

        assertEquals(2, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
    }

    @Test
    public void testMoveBackwardWest() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.W);
        rover.moveBackward();

        assertEquals(4, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
    }

    @Test
    public void testTurnLeftFromNorth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.N);
        rover.turnLeft();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.W, rover.getDirection());
    }

    @Test
    public void testTurnLeftFromSouth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.S);
        rover.turnLeft();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.E, rover.getDirection());
    }

    @Test
    public void testTurnLeftFromEast() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.E);
        rover.turnLeft();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.N, rover.getDirection());
    }

    @Test
    public void testTurnLeftFromWest() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.W);
        rover.turnLeft();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.S, rover.getDirection());
    }

    @Test
    public void testTurnRightFromNorth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.N);
        rover.turnRight();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.E, rover.getDirection());
    }

    @Test
    public void testTurnRightFromSouth() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.S);
        rover.turnRight();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.W, rover.getDirection());
    }

    @Test
    public void testTurnRightFromEast() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.E);
        rover.turnRight();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.S, rover.getDirection());
    }

    @Test
    public void testTurnRightFromWest() throws IllegalArgumentException {
        rover.land(planet, 3, 4, Rover.Direction.W);
        rover.turnRight();

        assertEquals(3, rover.getPositionX());
        assertEquals(4, rover.getPositionY());
        assertEquals(Rover.Direction.N, rover.getDirection());
    }

    @Test
    public void testGoForwardBeyondBoundryX() {
        rover.land(planet, 5, 8, Rover.Direction.E);
        rover.moveForward();
        assertEquals(0, rover.getPositionX());
        assertEquals(8, rover.getPositionY());

    }

    @Test
    public void testGoForwardBeyondBoundryY() {
        rover.land(planet, 5, 8, Rover.Direction.N);
        rover.moveForward();
        assertEquals(0, rover.getPositionY());
        assertEquals(5, rover.getPositionX());

    }

    @Test
    public void testGoForwardBelowBoundryX() {
        rover.land(planet, 0, 8, Rover.Direction.W);
        rover.moveForward();
        assertEquals(5, rover.getPositionX());
        assertEquals(8, rover.getPositionY());

    }

    @Test
    public void testGoForwardBelowBoundryY() {
        rover.land(planet, 5, 0, Rover.Direction.S);
        rover.moveForward();
        assertEquals(8, rover.getPositionY());
        assertEquals(5, rover.getPositionX());

    }

    @Test
    public void testGoBackwardBeyondBoundryX() {
        rover.land(planet, 5, 8, Rover.Direction.W);
        rover.moveBackward();
        assertEquals(0, rover.getPositionX());
        assertEquals(8, rover.getPositionY());

    }

    @Test
    public void testGoBackwardBeyondBoundryY() {
        rover.land(planet, 5, 8, Rover.Direction.S);
        rover.moveBackward();
        assertEquals(0, rover.getPositionY());
        assertEquals(5, rover.getPositionX());

    }

    @Test
    public void testGoBackwardBelowBoundryX() {
        rover.land(planet, 0, 8, Rover.Direction.E);
        rover.moveBackward();
        assertEquals(5, rover.getPositionX());
        assertEquals(8, rover.getPositionY());

    }

    @Test
    public void testGoBackwardBelowBoundryY() {
        rover.land(planet, 5, 0, Rover.Direction.N);
        rover.moveBackward();
        assertEquals(8, rover.getPositionY());
        assertEquals(5, rover.getPositionX());

    }

}
