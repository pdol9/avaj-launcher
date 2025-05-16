package aviation;

    /***************************************/
    /*              SharedState            */
    /***************************************/

public class SharedState {
    private boolean airborneStatus = false;

    public boolean isAirborne() {
        return this.airborneStatus;
    }

    public void setAirborneStatus(boolean flag) {
        this.airborneStatus = flag;
    }
}
