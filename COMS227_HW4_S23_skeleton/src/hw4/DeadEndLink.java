package hw4;


import api.Point;



/**
 * Models a link that connects a single path to nothing, getConnectedPoint()
 * returns null and shiftPoints() does nothing
 * 
 * @author omran
 *
 */
public class DeadEndLink extends AbstractLink{
	 /**
     * train crossing boolean to detect if train has entered crossing
     */
    private boolean trainCrossing;
    
	/**
	 * Constructs DeadEndLink
	 */
	public DeadEndLink() {
		trainCrossing = false;
	}
	
	@Override
	protected Point getEndPointA() {
		return null;
	}

	@Override
	protected Point getEndPointB() {
		return null;
	}

	@Override
	protected Point getEndPointC() {
		return null;
	}

	@Override
	protected Point getConnectedPointA() {
		return null;
	}

	@Override
	protected Point getConnectedPointB() {
		return null;
	}

	@Override
	protected Point getConnectedPointC() {
		return null;
	}

	@Override
	protected boolean getTrainCrossing() {
		return trainCrossing;
	}
}
