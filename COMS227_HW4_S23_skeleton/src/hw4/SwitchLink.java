package hw4;


import api.Point;
import api.PositionVector;

/**
 * Models a switchable link with three paths. A boolean turn determines which
 * path trains take. By default turn is set to false. The following figure shows
 * the three paths labeled A, B, and C.
 * 
 * A B --- --- \ \C
 * 
 * 
 * The paths A and B run in the same direction and C branches away.
 * 
 * When turn is true a train from A passes to C. 
 * When turn is false a train from A passes to B. 
 * A train from B always passes to A. 
 * A train from C always passes to A.
 * 
 * @author omran
 *
 */
public class SwitchLink extends AbstractLink {
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
	     * turn boolean for position vector
	     */
	    private boolean turn;
	    /**
	     * train crossing boolean to detect if train has entered crossing
	     */
	    private boolean trainCrossing;
	/**
	 * The given endpoints correspond to the paths as labeled below:
	 *  A     B
	 *  --- ---
	 *      \
	 *       \C
	 * @param endPointA
	 * @param endPointB
	 * @param endPointC
	 */
	public SwitchLink(Point endPointA, Point endPointB, Point endPointC) {
			this.endPointA = endPointA;
	        this.endPointB = endPointB;
	        this.endPointC = endPointC;
	        this.turn = false;
	        this.trainCrossing = false;

	}


	/**
	 * Updates the link to turn or not turn. 
	 * The turn cannot be modified (do nothing) when the train is currently in (entered but not exited) the crossing.
	 * @param turn
	 */
	public void setTurn(boolean turn) {
		 if (trainCrossing == false) {
	            this.turn = turn;
	        }
	    }
	  @Override
	    public void shiftPoints(PositionVector positionVector) {
	        Point pointB = positionVector.getPointB();
	        
	        //if train end equals path point A
	        if (pointB.equals(endPointA)) {
	        	//if train is turning
	        	//go to C
	            if (turn == true) {
	                positionVector.setPointA(endPointA);
	                positionVector.setPointB(endPointC);
	            } else {
	            	//go to B from A
	                positionVector.setPointA(endPointA);
	                positionVector.setPointB(endPointB);
	            }
	          //if train end equals path point B
	          //for to A from B
	        } else if (pointB.equals(endPointB)) {
	            positionVector.setPointA(endPointB);
	            positionVector.setPointB(endPointA);
	          //if train end equals path point C
	          //go to B from A
	        } else if (pointB.equals(endPointC)) {
	            positionVector.setPointA(endPointC);
	            positionVector.setPointB(endPointA);
	        }
	    }
	  @Override
		public Point getConnectedPoint (Point point) {
			//if point equals point A within the path
		    if (point.equals(endPointA)) {
		    	//if turning
		        if (turn == true) {
		            return endPointC;
		        } else {
		            return endPointB;
		        }
		    } else if (point.equals(endPointB)) {
		        return endPointA;
		    } else if (point.equals(endPointC)) {
		        return endPointA;
		    }
		    return null;
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
        if (turn == true) {
            return endPointC;
        } else {
            return endPointB;
        }
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
	public void trainEnteredCrossing() {
		trainCrossing = true;
		
	}

	@Override
	public void trainExitedCrossing() {
		trainCrossing = false;
		
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


