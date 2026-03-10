package org.jfree.test;

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
 * Testing methodology: White-Box testing targeting
 * 90% Statement Coverage, 70% Branch Coverage, and 60% Condition Coverage.
 * Techniques applied: Branch Coverage Analysis, Condition Coverage Analysis,
 * Boundary Value Analysis, and Equivalence Class Testing.
 *
 * @author Maheen and Dipu
 */
public class RangeTest {

    private Range exampleRange;

    @Before
    public void setUp() {
        exampleRange = new Range(-1.0, 1.0);
    }

    // #region Range(double, double) constructor tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: Range(double lower, double upper)
     *
     * #####################################################
     */

    /**
     * Test constructor with a valid positive range.
     * Equivalence Class: lower &lt; upper (valid input)
     * Expected: Range is created with correct lower and upper bounds
     */
    @Test
    public void testConstructorValidBounds() {
        Range r = new Range(1.0, 5.0);
        assertEquals(1.0, r.getLowerBound(), 0.0);
        assertEquals(5.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test constructor with equal lower and upper bounds.
     * Boundary Value: lower == upper (point range)
     * Expected: Range is created successfully with a single-point range
     */
    @Test
    public void testConstructorEqualBounds() {
        Range r = new Range(3.0, 3.0);
        assertEquals(3.0, r.getLowerBound(), 0.0);
        assertEquals(3.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test constructor with both bounds being negative.
     * Equivalence Class: Negative ranges (lower &lt; upper, both negative)
     * Expected: Range is created with correct negative bounds
     */
    @Test
    public void testConstructorNegativeBounds() {
        Range r = new Range(-5.0, -1.0);
        assertEquals(-5.0, r.getLowerBound(), 0.0);
        assertEquals(-1.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test constructor when lower bound is greater than upper bound.
     * Equivalence Class: Invalid input — lower &gt; upper
     * Expected: Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLowerGreaterThanUpper() {
        new Range(5.0, 1.0);
    }
    // #endregion

    // #region getLowerBound() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: getLowerBound()
     *
     * #####################################################
     */

    /**
     * Test getLowerBound() with a positive range.
     * Equivalence Class: Positive ranges
     * Expected: Returns 2.0 (the lower bound)
     */
    @Test
    public void testGetLowerBoundPositiveRange() {
        Range r = new Range(2.0, 8.0);
        assertEquals(2.0, r.getLowerBound(), 0.0);
    }

    /**
     * Test getLowerBound() with a fully negative range.
     * Equivalence Class: Negative ranges
     * Expected: Returns -10.0 (the lower bound)
     */
    @Test
    public void testGetLowerBoundNegativeRange() {
        Range r = new Range(-10.0, -1.0);
        assertEquals(-10.0, r.getLowerBound(), 0.0);
    }

    /**
     * Test getLowerBound() using the shared setUp range [-1.0, 1.0].
     * Equivalence Class: Range spanning zero
     * Expected: Returns -1.0 (the lower bound)
     */
    @Test
    public void testGetLowerBoundFromSetUp() {
        assertEquals(-1.0, exampleRange.getLowerBound(), 0.0);
    }
    // #endregion

    // #region getUpperBound() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: getUpperBound()
     *
     * #####################################################
     */

    /**
     * Test getUpperBound() with a positive range.
     * Equivalence Class: Positive ranges
     * Expected: Returns 8.0 (the upper bound)
     */
    @Test
    public void testGetUpperBoundPositiveRange() {
        Range r = new Range(2.0, 8.0);
        assertEquals(8.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test getUpperBound() with a fully negative range.
     * Equivalence Class: Negative ranges
     * Expected: Returns -1.0 (the upper bound)
     */
    @Test
    public void testGetUpperBoundNegativeRange() {
        Range r = new Range(-10.0, -1.0);
        assertEquals(-1.0, r.getUpperBound(), 0.0);
    }

    /**
     * Test getUpperBound() using the shared setUp range [-1.0, 1.0].
     * Equivalence Class: Range spanning zero
     * Expected: Returns 1.0 (the upper bound)
     */
    @Test
    public void testGetUpperBoundFromSetUp() {
        assertEquals(1.0, exampleRange.getUpperBound(), 0.0);
    }
    // #endregion

    // #region getLength() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: getLength()
     *
     * #####################################################
     */

    /**
     * Test getLength() with a positive range.
     * Equivalence Class: Positive ranges
     * Expected: Returns 4.0 (upper - lower)
     */
    @Test
    public void testGetLengthPositiveRange() {
        Range r = new Range(1.0, 5.0);
        assertEquals(4.0, r.getLength(), 0.0);
    }

    /**
     * Test getLength() with equal lower and upper bounds.
     * Boundary Value: lower == upper (zero-length range)
     * Expected: Returns 0.0
     */
    @Test
    public void testGetLengthZeroLength() {
        Range r = new Range(3.0, 3.0);
        assertEquals(0.0, r.getLength(), 0.0);
    }

    /**
     * Test getLength() with a range that crosses zero.
     * Equivalence Class: Ranges spanning positive and negative values
     * Expected: Returns 4.0 (total span from -2 to 2)
     */
    @Test
    public void testGetLengthCrossZero() {
        Range r = new Range(-2.0, 2.0);
        assertEquals(4.0, r.getLength(), 0.0);
    }
    // #endregion

    // #region getCentralValue() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: getCentralValue()
     *
     * #####################################################
     */

    /**
     * Test getCentralValue() with a symmetric range spanning zero.
     * Equivalence Class: Range spanning positive and negative values
     * Expected: Returns 0.0 (midpoint of [-1.0, 1.0])
     */
    @Test
    public void testGetCentralValueSymmetricRange() {
        assertEquals(0.0, exampleRange.getCentralValue(), 0.0);
    }

    /**
     * Test getCentralValue() with a positive range.
     * Equivalence Class: Positive ranges
     * Expected: Returns 3.0 (midpoint of [1.0, 5.0])
     */
    @Test
    public void testGetCentralValuePositiveRange() {
        Range r = new Range(1.0, 5.0);
        assertEquals(3.0, r.getCentralValue(), 0.0);
    }

    /**
     * Test getCentralValue() with a fully negative range.
     * Equivalence Class: Negative ranges
     * Expected: Returns -4.0 (midpoint of [-6.0, -2.0])
     */
    @Test
    public void testGetCentralValueNegativeRange() {
        Range r = new Range(-6.0, -2.0);
        assertEquals(-4.0, r.getCentralValue(), 0.0);
    }

    /**
     * Test getCentralValue() with equal lower and upper bounds.
     * Boundary Value: lower == upper (point range)
     * Expected: Returns 4.0 (the single value is its own midpoint)
     */
    @Test
    public void testGetCentralValueZeroLength() {
        Range r = new Range(4.0, 4.0);
        assertEquals(4.0, r.getCentralValue(), 0.0);
    }
    // #endregion

    // #region contains() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: contains(double value)
     *
     * #####################################################
     */

    /**
     * Test contains() with a value strictly below the lower bound.
     * Boundary Value: BLB (below lower bound)
     * Branch: value &lt; lower → returns false immediately
     * Expected: Returns false
     */
    @Test
    public void testContainsValueBelowLower() {
        assertFalse(exampleRange.contains(-2.0));
    }

    /**
     * Test contains() with a value strictly above the upper bound.
     * Boundary Value: AUB (above upper bound)
     * Branch: value &gt; upper → returns false
     * Expected: Returns false
     */
    @Test
    public void testContainsValueAboveUpper() {
        assertFalse(exampleRange.contains(2.0));
    }

    /**
     * Test contains() with a value exactly at the lower bound.
     * Boundary Value: LB (lower bound)
     * Equivalence Class: value == lower (boundary inclusion)
     * Expected: Returns true
     */
    @Test
    public void testContainsValueAtLowerBound() {
        assertTrue(exampleRange.contains(-1.0));
    }

    /**
     * Test contains() with a value exactly at the upper bound.
     * Boundary Value: UB (upper bound)
     * Equivalence Class: value == upper (boundary inclusion)
     * Expected: Returns true
     */
    @Test
    public void testContainsValueAtUpperBound() {
        assertTrue(exampleRange.contains(1.0));
    }

    /**
     * Test contains() with the midpoint value of the range.
     * Boundary Value: NOM (nominal midpoint)
     * Equivalence Class: lower &lt; value &lt; upper (strictly within range)
     * Expected: Returns true
     */
    @Test
    public void testContainsValueInMiddle() {
        assertTrue(exampleRange.contains(0.0));
    }
    // #endregion

    // #region intersects(double, double) tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: intersects(double b0, double b1)
     *
     * #####################################################
     */

    /**
     * Test intersects() when b0 is below the lower bound and b1 falls inside the
     * range.
     * Branch: b0 &lt;= lower is true, b1 &gt; lower is true → returns true
     * Expected: Returns true
     */
    @Test
    public void testIntersectsB0BelowLowerB1InsideRange() {
        Range r = new Range(2.0, 8.0);
        assertTrue(r.intersects(0.0, 5.0));
    }

    /**
     * Test intersects() when b0 is below the lower bound and b1 is exactly at the
     * lower bound.
     * Boundary Value: b1 == lower
     * Branch: b0 &lt;= lower is true, b1 &gt; lower is false → returns false
     * Expected: Returns false
     */
    @Test
    public void testIntersectsB0BelowLowerB1AtLower() {
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(0.0, 2.0));
    }

    /**
     * Test intersects() when both b0 and b1 are below the lower bound.
     * Equivalence Class: Disjoint range entirely to the left
     * Branch: b0 &lt;= lower is true, b1 &gt; lower is false → returns false
     * Expected: Returns false
     */
    @Test
    public void testIntersectsB0BelowLowerB1BelowLower() {
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(0.0, 1.0));
    }

    /**
     * Test intersects() when both b0 and b1 are strictly inside the range.
     * Equivalence Class: Contained range (b0 and b1 within bounds)
     * Branch: b0 &gt; lower, b0 &lt; upper, b1 &gt;= b0 → returns true
     * Expected: Returns true
     */
    @Test
    public void testIntersectsB0InsideRangeB1InsideRange() {
        Range r = new Range(2.0, 8.0);
        assertTrue(r.intersects(3.0, 6.0));
    }

    /**
     * Test intersects() when b0 is inside the range and b1 exceeds the upper bound.
     * Equivalence Class: Overlapping range extending beyond upper
     * Branch: b0 &gt; lower, b0 &lt; upper, b1 &gt; upper → returns true
     * Expected: Returns true
     */
    @Test
    public void testIntersectsB0InsideRangeB1AboveUpper() {
        Range r = new Range(2.0, 8.0);
        assertTrue(r.intersects(5.0, 12.0));
    }

    /**
     * Test intersects() when b0 is exactly at the upper bound.
     * Boundary Value: b0 == upper
     * Branch: b0 &gt; lower, b0 &lt; upper is false → returns false
     * Expected: Returns false
     */
    @Test
    public void testIntersectsB0AtUpper() {
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(8.0, 10.0));
    }

    /**
     * Test intersects() when b0 is strictly above the upper bound.
     * Equivalence Class: Disjoint range entirely to the right
     * Branch: b0 &gt; lower, b0 &lt; upper is false → returns false
     * Expected: Returns false
     */
    @Test
    public void testIntersectsB0AboveUpper() {
        Range r = new Range(2.0, 8.0);
        assertFalse(r.intersects(9.0, 12.0));
    }
    // #endregion

    // #region intersects(Range) tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: intersects(Range range)
     *
     * #####################################################
     */

    /**
     * Test intersects(Range) when two ranges overlap.
     * Equivalence Class: Overlapping ranges
     * Expected: Returns true
     */
    @Test
    public void testIntersectsRangeOverlapping() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 7.0);
        assertTrue(r1.intersects(r2));
    }

    /**
     * Test intersects(Range) when two ranges are disjoint.
     * Equivalence Class: Non-overlapping ranges
     * Expected: Returns false
     */
    @Test
    public void testIntersectsRangeNonOverlapping() {
        Range r1 = new Range(1.0, 3.0);
        Range r2 = new Range(5.0, 9.0);
        assertFalse(r1.intersects(r2));
    }
    // #endregion

    // #region constrain() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: constrain(double value)
     *
     * #####################################################
     */

    /**
     * Test constrain() with a value strictly within the range.
     * Equivalence Class: Value contained within range
     * Branch: contains() returns true → returns value unchanged
     * Expected: Returns 5.0
     */
    @Test
    public void testConstrainValueWithinRange() {
        Range r = new Range(0.0, 10.0);
        assertEquals(5.0, r.constrain(5.0), 0.0);
    }

    /**
     * Test constrain() with a value exactly at the lower bound.
     * Boundary Value: LB (lower bound)
     * Branch: contains() returns true → returns value unchanged
     * Expected: Returns 0.0
     */
    @Test
    public void testConstrainValueAtLowerBound() {
        Range r = new Range(0.0, 10.0);
        assertEquals(0.0, r.constrain(0.0), 0.0);
    }

    /**
     * Test constrain() with a value exactly at the upper bound.
     * Boundary Value: UB (upper bound)
     * Branch: contains() returns true → returns value unchanged
     * Expected: Returns 10.0
     */
    @Test
    public void testConstrainValueAtUpperBound() {
        Range r = new Range(0.0, 10.0);
        assertEquals(10.0, r.constrain(10.0), 0.0);
    }

    /**
     * Test constrain() with a value above the upper bound.
     * Boundary Value: AUB (above upper bound)
     * Branch: !contains → value &gt; upper → result = upper
     * Expected: Returns 10.0 (clamped to upper bound)
     */
    @Test
    public void testConstrainValueAboveUpper() {
        Range r = new Range(0.0, 10.0);
        assertEquals(10.0, r.constrain(15.0), 0.0);
    }

    /**
     * Test constrain() with a value below the lower bound.
     * Boundary Value: BLB (below lower bound)
     * Branch: !contains → value &lt; lower → result = lower
     * Expected: Returns 0.0 (clamped to lower bound)
     */
    @Test
    public void testConstrainValueBelowLower() {
        Range r = new Range(0.0, 10.0);
        assertEquals(0.0, r.constrain(-5.0), 0.0);
    }
    // #endregion

    // #region combine() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: combine(Range range1, Range range2)
     *
     * #####################################################
     */

    /**
     * Test combine() when range1 is null.
     * Branch: range1 == null → returns range2
     * Expected: Returns range2
     */
    @Test
    public void testCombineRange1Null() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combine(null, r));
    }

    /**
     * Test combine() when range2 is null.
     * Branch: range2 == null → returns range1
     * Expected: Returns range1
     */
    @Test
    public void testCombineRange2Null() {
        Range r = new Range(1.0, 5.0);
        assertEquals(r, Range.combine(r, null));
    }

    /**
     * Test combine() when both ranges are null.
     * Branch: range1 == null → returns range2 (which is also null)
     * Expected: Returns null
     */
    @Test
    public void testCombineBothNull() {
        assertNull(Range.combine(null, null));
    }

    /**
     * Test combine() when both ranges are non-null.
     * Equivalence Class: Both ranges valid — returns spanning range
     * Expected: Returns new Range(1.0, 9.0) spanning both inputs
     */
    @Test
    public void testCombineBothNonNull() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 9.0);
        assertEquals(new Range(1.0, 9.0), Range.combine(r1, r2));
    }
    // #endregion

    // #region combineIgnoringNaN() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: combineIgnoringNaN(Range range1, Range range2)
     *
     * #####################################################
     */

    /**
     * Test combineIgnoringNaN() when both ranges are null.
     * Branch: range1 == null, range2 == null → range2 != null is false → returns
     * null
     * Expected: Returns null
     */
    @Test
    public void testCombineIgnoringNaNBothNull() {
        assertNull(Range.combineIgnoringNaN(null, null));
    }

    /**
     * Test combineIgnoringNaN() when range1 is null and range2 is a valid range.
     * Branch: range1 == null, range2 is not a NaN range → returns range2
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
     * Test combineIgnoringNaN() when range2 is null and range1 is a valid range.
     * Branch: range2 == null, range1.isNaNRange() is false → returns range1
     * Expected: Returns range1
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
     * Equivalence Class: Both ranges non-null, no NaN bounds
     * Branch: private min/max methods take the non-NaN path
     * Expected: Returns new Range(1.0, 9.0) spanning both inputs
     */
    @Test
    public void testCombineIgnoringNaNBothNonNullValid() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 9.0);
        assertEquals(new Range(1.0, 9.0), Range.combineIgnoringNaN(r1, r2));
    }

    /**
     * Test combineIgnoringNaN() when both ranges are NaN ranges.
     * Branch: Both NaN → private min(NaN,NaN)=NaN, max(NaN,NaN)=NaN →
     * Double.isNaN(l) &amp;&amp; Double.isNaN(u) is true → returns null
     * Expected: Returns null
     */
    @Test
    public void testCombineIgnoringNaNBothNaN() {
        Range nan1 = new Range(Double.NaN, Double.NaN);
        Range nan2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(nan1, nan2));
    }

    /**
     * Test combineIgnoringNaN() when range1 has NaN bounds and range2 is valid.
     * Branch: private min(d1=NaN, d2) → d1 is NaN → returns d2;
     * private max(d1=NaN, d2) → d1 is NaN → returns d2
     * Expected: Returns new Range(2.0, 6.0) using range2's bounds
     */
    @Test
    public void testCombineIgnoringNaNRange1NaNBoundRange2Valid() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(2.0, 6.0);
        assertEquals(new Range(2.0, 6.0), Range.combineIgnoringNaN(r1, r2));
    }

    /**
     * Test combineIgnoringNaN() when range2 has NaN bounds and range1 is valid.
     * Branch: private min(d1, d2=NaN) → d2 is NaN → returns d1;
     * private max(d1, d2=NaN) → d2 is NaN → returns d1
     * Expected: Returns new Range(2.0, 6.0) using range1's bounds
     */
    @Test
    public void testCombineIgnoringNaNRange2NaNBoundRange1Valid() {
        Range r1 = new Range(2.0, 6.0);
        Range r2 = new Range(Double.NaN, Double.NaN);
        assertEquals(new Range(2.0, 6.0), Range.combineIgnoringNaN(r1, r2));
    }
    // #endregion

    // #region expandToInclude() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: expandToInclude(Range range, double value)
     *
     * #####################################################
     */

    /**
     * Test expandToInclude() when the range is null.
     * Branch: range == null → returns new Range(value, value)
     * Expected: Returns Range(5.0, 5.0)
     */
    @Test
    public void testExpandToIncludeNullRange() {
        Range r = Range.expandToInclude(null, 5.0);
        assertEquals(new Range(5.0, 5.0), r);
    }

    /**
     * Test expandToInclude() when the value is below the lower bound.
     * Boundary Value: value &lt; lower
     * Branch: value &lt; range.getLowerBound() → returns new Range(value, upper)
     * Expected: Returns Range(0.0, 8.0)
     */
    @Test
    public void testExpandToIncludeValueBelowLower() {
        Range r = new Range(2.0, 8.0);
        assertEquals(new Range(0.0, 8.0), Range.expandToInclude(r, 0.0));
    }

    /**
     * Test expandToInclude() when the value is above the upper bound.
     * Boundary Value: value &gt; upper
     * Branch: value &gt; range.getUpperBound() → returns new Range(lower, value)
     * Expected: Returns Range(2.0, 10.0)
     */
    @Test
    public void testExpandToIncludeValueAboveUpper() {
        Range r = new Range(2.0, 8.0);
        assertEquals(new Range(2.0, 10.0), Range.expandToInclude(r, 10.0));
    }

    /**
     * Test expandToInclude() when the value is within the range.
     * Equivalence Class: Value already contained in range
     * Branch: value within bounds → returns the original range object unchanged
     * Expected: Returns the same Range object (no expansion needed)
     */
    @Test
    public void testExpandToIncludeValueWithinRange() {
        Range r = new Range(2.0, 8.0);
        assertSame(r, Range.expandToInclude(r, 5.0));
    }
    // #endregion

    // #region expand() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: expand(Range range, double lowerMargin, double upperMargin)
     *
     * #####################################################
     */

    /**
     * Test expand() with valid positive margins.
     * Equivalence Class: Normal expansion with positive margins
     * Calculation: range [2,6], length=4; lower=2−4×0.25=1, upper=6+4×0.5=8
     * Expected: Returns Range(1.0, 8.0)
     */
    @Test
    public void testExpandNormal() {
        Range r = new Range(2.0, 6.0);
        assertEquals(new Range(1.0, 8.0), Range.expand(r, 0.25, 0.5));
    }

    /**
     * Test expand() when the range argument is null.
     * Branch: ParamChecks.nullNotPermitted throws IllegalArgumentException
     * Expected: Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpandNullRange() {
        Range.expand(null, 0.25, 0.5);
    }

    /**
     * Test expand() when negative margins cause the computed lower to exceed upper.
     * Branch: lower &gt; upper after expansion → both are collapsed to their
     * midpoint
     * Calculation: range [0,1], length=1; lower=1.5, upper=−0.5 → mid=0.5
     * Expected: Returns Range(0.5, 0.5)
     */
    @Test
    public void testExpandLowerExceedsUpperAfterExpansion() {
        Range result = Range.expand(new Range(0.0, 1.0), -1.5, -1.5);
        assertEquals(new Range(0.5, 0.5), result);
    }
    // #endregion

    // #region shift(Range, double) tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: shift(Range base, double delta)
     *
     * #####################################################
     */

    /**
     * Test shift() with a positive delta (delegates to 3-arg form,
     * allowZeroCrossing=false).
     * Equivalence Class: Positive delta, range stays positive
     * Expected: Returns Range(3.0, 5.0)
     */
    @Test
    public void testShiftPositiveDelta() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(3.0, 5.0), Range.shift(r, 2.0));
    }

    /**
     * Test shift() with a negative delta.
     * Equivalence Class: Negative delta, range stays positive
     * Expected: Returns Range(1.0, 3.0)
     */
    @Test
    public void testShiftNegativeDelta() {
        Range r = new Range(2.0, 4.0);
        assertEquals(new Range(1.0, 3.0), Range.shift(r, -1.0));
    }

    /**
     * Test shift() when the base range is null.
     * Branch: ParamChecks.nullNotPermitted throws IllegalArgumentException
     * Expected: Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testShiftNullBase() {
        Range.shift(null, 1.0);
    }
    // #endregion

    // #region shift(Range, double, boolean) tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: shift(Range base, double delta, boolean allowZeroCrossing)
     *
     * #####################################################
     */

    /**
     * Test shift() with allowZeroCrossing=true.
     * Branch: allowZeroCrossing is true → adds delta directly without clamping
     * Expected: Returns Range(2.0, 4.0)
     */
    @Test
    public void testShiftAllowZeroCrossingTrue() {
        Range r = new Range(-3.0, -1.0);
        assertEquals(new Range(2.0, 4.0), Range.shift(r, 5.0, true));
    }

    /**
     * Test shift() with allowZeroCrossing=false when values are positive and do not
     * cross zero.
     * Branch: value &gt; 0, delta &gt; 0 → Math.max(v+delta, 0) = v+delta
     * Expected: Returns Range(3.0, 5.0)
     */
    @Test
    public void testShiftNoZeroCrossingPositiveValueNoCross() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(3.0, 5.0), Range.shift(r, 2.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when a positive value would cross
     * zero.
     * Branch: value &gt; 0, v+delta &lt; 0 → Math.max(v+delta, 0) = 0
     * Expected: Returns Range(0.0, 0.0) (clamped at zero)
     */
    @Test
    public void testShiftNoZeroCrossingPositiveValueCrossesZero() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(0.0, 0.0), Range.shift(r, -5.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when values are negative and do not
     * cross zero.
     * Branch: value &lt; 0, delta &lt; 0 → Math.min(v+delta, 0) = v+delta
     * Expected: Returns Range(-5.0, -3.0)
     */
    @Test
    public void testShiftNoZeroCrossingNegativeValueNoCross() {
        Range r = new Range(-4.0, -2.0);
        assertEquals(new Range(-5.0, -3.0), Range.shift(r, -1.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when a negative value would cross
     * zero.
     * Branch: value &lt; 0, v+delta &gt; 0 → Math.min(v+delta, 0) = 0
     * Expected: Returns Range(0.0, 0.0) (clamped at zero)
     */
    @Test
    public void testShiftNoZeroCrossingNegativeValueCrossesZero() {
        Range r = new Range(-2.0, -1.0);
        assertEquals(new Range(0.0, 0.0), Range.shift(r, 5.0, false));
    }

    /**
     * Test shift() with allowZeroCrossing=false when initial value is exactly zero.
     * Boundary Value: value == 0
     * Branch: value == 0 → returns value + delta (no clamping)
     * Expected: Returns Range(3.0, 3.0)
     */
    @Test
    public void testShiftNoZeroCrossingZeroValue() {
        Range r = new Range(0.0, 0.0);
        assertEquals(new Range(3.0, 3.0), Range.shift(r, 3.0, false));
    }
    // #endregion

    // #region scale() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: scale(Range base, double factor)
     *
     * #####################################################
     */

    /**
     * Test scale() with a valid positive factor.
     * Equivalence Class: Positive factor
     * Expected: Returns Range(2.0, 6.0) (each bound multiplied by 2)
     */
    @Test
    public void testScaleNormal() {
        Range r = new Range(1.0, 3.0);
        assertEquals(new Range(2.0, 6.0), Range.scale(r, 2.0));
    }

    /**
     * Test scale() with a factor of zero.
     * Boundary Value: factor == 0
     * Expected: Returns Range(0.0, 0.0) (all bounds collapse to zero)
     */
    @Test
    public void testScaleByZero() {
        Range r = new Range(1.0, 5.0);
        assertEquals(new Range(0.0, 0.0), Range.scale(r, 0.0));
    }

    /**
     * Test scale() with a negative factor.
     * Branch: factor &lt; 0 → throws IllegalArgumentException
     * Expected: Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testScaleNegativeFactor() {
        Range.scale(new Range(1.0, 3.0), -1.0);
    }

    /**
     * Test scale() when the base range is null.
     * Branch: ParamChecks.nullNotPermitted throws IllegalArgumentException
     * Expected: Throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testScaleNullBase() {
        Range.scale(null, 2.0);
    }
    // #endregion

    // #region equals() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: equals(Object obj)
     *
     * #####################################################
     */

    /**
     * Test equals() with two ranges that have identical bounds.
     * Equivalence Class: Equal ranges
     * Branch: instanceof Range → lower equal → upper equal → returns true
     * Expected: Returns true
     */
    @Test
    public void testEqualsIdenticalRanges() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 5.0);
        assertTrue(r1.equals(r2));
    }

    /**
     * Test equals() when lower bounds differ.
     * Branch: instanceof Range, lower not equal → returns false
     * Expected: Returns false
     */
    @Test
    public void testEqualsDifferentLower() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(2.0, 5.0);
        assertFalse(r1.equals(r2));
    }

    /**
     * Test equals() when upper bounds differ.
     * Branch: instanceof Range, lower equal, upper not equal → returns false
     * Expected: Returns false
     */
    @Test
    public void testEqualsDifferentUpper() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 6.0);
        assertFalse(r1.equals(r2));
    }

    /**
     * Test equals() when compared against a non-Range object.
     * Branch: !(obj instanceof Range) → returns false
     * Expected: Returns false
     */
    @Test
    public void testEqualsNonRangeObject() {
        assertFalse(exampleRange.equals("not a range"));
    }

    /**
     * Test equals() when compared against null.
     * Branch: !(obj instanceof Range) since null is not an instance → returns false
     * Expected: Returns false
     */
    @Test
    public void testEqualsNull() {
        assertFalse(exampleRange.equals(null));
    }
    // #endregion

    // #region isNaNRange() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: isNaNRange()
     *
     * #####################################################
     */

    /**
     * Test isNaNRange() when both bounds are Double.NaN.
     * Condition: Double.isNaN(lower) &amp;&amp; Double.isNaN(upper) — both true
     * Expected: Returns true
     */
    @Test
    public void testIsNaNRangeBothNaN() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
    }

    /**
     * Test isNaNRange() when both bounds are ordinary numbers.
     * Condition: Double.isNaN(lower) is false → overall condition is false
     * Expected: Returns false
     */
    @Test
    public void testIsNaNRangeNeitherNaN() {
        assertFalse(exampleRange.isNaNRange());
    }
    // #endregion

    // #region hashCode() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: hashCode()
     *
     * #####################################################
     */

    /**
     * Test hashCode() contract: equal ranges must produce equal hash codes.
     * Equivalence Class: Equal ranges (same bounds)
     * Expected: Both hash codes are equal
     */
    @Test
    public void testHashCodeEqualRangesHaveEqualHashCodes() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.0, 5.0);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    /**
     * Test hashCode() contract: repeated calls return the same value.
     * Equivalence Class: Same object, multiple invocations
     * Expected: Both invocations return identical hash code
     */
    @Test
    public void testHashCodeConsistency() {
        int hash1 = exampleRange.hashCode();
        int hash2 = exampleRange.hashCode();
        assertEquals(hash1, hash2);
    }
    // #endregion

    // #region toString() tests
    /**
     * #####################################################
     *
     * Test suite for Range class
     * Method: toString()
     *
     * #####################################################
     */

    /**
     * Test toString() with a positive range.
     * Equivalence Class: Positive bounds
     * Expected: Returns "Range[1.0,5.0]"
     */
    @Test
    public void testToString() {
        Range r = new Range(1.0, 5.0);
        assertEquals("Range[1.0,5.0]", r.toString());
    }

    /**
     * Test toString() with a negative range.
     * Equivalence Class: Negative bounds
     * Expected: Returns "Range[-3.0,-1.0]"
     */
    @Test
    public void testToStringNegativeBounds() {
        Range r = new Range(-3.0, -1.0);
        assertEquals("Range[-3.0,-1.0]", r.toString());
    }
    // #endregion
}
