package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;


public class SeasonManager {


    SeasonDao seasonDao = new SeasonDao();

    // brings season with specific id
    public Season getById(int id){
        return this.seasonDao.getSeasonByID(id);
    }

    // brings the season with specific hotel id
    public ArrayList<Season> getSeasonsByOtelId(int id){return this.seasonDao.getSeasonsByOtelId(id);}

    // brings all seasons function
    public ArrayList<Season> findAll()
    {return this.seasonDao.findAll();}

    // the method that provides necessary information for table with generic data type as Object[] so, the array list may take
    // different type of objects
    public ArrayList<Object[]> getForTable(int size,ArrayList<Season> seasons){
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj : seasons){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getStart_date();
            rowObject[i++] = obj.getFinish_date();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    // adds the season record to db
    public boolean save(Season season){
        if(season.getId()!=0){
            Helper.showMsg("error");
        }
        return this.seasonDao.save(season);
    }

    // deletes the season witj specific id
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.seasonDao.delete(id);
    }
}
