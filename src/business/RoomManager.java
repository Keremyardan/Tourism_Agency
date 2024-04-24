package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class RoomManager {

    // new object for db connection
    RoomDao roomDao = new RoomDao();

    // function that brings the room with specific id
    public Room getById(int id){return this.roomDao.getByID(id);}

    // function that brings all room records
    public ArrayList<Room> findAll(){return this.roomDao.findAll();}

    // the method that provides necessary information for table with generic data type as Object[] so, the array list may take
    // different type of objects
    public ArrayList<Object[]> getForTable(int size,ArrayList<Room> rooms){
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getBed_capacity();
            rowObject[i++] = obj.getSquare_meter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGame_console();
            rowObject[i++] = obj.isCash_box();
            rowObject[i++] = obj.isProjection();
            roomList.add(rowObject);
        }
        return roomList;
    }

    // function that adds room record to database
    public boolean save(Room room){
        if(room.getId()!=0){
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    // function that updates stock amount for room
    public boolean updateStock(Room room){
        if(this.getById(room.getId())== null){
            return false;
        }
        return this.roomDao.updateStock(room);
    }

    // function that deletes the room with an id
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " id could not found");
            return false;
        }
        return this.roomDao.delete(id);
    }

    // function that updates room records
    public boolean update(Room room) {
        if (this.getById(room.getId()) == null) {
            Helper.showMsg(room.getId()+ " id could not found");
            return false;
        }else {
            return this.roomDao.update(room);
            ////////////////// dao'ya bak
        }



    }


    // filter method by search credentials

    // method below, returns an ArrayList of room objects and takes several parameters that specify search criteria for available rooms
    public ArrayList<Room> searchForTable(String hotelName, String cityAdress,String checkinDate,String checkoutDate, String adultNum, String childNum){

        // all columns from public.room is being taken and left joins to public.hotel and public.hotel_season with sql aliases
        String query = "SELECT * from public.room r " +
                "LEFT JOIN public.hotel h ON r.hotel_id = h.id " +
                "LEFT JOIN public.hotel_season s ON r.season_id = s.id WHERE";

        ArrayList<String> whereList = new ArrayList<>();
        // whereList is initialized to store the conditions for 'WHERE' clause of the query and since it is a number, checks if it is empty.
        whereList.add(" r.stock > " + 0);

        // check in and check out dates parsed to local date by using DateTimeFormatter then will be added to whereList.
        checkinDate = LocalDate.parse(checkinDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        checkoutDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        whereList.add(" AND s.start_date <= '" + checkinDate + "'");
        whereList.add(" AND s.finish_date >='" + checkoutDate + "'");
        // in case of not null situation, ILIKE sql operator creates a case insensitive pattern.
        if (hotelName != null){
            whereList.add(" AND h.name ILIKE '%" + hotelName + "%'");
        }
        // in case of not null situation, ILIKE sql operator creates a case insensitive pattern.
        if (cityAdress != null){

            whereList.add(" AND h.address ILIKE '%" + cityAdress + "%'");

        }


        // if adult amount and children amounts are valid and not null and not empty strings
        if ( adultNum != null && !adultNum.isEmpty() && childNum != null && !childNum.isEmpty()){

            // since the numbers are being taken as strings, a parse operation is being applied to
            // convert string numbers into integer.
            // once the process is done, these converted values are being stored into adultnum and childnum
            try {
                int adultNumber = Integer.parseInt(adultNum);
                int childNumber = Integer.parseInt(childNum);
                int totalNumber = adultNumber + childNumber;

                // checks if a bed capacity of a room is greater or equal than total numbers of guests.
                whereList.add(" AND r.bed_capacity >= '" + (totalNumber) + "'");

            // catches if anything goes wrong
            }catch (NumberFormatException e){
                e.printStackTrace();

            }
            // after conditions are added to the whereList, a concatenation process is being performed and query gets an
            // update with whereList
            query+= String.join("",whereList);

        }

        // we are taking the room information with selectbyquery method of roomdao. since return is expected as a list,
        // storing it in an arraylist.
        ArrayList<Room> queryResult = this.roomDao.selectByQuery(query);
        // and returning the result.
        return queryResult;
    }
}
