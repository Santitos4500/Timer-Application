package edu.luc.etl.cs313.android.simplestopwatch.test.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */

public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime and laptime are initially 0 or less.
     */

    @Test

    public void testPreconditions() {
        assertEquals(0, model.getRuntime());
        //assertTrue(model.getLaptime() <= 0);
    }

    /**
     * Verifies that runtime is incremented correctly.
     */

    @Test
    //add testIncrementRuntimeOne
    public void testIncrementRuntimeOne() {
        final int rt = model.getRuntime();
        model.incRuntime();
        assertEquals((rt + SEC_PER_TICK), model.getRuntime());

    }

    /**
     * Verifies that runtime turns over correctly.
     */

    @Test
    //add testIncrementRuntimeMany
    public void testIncrementRuntimeMany() {
        final int rt = model.getRuntime();
        for (int i = 0; i < model.getRuntime(); i ++) {
            model.incRuntime();
        }
        assertEquals(rt, model.getRuntime());
    }

    @Test
    //add testDecrementRuntimeOne
    public void testDecrementRuntimeOne() {
        final int rt = model.getRuntime();
        model.decRuntime();
        assertEquals((rt + SEC_PER_TICK), model.getRuntime());
    }

    @Test
    //add testDecrementRuntimeMany
    public void testDecrementRuntimeMany() {
        final int rt = model.getRuntime();
        for (int i = 0; i < model.getRuntime(); i++) {
            model.decRuntime();
        }
        assertEquals(rt, model.getRuntime());
    }
}
