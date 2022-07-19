package Sorting;

import java.util.Arrays;

public class IntervalMerge {

    public static void main(String[] args) {
        Interval[] intervals = {new Interval(7, 9), new Interval(6, 10), new Interval(4, 5), new Interval(1, 3),new Interval(2,4)};
        Arrays.sort(intervals);
        for (int i = 0; i < intervals.length; i++) {
            System.out.printf("[%d,%d]", intervals[i].getStart(), intervals[i].getEnd());
        }
        System.out.println();

        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[res].getEnd() >= intervals[i].getStart()) {
                intervals[res].setStart(Math.min(intervals[res].getStart(), intervals[i].getStart()));
                intervals[res].setEnd(Math.max(intervals[res].getEnd(), intervals[i].getEnd()));
            } else {
                res++;
                intervals[res] = intervals[i];
//                intervals[res].setStart(intervals[i].getStart());
//                intervals[res].setEnd(intervals[i].getEnd());
            }
        }
        for (int i = 0; i <= res; i++) {
            System.out.printf("[%d,%d]", intervals[i].getStart(), intervals[i].getEnd());
        }
    }

}

class Interval implements Comparable<Interval> {
    private int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public int compareTo(Interval b) {
        if (b.start > this.start) return -1;
        else if (b.start == this.start) return 0;
        else return 1;

    }
}
