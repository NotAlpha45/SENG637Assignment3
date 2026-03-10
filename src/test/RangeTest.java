import org.jfree.data.Range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

    private Range exampleRange;

    @Before
    public void setUp() {
        exampleRange = new Range(-1.0, 1.0);
    }

    // =========================================================
    // Constructor Tests
    // =========================================================

    @Test
    public void testConstructorValidBounds() {
        Range r = new Range(1.0, 5.0);
        assertEquals(1.0, r.getLowerBound(), 0.0);
        assertEquals(5.0, r.getUpperBound(), 0.0);
    }

    @Test
    public void testConstructorEqualBounds() {
        Range r = new Range(3.0, 3.0);
        assertEquals(3.0, r.getLowerBound(), 0.0);
        assertEquals(3.0, r.getUpperBound(), 0.0);
    }

    @Test
    public void testConstructorNegativeBounds() {
        Range r = new Range(-5.0, -1.0);
        assertEquals(-5.0, r.getLowerBound(), 0.0);
        assertEquals(-1.0, r.getUpperBound(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLowerGreaterThanUpper() {
        new Range(5.0, 1.0);
    }

    // =========================================================
    // getLowerBound Tests
    // =========================================================

    @Test
    public void testGetLowerBoundPositiveRange() {
        Range r = new Range(2.0, 8.0);
        assertEquals(2.0, r.getLowerBound(), 0.0);
    }

    @Test
    public void testGetLowerBoundNegativeRange() {
        Range r = new Range(-10.0, -1.0);
        assertEquals(-10.0, r.getLowerBound(), 0.0);
    }

    @Test
    public void testGetLowerBoundFromSetUp() {
        assertEquals(-1.0, exampleRange.getLowerBound(), 0.0);
    }

    // =========================================================
    // getUpperBound Tests
    // =========================================================

    @Test
    public void testGetUpperBoundPositiveRange() {
        Range r = new Range(2.0, 8.0);
        assertEquals(8.0, r.getUpperBound(), 0.0);
    }

    @Test
    public void testGetUpperBoundNegativeRange() {
        Range r = new Range(-10.0, -1.0);
        assertEquals(-1.0, r.getUpperBound(), 0.0);
    }

    @Test
    public void testGetUpperBoundFromSetUp() {
        assertEquals(1.0, exampleRange.getUpperBound(), 0.0);
    }

    // =========================================================
    // getLength Tests
    // =========================================================

    @Test
    public void testGetLengthPositiveRange() {
        Range r = new Range(1.0, 5.0);
        assertEquals(4.0, r.getLength(), 0.0);
    }

    @Test
    public void testGetLengthZeroLength() {
        Range r = new Range(3.0, 3.0);
        assertEquals(0.0, r.getLength(), 0.0);
    }

    @Test
    public void testGetLengthCrossZero() {
        Range r = new Range(-2.0, 2.0);
        assertEquals(4.0, r.getLength(), 0.0);
    }

    // =========================================================
    // getCentralValue Tests
    // =========================================================

    @Test
    public void testGetCentralValueSymmetricRange() {
        // exampleRange is [-1, 1], central value should be 0
        assertEquals(0.0, exampleRange.getCentralValue(), 0.0);
    }

    @Test
    public void testGetCentralValuePositiveRange() {
        Range r = new Range(1.0, 5.0);
        assertEquals(3.0, r.getCentralValue(), 0.0);
    }

    @Test
    public void testGetCentralValueNegativeRange() {
        Range r = new Range(-6.0, -2.0);
        assertEquals(-4.0, r.getCentralValue(), 0.0);
    }

    @Test
    public void testGetCentralValueZeroLength() {
        Range r = new Range(4.0, 4.0);
        assertEquals(4.0, r.getCentralValue(), 0.0);
    }

    // =========================================================
    // contains Tests
    // =========================================================

    @Test
    public void testContainsValueBelowLower() {
        // Branch: value < lower → false
        assertFalse(exampleRange.contains(-2.0));
    }

    @Test
    public void testContainsValueAboveUpper() {
        // Branch: value > upper → false
        assertFalse(exampleRange.contains(2.0));
    }

    @Test
    public void testContainsValueAtLowerBound() {
        assertTrue(exampleRange.contains(-1.0));
    }

    @Test
    public void testContainsValueAtUpperBound() {
        assertTrue(exampleRange.contains(1.0));
    }

    @Test
    public void testContainsValueInMiddle() {
        assertTrue(exampleRange.contains(0.0));
    }

    // =========================================================
    // intersects(double, double) Tests
    // =========================================================

    @Test
    public void testIntersectsB0BelowLowerB1InsideRange() {
        // b0 <= lower, b1 > lower → true
        Range r = new Range(2.0, 8.0);
        assertTrue(r.intersects(0.0, 5.0));
    }

    @Test
    public void testIntersectsB0BelowLowerB1AtLower() {
        // b0 <= lower, b1 == lower → false (b1 > lower is false)
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(0.0, 2.0));
    }

    @Test
    public void testIntersectsB0BelowLowerB1BelowLower() {
        // b0 < lower, b1 < lower → false
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(0.0, 1.0));
    }

    @Test
    public void testIntersectsB0InsideRangeB1InsideRange() {
        // b0 > lower, b0 < upper, b1 >= b0 → true
        Range r = new Range(2.0, 8.0);
        assertTrue(r.intersects(3.0, 6.0));
    }

    @Test
    public void testIntersectsB0InsideRangeB1AboveUpper() {
        // b0 > lower, b0 < upper, b1 > upper → true
        Range r = new Range(2.0, 8.0);
        assertTrue(r.intersects(5.0, 12.0));
    }

    @Test
    public void testIntersectsB0AtUpper() {
        // b0 > lower, b0 == upper → b0 < upper is false → false
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(8.0, 10.0));
    }

    @Test
    public void testIntersectsB0AboveUpper() {
        // b0 > upper → false
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(9.0, 12.0));
    }

    // =========================================================
    // intersects(Range) Tests
    // =========================================================

    @Test
    public void testIntersectsRangeOverlapping() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 7.0);
        assertTrue(r1.intersects(r2));
    }

    @Test
    public void testIntersectsRangeNonOverlapping() {
        Range r1 = new Range(1.0, 3.0);
        Range r2 = new Range(5.0, 9.0);
        assertFalse(r1.intersects(r2));
    }

    // =========================================================
    // constrain Tests
    // =========================================================

    @Test
    public void testConstrainValueWithinRange() {
        // contains() returns true → return original value
        Range r = new Range(0.0, 10.0);
        assertEquals(5.0, r.constrain(5.0), 0.0);
    }

    @Test
    public void testConstrainValueAtLowerBound() {
        Range r = new Range(0.0, 10.0);
        assertEquals(0.0, r.constrain(0.0), 0.0);
    }

    @Test
    public void testConstrainValueAtUpperBound() {
        Range r = new Range(0.0, 10.0);
        assertEquals(10.0, r.constrain(10.0), 0.0);
    }

    @Test
    public void testConstrainValueAboveUpper() {
        // !contains → value > upper → result = upper
        Range r = new Range(0.0, 10.0);
        assertEquals(10.0, r.constrain(15.0), 0.0);
    }

    @Test
    public void testConstrainValueBelowLower() {
        // !contains → value < lower → result = lower
        Range r = new Range(0.0, 10.0);
        assertEquals(0.0, r.constrain(-5.0), 0.0);
    }

    // =========================================================
    // combine Tests
    // =========================================================

    @Test
    public void testCombineRange1Null() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combine(null, r));
    }

    @Test
    public void testCombineRange2Null() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combine(r, null));
    }

    @Test
    public void testCombineBothNull() {
        assertNull(Range.combine(null, null));
    }

    @Test
    public void testCombineBothNonNull() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 9.0);
        assertEquals(new Range(1.0, 9.0), Range.combine(r1, r2));
    }

    // =========================================================
    // combineIgnoringNaN Tests
    // =========================================================

    @Test
    public void testCombineIgnoringNaNBothNull() {
        // range1 null, range2 null → range2 != null is false → return range2 (null)
        assertNull(Range.combineIgnoringNaN(null, null));
    }

    @Test
    public void testCombineIgnoringNaNRange1NullRange2Valid() {
        // range1 null, range2 not NaN → return range2
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combineIgnoringNaN(null, r));
    }

    @Test
    public void testCombineIgnoringNaNRange1NullRange2NaN() {
        // range1 null, range2 is NaN range → return null
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(null, nanRange));
    }

    @Test
    public void testCombineIgnoringNaNRange2NullRange1Valid() {
        // range2 null, range1 not NaN → return range1
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combineIgnoringNaN(r, null));
    }

    @Test
    public void testCombineIgnoringNaNRange2NullRange1NaN() {
        // range2 null, range1 is NaN range → return null
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(nanRange, null));
    }

    @Test
    public void testCombineIgnoringNaNBothNonNullValid() {
        // Both non-null, min/max private methods exercised with non-NaN values
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 9.0);
        assertEquals(new Range(1.0, 9.0), Range.combineIgnoringNaN(r1, r2));
    }

    @Test
    public void testCombineIgnoringNaNBothNaN() {
        // Both NaN ranges → l and u are NaN → return null
        Range nan1 = new Range(Double.NaN, Double.NaN);
        Range nan2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(nan1, nan2));
    }

    @Test
    public void testCombineIgnoringNaNRange1NaNBoundRange2Valid() {
        // Exercises min/max private methods: d1 is NaN → return d2
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(2.0, 6.0);
        // l = min(NaN, 2.0) = 2.0, u = max(NaN, 6.0) = 6.0
        assertEquals(new Range(2.0, 6.0), Range.combineIgnoringNaN(r1, r2));
    }

    @Test
    public void testCombineIgnoringNaNRange2NaNBoundRange1Valid() {
        // Exercises min/max private methods: d2 is NaN → return d1
        Range r1 = new Range(2.0, 6.0);
        Range r2 = new Range(Double.NaN, Double.NaN);
        // l = min(2.0, NaN) = 2.0, u = max(6.0, NaN) = 6.0
        assertEquals(new Range(2.0, 6.0), Range.combineIgnoringNaN(r1, r2));
    }

    // =========================================================
    // expandToInclude Tests
    // =========================================================

    @Test
    public void testExpandToIncludeNullRange() {
        // range == null → new Range(value, value)
        Range r = Range.expandToInclude(null, 5.0);
        assertEquals(new Range(5.0, 5.0), r);
    }

    @Test
    public void testExpandToIncludeValueBelowLower() {
        Range r = new Range(2.0, 8.0);
        assertEquals(new Range(0.0, 8.0), Range.expandToInclude(r, 0.0));
    }

    @Test
    public void testExpandToIncludeValueAboveUpper() {
        Range r = new Range(2.0, 8.0);
        assertEquals(new Range(2.0, 10.0), Range.expandToInclude(r, 10.0));
    }

    @Test
    public void testExpandToIncludeValueWithinRange() {
        // value within range → return same range object
        Range r = new Range(2.0, 8.0);
        assertSame(r, Range.expandToInclude(r, 5.0));
    }

    // =========================================================
    // expand Tests
    // =========================================================

    @Test
    public void testExpandNormal() {
        // range [2, 6], length = 4, lowerMargin 0.25, upperMargin 0.5
        // lower = 2 - 4*0.25 = 1, upper = 6 + 4*0.5 = 8
        Range r = new Range(2.0, 6.0);
        assertEquals(new Range(1.0, 8.0), Range.expand(r, 0.25, 0.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpandNullRange() {
        Range.expand(null, 0.25, 0.5);
    }

    @Test
    public void testExpandLowerExceedsUpperAfterExpansion() {
        // range [0, 1], length = 1
        // lowerMargin = -1.5 → lower = 0 - 1*(-1.5) = 1.5
        // upperMargin = -1.5 → upper = 1 + 1*(-1.5) = -0.5
        // lower (1.5) > upper (-0.5) → midpoint: (1.5/2 + (-0.5)/2) = 0.5
        Range result = Range.expand(new Range(0.0, 1.0), -1.5, -1.5);
        assertEquals(new Range(0.5, 0.5), result);
    }

    // =========================================================
    // shift(Range, double) Tests
    // =========================================================

    @Test
    public void testShiftPositiveDelta() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(3.0, 5.0), Range.shift(r, 2.0));
    }

    @Test
    public void testShiftNegativeDelta() {
        Range r = new Range(2.0, 4.0);
        assertEquals(new Range(1.0, 3.0), Range.shift(r, -1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShiftNullBase() {
        Range.shift(null, 1.0);
    }

    // =========================================================
    // shift(Range, double, boolean) Tests
    // =========================================================

    @Test
    public void testShiftAllowZeroCrossingTrue() {
        // Simply adds delta without clamping
        Range r = new Range(-3.0, -1.0);
        assertEquals(new Range(2.0, 4.0), Range.shift(r, 5.0, true));
    }

    @Test
    public void testShiftNoZeroCrossingPositiveValueNoCross() {
        // value > 0, delta positive: max(v + delta, 0) = v + delta
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(3.0, 5.0), Range.shift(r, 2.0, false));
    }

    @Test
    public void testShiftNoZeroCrossingPositiveValueCrossesZero() {
        // value > 0, delta causes v + delta < 0: max(v + delta, 0) = 0
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(0.0, 0.0), Range.shift(r, -5.0, false));
    }

    @Test
    public void testShiftNoZeroCrossingNegativeValueNoCross() {
        // value < 0, delta negative: min(v + delta, 0) = v + delta
        Range r = new Range(-4.0, -2.0);
        assertEquals(new Range(-5.0, -3.0), Range.shift(r, -1.0, false));
    }

    @Test
    public void testShiftNoZeroCrossingNegativeValueCrossesZero() {
        // value < 0, delta causes v + delta > 0: min(v + delta, 0) = 0
        Range r = new Range(-2.0, -1.0);
        assertEquals(new Range(0.0, 0.0), Range.shift(r, 5.0, false));
    }

    @Test
    public void testShiftNoZeroCrossingZeroValue() {
        // value == 0: return value + delta
        Range r = new Range(0.0, 0.0);
        assertEquals(new Range(3.0, 3.0), Range.shift(r, 3.0, false));
    }

    // =========================================================
    // scale Tests
    // =========================================================

    @Test
    public void testScaleNormal() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(2.0, 6.0), Range.scale(r, 2.0));
    }

    @Test
    public void testScaleByZero() {
        Range r = new Range(1.0, 5.0);
        assertEquals(new Range(0.0, 0.0), Range.scale(r, 0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScaleNegativeFactor() {
        Range.scale(new Range(1.0, 3.0), -1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScaleNullBase() {
        Range.scale(null, 2.0);
    }

    // =========================================================
    // equals Tests
    // =========================================================

    @Test
    public void testEqualsIdenticalRanges() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 5.0);
        assertTrue(r1.equals(r2));
    }

    @Test
    public void testEqualsDifferentLower() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(2.0, 5.0);
        assertFalse(r1.equals(r2));
    }

    @Test
    public void testEqualsDifferentUpper() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 6.0);
        assertFalse(r1.equals(r2));
    }

    @Test
    public void testEqualsNonRangeObject() {
        assertFalse(exampleRange.equals("not a range"));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(exampleRange.equals(null));
    }

    // =========================================================
    // isNaNRange Tests
    // =========================================================

    @Test
    public void testIsNaNRangeBothNaN() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
    }

    @Test
    public void testIsNaNRangeNeitherNaN() {
        assertFalse(exampleRange.isNaNRange());
    }

    // =========================================================
    // hashCode Tests
    // =========================================================

    @Test
    public void testHashCodeEqualRangesHaveEqualHashCodes() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 5.0);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testHashCodeConsistency() {
        int hash1 = exampleRange.hashCode();
        int hash2 = exampleRange.hashCode();
        assertEquals(hash1, hash2);
    }

    // =========================================================
    // toString Tests
    // =========================================================

    @Test
    public void testToString() {
        Range r = new Range(1.0, 5.0);
        assertEquals("Range[1.0,5.0]", r.toString());
    }

    @Test
    public void testToStringNegativeBounds() {
        Range r = new Range(-3.0, -1.0);
        assertEquals("Range[-3.0,-1.0]", r.toString());
    }
}
