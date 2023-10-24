package hw4;

import api.Crossable;



import api.Point;
import api.PositionVector;
/**
 * Models a link between paths
 * @author omran
 *
 */
public abstract class AbstractLink implements Crossable {
	/**
	 * Point object corresponding to endPointA
	 * @return
	 */
	protected abstract Point getEndPointA();
	/**
	 * Point object corresponding to endPointB
	 * @return
	 */
    protected abstract Point getEndPointB();
    /**
	 * Point object corresponding to endPointC
	 * @return
	 */
    protected abstract Point getEndPointC();
    /**
     * Point object corresponding to connection to endPointA
     * @return
     */
    protected abstract Point getConnectedPointA();
    /**
     * Point object corresponding to connection to endPointB
     * @return
     */
    protected abstract Point getConnectedPointB();
    /**
     * Point object corresponding to connection to endPointC
     * @return
     */
    protected abstract Point getConnectedPointC();
    /**
     * Boolean to track train crossing
     * @return
     */
    protected abstract boolean getTrainCrossing();
    /**
     * Constructs AbstractLink parent class
     */
    public AbstractLink() {
        
    }

    public Point getConnectedPoint(Point point) {
        if (point.equals(getEndPointA())) {
            return getConnectedPointA();
        } else if (point.equals(getEndPointB())) {
            return getConnectedPointB();
        } else if (point.equals(getEndPointC())) {
            return getConnectedPointC();
        } else {
            return null;
        }
    }
  
	public void trainEnteredCrossing() {
		getTrainCrossing();
		
	}

	public void trainExitedCrossing() {
		getTrainCrossing();
		
	}

	
	public int getNumPaths() {
		return 1;
	}

	
	
	//not overidden by TurnLink, and StraightLink
	 public void shiftPoints(PositionVector positionVector) {
        Point pointB = positionVector.getPointB();
        //if train end equals path point A
        //go from A to C
        if (pointB.equals(getEndPointA())) {
            positionVector.setPointA(getEndPointA());
            positionVector.setPointB(getEndPointC());
          //if train end equals path point B
          //go from B to A
        } else if (pointB.equals(getEndPointB())) {
            positionVector.setPointA(getEndPointB());
            positionVector.setPointB(getEndPointA());
          //if train end equals path point C
          //go from C to A
        } else if (pointB.equals(getEndPointC())) {
            positionVector.setPointA(getEndPointC());
            positionVector.setPointB(getEndPointA());
        }
    }
}


	