public class Segment {
    Point p1=new Point();
    Point p2=new Point();
    public double length_o()
    {
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    }
    public Segment maximal(Segment []arr)
    {
        Segment maxi=new Segment();
        maxi.p1.x=p1.x;
        maxi.p1.y=p1.y;
        maxi.p2.x=p1.x;
        maxi.p2.y=p1.y;
        for(int i=0;i<arr.length;i++)
        {
            if(maxi.length_o()<arr[i].length_o())
            {
                maxi.p1.x=arr[i].p1.x;
                maxi.p1.y=arr[i].p1.y;
                maxi.p2.x=arr[i].p2.x;
                maxi.p2.y=arr[i].p2.y;
            }
        }
        return maxi;
    }

}
