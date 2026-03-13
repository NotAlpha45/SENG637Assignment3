
import java.security.InvalidParameterException;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.UnknownKeyException;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataUtilitiesTest {

    /**
     * for exception handling
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * mockery context
     */
    private Mockery context;

    @Before
    public void setUp() {
        context = new JUnit4Mockery();
    }

    /*
     * 3.1 Method: Testing calculateColumnTotal(Values2D data, int column)
     */

    /*
     * Equivalence Classes Test Cases (Weak)
     */
    /**
     * ECT Test Case 1 | Partition D1 - data - valid Values2D object with
     * non-null values | Partition C1 - column - (0 ≤ column < columnCount) | →
     * Correct sum
     */
    @Test
    public void test_calculateColumnTotal_ect_1() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(1));
                allowing(values).getValue(0, 0);
                will(returnValue(5.0));
                allowing(values).getValue(1, 0);
                will(returnValue(5.0));
            }
        });

        assertEquals(10.0,
                DataUtilities.calculateColumnTotal(values, 0),
                0.000000001d);
    }

    /**
     * ECT Test Case 2 | Partition D2 - data - null | Partition C2 - column -
     * (column < 0) | → InvalidParameterException
     */
    @Test
    public void test_calculateColumnTotal_ect_2() {
        thrown.expect(InvalidParameterException.class);
        DataUtilities.calculateColumnTotal(null, -1);
    }

    /**
     * ECT Test Case 3 | Partition D3 - data - with one or more values in the
     * column as null | Partition C3 - column - (column ≥ columnCount) | →
     * InvalidParameterException
     */
    @Test
    public void test_calculateColumnTotal_ect_3() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(1));
                allowing(values).getValue(0, 1);
                will(returnValue(null));
                allowing(values).getValue(1, 1);
                will(returnValue(5.0));
                allowing(values).getValue(0, 7);
                will(throwException(new IndexOutOfBoundsException()));
                allowing(values).getValue(1, 7);
                will(throwException(new IndexOutOfBoundsException()));
            }
        });

        thrown.expect(InvalidParameterException.class);
        DataUtilities.calculateColumnTotal(values, 7);
    }

    /*
     * Boundary Value Test Cases
     */
    /**
     * BVT Test Case 1 - Sum of Column - 0.0 - data_NOM, column - 0 - column_LB
     */
    @Test
    public void test_calculateColumnTotal_bvt_1() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(1));
                allowing(values).getColumnCount();
                will(returnValue(10_000));
                allowing(values).getValue(0, 0);
                will(returnValue(0.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateColumnTotal(values, 0),
                0.000000001d);
    }

    /**
     * BVT Test Case 2 - Sum of Column - 0.0 - data_NOM, column - 1 - column_ALB
     */
    @Test
    public void test_calculateColumnTotal_bvt_2() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(10_000));
                allowing(values).getValue(0, 1);
                will(returnValue(-1.0));
                allowing(values).getValue(1, 1);
                will(returnValue(1.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateColumnTotal(values, 1),
                0.000000001d);
    }

    /**
     * BVT Test Case 3 - Sum of Column - 0.0 - data_NOM, column - 500 -
     * column_NOM
     */
    @Test
    public void test_calculateColumnTotal_bvt_3() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(10_000));
                allowing(values).getValue(0, 500);
                will(returnValue(-1.0));
                allowing(values).getValue(1, 500);
                will(returnValue(1.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateColumnTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 4 - Sum of Column - 0.0 - data_NOM, column -
     * (Integer.MAX_VALUE - 2) - column_BUB
     */
    @Test
    public void test_calculateColumnTotal_bvt_4() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(Integer.MAX_VALUE));
                allowing(values).getValue(0, Integer.MAX_VALUE - 2);
                will(returnValue(-1.0));
                allowing(values).getValue(1, Integer.MAX_VALUE - 2);
                will(returnValue(1.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateColumnTotal(values,
                        Integer.MAX_VALUE - 2),
                0.000000001d);
    }

    /**
     * BVT Test Case 5 - Sum of Column - 0.0 - data_NOM, column -
     * (Integer.MAX_VALUE - 1) - column_UB
     */
    @Test
    public void test_calculateColumnTotal_bvt_5() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(Integer.MAX_VALUE));
                allowing(values).getValue(0, Integer.MAX_VALUE - 1);
                will(returnValue(-1.0));
                allowing(values).getValue(1, Integer.MAX_VALUE - 1);
                will(returnValue(1.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateColumnTotal(values,
                        Integer.MAX_VALUE - 1),
                0.000000001d);
    }

    /**
     * BVT Test Case 6 - Sum of Column - (-Double.MAX_VALUE) - data_LB, column -
     * 500 - column_NOM
     */
    @Test
    public void test_calculateColumnTotal_bvt_6() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(10_000));
                allowing(values).getValue(0, 500);
                will(returnValue((-Double.MAX_VALUE) / 2));
                allowing(values).getValue(1, 500);
                will(returnValue((-Double.MAX_VALUE) / 2));
            }
        });

        assertEquals(-Double.MAX_VALUE,
                DataUtilities.calculateColumnTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 7 - Sum of Column - (-Double.MAX_VALUE + 1) - data_ALB,
     * column - 500 - column_NOM
     */
    @Test
    public void test_calculateColumnTotal_bvt_7() {

        double val = -Double.MAX_VALUE + 1;
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(10_000));
                allowing(values).getValue(0, 500);
                will(returnValue(val / 2));
                allowing(values).getValue(1, 500);
                will(returnValue(val / 2));
            }
        });

        assertEquals(val,
                DataUtilities.calculateColumnTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 8 - Sum of Column - (Double.MAX_VALUE -1) - data_BUB,
     * column - 500 - column_NOM
     */
    @Test
    public void test_calculateColumnTotal_bvt_8() {

        double val = Double.MAX_VALUE - 1;
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(10_000));
                allowing(values).getValue(0, 500);
                will(returnValue(val / 2));
                allowing(values).getValue(1, 500);
                will(returnValue(val / 2));
            }
        });

        assertEquals(val,
                DataUtilities.calculateColumnTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 9 - Sum of Column - Double.MAX_VALUE - data_UB, column -
     * 500 - column_NOM
     */
    @Test
    public void test_calculateColumnTotal_bvt_9() {
        double val = Double.MAX_VALUE;
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(10_000));
                allowing(values).getValue(0, 500);
                will(returnValue(val / 2));
                allowing(values).getValue(1, 500);
                will(returnValue(val / 2));
            }
        });

        assertEquals(val,
                DataUtilities.calculateColumnTotal(values, 500),
                0.000000001d);
    }

    /*
     * 3.2 Method: Testing calculateRowTotal(Values2D data, int row)
     */

    /*
     * Equivalence Classes Test Cases (Weak)
     */
    /**
     * ECT Test Case 1 | Partition D1 - data - valid Values2D object with
     * non-null values | Partition C1 - row - (0 ≤ row < rowCount) | → Correct
     * sum
     */
    @Test
    public void test_calculateRowTotal_ect_1() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(0, 0);
                will(returnValue(5.0));
                allowing(values).getValue(0, 1);
                will(returnValue(5.0));
            }
        });

        assertEquals(10.0,
                DataUtilities.calculateRowTotal(values, 0),
                0.000000001d);
    }

    /**
     * ECT Test Case 2 | Partition D2 - data - null | Partition C2 - row - (row
     * < 0) | → InvalidParameterException
     */
    @Test
    public void test_calculateRowTotal_ect_2() {
        thrown.expect(InvalidParameterException.class);
        DataUtilities.calculateRowTotal(null, -1);
    }

    /**
     * ECT Test Case 3 | Partition D3 - data - with one or more values in the
     * row as null | Partition C3 - row - (row ≥ rowCount) | →
     * InvalidParameterException
     */
    @Test
    public void test_calculateRowTotal_ect_3() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(7, 0);
                will(throwException(new IndexOutOfBoundsException()));
                allowing(values).getValue(7, 1);
                will(throwException(new IndexOutOfBoundsException()));
            }
        });
        thrown.expect(InvalidParameterException.class);
        DataUtilities.calculateRowTotal(values, 7);
    }

    /*
     * Boundary Value Test Cases
     */
    /**
     * BVT Test Case 1 - Sum of Row - 0.0 - data_NOM, row - 0 - row_LB
     */
    @Test
    public void test_calculateRowTotal_bvt_1() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(10_000));
                allowing(values).getColumnCount();
                will(returnValue(1));
                allowing(values).getValue(0, 0);
                will(returnValue(0.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateRowTotal(values, 0),
                0.000000001d);
    }

    /**
     * BVT Test Case 2 - Sum of Row - 0.0 - data_NOM, row - 1 - row_ALB
     */
    @Test
    public void test_calculateRowTotal_bvt_2() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(10_000));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(1, 0);
                will(returnValue(-1.0));
                allowing(values).getValue(1, 1);
                will(returnValue(1.0));
            }
        });
        assertEquals(0.0,
                DataUtilities.calculateRowTotal(values, 1),
                0.000000001d);
    }

    /**
     * BVT Test Case 3 - Sum of Row - 0.0 - data_NOM, row - 500 - row_NOM
     */
    @Test
    public void test_calculateRowTotal_bvt_3() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(10_000));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(500, 0);
                will(returnValue(-1.0));
                allowing(values).getValue(500, 1);
                will(returnValue(1.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateRowTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 4 - Sum of Row - 0.0 - data_NOM, row - (Integer.MAX_VALUE -
     * 2) - row_BUB
     */
    @Test
    public void test_calculateRowTotal_bvt_4() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(Integer.MAX_VALUE));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(Integer.MAX_VALUE - 2, 0);
                will(returnValue(-1.0));
                allowing(values).getValue(Integer.MAX_VALUE - 2, 1);
                will(returnValue(1.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateRowTotal(values,
                        Integer.MAX_VALUE - 2),
                0.000000001d);
    }

    /**
     * BVT Test Case 5 - Sum of Row - 0.0 - data_NOM, row - (Integer.MAX_VALUE -
     * 1) - row_UB
     */
    @Test
    public void test_calculateRowTotal_bvt_5() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(Integer.MAX_VALUE));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(Integer.MAX_VALUE - 1, 0);
                will(returnValue(-1.0));
                allowing(values).getValue(Integer.MAX_VALUE - 1, 1);
                will(returnValue(1.0));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateRowTotal(values,
                        Integer.MAX_VALUE - 1),
                0.000000001d);
    }

    /**
     * BVT Test Case 6 - Sum of Row - (-Double.MAX_VALUE) - data_LB, row - 500 -
     * row_NOM
     */
    @Test
    public void test_calculateRowTotal_bvt_6() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(10_000));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(500, 0);
                will(returnValue((-Double.MAX_VALUE) / 2));
                allowing(values).getValue(500, 1);
                will(returnValue((-Double.MAX_VALUE) / 2));
            }
        });

        assertEquals(-Double.MAX_VALUE,
                DataUtilities.calculateRowTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 7 - Sum of Row - (-Double.MAX_VALUE + 1) - data_ALB, row -
     * 500 - row_NOM
     */
    @Test
    public void test_calculateRowTotal_bvt_7() {
        Values2D values = context.mock(Values2D.class);
        double val = -Double.MAX_VALUE + 1;

        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(10_000));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(500, 0);
                will(returnValue(val / 2));
                allowing(values).getValue(500, 1);
                will(returnValue(val / 2));
            }
        });

        assertEquals(val,
                DataUtilities.calculateRowTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 8 - Sum of Row - (Double.MAX_VALUE -1) - data_BUB, row -
     * 500 - row_NOM
     */
    @Test
    public void test_calculateRowTotal_bvt_8() {
        Values2D values = context.mock(Values2D.class);
        double val = Double.MAX_VALUE - 1;

        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(10_000));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(500, 0);
                will(returnValue(val / 2));
                allowing(values).getValue(500, 1);
                will(returnValue(val / 2));
            }
        });

        assertEquals(val,
                DataUtilities.calculateRowTotal(values, 500),
                0.000000001d);
    }

    /**
     * BVT Test Case 9 - Sum of Row - Double.MAX_VALUE - data_UB, row - 500 -
     * row_NOM
     */
    @Test
    public void test_calculateRowTotal_bvt_9() {
        double val = Double.MAX_VALUE;
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(10_000));
                allowing(values).getColumnCount();
                will(returnValue(2));
                allowing(values).getValue(500, 0);
                will(returnValue(val / 2));
                allowing(values).getValue(500, 1);
                will(returnValue(val / 2));
            }
        });

        assertEquals(val,
                DataUtilities.calculateRowTotal(values, 500),
                0.000000001d);
    }

    /*
     * 3.3 Method: Testing createNumberArray(double[] data)
     */
    private static final int LARGE_SIZE = 10_000;

    /*
     * Helper Methods for 3.3
     */
    /**
     * Assert actual Number array equals expected double array.
     *
     * @param expected double array
     * @param actual   Number array
     */
    private static void assertNumberArrayEquals(double[] expected, Number[] actual) {
        assertNotNull("\"Expected\" array must not be null", expected);
        assertNotNull("\"Actual\" array must not be null", actual);
        assertEquals("Array lengths must match", expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertNotNull("\"Actual\" array element " + i + " must not be null", actual[i]);
            assertEquals("Failure - Value mismatch at index " + i, expected[i], actual[i].doubleValue(), .000000001d);
        }
    }

    /*
     * Equivalence Classes Test Cases (Weak)
     */
    /**
     * ECT Test Case 1 - Partition D1 - array with values → Correct number array
     * created
     */
    @Test
    public void test_createNumberArray_ect_1() {

        final double[] data = { -122.40, 0, 0.3, 23.12, 123.40 };
        final Number[] result = DataUtilities.createNumberArray(data);
        assertNumberArrayEquals(data, result);
    }

    /**
     * ECT Test Case 2 - Partition D2 - empty array → Empty number array created
     */
    @Test
    public void test_createNumberArray_ect_2() {

        final double[] data = {};
        final Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("\"Result\" array must not be null", result);
        assertEquals("\"Result\" array must be empty", 0, result.length);
    }

    /**
     * ECT Test Case 3 - Partition D3 - null → InvalidParameterException
     */
    @Test
    public void test_createNumberArray_ect_3() {
        thrown.expect(InvalidParameterException.class);
        DataUtilities.createNumberArray(null);
    }

    /*
     * Boundary Value Test Cases
     */
    /**
     * BVT Test Case 1 - Size of array 500 - data_NOM
     */
    @Test
    public void test_createNumberArray_bvt_1() {
        double[] data = new double[500];
        double elementAdjuster = (data.length) / 2.02;
        for (int i = 0; i < data.length; i++) {
            data[i] = i - elementAdjuster;
        }
        final Number[] result = DataUtilities.createNumberArray(data);
        assertNumberArrayEquals(data, result);
    }

    /**
     * BVT Test Case 2 - Skipped as redundant - same as ECT Test Case 2
     */
    /**
     * BVT Test Case 3 - Size of array 1 - data_ALB
     */
    @Test
    public void test_createNumberArray_bvt_3() {
        double[] data = { 1.001 };
        final Number[] result = DataUtilities.createNumberArray(data);
        assertNumberArrayEquals(data, result);
    }

    /**
     * BVT Test Case 4 - Size of array (LARGE_SIZE - 1) - data_BUB
     */
    @Test
    public void test_createNumberArray_bvt_4() {
        double[] data = new double[LARGE_SIZE - 1];
        double elementAdjuster = (LARGE_SIZE - 1) / 2.02;
        for (int i = 0; i < data.length; i++) {
            data[i] = i - elementAdjuster;
        }
        final Number[] result = DataUtilities.createNumberArray(data);
        assertNumberArrayEquals(data, result);
    }

    /**
     * BVT Test Case 5 - Size of array LARGE_SIZE - data_UB
     */
    @Test
    public void test_createNumberArray_bvt_5() {
        double[] data = new double[LARGE_SIZE];
        double elementAdjuster = LARGE_SIZE / 2.02;
        for (int i = 0; i < data.length; i++) {
            data[i] = i - elementAdjuster;
        }
        final Number[] result = DataUtilities.createNumberArray(data);
        assertNumberArrayEquals(data, result);
    }

    /*
     * 3.4 Method: Testing createNumberArray2D(double[][] data)
     */
    private static final int LARGE_SIZE_2D = 1_000;

    /*
     * Helper Methods for 3.4
     */
    /**
     * Assert actual Number array 2D equals expected double array 2D.
     *
     * @param expected
     * @param actual
     */
    private static void assertNumberArray2DEquals(double[][] expected, Number[][] actual) {
        assertNotNull("\"Expected\" 2D array must not be null", expected);
        assertNotNull("\"Actual\" 2D array must not be null", actual);
        assertEquals("Outer array lengths must match", expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertNotNull("\"Actual\" 2D array's inner array " + i + " must not be null", actual[i]);
            assertEquals("Inner array " + i + " lengths must match", expected[i].length, actual[i].length);
            for (int j = 0; j < expected[i].length; j++) {
                assertNotNull("\"Actual\" 2D array's inner array " + i + "'s element " + j + " must not be null",
                        actual[i][j]);
                assertEquals("Failure - Value mismatch at inner array " + i + "'s index " + j, expected[i][j],
                        actual[i][j].doubleValue(), .000000001d);
            }

        }
    }

    /*
     * Equivalence Classes Test Cases (Weak)
     */
    /**
     * ECT Test Case 1 - Partition D1 - 2D array with non-null values → Correct
     * 2D number array created
     */
    @Test
    public void test_createNumberArray2D_ect_1() {

        final double[][] data = { { -122.40, 0, 0.3, 23.12, 123.40 }, { -100_012.31, -250.1, 0.3, 500.0 },
                { -100_012_030.10, -10_000.1, 0.0001, 25_000.123 } };
        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * ECT Test Case 2 - Partition D2 - 2D array with one or more null values in
     * the outer array → InvalidParameterException
     */
    @Test
    public void test_createNumberArray2D_ect_2() {
        final double[][] data = { { -122.40, 0, 0.3, 23.12, 123.40 }, { -100_012.31, -250.1, 0.3, 500.0 }, null };
        thrown.expect(InvalidParameterException.class);
        DataUtilities.createNumberArray2D(data);
    }

    /**
     * ECT Test Case 3 - Partition D3 - Empty outer array → Empty 2D array
     * created
     */
    @Test
    public void test_createNumberArray2D_ect_3() {

        final double[][] data = {};
        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull("\"Result\" 2D array must not be null", result);
        assertEquals("\"Result\" 2D array must be empty", 0, result.length);
    }

    /**
     * ECT Test Case 4 - Partition D4 - One or more empty inner arrays → Correct
     * 2D array created with corresponding empty inner arrays
     */
    @Test
    public void test_createNumberArray2D_ect_4() {

        final double[][] data = { { -122.40, 0, 0.3, 23.12, 123.40 }, {},
                { -100_012_030.10, -10_000.1, 0.0001, 25_000.123 }, {} };
        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * ECT Test Case 5 - Partition D5 - Null → InvalidParameterException
     */
    @Test
    public void test_createNumberArray2D_ect_5() {
        thrown.expect(InvalidParameterException.class);
        DataUtilities.createNumberArray2D(null);
    }

    /*
     * Boundary Value Test Cases
     */
    /**
     * BVT Test Case 1 - Size of outer array - 500 - outer_NOM, Size of inner
     * arrays - 0 - inner_LB
     */
    @Test
    public void test_createNumberArray2D_bvt_1() {
        double[][] data = new double[500][];
        for (int i = 0; i < data.length; i++) {
            data[i] = new double[0];
        }
        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * BVT Test Case 2 - Size of outer array - 500 - outer_NOM, Size of inner
     * arrays - 1 - inner_ALB
     */
    @Test
    public void test_createNumberArray2D_bvt_2() {
        double[][] data = new double[500][];

        double elementAdjuster = (data.length) / 2.02;

        for (int i = 0; i < data.length; i++) {
            data[i] = new double[] { (i - elementAdjuster) };
        }

        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * BVT Test Case 3 - Size of outer array - 500 - outer_NOM, Size of inner
     * arrays - 500 - inner_NOM
     */
    @Test
    public void test_createNumberArray2D_bvt_3() {
        double[][] data = new double[500][];

        double elementAdjuster = (data.length) / 2.02;

        for (int i = 0; i < data.length; i++) {
            double innerElementAdjuster = elementAdjuster - 200.0;
            data[i] = new double[500];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = j - innerElementAdjuster;
            }
        }

        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * BVT Test Case 4 - Size of outer array - 500 - outer_NOM, Size of inner
     * arrays - (LARGE_SIZE_2D - 1) - inner_BUB
     */
    @Test
    public void test_createNumberArray2D_bvt_4() {
        double[][] data = new double[500][];

        double elementAdjuster = (data.length) / 2.02;

        for (int i = 0; i < data.length; i++) {
            double innerElementAdjuster = elementAdjuster - 200.0;
            data[i] = new double[LARGE_SIZE_2D - 1];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = j - innerElementAdjuster;
            }
        }

        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * BVT Test Case 5 - Size of outer array - 500 - outer_NOM, Size of inner
     * arrays - (LARGE_SIZE_2D) - inner_UB
     */
    @Test
    public void test_createNumberArray2D_bvt_5() {
        double[][] data = new double[500][];

        double elementAdjuster = (data.length) / 2.02;

        for (int i = 0; i < data.length; i++) {
            double innerElementAdjuster = elementAdjuster - 200.0;
            data[i] = new double[LARGE_SIZE_2D];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = j - innerElementAdjuster;
            }
        }

        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * BVT Test Case 6 - Size of outer array - 1 - outer_ALB, Size of inner
     * arrays - 500 - inner_NOM
     */
    @Test
    public void test_createNumberArray2D_bvt_6() {
        double[][] data = new double[1][];

        double elementAdjuster = (data.length) / 2.02;

        for (int i = 0; i < data.length; i++) {
            double innerElementAdjuster = elementAdjuster - 200.0;
            data[i] = new double[500];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = j - innerElementAdjuster;
            }
        }

        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * BVT Test Case 7 - Size of outer array - (LARGE_SIZE_2D-1) - outer_BUB,
     * Size of inner arrays - 500 - inner_NOM
     */
    @Test
    public void test_createNumberArray2D_bvt_7() {
        double[][] data = new double[(LARGE_SIZE_2D - 1)][];

        double elementAdjuster = (data.length) / 2.02;

        for (int i = 0; i < data.length; i++) {
            double innerElementAdjuster = elementAdjuster - 200.0;
            data[i] = new double[500];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = j - innerElementAdjuster;
            }
        }

        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /**
     * BVT Test Case 8 - Size of outer array - LARGE_SIZE_2D - outer_UB, Size of
     * inner arrays - 500 - inner_NOM
     */
    @Test
    public void test_createNumberArray2D_bvt_8() {
        double[][] data = new double[LARGE_SIZE_2D][];

        double elementAdjuster = (data.length) / 2.02;

        for (int i = 0; i < data.length; i++) {
            double innerElementAdjuster = elementAdjuster - 200.0;
            data[i] = new double[500];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = j - innerElementAdjuster;
            }
        }

        final Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNumberArray2DEquals(data, result);
    }

    /*
     * 3.5 Method: Testing getCumulativePercentages(KeyedValues data)
     */
    private static final int LARGE_KEYS = 10_000;
    private static final double LARGE_SUM = 1_000_000.0;
    private static final double HIGH_DELTA = 0.01d;

    private int mockSeq = 0;

    /*
     * Helper Methods for 3.5
     */
    /**
     * Mocks KeyedValues for a given Number array
     *
     * @param values
     * @return mocked KeyedValues
     */
    private KeyedValues mockKeyedValues(final Number[] values) {
        final KeyedValues kv = context.mock(KeyedValues.class, "kv_" + mockSeq);
        mockSeq = mockSeq + 1;
        final int n = values.length;

        final String[] keys = new String[n];
        for (int i = 0; i < n; i++) {
            String key = "k" + i;
            keys[i] = key;
        }

        context.checking(new Expectations() {
            {
                // getItemCount()
                allowing(kv).getItemCount();
                will(returnValue(n));

                // getKey(int), getValue(int) for valid indices
                for (int i = 0; i < n; i++) {
                    allowing(kv).getKey(i);
                    will(returnValue(keys[i]));
                    allowing(kv).getValue(i);
                    will(returnValue(values[i]));
                }

                // getKey(int): unknown → IndexOutOfBoundsException
                allowing(kv).getKey(with(any(int.class)));
                will(throwException(new IndexOutOfBoundsException()));

                // Out-of-range calls for getValue would be unexpected (and fail) by design.
                // getKeys(): ordered list, never null
                allowing(kv).getKeys();
                will(returnValue(Arrays.asList(keys)));

                // getIndex(Comparable): specific keys → index; unknown → -1
                for (int i = 0; i < n; i++) {
                    allowing(kv).getIndex(keys[i]);
                    will(returnValue(i));
                }

                allowing(kv).getIndex(with(any(Comparable.class)));
                will(returnValue(-1));

                // getValue(Comparable): known keys → value; unknown → UnknownKeyException
                for (int i = 0; i < n; i++) {
                    allowing(kv).getValue(keys[i]);
                    will(returnValue(values[i]));
                }

                allowing(kv).getValue(with(any(Comparable.class)));
                will(throwException(new UnknownKeyException("key not recognised")));
            }
        });
        return kv;
    }

    /**
     * Assert that values are between 0.0 and 1.0, with increasing values from
     * beginning to end and the end value is 1.0. Assumes that values are within
     * delta range.
     *
     * @param kv
     * @param delta
     */
    private static void assertMonotoneAndEndsAtOne(KeyedValues kv, double delta) {
        assertNotNull(kv);
        int n = kv.getItemCount();
        if (n == 0) {
            return;
        }

        double prev = -1.0;
        for (int i = 0; i < n; i++) {
            Number v = kv.getValue(i);
            assertNotNull("Percentage at " + i + " must not be null", v);
            double d = v.doubleValue();

            assertTrue("Percentage out of [0,1] at index:" + i, ((d >= -delta) && (d <= 1.0 + delta)));

            assertTrue("Percentages must be non-decreasing at index:" + i, (d >= prev - delta));

            prev = d;
        }
        assertEquals("Final cumulative percentage should be 1.0", 1.0, kv.getValue(n - 1).doubleValue(), delta);
    }

    /**
     * Stricter element-wise check for uniform data: expects cumulative[i] ≈
     * (i+1)/n also runs the assertMonotoneAndEndsAtOne check
     *
     * @param kv
     * @param delta
     */
    private static void assertCumulativeMatchesUniform(KeyedValues kv, double delta) {
        assertNotNull(kv);
        int n = kv.getItemCount();
        if (n == 0) {
            return;
        }

        assertMonotoneAndEndsAtOne(kv, delta);

        for (int i = 0; i < n; i++) {
            double expected = (i + 1) / (double) n;
            assertEquals("Mismatch at index:" + i, expected, kv.getValue(i).doubleValue(), delta);
        }
    }

    /*
     * Equivalence Classes Test Cases (Weak)
     */
    /**
     * ECT Test Case 1 - Partition D1 - Valid multi-element dataset → Correct
     * cumulative percentages
     */
    @Test
    public void test_getCumulativePercentages_ect_1() {

        Number[] values = new Number[] { 5.0, 9.0, 2.0 };
        KeyedValues data = mockKeyedValues(values);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Item count must be 3", 3, result.getItemCount());
        assertEquals("Incorrect Cumulative Percentage at index 0", 0.3125, result.getValue(0).doubleValue(),
                HIGH_DELTA);
        assertEquals("Incorrect Cumulative Percentage at index 1", 0.8750, result.getValue(1).doubleValue(),
                HIGH_DELTA);
        assertEquals("Incorrect Cumulative Percentage at index 2", 1.0000, result.getValue(2).doubleValue(),
                HIGH_DELTA);

    }

    /**
     * ECT Test Case 2 - Partition D2 - Single element → single key with 100%
     */
    @Test
    public void test_getCumulativePercentages_ect_2() {
        Number[] values = new Number[] { 1000.0 };
        KeyedValues data = mockKeyedValues(values);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Item count must be 1", 1, result.getItemCount());
        assertEquals("Incorrect Cumulative Percentage at index 0", 1.0, result.getValue(0).doubleValue(), HIGH_DELTA);

    }

    /**
     * ECT Test Case 3 - Partition D3 - Empty dataset → Empty cumulative
     * percentages
     */
    @Test
    public void test_getCumulativePercentages_ect_3() {

        Number[] values = new Number[] {};
        KeyedValues data = mockKeyedValues(values);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertNotNull("KeyedValues must not be null", result);
        assertEquals("Item count must be 0", 0, result.getItemCount());

    }

    /**
     * ECT Test Case 4 - Partition D4 - Dataset with all values as zero →
     * InvalidParameterException
     */
    @Test
    public void test_getCumulativePercentages_ect_4() {

        Number[] values = new Number[] { 0.0, 0.0, 0.0 };
        KeyedValues data = mockKeyedValues(values);

        thrown.expect(InvalidParameterException.class);
        DataUtilities.getCumulativePercentages(data);

    }

    /**
     * ECT Test Case 5 - Partition D5 - Null → InvalidParameterException
     */
    @Test
    public void test_getCumulativePercentages_ect_5() {
        thrown.expect(InvalidParameterException.class);
        DataUtilities.getCumulativePercentages(null);
    }

    /**
     * ECT Test Case 6 - Partition D6 - One or more values in the dataset are
     * null → InvalidParameterException
     */
    @Test
    public void test_getCumulativePercentages_ect_6() {
        Number[] values = new Number[] { 5.0, 9.0, null, 2.0 };
        KeyedValues data = mockKeyedValues(values);

        thrown.expect(InvalidParameterException.class);
        DataUtilities.getCumulativePercentages(data);
    }

    /*
     * Boundary Value Test Cases
     */
    /**
     * BVT Test Case 1 - Sum of all values - 500.0 - sum_NOM, Number of keys - 1
     * - keys_ALB
     */
    @Test
    public void test_getCumulativePercentages_bvt_1() {
        Number[] values = new Number[] { 500.0 };
        KeyedValues data = mockKeyedValues(values);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Item count must be 1", 1, result.getItemCount());
        assertEquals("Incorrect Cumulative Percentage at index 0", 1.0, result.getValue(0).doubleValue(), HIGH_DELTA);
    }

    /**
     * BVT Test Case 2 - Sum of all values - 500.0 - sum_NOM, Number of keys -
     * 500 - keys_NOM
     */
    @Test
    public void test_getCumulativePercentages_bvt_2() {

        Number[] values = new Number[500];
        Arrays.fill(values, 1.0);

        KeyedValues data = mockKeyedValues(values);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertCumulativeMatchesUniform(result, HIGH_DELTA);

    }

    /**
     * BVT Test Case 3 - Sum of all values - 500.0 - sum_NOM, Number of keys -
     * (LARGE_KEYS - 1) - keys_BUB
     */
    @Test
    public void test_getCumulativePercentages_bvt_3() {
        int numKeys = LARGE_KEYS - 1;

        Number[] values = new Number[numKeys];
        double each = 500.0 / numKeys;

        Arrays.fill(values, each);

        KeyedValues data = mockKeyedValues(values);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertCumulativeMatchesUniform(result, HIGH_DELTA);

    }

    /**
     * BVT Test Case 4 - Sum of all values - 500.0 - sum_NOM, Number of keys -
     * LARGE_KEYS - keys_UB
     */
    @Test
    public void test_getCumulativePercentages_bvt_4() {
        int numKeys = LARGE_KEYS;

        Number[] values = new Number[numKeys];
        double each = 500.0 / numKeys;

        Arrays.fill(values, each);

        KeyedValues data = mockKeyedValues(values);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertCumulativeMatchesUniform(result, HIGH_DELTA);
    }

    /**
     * BVT Test Case 5 - Sum of all values - 0 - sum_LB, Number of keys - 500 -
     * keys_NOM
     */
    @Test
    public void test_getCumulativePercentages_bvt_5() {
        int numKeys = 500;
        Number[] values = new Number[numKeys];
        Arrays.fill(values, 0.0);

        KeyedValues data = mockKeyedValues(values);

        thrown.expect(InvalidParameterException.class);
        DataUtilities.getCumulativePercentages(data);

    }

    /**
     * BVT Test Case 6 - Sum of all values - 1.0 - sum_ALB, Number of keys - 500
     * - keys_NOM
     */
    @Test
    public void test_getCumulativePercentages_bvt_6() {
        int numKeys = 500;
        Number[] values = new Number[numKeys];
        double each = 1.0 / numKeys;

        Arrays.fill(values, each);

        KeyedValues data = mockKeyedValues(values);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertCumulativeMatchesUniform(result, HIGH_DELTA);
    }

    /**
     * BVT Test Case 7 - Sum of all values - (LARGE_SUM - 1) - sum_BUB, Number
     * of keys - 500 - keys_NOM
     */
    @Test
    public void test_getCumulativePercentages_bvt_7() {
        int numKeys = 500;
        Number[] values = new Number[numKeys];
        double each = (LARGE_SUM - 1.0) / numKeys;

        Arrays.fill(values, each);

        KeyedValues data = mockKeyedValues(values);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertCumulativeMatchesUniform(result, HIGH_DELTA);
    }

    /**
     * BVT Test Case 8 - Sum of all values - LARGE_SUM - sum_UB, Number of keys
     * - 500 - keys_NOM
     */
    @Test
    public void test_getCumulativePercentages_bvt_8() {
        int numKeys = 500;
        Number[] values = new Number[numKeys];
        double each = LARGE_SUM / numKeys;

        Arrays.fill(values, each);

        KeyedValues data = mockKeyedValues(values);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertCumulativeMatchesUniform(result, HIGH_DELTA);
    }

    // @Test(timeout = 2000)
    // public void testNegativeRowCountForCalculateColumnTotal() {
    // Values2D values = context.mock(Values2D.class);
    // context.checking(new Expectations() {
    // {
    // allowing(values).getRowCount();
    // will(returnValue(-1));
    // allowing(values).getValue(with(any(int.class)), 0);
    // will(returnValue(1.0));
    // }
    // });
    //
    // thrown.expect(Exception.class);
    // DataUtilities.calculateColumnTotal(values, 0);
    // }
    //
    //
    // @Test(timeout = 2000)
    // public void testNegativeRowCountForCalculateRowTotal() {
    // Values2D values = context.mock(Values2D.class);
    // context.checking(new Expectations() {
    // {
    // allowing(values).getColumnCount();
    // will(returnValue(-1));
    // allowing(values).getValue(0, with(any(int.class)));
    // will(returnValue(1.0));
    // }
    // });
    //
    // thrown.expect(Exception.class);
    // DataUtilities.calculateRowTotal(values, 0);
    // }

    @Test
    public void testValues2DWithANullValueForCalculateColumnTotal() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(1));
                allowing(values).getValue(0, 0);
                will(returnValue(null));
            }
        });

        assertEquals(0.0,
                DataUtilities.calculateColumnTotal(values, 0),
                0.000000001d);
    }

    @Test
    public void testValues2DWithANullValueForCalculateRowTotal() {
        Values2D values = context.mock(Values2D.class);
        context.checking(new Expectations() {
            {
                allowing(values).getColumnCount();
                will(returnValue(1));
                allowing(values).getValue(0, 0);
                will(returnValue(null));
            }
        });
        assertEquals(0.0,
                DataUtilities.calculateRowTotal(values, 0),
                0.000000001d);
    }

}
