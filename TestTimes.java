

public class TestTimes implements TestTimesInterface {

private long[] testTimes;
private int Index;

public TestTimes() {
	this.testTimes=new long[10];
	Index = 0;
}

@Override
public long getLastTestTime() {
	if(Index != 0) {
		return testTimes[Index -1];
	}else{
		return 0;
	}
}

@Override
public long[] getTestTimes() {
    long[] lastTenValues = new long[10];
    for (int i = 0; i < Index; i++) {
        lastTenValues[i] = testTimes[i];
    }
    return lastTenValues;
}



@Override
public void resetTestTimes() {
   testTimes = new long [10];
   
   Index = 0;
}


@Override
public void addTestTime(long testTime) {
    if (Index < testTimes.length) {
        testTimes[Index++]= testTime;
    } else {
        for (int i =0 ; i< (testTimes.length - 1); i++) {
        	testTimes[i] = testTimes[i + 1];
        }
    }
    testTimes[9] = testTime;
}

@Override
public double getAverageTestTime() {
	if (Index <= 1) {
		return testTimes[0];
	}else {
		double lastTenValues = 0;
		
		for(int i = 0; i < Index; i++) {
			lastTenValues += testTimes[i];
		}
	return (lastTenValues/((double)Index));
	
	
	}
   
}





}










    



