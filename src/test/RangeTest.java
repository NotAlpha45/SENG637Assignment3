
import org.jfree.data.Range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for org.jfree.data.Range class.
 *
 * Base tests use Boundary Value Analysis and Weak Equivalence Class Testing
 * (requirements-based / black-box).
 *
 * Additional white-box tests target improved statement, branch, and condition
 * coverage, covering methods not exercised by the base suite.
 *
 * @author Maheen and Dipu
 */
public class RangeTest {

    private Range exampleRange;

    @Before
    public void setUp() {
        exampleRange = new Range(-1.0, 1.0);
    }

    // #region Range(double, double) constructor tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: Range(double lower, double upper)
     *
     * #####################################################
     */
    /**
     * Test constructor with a valid positive range. Equivalence Class: lower <
     * upper (valid input) Expected: Range is created with correct lower and
     * upper bounds
     */
    @Test
    public void testConstructorValidBounds() {
        Range r = new Range(1.0, 5.0);
        assertEquals(1.0, r.getLowerBound(), 0.0);
        assertEquals(5.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test constructor with equal lower and upper bounds. Boundary Value: lower
     * == upper (point range) Expected: Range is created successfully with a
     * single-point range
     */
    @Test
    public void testConstructorEqualBounds() {
        Range r = new Range(3.0, 3.0);
        assertEquals(3.0, r.getLowerBound(), 0.0);
        assertEquals(3.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test constructor with both bounds being negative. Equivalence Class:
     * Negative ranges (lower < upper, both negative) Expected: Range is created
     * with correct negative bounds
     */
    @Test
    public void testConstructorNegativeBounds() {
        Range r = new Range(-5.0, -1.0);
        assertEquals(-5.0, r.getLowerBound(), 0.0);
        assertEquals(-1.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test constructor when lower bound is greater than upper bound.
     * Equivalence Class: Invalid input — lower > upper Expected: Throws
     * IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLowerGreaterThanUpper() {
        new Range(5.0, 1.0);
    }
    // #endregion

    // #region getLowerBound() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: getLowerBound()
     *
     * #####################################################
     */
    /**
     * Test getLowerBound() with a positive range. Equivalence Class: Positive
     * ranges Expected: Returns 2.0 (the lower bound)
     */
    @Test
    public void testGetLowerBoundPositiveRange() {
        Range r = new Range(2.0, 8.0);
        assertEquals(2.0, r.getLowerBound(), 0.0);
    }

    /**
     * Test getLowerBound() with a fully negative range. Equivalence Class:
     * Negative ranges Expected: Returns -10.0 (the lower bound)
     */
    @Test
    public void testGetLowerBoundNegativeRange() {
        Range r = new Range(-10.0, -1.0);
        assertEquals(-10.0, r.getLowerBound(), 0.0);
    }

    /**
     * Test getLowerBound() using the shared setUp range [-1.0, 1.0].
     * Equivalence Class: Range spanning zero Expected: Returns -1.0 (the lower
     * bound)
     */
    @Test
    public void testGetLowerBoundFromSetUp() {
        assertEquals(-1.0, exampleRange.getLowerBound(), 0.0);
    }
    // #endregion

    // #region getUpperBound() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: getUpperBound()
     *
     * #####################################################
     */
    /**
     * Test getUpperBound() with a positive range. Equivalence Class: Positive
     * ranges Expected: Returns 8.0 (the upper bound)
     */
    @Test
    public void testGetUpperBoundPositiveRange() {
        Range r = new Range(2.0, 8.0);
        assertEquals(8.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test getUpperBound() with a fully negative range. Equivalence Class:
     * Negative ranges Expected: Returns -1.0 (the upper bound)
     */
    @Test
    public void testGetUpperBoundNegativeRange() {
        Range r = new Range(-10.0, -1.0);
        assertEquals(-1.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test getUpperBound() using the shared setUp range [-1.0, 1.0].
     * Equivalence Class: Range spanning zero Expected: Returns 1.0 (the upper
     * bound)
     */
    @Test
    public void testGetUpperBoundFromSetUp() {
        assertEquals(1.0, exampleRange.getUpperBound(), 0.0);
    }
    // #endregion

    // #region getCentralValue() tests
    /**
     * #####################################################
     *
     * Test suite for Range class Method: getCentralValue()
     *
     * #####################################################
     */
    /**
     * Test getCentralValue() with a positive range. Equivalence Class: Positive
     * ranges Expected: Returns midpoint (4.0)
     */
    @Test
    public void testGetCentralValueWithPositiveRange() {
        Range range = new Range(2.0, 6.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [2.0, 6.0] should be 4.0",
                4.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with a negative range. Equivalence Class: Negative
     * ranges Expected: Returns midpoint (-4.0)
     */
    @Test
    public void testGetCentralValueWithNegativeRange() {
        Range range = new Range(-6.0, -2.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [-6.0, -2.0] should be -4.0",
                -4.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with a range crossing zero. Equivalence Class:
     * Ranges spanning positive and negative values Expected: Returns midpoint
     * (0.0)
     */
    @Test
    public void testGetCentralValueCrossingZero() {
        Range range = new Range(-1.0, 1.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [-1.0, 1.0] should be 0.0",
                0.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with equal lower and upper bounds. Boundary Value:
     * Lower == Upper Expected: Returns the same value
     */
    @Test
    public void testGetCentralValueWithEqualBounds() {
        Range range = new Range(5.0, 5.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [5.0, 5.0] should be 5.0",
                5.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with a very small range. Boundary Value: Minimum
     * practical range Expected: Returns accurate midpoint despite small values
     */
    @Test
    public void testGetCentralValueWithVerySmallRange() {
        Range range = new Range(0.0000001, 0.0000003);
        double result = range.getCentralValue();
        assertEquals("Central value of range [0.0000001, 0.0000003] should be 0.0000002",
                0.0000002, result, 0.00000001d);
    }

    /**
     * Test getCentralValue() with a large range. Boundary Value: Large positive
     * values Expected: Returns accurate midpoint for large numbers
     */
    @Test
    public void testGetCentralValueWithLargeRange() {
        Range range = new Range(-1000000.0, 1000000.0);
        double result = range.getCentralValue();
        assertEquals("Central value of range [-1000000.0, 1000000.0] should be 0.0",
                0.0, result, 0.0000001d);
    }

    /**
     * Test getCentralValue() with decimal precision. Boundary Value:
     * Non-integer boundaries Expected: Handles decimal precision correctly
     */
    @Test
    public void testGetCentralValueWithDecimals() {
        Range range = new Range(1.5, 4.5);
        double result = range.getCentralValue();
        assertEquals("Central value of range [1.5, 4.5] should be 3.0",
                3.0, result, 0.0000001d);
    }
    // #endregion

    // #region getLength() tests
    /**
     * #####################################################
     *
     * Test suite for Range class Method: getLength()
     *
     * #####################################################
     */
    /**
     * Test getLength() with a positive range. Equivalence Class: Positive
     * ranges Expected: Returns length (4.0)
     */
    @Test
    public void testGetLengthWithPositiveRange() {
        Range range = new Range(2.0, 6.0);
        double result = range.getLength();
        assertEquals("Length of range [2.0, 6.0] should be 4.0",
                4.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with a negative range. Equivalence Class: Negative
     * ranges Expected: Returns positive length (4.0)
     */
    @Test
    public void testGetLengthWithNegativeRange() {
        Range range = new Range(-6.0, -2.0);
        double result = range.getLength();
        assertEquals("Length of range [-6.0, -2.0] should be 4.0",
                4.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with a range crossing zero. Equivalence Class: Ranges
     * spanning positive and negative values Expected: Returns total span (10.0)
     */
    @Test
    public void testGetLengthCrossingZero() {
        Range range = new Range(-4.0, 6.0);
        double result = range.getLength();
        assertEquals("Length of range [-4.0, 6.0] should be 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with equal lower and upper bounds. Boundary Value: Lower
     * == Upper Expected: Returns zero
     */
    @Test
    public void testGetLengthWithEqualBounds() {
        Range range = new Range(5.0, 5.0);
        double result = range.getLength();
        assertEquals("Length of range [5.0, 5.0] should be 0.0",
                0.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with a very small range. Boundary Value: Minimum
     * practical range Expected: Returns accurate length for very small values
     */
    @Test
    public void testGetLengthWithVerySmallRange() {
        Range range = new Range(0.0000001, 0.0000005);
        double result = range.getLength();
        assertEquals("Length of range [0.0000001, 0.0000005] should be 0.0000004",
                0.0000004, result, 0.000000001d);
    }

    /**
     * Test getLength() with a large range. Boundary Value: Large positive
     * values Expected: Returns accurate length for large numbers
     */
    @Test
    public void testGetLengthWithLargeRange() {
        Range range = new Range(1000000.0, 9000000.0);
        double result = range.getLength();
        assertEquals("Length of range [1000000.0, 9000000.0] should be 8000000.0",
                8000000.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with decimal boundaries. Boundary Value: Non-integer
     * boundaries Expected: Handles decimal precision correctly
     */
    @Test
    public void testGetLengthWithDecimals() {
        Range range = new Range(1.5, 4.7);
        double result = range.getLength();
        assertEquals("Length of range [1.5, 4.7] should be 3.2",
                3.2, result, 0.0000001d);
    }

    /**
     * Test getLength() with zero as lower bound. Boundary Value: Zero boundary
     * Expected: Returns upper bound value
     */
    @Test
    public void testGetLengthWithZeroLowerBound() {
        Range range = new Range(0.0, 10.0);
        double result = range.getLength();
        assertEquals("Length of range [0.0, 10.0] should be 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test getLength() with zero as upper bound. Boundary Value: Zero boundary
     * Expected: Returns absolute value of lower bound
     */
    @Test
    public void testGetLengthWithZeroUpperBound() {
        Range range = new Range(-10.0, 0.0);
        double result = range.getLength();
        assertEquals("Length of range [-10.0, 0.0] should be 10.0",
                10.0, result, 0.0000001d);
    }
    // #endregion

    // #region contains() tests
    /**
     * #####################################################
     *
     * Test suite for Range class Method: contains(double value)
     *
     * #####################################################
     */
    /**
     * Test contains() with a value below the lower bound. Boundary Value: Just
     * below Expected: Returns false (value is not contained)
     */
    @Test
    public void testContainsBelowLowerBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(1.0);
        assertEquals("Range [2.0, 6.0] should not contain 1.0",
                false, result);
    }

    /**
     * Test contains() with a value at the lower bound. Boundary Value: Just at
     * lower bound Expected: Returns true (value is contained)
     */
    @Test
    public void testContainsAtLowerBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(2.0);
        assertEquals("Range [2.0, 6.0] should contain 2.0",
                true, result);
    }

    /**
     * Test contains() with a value just after the lower bound. Boundary Value:
     * Just after lower bound Expected: Returns true (value is contained)
     */
    @Test
    public void testContainsJustAfterLowerBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(2.1);
        assertEquals("Range [2.0, 6.0] should contain 2.1",
                true, result);
    }

    /**
     * Test contains() with the midpoint value. Boundary Value: NOM (midpoint of
     * range) Equivalence Class: V3 — lower < value < upper (within range)
     * Expected: Returns true
     */
    @Test
    public void testContainsAtNominalMidpoint() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(4.0);
        assertEquals("Range [2.0, 6.0] should contain 4.0",
                true, result);
    }

    /**
     * Test contains() with a value just before the upper bound. Boundary Value:
     * Just before upper bound Expected: Returns true (value is contained)
     */
    @Test
    public void testContainsJustBeforeUpperBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(5.9);
        assertEquals("Range [2.0, 6.0] should contain 5.9",
                true, result);
    }

    /**
     * Test contains() with a value exactly at the upper bound. Boundary Value:
     * UB (upper) Equivalence Class: V4 — value = upper Expected: Returns true
     */
    @Test
    public void testContainsAtUpperBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(6.0);
        assertEquals("Range [2.0, 6.0] should contain 6.0",
                true, result);
    }

    /**
     * Test contains() with a value just above the upper bound. Boundary Value:
     * AUB (upper + 1) Equivalence Class: V5 — value > upper (above range)
     * Expected: Returns false
     */
    @Test
    public void testContainsAboveUpperBound() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.contains(7.0);
        assertEquals("Range [2.0, 6.0] should not contain 7.0",
                false, result);
    }

    /**
     * Test contains() with a very small range. Boundary Value Expected: Returns
     * true for values within the small range
     */
    @Test
    public void testContainsWithVerySmallRange() {
        Range range = new Range(0.0000002, 0.0000006);
        boolean result = range.contains(0.0000004);
        assertEquals("Range [0.0000002, 0.0000006] should contain 0.0000004",
                true, result);
    }

    /**
     * Test contains() with a large range. Boundary Value: Large positive values
     * Expected: Returns true for values within the large range
     */
    @Test
    public void testContainsWithLargeRange() {
        Range range = new Range(2000000.0, 6000000.0);
        boolean result = range.contains(4000000.0);
        assertEquals("Range [2000000.0, 6000000.0] should contain 4000000.0",
                true, result);
    }
    // #endregion

    // #region constrain() tests
    /**
     * #####################################################
     *
     * Test suite for Range class Method: constrain(double value)
     *
     * #####################################################
     */
    /**
     * Test constrain() with a value below the lower bound. Boundary Value:
     * Expected: Returns -5.0 (lower bound)
     */
    @Test
    public void testConstrainBelowLowerBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(-6.0);
        assertEquals("Constrain of -6.0 on range [-5.0, 10.0] should return -5.0",
                -5.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value exactly at the lower bound. Boundary Value:
     * Expected: Returns -5.0 (lower bound)
     */
    @Test
    public void testConstrainAtLowerBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(-5.0);
        assertEquals("Constrain of -5.0 on range [-5.0, 10.0] should return -5.0",
                -5.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value just above the lower bound. Boundary Value:
     * Expected: Returns -4.0
     */
    @Test
    public void testConstrainAboveLowerBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(-4.0);
        assertEquals("Constrain of -4.0 on range [-5.0, 10.0] should return -4.0",
                -4.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with the midpoint value. Boundary Value: Expected:
     * Returns 2.5
     */
    @Test
    public void testConstrainAtNominalMidpoint() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(2.5);
        assertEquals("Constrain of 2.5 on range [-5.0, 10.0] should return 2.5",
                2.5, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value just below the upper bound. Boundary Value:
     * Expected: Returns 9.0
     */
    @Test
    public void testConstrainBelowUpperBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(9.0);
        assertEquals("Constrain of 9.0 on range [-5.0, 10.0] should return 9.0",
                9.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value exactly at the upper bound. Boundary Value:
     * Expected: Returns 10.0
     */
    @Test
    public void testConstrainAtUpperBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(10.0);
        assertEquals("Constrain of 10.0 on range [-5.0, 10.0] should return 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a value just above the upper bound. Boundary Value:
     * Expected: Returns 10.0
     */
    @Test
    public void testConstrainAboveUpperBound() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(11.0);
        assertEquals("Constrain of 11.0 on range [-5.0, 10.0] should return 10.0",
                10.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a very small value. Expected: Returns the lower
     * bound for values below lower bound
     */
    @Test
    public void testConstrainWithVerySmallRange() {
        Range range = new Range(-5.0, 10);
        double result = range.constrain(-9999999.0);
        assertEquals("Constrain of -9999999.0 on range [-5.0, 10.0] should return -5.0",
                -5.0, result, 0.0000001d);
    }

    /**
     * Test constrain() with a very large value. Expected: Returns the upper
     * bound for values above the upper bound
     */
    @Test
    public void testConstrainWithLargeRange() {
        Range range = new Range(-5.0, 10.0);
        double result = range.constrain(9999999.0);
        assertEquals("Constrain of 9999999.0 on range [-5.0, 10.0] should return 10.0",
                10.0, result, 0.0000001d);
    }
    // #endregion

    // #region intersects(double, double) tests
    /**
     * #####################################################
     *
     * Test suite for Range class Method: intersects(double b0, double b1)
     *
     * #####################################################
     */
    /**
     * Test intersects() with an overlapping range. Expected: Returns true
     */
    @Test
    public void testIntersectsWithOverlappingRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(4.0, 8.0);
        assertEquals("Range [2.0, 6.0] should intersect with [4.0, 8.0]",
                true, result);
    }

    /**
     * Test intersects() with a range touching the lower boundary. Expected:
     * Returns true
     */
    @Test
    public void testIntersectsWithTouchingLowerBoundary() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(-2.0, 2.0);
        assertEquals("Range [2.0, 6.0] should intersect with [-2.0, 2.0]",
                true, result);
    }

    /**
     * Test intersects() with a range touching the upper boundary. Expected:
     * Returns true
     */
    @Test
    public void testIntersectsWithTouchingUpperBoundary() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(6.0, 10.0);
        assertEquals("Range [2.0, 6.0] should intersect with [6.0, 10.0]",
                true, result);
    }

    /**
     * Test intersects() with a disjoint range entirely to the left. Expected:
     * Returns false
     */
    @Test
    public void testIntersectsWithDisjointLeftRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(-2.0, 1.0);
        assertEquals("Range [2.0, 6.0] should not intersect with [-2.0, 1.0]",
                false, result);
    }

    /**
     * Test intersects() with a disjoint range entirely to the right. Expected:
     * Returns false
     */
    @Test
    public void testIntersectsWithDisjointRightRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(7.0, 9.0);
        assertEquals("Range [2.0, 6.0] should not intersect with [7.0, 9.0]",
                false, result);
    }

    /**
     * Test intersects() with an identical range. Expected: Returns true
     */
    @Test
    public void testIntersectsWithIdenticalRange() {
        Range range = new Range(2.0, 6.0);
        boolean result = range.intersects(2.0, 6.0);
        assertEquals("Range [2.0, 6.0] should intersect with [2.0, 6.0]",
                true, result);
    }

    /**
     * Test intersects() when b0 is below the lower bound and b1 is exactly at
     * the lower bound. Boundary Value: b1 == lower Branch: b0 <= lower is true, b1
     * > lower is false → returns false Expected: Returns false
     */
    @Test
    public void testIntersectsB0BelowLowerB1AtLower() {
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(0.0, 2.0));
    }

    /**
     * Test intersects() when b0 is exactly at the upper bound. Boundary Value:
     * b0 == upper Branch: b0 > lower, b0 < upper is false → returns false
     * Expected: Returns false
     */
    @Test
    public void testIntersectsB0AtUpper() {
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(8.0, 10.0));
    }
    // #endregion

    // #region intersects(Range) tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: intersects(Range range)
     *
     * #####################################################
     */
    /**
     * Test intersects(Range) when two ranges overlap. Equivalence Class:
     * Overlapping ranges Expected: Returns true
     */
    @Test
    public void testIntersectsRangeOverlapping() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 7.0);
        assertTrue(r1.intersects(r2));
    }

    /**
     * Test intersects(Range) when two ranges are disjoint. Equivalence Class:
     * Non-overlapping ranges Expected: Returns false
     */
    @Test
    public void testIntersectsRangeNonOverlapping() {
        Range r1 = new Range(1.0, 3.0);
        Range r2 = new Range(5.0, 9.0);
        assertFalse(r1.intersects(r2));
    }
    // #endregion

    // #region combine() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: combine(Range range1, Range range2)
     *
     * #####################################################
     */
    /**
     * Test combine() when range1 is null. Branch: range1 == null → returns
     * range2 Expected: Returns range2
     */
    @Test
    public void testCombineRange1Null() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combine(null, r));
    }

    /**
     * Test combine() when range2 is null. Branch: range2 == null → returns
     * range1 Expected: Returns range1
     */
    @Test
    public void testCombineRange2Null() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combine(r, null));
    }

    /**
     * Test combine() when both ranges are null. Branch: range1 == null →
     * returns range2 (which is also null) Expected: Returns null
     */
    @Test
    public void testCombineBothNull() {
        assertNull(Range.combine(null, null));
    }

    /**
     * Test combine() when both ranges are non-null. Equivalence Class: Both
     * ranges valid — returns spanning range Expected: Returns new Range(1.0,
     * 9.0) spanning both inputs
     */
    @Test
    public void testCombineBothNonNull() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 9.0);
        assertEquals(new Range(1.0, 9.0), Range.combine(r1, r2));
    }
    // #endregion

    // #region combineIgnoringNaN() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: combineIgnoringNaN(Range range1, Range
     * range2)
     *
     * #####################################################
     */
    /**
     * Test combineIgnoringNaN() when both ranges are null. Branch: range1 ==
     * null, range2 == null → returns null Expected: Returns null
     */
    @Test
    public void testCombineIgnoringNaNBothNull() {
        assertNull(Range.combineIgnoringNaN(null, null));
    }

    /**
     * Test combineIgnoringNaN() when range1 is null and range2 is a valid
     * range. Branch: range1 == null, range2 is not a NaN range → returns range2
     * Expected: Returns range2
     */
    @Test
    public void testCombineIgnoringNaNRange1NullRange2Valid() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combineIgnoringNaN(null, r));
    }

    /**
     * Test combineIgnoringNaN() when range1 is null and range2 is a NaN range.
     * Branch: range1 == null, range2.isNaNRange() is true → returns null
     * Expected: Returns null
     */
    @Test
    public void testCombineIgnoringNaNRange1NullRange2NaN() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(null, nanRange));
    }

    /**
     * Test combineIgnoringNaN() when range2 is null and range1 is a valid
     * range. Branch: range2 == null, range1.isNaNRange() is false → returns
     * range1 Expected: Returns range1
     */
    @Test
    public void testCombineIgnoringNaNRange2NullRange1Valid() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combineIgnoringNaN(r, null));
    }

    /**
     * Test combineIgnoringNaN() when range2 is null and range1 is a NaN range.
     * Branch: range2 == null, range1.isNaNRange() is true → returns null
     * Expected: Returns null
     */
    @Test
    public void testCombineIgnoringNaNRange2NullRange1NaN() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(nanRange, null));
    }

    /**
     * Test combineIgnoringNaN() when both ranges are valid (no NaN values).
     * Equivalence Class: Both ranges non-null, no NaN bounds Expected: Returns
     * new Range(1.0, 9.0) spanning both inputs
     */
    @Test
    public void testCombineIgnoringNaNBothNonNullValid() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 9.0);
        assertEquals(new Range(1.0, 9.0), Range.combineIgnoringNaN(r1, r2));
    }

    /**
     * Test combineIgnoringNaN() when both ranges are NaN ranges. Branch: Both
     * NaN → min/max produce NaN → returns null Expected: Returns null
     */
    @Test
    public void testCombineIgnoringNaNBothNaN() {
        Range nan1 = new Range(Double.NaN, Double.NaN);
        Range nan2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(nan1, nan2));
    }

    /**
     * Test combineIgnoringNaN() when range1 has NaN bounds and range2 is valid.
     * Branch: min/max with NaN d1 → returns d2 Expected: Returns new Range(2.0,
     * 6.0) using range2's bounds
     */
    @Test
    public void testCombineIgnoringNaNRange1NaNBoundRange2Valid() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(2.0, 6.0);
        assertEquals(new Range(2.0, 6.0), Range.combineIgnoringNaN(r1, r2));
    }

    /**
     * Test combineIgnoringNaN() when range2 has NaN bounds and range1 is valid.
     * Branch: min/max with NaN d2 → returns d1 Expected: Returns new Range(2.0,
     * 6.0) using range1's bounds
     */
    @Test
    public void testCombineIgnoringNaNRange2NaNBoundRange1Valid() {
        Range r1 = new Range(2.0, 6.0);
        Range r2 = new Range(Double.NaN, Double.NaN);
        assertEquals(new Range(2.0, 6.0), Range.combineIgnoringNaN(r1, r2));
    }
    // #endregion

    // #region expandToInclude() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: expandToInclude(Range range, double
     * value)
     *
     * #####################################################
     */
    /**
     * Test expandToInclude() when the range is null. Branch: range == null →
     * returns new Range(value, value) Expected: Returns Range(5.0, 5.0)
     */
    @Test
    public void testExpandToIncludeNullRange() {
        Range r = Range.expandToInclude(null, 5.0);
        assertEquals(new Range(5.0, 5.0), r);
    }

    /**
     * Test expandToInclude() when the value is below the lower bound. Boundary
     * Value: value < lower Branch: value < range.getLowerBound() → returns new
     * Range(value, upper) Expected: Returns Range(0.0, 8.0)
     */
    @Test
    public void testExpandToIncludeValueBelowLower() {
        Range r = new Range(2.0, 8.0);
        assertEquals(new Range(0.0, 8.0), Range.expandToInclude(r, 0.0));
    }

    /**
     * Test expandToInclude() when the value is above the upper bound. Boundary
     * Value: value > upper Branch: value > range.getUpperBound() → returns new
     * Range(lower, value) Expected: Returns Range(2.0, 10.0)
     */
    @Test
    public void testExpandToIncludeValueAboveUpper() {
        Range r = new Range(2.0, 8.0);
        assertEquals(new Range(2.0, 10.0), Range.expandToInclude(r, 10.0));
    }

    /**
     * Test expandToInclude() when the value is within the range. Equivalence
     * Class: Value already contained in range Branch: value within bounds →
     * returns the original range object unchanged Expected: Returns the same
     * Range object (no expansion needed)
     */
    @Test
    public void testExpandToIncludeValueWithinRange() {
        Range r = new Range(2.0, 8.0);
        assertSame(r, Range.expandToInclude(r, 5.0));
    }
    // #endregion

    // #region expand() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: expand(Range range, double
     * lowerMargin, double upperMargin)
     *
     * #####################################################
     */
    /**
     * Test expand() with valid positive margins. Equivalence Class: Normal
     * expansion with positive margins Calculation: range [2,6], length=4;
     * lower=2−4×0.25=1, upper=6+4×0.5=8 Expected: Returns Range(1.0, 8.0)
     */
    @Test
    public void testExpandNormal() {
        Range r = new Range(2.0, 6.0);
        assertEquals(new Range(1.0, 8.0), Range.expand(r, 0.25, 0.5));
    }

    /**
     * Test expand() when the range argument is null. Branch:
     * ParamChecks.nullNotPermitted throws IllegalArgumentException Expected:
     * Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpandNullRange() {
        Range.expand(null, 0.25, 0.5);
    }

    /**
     * Test expand() when negative margins cause the computed lower to exceed
     * upper. Branch: lower > upper after expansion → both are collapsed to
     * their midpoint Calculation: range [0,1], length=1; lower=1.5, upper=−0.5
     * → mid=0.5 Expected: Returns Range(0.5, 0.5)
     */
    @Test
    public void testExpandLowerExceedsUpperAfterExpansion() {
        Range result = Range.expand(new Range(0.0, 1.0), -1.5, -1.5);
        assertEquals(new Range(0.5, 0.5), result);
    }
    // #endregion

    // #region shift(Range, double) tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: shift(Range base, double delta)
     *
     * #####################################################
     */
    /**
     * Test shift() with a positive delta. Equivalence Class: Positive delta,
     * range stays positive Expected: Returns Range(3.0, 5.0)
     */
    @Test
    public void testShiftPositiveDelta() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(3.0, 5.0), Range.shift(r, 2.0));
    }

    /**
     * Test shift() with a negative delta. Equivalence Class: Negative delta,
     * range stays positive Expected: Returns Range(1.0, 3.0)
     */
    @Test
    public void testShiftNegativeDelta() {
        Range r = new Range(2.0, 4.0);
        assertEquals(new Range(1.0, 3.0), Range.shift(r, -1.0));
    }

    /**
     * Test shift() when the base range is null. Branch:
     * ParamChecks.nullNotPermitted throws IllegalArgumentException Expected:
     * Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testShiftNullBase() {
        Range.shift(null, 1.0);
    }
    // #endregion

    // #region shift(Range, double, boolean) tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: shift(Range base, double delta,
     * boolean allowZeroCrossing)
     *
     * #####################################################
     */
    /**
     * Test shift() with allowZeroCrossing=true. Branch: allowZeroCrossing is
     * true → adds delta directly without clamping Expected: Returns Range(2.0,
     * 4.0)
     */
    @Test
    public void testShiftAllowZeroCrossingTrue() {
        Range r = new Range(-3.0, -1.0);
        assertEquals(new Range(2.0, 4.0), Range.shift(r, 5.0, true));
    }

    /**
     * Test shift() with allowZeroCrossing=false when values are positive and do
     * not cross zero. Branch: value > 0, delta > 0 → Math.max(v+delta, 0) =
     * v+delta Expected: Returns Range(3.0, 5.0)
     */
    @Test
    public void testShiftNoZeroCrossingPositiveValueNoCross() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(3.0, 5.0), Range.shift(r, 2.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when a positive value would
     * cross zero. Branch: value > 0, v+delta < 0 → Math.max(v+delta, 0) = 0
     * Expected: Returns Range(0.0, 0.0) (clamped at zero)
     */
    @Test
    public void testShiftNoZeroCrossingPositiveValueCrossesZero() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(0.0, 0.0), Range.shift(r, -5.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when values are negative and do
     * not cross zero. Branch: value < 0, delta < 0 → Math.min(v+delta, 0) =
     * v+delta Expected: Returns Range(-5.0, -3.0)
     */
    @Test
    public void testShiftNoZeroCrossingNegativeValueNoCross() {
        Range r = new Range(-4.0, -2.0);
        assertEquals(new Range(-5.0, -3.0), Range.shift(r, -1.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when a negative value would
     * cross zero. Branch: value < 0, v+delta > 0 → Math.min(v+delta, 0) = 0
     * Expected: Returns Range(0.0, 0.0) (clamped at zero)
     */
    @Test
    public void testShiftNoZeroCrossingNegativeValueCrossesZero() {
        Range r = new Range(-2.0, -1.0);
        assertEquals(new Range(0.0, 0.0), Range.shift(r, 5.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when initial value is exactly
     * zero. Boundary Value: value == 0 Branch: value == 0 → returns value +
     * delta (no clamping) Expected: Returns Range(3.0, 3.0)
     */
    @Test
    public void testShiftNoZeroCrossingZeroValue() {
        Range r = new Range(0.0, 0.0);
        assertEquals(new Range(3.0, 3.0), Range.shift(r, 3.0, false));
    }
    // #endregion

    // #region scale() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: scale(Range base, double factor)
     *
     * #####################################################
     */
    /**
     * Test scale() with a valid positive factor. Equivalence Class: Positive
     * factor Expected: Returns Range(2.0, 6.0) (each bound multiplied by 2)
     */
    @Test
    public void testScaleNormal() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(2.0, 6.0), Range.scale(r, 2.0));
    }

    /**
     * Test scale() with a factor of zero. Boundary Value: factor == 0 Expected:
     * Returns Range(0.0, 0.0) (all bounds collapse to zero)
     */
    @Test
    public void testScaleByZero() {
        Range r = new Range(1.0, 5.0);
        assertEquals(new Range(0.0, 0.0), Range.scale(r, 0.0));
    }

    /**
     * Test scale() with a negative factor. Branch: factor < 0 → throws
     * IllegalArgumentException Expected: Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testScaleNegativeFactor() {
        Range.scale(new Range(1.0, 3.0), -1.0);
    }

    /**
     * Test scale() when the base range is null. Branch:
     * ParamChecks.nullNotPermitted throws IllegalArgumentException Expected:
     * Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testScaleNullBase() {
        Range.scale(null, 2.0);
    }
    // #endregion

    // #region equals() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: equals(Object obj)
     *
     * #####################################################
     */
    /**
     * Test equals() with two ranges that have identical bounds. Branch:
     * instanceof Range → lower equal → upper equal → returns true Expected:
     * Returns true
     */
    @Test
    public void testEqualsIdenticalRanges() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 5.0);
        assertTrue(r1.equals(r2));
    }

    /**
     * Test equals() when lower bounds differ. Branch: instanceof Range, lower
     * not equal → returns false Expected: Returns false
     */
    @Test
    public void testEqualsDifferentLower() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(2.0, 5.0);
        assertFalse(r1.equals(r2));
    }

    /**
     * Test equals() when upper bounds differ. Branch: instanceof Range, lower
     * equal, upper not equal → returns false Expected: Returns false
     */
    @Test
    public void testEqualsDifferentUpper() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 6.0);
        assertFalse(r1.equals(r2));
    }

    /**
     * Test equals() when compared against a non-Range object. Branch: !(obj
     * instanceof Range) → returns false Expected: Returns false
     */
    @Test
    public void testEqualsNonRangeObject() {
        assertFalse(exampleRange.equals("not a range"));
    }

    /**
     * Test equals() when compared against null. Branch: !(obj instanceof Range)
     * since null is not an instance → returns false Expected: Returns false
     */
    @Test
    public void testEqualsNull() {
        assertFalse(exampleRange.equals(null));
    }
    // #endregion

    // #region isNaNRange() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: isNaNRange()
     *
     * #####################################################
     */
    /**
     * Test isNaNRange() when both bounds are Double.NaN. Condition:
     * Double.isNaN(lower) && Double.isNaN(upper) — both true Expected: Returns
     * true
     */
    @Test
    public void testIsNaNRangeBothNaN() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
    }

    /**
     * Test isNaNRange() when both bounds are ordinary numbers. Condition:
     * Double.isNaN(lower) is false → overall condition is false Expected:
     * Returns false
     */
    @Test
    public void testIsNaNRangeNeitherNaN() {
        assertFalse(exampleRange.isNaNRange());
    }
    // #endregion

    // #region hashCode() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: hashCode()
     *
     * #####################################################
     */
    /**
     * Test hashCode() contract: equal ranges must produce equal hash codes.
     * Equivalence Class: Equal ranges (same bounds) Expected: Both hash codes
     * are equal
     */
    @Test
    public void testHashCodeEqualRangesHaveEqualHashCodes() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 5.0);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    /**
     * Test hashCode() contract: repeated calls return the same value.
     * Equivalence Class: Same object, multiple invocations Expected: Both
     * invocations return identical hash code
     */
    @Test
    public void testHashCodeConsistency() {
        int hash1 = exampleRange.hashCode();
        int hash2 = exampleRange.hashCode();
        assertEquals(hash1, hash2);
    }
    // #endregion

    // #region toString() tests [WHITE-BOX ADDITION]
    /**
     * #####################################################
     *
     * Test suite for Range class Method: toString()
     *
     * #####################################################
     */
    /**
     * Test toString() with a positive range. Equivalence Class: Positive bounds
     * Expected: Returns "Range[1.0,5.0]"
     */
    @Test
    public void testToString() {
        Range r = new Range(1.0, 5.0);
        assertEquals("Range[1.0,5.0]", r.toString());
    }

    /**
     * Test toString() with a negative range. Equivalence Class: Negative bounds
     * Expected: Returns "Range[-3.0,-1.0]"
     */
    @Test
    public void testToStringNegativeBounds() {
        Range r = new Range(-3.0, -1.0);
        assertEquals("Range[-3.0,-1.0]", r.toString());
    }
    // #endregion
}
