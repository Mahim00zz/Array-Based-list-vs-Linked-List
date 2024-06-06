public class ListDriver implements ListDriverInterface {

    public static void main(String[] args) {
        ListDriver d = new ListDriver();

        System.out.println("Running test = AddSortedOdd");

        displayHeader();

        Object[] r = d.runTestCase(ListType.ArrayBasedList, TestType.AddSortedOdd, 10);

        displayRuntime(ListType.ArrayBasedList, r, d.memoryUsage());

        r = d.runTestCase(ListType.LinkedList, TestType.AddSortedOdd, 10);

        displayRuntime(ListType.LinkedList, r, d.memoryUsage());
        System.out.println();

        // ================================================================================================
        System.out.println("\nRunning test = AddSortedEven");

        displayHeader();

        r = d.runTestCase(ListType.ArrayBasedList, TestType.AddSortedEven, 10);

        displayRuntime(ListType.ArrayBasedList, r, d.memoryUsage());

        r = d.runTestCase(ListType.LinkedList, TestType.AddSortedEven, 10);

        displayRuntime(ListType.LinkedList, r, d.memoryUsage());
        System.out.println();

        // ================================================================================================
        System.out.println("\nRunning test = AddAll");

        displayHeader();

        r = d.runTestCase(ListType.ArrayBasedList, TestType.AddAll, 10);

        displayRuntime(ListType.ArrayBasedList, r, d.memoryUsage());

        r = d.runTestCase(ListType.LinkedList, TestType.AddAll, 10);

        displayRuntime(ListType.LinkedList, r, d.memoryUsage());
        System.out.println();

        // ================================================================================================
        System.out.println("\nRunning test = AddAllAtIndexZero");

        displayHeader();

        r = d.runTestCase(ListType.ArrayBasedList, TestType.AddAllAtIndexZero, 10);

        displayRuntime(ListType.ArrayBasedList, r, d.memoryUsage());

        r = d.runTestCase(ListType.LinkedList, TestType.AddAllAtIndexZero, 10);

        displayRuntime(ListType.LinkedList, r, d.memoryUsage());
        System.out.println();

        // ================================================================================================
        System.out.println("\nRunning test = RemoveAllOdd");

        displayHeader();

        r = d.runTestCase(ListType.ArrayBasedList, TestType.RemoveAllOdd, 10);

        displayRuntime(ListType.ArrayBasedList, r, d.memoryUsage());

        r = d.runTestCase(ListType.LinkedList, TestType.RemoveAllOdd, 10);

        displayRuntime(ListType.LinkedList, r, d.memoryUsage());
        System.out.println();

        // ================================================================================================
        System.out.println("\nRunning test = RemoveAllEven");

        displayHeader();

        r = d.runTestCase(ListType.ArrayBasedList, TestType.RemoveAllEven, 10);

        displayRuntime(ListType.ArrayBasedList, r, d.memoryUsage());

        r = d.runTestCase(ListType.LinkedList, TestType.RemoveAllEven, 10);

        displayRuntime(ListType.LinkedList, r, d.memoryUsage());
    }

    private static void displayHeader() {
        System.out.format("%14s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %14s %12s", "", "Run 1 ", "Run 2 ", "Run 3 ",
                "Run 4 ", "Run 5 ", "Run 6 ", "Run 7 ", "Run 8 ", "Run 9", "Run 10", "Average", "Memory Usage");
        System.out.format("\n%14s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %14s %12s", "", "Seconds", "Seconds",
                "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds",
                "Mega Bytes");
        System.out.format("\n%14s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %12s %12s", "", "----------", "----------",
                "----------", "----------", "----------", "----------", "----------", "----------", "----------", "----------", "--------------",
                "-------------");
    }

    private static void displayRuntime(ListType listType, Object[] r, double memoryUsage) {
        System.out.format("\n%-14s ", listType.toString());

        TestTimes testTimes = (TestTimes) r[r.length - 1];
        long[] runTimes = testTimes.getTestTimes();
        for (int i = 0; i < runTimes.length; i++)
            System.out.format("%10d ", runTimes[i]);

        System.out.format("%14.2f ", testTimes.getAverageTestTime());

        System.out.format("%12.2f ", memoryUsage);
    }

    @Override
    public ListInterface<Integer> createList(ListType listType, TestType testType) {
        ListInterface<Integer> list = null;
        switch (testType) {
            case AddSortedOdd:
                if (listType.equals(ListType.ArrayBasedList)) {
                    list = new ArrayBasedList<>();
                } else if (listType.equals(ListType.LinkedList)) {
                    list = new LinkedList<>();
                }
                break;
            case AddSortedEven:
            case AddAll:
            case AddAllAtIndexZero:
            case RemoveAllEven:
            case RemoveAllOdd:
                if (listType.equals(ListType.ArrayBasedList)) {
                    list = new ArrayBasedList<>();
                } else if (listType.equals(ListType.LinkedList)) {
                    list = new LinkedList<>();
                }
                break;
            default:
                System.out.println("Unsupported test type.");
                break; 
        }

        return list;
    }




    @Override
    public double memoryUsage() {
        Runtime systemRunTime = Runtime.getRuntime();
        long totalMemory = systemRunTime.totalMemory();
        long freeMemory = systemRunTime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        double usedMemoryInMegabytes = usedMemory / (1024.0 * 1024.0);

        return usedMemoryInMegabytes;
    }


    @Override
    public Object[] runTestCase(ListType listType, TestType testType, int numberOfTimes) {
        Object[] objArr = new Object[numberOfTimes * 2 + 1];
        TestTimes t = new TestTimes();

        for (int i = 0; i < numberOfTimes; i++) {
            ListInterface<Integer> list = createList(listType, testType);
            objArr[i * 2] = list.copy();

            long startTime = System.nanoTime();
            // Test execution
            switch (testType) {
                case AddSortedOdd:
                    for (int j = 1; j <= 9999; j += 2) {
                        list.addSorted(j);
                    }
                    break;
                case AddSortedEven:
                    for (int j = 2; j <= 10000; j += 2) {
                        list.addSorted(j);
                    }
                    break;
                case AddAll:
                    for (int j = 1; j <= 10000; j++) {
                        list.add(j);
                    }
                    break;
                case AddAllAtIndexZero:
                    for (int j = 10000; j >= 1; j--) {
                        list.add(j, 0);
                    }
                    break;
                case RemoveAllEven:
                    for (int j = list.size() - 1; j >= 0; j--) {
                        if (list.get(j) % 2 == 0) {
                            list.remove(j);
                        }
                    }
                    break;
                case RemoveAllOdd:
                    for (int j = list.size() - 1; j >= 0; j--) {
                        if (list.get(j) % 2 != 0) {
                            list.remove(j);
                        }
                    }
                    break;
                default:
                    System.out.println("Unsupported test type.");
                    break;
            }
            long endTime = System.nanoTime();
            long testTime = endTime - startTime;
            t.addTestTime(testTime);
            objArr[i * 2 + 1] = list.copy();
        }

        objArr[numberOfTimes * 2] = t;
        return objArr;
    }



    @Override
    public ListInterface<Integer> initializeList(ListInterface<Integer> list, int firstNumber, int lastNumber, int increment) {
        if (list == null) {
            list = new ArrayBasedList<>();
        }
        for (int i = firstNumber; i <= lastNumber; i += increment) {
            list.add(i);
        }
        return list;
    }
}

