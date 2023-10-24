package hw4;

import java.util.ArrayList;



import api.Point;
import api.PointPair;
import api.PositionVector;

/**
 * Models a link with a minimum of 2 to a maximum of 6 paths. The following figure shows 6 paths. 
 *  \   /
 *   \ /
 * --- ---
 *   / \
 *  /   \
 *  The connections are provided to the constructor as an array of 2 to 6 point pairs. 
 *  Each point pair describes the two endpoints where the train comes from and goes to.
 *  The turn cannot be modified when the train is in the crossing.
 *  The number of point pairs should be the same as the number of paths.
 *  @author omran
 *
 */
public class MultiSwitchLink extends AbstractLink{
	/**
     * train crossing boolean to detect if train has entered crossing
     */
    private boolean trainCrossing;
    /**
     * array list of point pairs
     */
	private ArrayList<PointPair> connections;
	/**
	 * Creates a new MultiSwitchLink. The given array of point pairs describes the connection. 
	 * Each PointPair indicates where the train comes from and goes to.
	 * @param connections
	 * @return
	 */
	 public MultiSwitchLink(PointPair[] connections) {
	        this.connections = new ArrayList<>();
	        for (PointPair connection : connections) {
	            this.connections.add(connection);
	        }
	        trainCrossing = false;
	    }
	/**
	 * Updates the connection point pair at the given index. 
	 * The connection cannot be modified (method does nothing) when the train is currently in (entered but not exited) the crossing.
	 * @param pointPair
	 * @param index
	 */
	    public void switchConnection(PointPair pointPair, int index) {
	        if (!trainCrossing && index >= 0 && index < connections.size()) {
	            connections.set(index, pointPair);
	        }
	    }

	@Override
	  public void shiftPoints(PositionVector positionVector) {
		//change in x and y cords in relation to the train
        double dx = positionVector.getPointB().getX() - positionVector.getPointA().getX();
        double dy = positionVector.getPointB().getY() - positionVector.getPointA().getY();
        //update position of the points in the connections
        for (PointPair connection : connections) {
        	//add change in cord to original position
            Point shiftedPointA = new Point(connection.getPointA().getX() + dx, connection.getPointA().getY() + dy);
            Point shiftedPointB = new Point(connection.getPointB().getX() + dx, connection.getPointB().getY() + dy);
            //set new points
            connection.setPointA(shiftedPointA);
            connection.setPointB(shiftedPointB);
        }
    }

	
    @Override
    protected Point getEndPointA() {
        return connections.get(0).getPointA();
    }

    @Override
    protected Point getEndPointB() {
        return connections.get(0).getPointB();
    }

    @Override
    protected Point getEndPointC() {
        return connections.get(connections.size() - 1).getPointB();
    }

    @Override
    protected Point getConnectedPointA() {
        if (connections.size() > 1) {
            return connections.get(1).getPointB();
        }
        return null;
    }

    @Override
    protected Point getConnectedPointB() {
        if (connections.size() > 3) {
            return connections.get(3).getPointB();
        }
        return null;
    }

    @Override
    protected Point getConnectedPointC() {
        if (connections.size() > 5) {
            return connections.get(5).getPointB();
        }
        return null;
    }
	@Override
	public void trainEnteredCrossing() {
		trainCrossing = true;
		
	}
	@Override
	public void trainExitedCrossing() {
		trainCrossing = false;
		
	}
	@Override
	public int getNumPaths() {
	 return connections.size();
	}
	@Override
	protected boolean getTrainCrossing() {
		return trainCrossing;
		
	}
}
