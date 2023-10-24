package hw4;


import api.Path;

import api.Point;
import api.PositionVector;

/**
 * Models a link between exactly two paths. When the train reaches the endpoint of one of the paths, it passes to the endpoint of the other path next.
 * @author omran
 *
 */
public class CouplingLink extends AbstractLink {
	/**
	 * track first end point
	 */
    private Point endPointA;
    /**
     * tracks second end point
     */
    private Point endPointB;
    /**
     * train crossing boolean to detect if train has entered crossing
     */
    private boolean trainCrossing;

    /**
     * Creates a new CouplingLink that connects the two given endpoints
     * @param endpoint1
     * @param endpoint2
     */
    public CouplingLink(Point endpoint1, Point endpoint2) {
        endPointA = endpoint1;
        endPointB = endpoint2;
        trainCrossing = false;
    }

    @Override
    public void shiftPoints(PositionVector positionVector) {
        @SuppressWarnings("unused")
		Point pointA = positionVector.getPointA();
        Point pointB = positionVector.getPointB();
        //check if the train is crossing the link to the second path from endpoint1
        if (pointB.equals(endPointA)) {
        	//path regarding endpoint2
            Path path = endPointB.getPath();
            //index of points for the path
            int index = endPointB.getPointIndex();
            //only if the next point is within bounds
            if (index + 1 < path.getNumPoints()) {
            	//move the train over
                positionVector.setPointA(endPointB);
                positionVector.setPointB(path.getPointByIndex(index + 1));
            }
           
        }
        //check if the train is crossing the link to the first path from endpoint2
        else if (pointB.equals(endPointB)) {
            Path path = endPointA.getPath();
            int index = endPointA.getPointIndex();
            //if previous point is within bounds
            if (index - 1 >= 0) {
                positionVector.setPointA(endPointA);
                positionVector.setPointB(path.getPointByIndex(index - 1));
            }
        }
    }
    

    protected Point getEndPointA() {
        return endPointA;
    }

    @Override
    protected Point getEndPointB() {
        return endPointB;
    }

    @Override
    protected Point getEndPointC() {
        return null;
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
        return null;
    }
   
    @Override
    public int getNumPaths() {
        return 2; //there are always two paths connected by a CouplingLink
    }

	@Override
	protected boolean getTrainCrossing() {
		return trainCrossing;
	}

}
