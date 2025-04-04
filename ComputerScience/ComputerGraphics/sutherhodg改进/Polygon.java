package ComputerScience.ComputerGraphics.sutherhodg改进;

import java.awt.*;
import java.util.ArrayList;

public class Polygon implements Figure{

    private final ArrayList<Point> clearDotes = new ArrayList<>();
    private final ArrayList<Point> clearWindowDotes = new ArrayList<>();
    private final ArrayList<Point> dotes = new ArrayList<>();
    private final ArrayList<Point> windowDotes = new ArrayList<>();
    private final int[] dotes_x = new int[100];
    private final int[] dotes_y = new int[100];
    Point intersectDote = null;
    Boolean end = false;

    public Polygon() {
        clearWindowDotes.add(new Point(350, 500, false));
        clearWindowDotes.add(new Point(350, 100, false));
        clearWindowDotes.add(new Point(650, 100, false));
        clearWindowDotes.add(new Point(650, 500, false));
    }

    public void update(Point dote) {
        clearDotes.add(dote);
        dotes_x[clearDotes.size() - 1] = dote.x;
        dotes_y[clearDotes.size() - 1] = dote.y;
        dotes.clear();
        windowDotes.clear();
        intersectDote = null;
    }

    public void clear(){
        clearDotes.clear();
        dotes.clear();
        windowDotes.clear();
        end = false;
        intersectDote = null;
    }

    public void windowList(ArrayList<Point> polygonDotes, ArrayList<Point> areaDotes, ArrayList<Point> windowDotes,
                           Point currentDote, Point startDote) {
        for (int i = windowDotes.indexOf(currentDote) + 1; !end; i++) {
            currentDote.isUsed = true;
            if (i == windowDotes.size())
                i = 0;
            if (i == windowDotes.size() - 1)
                currentDote = windowDotes.get(0);
            else
                currentDote = windowDotes.get(i + 1);

            windowDotes.get(i).isUsed = true;

            if((windowDotes.get(i).x == startDote.x) && (windowDotes.get(i).y == startDote.y)) {
                end = true;
                return;
            }

            areaDotes.add(windowDotes.get(i));
            if((currentDote.x == startDote.x) && (currentDote.y == startDote.y)) {
                end = true;
                return;
            }

            if(windowDotes.get(i).isIntersectDote)
                polygonList(polygonDotes,areaDotes, windowDotes, windowDotes.get(i), startDote);
        }

    }

    public void polygonList(ArrayList<Point> polygonDotes, ArrayList<Point> areaDotes, ArrayList<Point> windowDotes,
                            Point currentDote, Point startDote) {
        for (int i = polygonDotes.indexOf(currentDote) + 1; !end ; i++) {
            currentDote.isUsed = true;
            if (i == polygonDotes.size())
                i = 0;
            if (i == polygonDotes.size() - 1)
                currentDote = polygonDotes.get(0);
            else
                currentDote = polygonDotes.get(i + 1);

            polygonDotes.get(i).isUsed = true;

            if((polygonDotes.get(i).x == startDote.x) && (polygonDotes.get(i).y == startDote.y)) {
                end = true;
                return;
            }

            areaDotes.add(polygonDotes.get(i));
            if ((currentDote.x == startDote.x) && (currentDote.y == startDote.y)) {
                end = true;
                return;
            }

            if(polygonDotes.get(i).isIntersectDote)
                windowList(polygonDotes, areaDotes, windowDotes, polygonDotes.get(i), startDote);
        }

    }

    public double countDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public void DoteSort(ArrayList<Point> windowDotes, int[] countJ, int vertexNumber) {
        for (int i = 0; i < vertexNumber; i++){
            if (countJ[i] > 1) {
                int index = i;

                for (int k = 0; k < vertexNumber - 1; k++){
                    if (i > k) index += countJ[k];
                }

                Point p1 = new Point(windowDotes.get(index));
                for(int k = 0; k < countJ[i] - 1; k++) {
                    for (int j = index + countJ[i]; j > index + 1; j--) {
                        if (countDistance(p1, windowDotes.get(j - 1)) > countDistance(p1, windowDotes.get(j))) {
                            windowDotes.add(j-1, windowDotes.get(j));
                            windowDotes.remove(j+1);
                        }
                    }
                }
            }
        }
    }

    public int countIndex(int[] countJ, int j) {
        int res = 0;
        if(j >= 0) res += countJ[0];
        if(j >= 1) res += countJ[1];
        if(j >= 2) res += countJ[2];
        if(j >= 3) res += countJ[3];

        return res + j;
    }

    @Override
    public void paint(Graphics2D g, Color color) {
        g.setColor(Color.black);
        g.drawPolygon(dotes_x, dotes_y, clearDotes.size());

        for (Point dote : clearDotes)
            dotes.add(new Point(dote));
        for (Point dote : clearWindowDotes)
            windowDotes.add(new Point(dote));

        if (dotes.size() >= 3) {

            int[] countI = new int[30];
            int[] countJ = new int[]{0, 0, 0, 0};
            for (int i = 0, k = 0; i < dotes.size(); i++, k++) {
                Point p2;
                if (i == dotes.size() - 1) p2 = new Point(dotes.get(0));
                    else p2 = new Point(dotes.get(i + 1));

                for (int j = 0; j < clearWindowDotes.size(); j++) {
                    Point p4;
                    if (j == clearWindowDotes.size() - 1) p4 = new Point(clearWindowDotes.get(0));
                        else p4 = new Point(clearWindowDotes.get(j + 1));

                    if(checkIntersection(dotes.get(i), p2, clearWindowDotes.get(j), p4)) {
                        dotes.add(i + countI[k] + 1, intersectDote);
                        int index = countIndex(countJ, j);
                        windowDotes.add(index + 1, intersectDote);
                        countI[k] ++;
                        countJ[j] ++;
                    }
                }
                i += countI[k];
            }

            DoteSort(windowDotes, countJ, 4);
            DoteSort(dotes, countI, clearDotes.size());

            for (Point dote : dotes) {
                if(dote.isIntersectDote && !dote.isUsed) {
                    end = false;
                    ArrayList<Point> area = new ArrayList<>();
                    area.add(dote);
                    polygonList(dotes, area, windowDotes, dote, dote);
                    int[] poly_x = new int[100];
                    int[] poly_y = new int[100];
                    int i = 0;
                    for (Point dote1 : area) {
                        poly_x[i] = dote1.x;
                        poly_y[i] = dote1.y;
                        i++;
                    }
                    g.setColor(Color.GRAY);
                    g.fillPolygon(poly_x, poly_y, area.size());
                }
            }
        }
    }

    private boolean checkIntersection(Point p1, Point p2, Point p3, Point p4) {
        intersectDote = null;
        if (p2.x < p1.x) { //sort
            Point tmp = p1;
            p1 = p2;
            p2 = tmp;
        }

        if (p4.x < p3.x) {
            Point tmp = p3;
            p3 = p4;
            p4 = tmp;
        }

        if (p2.x < p3.x) {
            return false;
        }
        if((p1.x - p2.x == 0) && (p3.x - p4.x == 0)) {
            if(p1.x == p3.x) {
                if (!((Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y)) ||
                        (Math.min(p1.y, p2.y) > Math.max(p3.y, p4.y)))) {
                    return false;
                }
            }
            return false;
        }
        if (p1.x - p2.x == 0) {
            int Xa = p1.x;
            float A2 = ((float) (p3.y - p4.y)) / (p3.x - p4.x);
            float b2 = p3.y - A2 * p3.x;
            int Ya = (int) (A2 * Xa + b2);

            if (p3.x <= Xa && p4.x >= Xa && Math.min(p1.y, p2.y) <= Ya &&
                    Math.max(p1.y, p2.y) >= Ya) {
                intersectDote = new Point(Xa, Ya, true);
                return true;
            }
            return false;
        }
        if (p3.x - p4.x == 0) {
            int Xa = p3.x;
            float A1 = ((float) (p1.y - p2.y)) / (p1.x - p2.x);
            float b1 = p1.y - A1 * p1.x;
            int Ya = (int) (A1 * Xa + b1);

            if (p1.x <= Xa && p2.x >= Xa && Math.min(p3.y, p4.y) <= Ya &&
                    Math.max(p3.y, p4.y) >= Ya) {
                intersectDote = new Point(Xa, Ya, true);
                return true;
            }

            return false;
        }
        float A1 = ((float) (p1.y - p2.y)) / (p1.x - p2.x);
        float A2 = ((float) (p3.y - p4.y)) / (p3.x - p4.x);
        float b1 = p1.y - A1 * p1.x;
        float b2 = p3.y - A2 * p3.x;
        if (A1 == A2) {
            return false;
        }
        int Xa = (int) ((b2 - b1) / (A1 - A2));
        int Ya = (int) (A1 * Xa + b1);

        if ((Xa < Math.max(p1.x, p3.x)) || (Xa > Math.min( p2.x, p4.x))) {
            return false;
        }
        else {
            intersectDote = new Point(Xa, Ya,true);
            return true;
        }
    }
}
