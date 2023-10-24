package hw4;


import api.Point;
import api.PointPair;
import api.PositionVector;

/**
 * Models a link with a minimum of 2 to a maximum of 6 paths. The following
 * figure shows 6 paths.
 *   \   /
 *    \ /
 *  --- ---
 *    / \
 *   /   \
 * The connections are provided to the constructor as an array of 2 to 6 point
 * pairs. Each point pair indicates the two endpoints where the train comes from
 * and goes to.
 *
 * The number of point pairs should be the same as the number of paths.
 * 
 * @author omran
 *
 */
public class MultiFixedLink extends AbstractLink {
	/**
     * train crossing boolean to detect if train has entered crossing
     */
    private boolean trainCrossing;
    /**
     * array of point pairs
     */
	private PointPair[] connections;
	
	/**
	 * Creates a new MultiFixedLink
	 * 
	 * @param connections
	 */
	public MultiFixedLink(PointPair[] connections) {
		this.connections = connections;
		trainCrossing = false;
	}

	

	@Override
	 public void shiftPoints(PositionVector positionVector) {
		//set points to each point in the pair of connections
        for (PointPair pair : connections) {
            Point pointA = pair.getPointA();
            Point pointB = pair.getPointB();
            //new X and Y cords for point A in relation to the train
            double newX = pointA.getX() + positionVector.getRelativeDistance() * (pointB.getX() - pointA.getX());
            double newY = pointA.getY() + positionVector.getRelativeDistance() * (pointB.getY() - pointA.getY());
            //new poits initialized as a point object
            Point newPointA = new Point(newX, newY);
            //set path and index to match orignal point
            newPointA.setPath(pointA.getPath());
            newPointA.setPointIndex(pointA.getPointIndex());
            pair.setPointA(newPointA);
        }
    }

	@Override
	protected Point getConnectedPointA() {
	    return connections[0].getPointB();
	}

	@Override
	protected Point getConnectedPointB() {
	    return connections[1].getPointB();
	}

	@Override
	protected Point getConnectedPointC() {
	    if (connections.length > 2) {
	        return connections[2].getPointB();
	    } else {
	        return null;
	    }
	}
	protected Point getEndPointA() {
	    return connections[0].getPointA();
	}

	protected Point getEndPointB() {
	    return connections[0].getPointB();
	}

	protected Point getEndPointC() {
	    return null;
	}


	@Override
	public int getNumPaths() {
		return connections.length;
	}


	@Override
	protected boolean getTrainCrossing() {
		return trainCrossing;
	}
}
