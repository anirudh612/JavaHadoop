public class Elevator
{
	//Total number of floors in building
	public final int NUM_FLOORS = 7;

	//Member variables
	private int _currentFloor;
	private int _direction = 0;			//-1 is down, 1 is up
	private int _currentDestination;

	private int _numPassengers = 0;

	//Destinations[i-1] is number of people getting off at i-th floor
	private int [] _destinations = new int[NUM_FLOORS+1];

	//stopFloors[i-1] = 1 means someone is getting off on i-th floor
	//stopFloors[i-1]=0 means no one is getting off on the i-th floor
	private boolean [] _stopFloors = new boolean[NUM_FLOORS+1];

	public Elevator()
	{
		//Set the current floor to 1
		_currentFloor = 1;

		//Initialize destinations and stopFloor arrays to 0
		for(int i=1; i<=NUM_FLOORS; i++)
		{
			_destinations[i]=0;
			_stopFloors[i]=false;
		}
	}

	public void move()
	{

		//Increment current floor by +1 if going up, -1 if going down
		_currentFloor = _currentFloor + _direction;

		if(_stopFloors[_currentFloor]==true)
		{
			_numPassengers = _numPassengers - _destinations[_currentFloor];
			_destinations[_currentFloor] = 0;
			_stopFloors[_currentFloor] = false;
		}

		//If reached first or last floor, reverse direction
		if(_currentFloor==NUM_FLOORS || _currentFloor==1)
		{
			_direction = -_direction;
		}
	}

	public void boardPassenger(int floor)
	{
		//Throw error if floor is invalid
		if(floor<1 || floor>NUM_FLOORS)
		{
			String errorString = "Floor can only be between 1 and " + Integer.toString(NUM_FLOORS);
			throw new IllegalArgumentException(errorString);
		}

		_destinations[floor] = _destinations[floor] + 1;		//add 1 passenger destined for input floor
		_stopFloors[floor] = true;																//Flag to signify elevator will stop at this floor
		_numPassengers = _numPassengers + 1;										//add 1 passenger to the total counter
	}

	public String toString()
	{
		String outString = "Floor " + Integer.toString(_currentFloor) + ": ";
		outString = outString + Integer.toString(_numPassengers);
		if(_numPassengers == 1)
		{
			outString = outString + " passenger";
		}
		else
		{
			outString = outString + " passengers";
		}

		return outString;
	}

/*
	public void moveToTop()
	{
		if(_currentFloor == numFloors)
		{
			return;
		}

		//set Direction to up (+1)
		setDirection(1);

		while(_currentFloor<numFloors)
		{
			move();
		}
	}

	public void moveToBottom()
	{
		if(_currentFloor == numFloors)
		{
			return;
		}

		//set Direction to down (-1)
		setDirection(-1);

		while(_currentFloor<numFloors)
		{
			move();
		}
	}
*/

	//Setters
	public void setCurrentFloor(int floor)
	{
		if(floor<1 || floor>NUM_FLOORS)
		{
			String errorString = "Floor can only be between 1 and " + Integer.toString(NUM_FLOORS);
			throw new IllegalArgumentException(errorString);
		}
		_currentFloor = floor;
	}

	//Getters
	public int getCurrentFloor()
	{
		return _currentFloor;
	}


	//Helper method to set direction
	private void setDirection(int direction)
	{
		/*
		System.out.println("The direction is " + Integer.toString(direction));
		System.out.println(Boolean.toString(direction!=1));
		System.out.println(Boolean.toString(direction!=-1));

		Boolean invalidDirection = (direction!=1) || (direction != -1);
		System.out.println(Boolean.toString(invalidDirection));
		*/
		if( (direction == 1) || (direction == -1) )
		{
			_direction = direction;
		}
		else
		{
			throw new IllegalArgumentException("Direction can only be -1 or +1");
		}

	}

	public static void main(String argv[])
	{
		Elevator elev = new Elevator();

		//Set Direction integers
		int up = 1;
		int down = -1;


		int numFloors = elev.NUM_FLOORS;

		int counter = 1;

		//board 2 passengers to floor 3
		elev.boardPassenger(3);
		elev.boardPassenger(3);

		//board 1 passenger to floor 5
		elev.boardPassenger(5);


		//Print state of the elevator before moving
		System.out.println(elev.toString());


		//Move elevator to top

		elev.setDirection(up);
		while(elev.getCurrentFloor() < numFloors)
		{
			elev.move();
			System.out.println(elev.toString());
			counter++;
			if(counter>10)
			{
				System.out.println("Infinite while loop moving up. Breaking out of loop");
				break;
			}
		}

		elev.setDirection(down);
		//Move elevator to bottom
		counter = 0;
		while(elev.getCurrentFloor() > 1)
		{
			elev.move();
			System.out.println(elev.toString());
			counter++;
			if(counter>10)
			{
				System.out.println("Infinite while loop moving down. Breaking out of loop");
				break;
			}
		}
	}
}
