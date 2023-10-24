package hw4;


import api.Point;



/**
 * Models a fixed link with three paths. The following figure shows the three
 * paths labeled A, B, and C.
 * 
 *
 * 
 * 
 * The paths A and B run in the same direction and C branches away.
 * 
 * A train from A always passes to B. 
 * A train from B always passes to A.
 * A train from C always passes to A.
 * 
 * @author omran
 *
 */
public class StraightLink extends AbstractLink {
	/**
     * train crossing boolean to detect if train has entered crossing
     */
    private boolean trainCrossing;
	/**
	 * track first end point
	 */
	private Point endPointA;
	/**
	 * track second end point
	 */
	private Point endPointB;
	/**
	 * track third end point
	 */
	private Point endPointC;
	/**
	 * Creates a new StraightLink, give endpoints correspond to the paths as labeled below
	 *  A     B
	 *  --- ---
	 *  	\
	 *  	 \C
	 * @param endPointA
	 * @param endPointB
	 * @param endPointC
	 */
	public StraightLink(Point endPointA, Point endPointB, Point endPointC) {
		this.endPointA = endPointA;
		this.endPointB = endPointB;
		this.endPointC = endPointC;
		trainCrossing = false;
	}

    @Override
    protected Point getEndPointA() {
        return endPointA;
    }

    @Override
    protected Point getEndPointB() {
        return endPointB;
    }

    @Override
    protected Point getEndPointC() {
        return endPointC;
    }

    @Override
    protected Point getConnectedPointA() {
        return endPointB;
    }

    @Override
    protected Point getConnectedPointB() {
        return endPointA;
    }

    @Override
    protected Point getConnectedPointC() {
        return endPointA;
    }

	
	@Override
	public int getNumPaths() {
		return 3;
	}

	@Override
	protected boolean getTrainCrossing() {
		return trainCrossing;
	}




}
